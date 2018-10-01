package org.sadr.web.main.system.log.remote;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.LogManagerTask;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system.irror.IrrorService;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.sadr.web.main.system.task.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("مدیریت رویدادنگاری برخط")
public class RemoteLogController extends GenericControllerImpl<RemoteLog, RemoteLogService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/log/remote";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////


    private IrrorService irrorService;
    private ModuleService moduleService;
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    public RemoteLogController() {
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(List.class, "tasks", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return taskService.findById(Integer.parseInt((String) element));
                }
                return null;
            }
        });
    }

    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_list)
    @PersianName("لیست رویدادها")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView list(Model model
    ) {
        GB.searchTableColumns(model,
                RemoteLog.class,
                GB.col(RemoteLog.ID),
                GB.col(RemoteLog.CREATE_DATE_TIME),
                GB.col(RemoteLog.TASK_TITLE),
                GB.col(RemoteLog.USER_ID),
                GB.col(RemoteLog.USER_LEVEL),
                GB.col(RemoteLog.USER_GROUP_ID),
                GB.col(RemoteLog.SENSITIVITY),
                GB.col(RemoteLog.IMPORTANCE_LEVEL),
                GB.col(RemoteLog.URL),
                GB.col(RemoteLog.PORT_NUMBER)
        );
        return TtTile___.p_sys_log_remote_list.___getDisModel();
    }

    @LogManagerTask
    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> list(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            HttpServletRequest request,
            HttpSession session,
            HttpServletResponse response) {
        try {
            GB gb = GB.init(RemoteLog.class)
                    .set(
                            RemoteLog.CREATE_DATE_TIME,
                            RemoteLog.TASK_TITLE,
                            RemoteLog.SENSITIVITY,
                            RemoteLog.PORT_NUMBER,
                            RemoteLog.URL,
                            RemoteLog.PORT_NUMBER,
                            RemoteLog.IMPORTANCE_LEVEL,
                            RemoteLog.USER_ID,
                            RemoteLog.USER_GROUP_ID,
                            RemoteLog.USER_LEVEL
                    )
                    .setSearchParams(ajaxParam);

            JB jb = JB.init()
                    .set(
                            RemoteLog.CREATE_DATE_TIME,
                            RemoteLog.TASK_TITLE,
                            RemoteLog.SENSITIVITY,
                            RemoteLog.PORT_NUMBER,
                            RemoteLog.URL,
                            RemoteLog.PORT_NUMBER,
                            RemoteLog.IMPORTANCE_LEVEL,
                            RemoteLog.USER_ID,
                            RemoteLog.USER_GROUP_ID,
                            RemoteLog.USER_LEVEL
                    );
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity<>(this.service.findAllJson(gb, jb), headers, HttpStatus.OK);
        } catch (Exception e) {
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }


    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_details)
    @PersianName("نمایش جزئیات رویدادنگاری")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pShow(Model model,
                              @PathVariable("id") long id
    ) {
        RemoteLog i = this.service.findById(id);
        if (i == null) {
            return TtHttpErrorCode___.NotFound_404.___getPanelDisModel();
        }
        model.addAttribute("log", i);
        return TtTile___.p_sys_log_remote_details.___getDisModel();
    }


    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_moduleList)
    @PersianName("لیست ماژول ها برای پیکربندی رویداد")
    @RequestMapping(value = _PANEL_URL + "/module/list")
    public ModelAndView pListModule(Model model, final RedirectAttributes redirectAttributes
    ) {
        List<Module> mList = this.moduleService.findAll(Module._TASKS);
        List<Module> newList = new ArrayList<>();
        boolean isExist;
        for (Module m : mList) {
            isExist = false;
            for (Task t : m.getTasks()) {
                if (t.getIsSuperAdmin() == false && t.getIsAjax() == false) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                newList.add(m);
            }
        }
        model.addAttribute("mlist", newList);
        return TtTile___.p_sys_log_remote_moduleList.___getDisModel();
    }


    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_taskList)
    @PersianName("لیست عملیات های ماژول برای پیکربندی رویداد")
    @RequestMapping(value = _PANEL_URL + "/task/list/{mid}")
    public ModelAndView pListTask(Model model, @PathVariable("mid") long id, final RedirectAttributes redirectAttributes
    ) {
        Module module = moduleService.findById(id);
        if (module == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.module.not.found", JsonBuilder.toJson("moduleId", id + ""), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/module/list");
        }

        List<Task> tasks = this.taskService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Task._MODULE, module),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                )
        );

        model.addAttribute("module", module);
        model.addAttribute("tlist", tasks);
        return TtTile___.p_sys_log_remote_taskList.___getDisModel();
    }


    @LogManagerTask
    @PersianName("پیکربندی رویدادنگاری عملیات")
    @MenuIdentity(TtTile___.p_sys_log_remote_taskConfig)
    @RequestMapping(value = _PANEL_URL + "/task/config/{id}")
    public ModelAndView pTaskConfig(Model model, @PathVariable("id") long id,
                                    final RedirectAttributes redirectAttributes) {
        Task task = this.taskService.findBy(
                Restrictions.and(
                        Restrictions.eq(Task.ID, id),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                ),
                Task._MODULE);
        if (task == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.task.not.found", JsonBuilder.toJson("taskId", id + ""), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/module/list");
        }
        model.addAttribute("task", task);
        return TtTile___.p_sys_log_remote_taskConfig.___getDisModel(_PANEL_URL + "/task/config");
    }

    @LogManagerTask
    @RequestMapping(value = _PANEL_URL + "/task/config", method = RequestMethod.POST)
    public ModelAndView pTaskConfig(Model model,
                                    @ModelAttribute("task") Task formObj,
                                    final RedirectAttributes redirectAttributes
    ) {
        Task dbObj = this.taskService.findBy(
                Restrictions.and(
                        Restrictions.eq(Task.ID, formObj.getId()),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                ));
        if (dbObj == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.task.not.found", formObj.getSecretNote(), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/log/remote/module/list");
        }

        dbObj.setSensitivity(formObj.getSensitivity());
        dbObj.setImportanceLevel(formObj.getImportanceLevel());
        dbObj.setIsOnlineLogging(formObj.getIsOnlineLogging());
        dbObj.setOnlineLoggingStrategy(formObj.getOnlineLoggingStrategy());
        dbObj.setOnlineSchedulingTime(formObj.getOnlineSchedulingTime());
        dbObj.setOnlineLoggingDelay(formObj.getOnlineLoggingDelay());
        dbObj.setActionType(formObj.getActionType());
        this.taskService.update(dbObj);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.log.task.config.success", formObj.getSecretNote(), TtNotice.Success)));
        return new ModelAndView("redirect:/panel/log/remote/task/config/" + formObj.getId());
    }


    ///------------------------------ online logging
    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_onlineLogging)
    @PersianName("فعالسازی رویدادنگاری برخط عملیاتها")
    @RequestMapping(value = _PANEL_URL + "/task/online/logging/{mid}")
    public ModelAndView pActiveOnlineLogging(org.springframework.ui.Model model,
                                             @PathVariable("mid") int mid,
                                             final RedirectAttributes redirectAttributes
    ) {

        Module mud = this.moduleService.findById(mid);
        if (mud == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.module.not.found", JsonBuilder.toJson("moduleId", ""), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/module/list");
        }

        List<Task> tasksNotActive = this.taskService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Task._MODULE, mud),
                        Restrictions.eq(Task.IS_ONLINE_LOGGING, false),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                ));

        TaskViewModel taskViewModel = new TaskViewModel();

        taskViewModel.setTasks(this.taskService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Task._MODULE, mud),
                        Restrictions.eq(Task.IS_ONLINE_LOGGING, true),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                )));

        taskViewModel.setModuleId(mid);
        taskViewModel.setModuleName(SpringMessager.get(mud.getMessageCode()));

        model.addAttribute(taskViewModel);
        model.addAttribute("tlist", tasksNotActive);

        return TtTile___.p_sys_log_remote_onlineLogging.___getDisModel(_PANEL_URL + "/task/online/logging");
    }

    @LogManagerTask
    @RequestMapping(value = _PANEL_URL + "/task/online/logging", method = RequestMethod.POST)
    public ModelAndView pActiveOnlineLogging(org.springframework.ui.Model model,
                                             HttpServletRequest request,
                                             @ModelAttribute TaskViewModel taskViewModel,
                                             BindingResult taskViewModelBindingResult,
                                             final RedirectAttributes redirectAttributes
    ) {

        Module module = this.moduleService.findById(taskViewModel.getModuleId());
        if (module == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.module.not.found", taskViewModel.getSecretNote(), TtNotice.Warning)));
            return Referer.redirect("/panel/log/remote/module/list");
        }

        if (taskViewModel.getTasks() != null && !taskViewModel.getTasks().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (Task t : taskViewModel.getTasks()) {
                if (t != null) {
                    if (t.getIsOnlineLogging() != true) {
                        t.setIsOnlineLogging(true);
                        this.taskService.update(t);
                    }
                    ids.add(t.getId());
                }
            }
            List<Task> naTasks = this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq(Task._MODULE, module),
                            Restrictions.eq(Task.IS_ONLINE_LOGGING, true),
                            Restrictions.eq(Task.IS_SUPER_ADMIN, false),
                            Restrictions.not(
                                    Restrictions.in(Task.ID, ids)
                            )
                    ));
            if (naTasks != null && !naTasks.isEmpty()) {
                for (Task t : naTasks) {
                    t.setIsOnlineLogging(false);
                    this.taskService.update(t);
                }
            }
        } else {
            List<Task> tasks = this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq(Task._MODULE, module),
                            Restrictions.eq(Task.IS_ONLINE_LOGGING, true),
                            Restrictions.eq(Task.IS_SUPER_ADMIN, false)

                    ));
            if (tasks != null && !tasks.isEmpty()) {
                for (Task t : tasks) {
                    t.setIsOnlineLogging(false);
                    this.taskService.update(t);
                }
            }
        }

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.log.task.online.logging.success", module.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/task/online/logging/" + taskViewModel.getModuleId(), request);
    }

}
