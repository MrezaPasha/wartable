package org.sadr.web.main.system.module;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtInitResult;
import org.sadr._core.meta.annotation.Front;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core._type.TtRequestMethod;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core.meta.annotation.*;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskDao;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author masoud
 */
@Service
//@Component
public class ModuleServiceImp extends GenericServiceImpl<Module, ModuleDao> implements ModuleService {

    private TaskDao taskDao;
    @Autowired
    private ListableBeanFactory beanFactory;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Module findByClassName(String className) {
        return dao.findByClassName(className);
    }

    @Override
    public Module findByPackageName(String packageName) {
        return dao.findByPackageName(packageName);
    }

    @Override
    public boolean clean() {
        try {
            List<Task> delTasks = this.taskDao.findAllBy(Restrictions.eq(Task.IS_REFRESHED, false), Task._USERS, Task._USER_GROUPS);
            if (delTasks.size() == 0) {
                return true;
            }
            for (Task delTask : delTasks) {
                OutLog.pl("Deleted T:  " + delTask.getSignature());
                delTask.setUserGroups(null);
                delTask.setUsers(null);
                taskDao.update(delTask);
            }
            OutLog.pl("Sleep...");
            Thread.sleep(3000);
            OutLog.pl("Continue...");
//            for (Task delTask : delTasks) {
//                taskDao.delete(delTask);
//            }
            taskDao.deleteAllBy(0163, delTasks);

            OutLog.pl("Sleep...");
            Thread.sleep(3000);
            OutLog.pl("Continue...");

            List<Module> delModules = this.findAllBy(Restrictions.eq(Module.IS_REFRESHED, false));
//            for (Module delModule : delModules) {
//                OutLog.pl("Deleted M:  " + delModule.getClassName());
//                delete(delModule);
//            }
            deleteAllBy(0163, delModules);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TtInitResult init() {
        boolean isHasModel;
        List<Class<?>> modelClasses = WebConfigHandler.getModelClasses();

        refreshModelAndTask();

        try {
            List<String> msgList = new ArrayList<>();
            Class controllerBean;
            String controllerName, modelName;
            Module module;
            Task task;
            for (String bean : beanFactory.getBeanDefinitionNames()) {
                if (bean.endsWith("Controller")) {
                    try {
                        controllerBean = beanFactory.getBean(bean).getClass();
                        int ix = controllerBean.getCanonicalName().indexOf("$");

                        // به احتمال زیاد به خاطر سیم کشی اتوماتیک کنترلرها در پیکربندیها
                        // هنگام فراخوانی کنترلر از bean به جای کلاس انها شی آنها برگردانده می شود
                        // اگر شی باشد با علامت $ قابل تشخیص است
                        // چون ما به کلاس نیاز داریم اگر شی برگردانده شود آن را به کلاس تبدیل می کنیم.
                        if (ix > 0) {
                            controllerBean = controllerBean.getSuperclass();
                        }
                        // ماژولهایی که نمی خواهیم در فرآیند دسترسی ها نشان داده شوند
//                        if (controllerBean.isAnnotationPresent(ProtectedModule.class)) {
//                            continue;
//                        }

                        // برای یافتن کنترلرهایی که مدل ندارند
                        if (!controllerBean.isAnnotationPresent(StandaloneController.class)) {
                            isHasModel = false;
                            for (Class<?> mc : modelClasses) {
                                if (controllerBean.getName().contains(mc.getName())) {
                                    isHasModel = true;
                                    break;
                                }
                            }
                            if (!isHasModel) {
                                continue;
                            }
                        }

                        controllerName = controllerBean.getSimpleName();

                        modelName = controllerName.substring(0, 1).toLowerCase()
                                + controllerName.substring(1, controllerName.indexOf("Controller"));

                        OutLog.pl(controllerName + " > " + modelName);


                        module = this.findBy(Restrictions.eq(Module.CLASS_NAME, controllerBean.getName()));
                        if (module == null) {
                            module = new Module();
                            module.setMessageCode(modelName + ".c");
                            module.setMenuMessageCode("module.menu." + modelName);
                            module.setClassName(controllerBean.getName());
                            module.setPackageName(controllerBean.getPackage().getName());
//                            module.setIsProtected(controllerBean.isAnnotationPresent(ProtectedModule.class));
                            module.setIsRefreshed(true);
                            save(module);

                        } else {
                            module.setMessageCode(modelName + ".c");
                            module.setMenuMessageCode("module.menu." + modelName);
                            module.setPackageName(controllerBean.getPackage().getName());
//                            module.setIsProtected(controllerBean.isAnnotationPresent(ProtectedModule.class));
                            module.setIsRefreshed(true);
                            update(module);
                        }

                        Method[] methods = controllerBean.getMethods();
                        List<Method> postTasks = new ArrayList<>();

                        boolean isExist;
                        for (Method method : methods) {
                            if (!method.isAnnotationPresent(RequestMapping.class)) {
                                continue;
                            }
//                            if (method.isAnnotationPresent(SuperAdminTask.class)) {
//                                continue;
//                            }
                            try {
                                if (method.getAnnotation(RequestMapping.class).method()[0] == RequestMethod.POST) {
                                    postTasks.add(method);
                                    continue;
                                }
                            } catch (Exception e) {
                            }

                            //////////// Tasks
                            task = taskDao.findBy(Restrictions.eq(Task.SIGNATURE, controllerBean.getName() + "." + method.getName()), Task._MODULE);

                            task = fillTask(task, method, controllerBean, modelName, module, TtRequestMethod.Get);

                            isExist = false;
                            for (String msg : msgList) {
                                if (msg.equals(task.getMessageCode())) {
                                    isExist = true;
                                    break;
                                }
                            }
                            if (!isExist) {
                                msgList.add(task.getMessageCode());
                            }
                        }

                        // اعمال وضعیت تسک هایی که فقط متد پست هستند.
                        for (Method postTask : postTasks) {
                            if (taskDao.isExist(Restrictions.and(
                                    Restrictions.eq(Task.SIGNATURE, controllerBean.getName() + "." + postTask.getName()),
                                    Restrictions.eq(Task.IS_REFRESHED, true))
                            )) {
                                continue;
                            }

                            task = taskDao.findBy(Restrictions.and(
                                    Restrictions.eq(Task.SIGNATURE, controllerBean.getName() + "." + postTask.getName()),
                                    Restrictions.eq(Task.IS_REFRESHED, false)), Task._MODULE
                            );
                            fillTask(task, postTask, controllerBean, modelName, module, TtRequestMethod.Post);

                        }
                    } catch (IllegalArgumentException | SecurityException ex) {
                        Logger.getLogger(ModuleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            for (String msg : msgList) {
                System.out.println(msg);
            }

            List<Task> delTasks = this.taskDao.findAllBy(Restrictions.eq(Task.IS_REFRESHED, false), Task._USERS, Task._USER_GROUPS);
            for (Task delTask : delTasks) {
                OutLog.pl("Deleted T:  " + delTask.getSignature());
                delTask.setUserGroups(null);
                delTask.setUsers(null);

                taskDao.update(delTask);
            }
            OutLog.pl("Sleep...");
            Thread.sleep(3000);
            OutLog.pl("Continue...");
//            for (Task delTask : delTasks) {
//                taskDao.delete(delTask);
//            }
            taskDao.deleteAllBy(0163, delTasks);

            OutLog.pl("Sleep...");
            Thread.sleep(3000);
            OutLog.pl("Continue...");

            List<Module> delModules = this.findAllBy(Restrictions.eq(Module.IS_REFRESHED, false));
//            for (Module delModule : delModules) {
//                OutLog.pl("Deleted M:  " + delModule.getClassName());
//                delete(delModule);
//            }
            deleteAllBy(0163, delModules);


            ////////////////////////////////////////////////////////////////////
        } catch (Exception ee) {
            ee.printStackTrace();
            return TtInitResult.Exception;
        }
        return TtInitResult.Success;
    }

    private void refreshModelAndTask() {

        /// refreshing models
        List<Module> dbModules = findAll();
        if (dbModules != null && !dbModules.isEmpty()) {
            for (Module m : dbModules) {
                m.setIsRefreshed(false);
                update(m);
            }
        }

        /// refreshing tasks
        List<Task> dbTasks = taskDao.findAll();
        if (dbTasks != null && !dbTasks.isEmpty()) {
            for (Task t : dbTasks) {
                t.setIsRefreshed(false);
                taskDao.update(t);
            }
        }
    }


    private Task fillTask(Task task, Method method, Class controllerBean, String modelName, Module module, TtRequestMethod requestMethod) {
        String menuId = null;

        OutLog.pl("  ---  " + method.getName());

        if (task == null) {
            OutLog.p();
            task = new Task();
//            task.setIsActive(true);
        }

//        task.setIsProtected(method.isAnnotationPresent(ProtectedTask.class));
        task.setIsSuperAdmin(method.isAnnotationPresent(SuperAdminTask.class));

        if (task.getIsSuperAdmin()) {
            task.setIsActive(false);
            task.setIsTwoLevelConfirm(false);
        } else if (task.getIsActive() == null) {
            task.setIsActive(true);
        }
        if (task.getIsTwoLevelConfirm() == null) {
            task.setIsTwoLevelConfirm(false);
        }
        if (task.getIsOnlineLogging() == null) {
            task.setIsOnlineLogging(false);
        }
        if (task.getIsActiveLogging() == null) {
            task.setIsActiveLogging(false);
        }
        task.setIsAjax(method.isAnnotationPresent(ResponseBody.class));
        task.setIsLogManager(method.isAnnotationPresent(LogManagerTask.class));
        task.setIsRefreshed(true);
        task.setMethod(requestMethod);
        task.setMenuMessageCode("task.menu." + method.getName());
        task.setMessageCode(modelName + ".c." + method.getName());
        task.setModule(module);
        task.setIsPanelTask(!method.isAnnotationPresent(Front.class));
        task.setSignature(controllerBean.getName() + "." + method.getName());
        try {
            task.setAccessLevel(method.getAnnotation(TaskAccessLevel.class).value());
        } catch (Exception e) {
            task.setAccessLevel(TtTaskAccessLevel.Grant);
        }
        try {
            task.setUrl(method.getAnnotation(RequestMapping.class).value()[0]);
        } catch (Exception e) {
        }
        ///menuId
        OutLog.p();
        if (method.isAnnotationPresent(MenuIdentity.class)) {
            OutLog.p();
            menuId = method.getAnnotation(MenuIdentity.class).value().toString();
            task.setMenuIdentity(menuId);
        } else {
            try {
                Class clz;
                clz = Class.forName("org.sadr.web.main._project.meta.annotation.ProjectMenuIdentity");
                if (method.isAnnotationPresent(clz)) {
                    Method mt;
                    OutLog.pl(clz.getName());
                    mt = clz.getMethod("value");
                    Object ttPrjTile = mt.invoke(method.getAnnotation(clz));
                    clz = Class.forName("org.sadr.web.main._project._type.TtProjectTile___");
                    mt = clz.getMethod("toString");
                    menuId = (String) mt.invoke(ttPrjTile);
                    task.setMenuIdentity(menuId);
                }
            } catch (Exception e) {
                menuId = null;
            }
        }
        if (menuId == null || menuId.isEmpty()) {
            try {
                menuId = method.getAnnotation(RequestMapping.class).value()[0];
                if (menuId != null) {
                    int ind = menuId.indexOf("{");
                    // used for activing menu for master users (as data-menu-id)
                    if (ind == -1) {
                        if (menuId.contains("panel")) {
                            task.setMenuIdentity(menuId.substring(1).replaceAll("/", "_").replace("panel_", "p_"));
                        } else if (menuId.contains("manage")) {
                            task.setMenuIdentity(menuId.substring(1).replaceAll("/", "_").replace("manage_", "m_"));
                        } else {
                            task.setMenuIdentity("f_" + menuId.substring(1).replaceAll("/", "_"));
                        }
                    } else {
                        if (menuId.contains("panel")) {
                            task.setMenuIdentity(menuId.substring(1, ind - 1).replaceAll("/", "_").replace("panel_", "p_"));
                        } else if (menuId.contains("manage")) {
                            task.setMenuIdentity(menuId.substring(1, ind - 1).replaceAll("/", "_").replace("manage_", "m_"));
                        } else {
                            task.setMenuIdentity("f_" + menuId.substring(1, ind - 1).replaceAll("/", "_"));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        if (task.getIdi() == 0) {
            taskDao.save(task);
        } else {
            taskDao.update(task);
        }
        return task;
    }
}
