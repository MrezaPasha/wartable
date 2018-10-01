package org.sadr.web.main.admin.user.group;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system.irror.IrrorService;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName("مدیریت گروه کاربری")
@RestController
public class UserGroupController extends GenericControllerImpl<UserGroup, UserGroupService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/user/group";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    public UserGroupController() {
    }

    private UserService userService;

    private ModuleService moduleService;

    private TaskService taskService;

    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Set.class, "users", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return userService.findById(Integer.parseInt((String) element));
                }
                return null;
            }
        });
    }

    @InitBinder
    protected void initBinder2(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Set.class, "tasks", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return taskService.findById(Integer.parseInt((String) element));
                }
                return null;
            }
        });
    }

    @PersianName("میز کار")
    @RequestMapping(value = _PANEL_URL + "/desk")
    public ModelAndView pDesk(Model model,
                              @PathParam("ix") String ix) {

        UserGroup obj = (UserGroup) model.asMap().get("raObj_1");
        if (obj == null) {
            Integer id;
            try {
                id = Integer.parseInt(ix);
            } catch (Exception e) {
                id = 0;
            }
            if (id == 0) {
                obj = new UserGroup();
            } else {
                obj = this.service.findById(id, UserGroup._PARENT);
                if (obj == null) {
                    obj = new UserGroup();
                }
            }
        }

        model.addAttribute("uglist", this.service.findAll(UserGroup._PARENT));
        model.addAttribute("userGroup", obj);
        return TtTile___.p_user_group_desk.___getDisModel();
    }

    @RequestMapping(value = _PANEL_URL + "/desk", method = RequestMethod.POST)
    public ModelAndView pDesk(
        Model model,
        @ModelAttribute("userGroup") @Valid UserGroup formObj,
        BindingResult bindingResultformObj,
        final RedirectAttributes redirectAttributes) {
        formObj.setTitle(formObj.getTitle().trim());
        if (bindingResultformObj.hasErrors()) {
            if (bindingResultformObj.getErrorCount() > 1 || !bindingResultformObj.getFieldError().getField().equals(UserGroup._PARENT + ".id")) {
                redirectAttributes.addFlashAttribute("raObj_1", formObj);
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.validation.error")));
                return new ModelAndView("redirect:/panel/user/group/desk?ix=" + formObj.getId());
            }
        }

        if (formObj.getParent() != null && formObj.getParent().getIdi() == 0) {
            formObj.setParent(null);
        }

        if (formObj.getIdi() == 0) {
            // ===================================== CREATE
            if (this.service.isExist(Restrictions.eq(UserGroup.TITLE, formObj.getTitle()))) {
                redirectAttributes.addFlashAttribute("raObj_1", formObj);
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.title.duplicated", formObj.getSecretNote(), TtNotice.Warning)));
                return new ModelAndView("redirect:/panel/user/group/desk?ix=" + formObj.getId());
            }

            this.service.save(formObj);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.create.success", formObj.getSecretNote(), TtNotice.Success, formObj.getTitle())));
        } else {
            // ===================================== EDIT
            UserGroup dbObj = this.service.findById(formObj.getId());
            if (dbObj == null) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.userGroup.not.found", formObj.getSecretNote(), TtNotice.Warning)));
                return new ModelAndView("redirect:/panel/user/group/desk?ix=" + formObj.getId());
            }

            if (this.service.isDuplicateWith(Restrictions.eq(UserGroup.TITLE, formObj.getTitle()), formObj.getId())) {
                redirectAttributes.addFlashAttribute("raObj_1", formObj);
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.title.duplicated", formObj.getSecretNote(), TtNotice.Warning)));
                return new ModelAndView("redirect:/panel/user/group/desk?ix=" + formObj.getId());
            }

            // Parent Loop check
            if (formObj.getParent() != null) {
                UserGroup parent = this.service.findById(formObj.getParent().getId(), UserGroup._PARENT);
                while (parent != null) {
                    if (parent.getIdi() == formObj.getIdi()) {
                        redirectAttributes.addFlashAttribute("raObj_1", formObj);
                        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.group.parent.loop", formObj.getSecretNote(), TtNotice.Danger)));
                        return new ModelAndView("redirect:/panel/user/group/desk?ix=" + formObj.getId());
                    } else {
                        if (parent.getParent() == null) {
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
            }

            dbObj.setTitle(formObj.getTitle());
            dbObj.setParent(formObj.getParent());
            this.service.update(dbObj);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", dbObj.getSecretNote(), TtNotice.Success, dbObj.getTitle())));
        }
        return new ModelAndView("redirect:/panel/user/group/desk");
    }

    @PersianName("تخصیص کاربر به گروه")
    @RequestMapping(value = _PANEL_URL + "/assign/{gid}")
    public ModelAndView pAssign(Model model,
                                @PathVariable("gid") int gid
    ) {
        if (gid == 0) {
            model.addAttribute("uglist", this.service.findAll());
            model.addAttribute("selectedUserGroup", new UserGroup());
        } else {
            UserGroup nc = this.service.findById(gid,
                UserGroup._USERS
            );
            if (nc == null) {
                model.addAttribute("uglist", this.service.findAll());
                model.addAttribute("selectedUserGroup", new UserGroup());
                Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + gid), TtNotice.Warning)));
            } else {
                List<User> dbUsers = this.userService.findAllBy(Restrictions.eq(User.LEVEL, TtUserLevel.Master));
                List<User> newUsers = new ArrayList<>();
                boolean ise;
                for (User dbu : dbUsers) {
                    ise = false;
                    for (User getu : nc.getUsers()) {
                        if (dbu.getIdi() == getu.getIdi()) {
                            ise = true;
                            break;
                        }
                    }
                    if (!ise) {
                        newUsers.add(dbu);
                    }
                }
                model.addAttribute("ulist", newUsers);
                model.addAttribute("userGroup", nc);
            }
        }
        return TtTile___.p_user_group_assign.___getDisModel();
    }

    @RequestMapping(value = _PANEL_URL + "/assign", method = RequestMethod.POST)
    public ModelAndView pAssign(Model model,
                                @ModelAttribute("userGroup") UserGroup formug,
                                BindingResult bindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes
    ) {
        UserGroup dbug = this.service.findById(formug.getId(), UserGroup._USERS);

        //  Get differential of tow list -- invalidate diff list
        User[] dbusers = new User[0];
        if (dbug.getUsers() != null && !dbug.getUsers().isEmpty()) {
            dbusers = new User[dbug.getUsers().size()];
            dbug.getUsers().toArray(dbusers);
        }
        User[] formusers = new User[0];
        if (formug.getUsers() != null && !formug.getUsers().isEmpty()) {
            formusers = new User[formug.getUsers().size()];
            formug.getUsers().toArray(formusers);
        }
        for (int i = 0; i < dbusers.length; i++) {
            for (int j = 0; j < formusers.length; j++) {
                if (formusers[j] != null && dbusers[i].getIdi() == formusers[j].getIdi()) {
                    dbusers[i] = null;
                    formusers[j] = null;
                    break;
                }
            }
        }
        for (User dbuser : dbusers) {
            if (dbuser != null) {
                SessionListener.invalidate(dbuser.getId());
            }
        }
        for (User formuser : formusers) {
            if (formuser != null) {
                SessionListener.invalidate(formuser.getId());
            }
        }
        //-------------------

        dbug.setUsers(formug.getUsers());
        this.service.update(dbug);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.assign.success", dbug.getSecretNote(), TtNotice.Success, dbug.getTitle())));
        return new ModelAndView("redirect:/panel/user/group/assign/" + dbug.getId());

    }

    @PersianName("لیست سطح دسترسی ها")
    @RequestMapping(value = _PANEL_URL + "/access/list/{id}")
    public ModelAndView pAccessList(Model model,
                                    @PathVariable("id") int id,
                                    final RedirectAttributes redirectAttributes
    ) {
        if (id == 0) {
            model.addAttribute("uglist", this.service.findAll());
            model.addAttribute("selectedUserGroup", new UserGroup());
        } else {
            UserGroup userGroup = this.service.findById(id,
                UserGroup._TASKS,
                UserGroup._TASKS + "." + Task._MODULE);
            if (userGroup == null) {
                model.addAttribute("uglist", this.service.findAll());
                model.addAttribute("selectedUserGroup", new UserGroup());
                Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + id), TtNotice.Warning)));
            } else {
                List<Module> moduless = this.moduleService.findAll(Module._TASKS);
                List<Module> modules = new ArrayList<>();

                for (Module module : moduless) {
                    if (module.getTasks() != null && !module.getTasks().isEmpty()) {
                        boolean ise = false;
                        for (Task task : module.getTasks()) {
                            if (task.getAccessLevel() == TtTaskAccessLevel.Grant) {
                                ise = true;
                                break;
                            }
                        }
                        if (ise) {
                            module.setTasks(new HashSet<>());
                            modules.add(module);
                        }
                    }
                }
                for (Task task : userGroup.getTasks()) {
                    for (Module module : modules) {
                        if (task.getModule().getIdi() == module.getIdi()) {
                            module.getTasks().add(task);
                            break;
                        }
                    }
                }
                model.addAttribute("mlist", modules);
                model.addAttribute("userGroup", userGroup);
            }
        }
        return TtTile___.p_user_group_access_list.___getDisModel();
    }

    @PersianName("تنظیم سطح دسترسی")
    @RequestMapping(value = _PANEL_URL + "/access/{uid}/{mid}")
    public ModelAndView pAccess(Model model,
                                @PathVariable("uid") int uid,
                                @PathVariable("mid") int mid,
                                final RedirectAttributes redirectAttributes
    ) {
        UserGroup ug;
        if (uid == 0 || mid == 0) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/list");
        }
        ug = this.service.findById(uid, UserGroup._TASKS);
        if (ug == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/list");
        }
        Module mud = this.moduleService.findById(mid);
        if (mud == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/list");
        }

        if (ug.getTasks().size() > 0) {
            Set<Task> userTasksOrg = ug.getTasks();
            List<Task> allTk = this.taskService.findAllBy(
                Restrictions.and(
                    Restrictions.eq(Task._MODULE, mud),
                    Restrictions.eq(Task.ACCESS_LEVEL, TtTaskAccessLevel.Grant)
                ));
            List<Task> newTk = new ArrayList<>();
            Set<Task> newMyTk = new HashSet<>();
            boolean isExist;

            for (Task my : userTasksOrg) {
                isExist = false;
                for (Task newT : allTk) {
                    if (my.getIdi() == newT.getIdi()) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    newMyTk.add(my);
                }
            }

            for (Task ac : allTk) {
                isExist = false;
                for (Task mc : newMyTk) {
                    if (ac.getIdi() == mc.getIdi()) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    newTk.add(ac);
                } else {
                }
            }
            ug.setTasks(newMyTk);
            model.addAttribute("tasks", newTk);

        } else {
            model.addAttribute("tasks", this.taskService.findAllBy(
                Restrictions.and(
                    Restrictions.eq(Task._MODULE, moduleService.findById(mid)),
                    Restrictions.eq(Task.ACCESS_LEVEL, TtTaskAccessLevel.Grant)
                )));

        }
        model.addAttribute("moduleName", mud.getMessageCode());
        model.addAttribute("userGroup", ug);
        model.addAttribute("moduleId", mid);
        return TtTile___.p_user_group_access_assign.___getDisModel();
    }

    @RequestMapping(value = _PANEL_URL + "/access", method = RequestMethod.POST)
    public ModelAndView pAccess(Model model,
                                HttpServletRequest request,
                                @ModelAttribute("moduleId") String sid,
                                @ModelAttribute("userGroup") UserGroup ug,
                                BindingResult userBindingResult,
                                HttpSession session,
                                final RedirectAttributes redirectAttributes
    ) {
        int mid = 0;
        try {
            mid = Integer.valueOf(sid.trim());
        } catch (Exception e) {
            irrorService.submit(e,request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/group/list");
        }
        if (ug.getIdi() == 0) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/group/list");
        } else {
            UserGroup dbU = service.findById(ug.getId(),
                UserGroup._USERS,
                UserGroup._TASKS,
                UserGroup._TASKS + "." + Task._MODULE);
            if (dbU == null) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
                return new ModelAndView("redirect:/panel/user/group/list");
            }
            Set<Task> tasks = new HashSet<>();
            for (Task dt : dbU.getTasks()) {
                if (dt.getModule().getIdi() != mid) {
                    tasks.add(dt);
                }
            }
            if (ug.getTasks() != null) {
                for (Task ut : ug.getTasks()) {
                    tasks.add(ut);
                }
            }
            dbU.setTasks(tasks);
            this.service.update(dbU);
            for (User user : dbU.getUsers()) {
                SessionListener.invalidate(user.getId());
            }
        }
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.group.access.success", ug.getSecretNote(), TtNotice.Success)));
        return new ModelAndView("redirect:/panel/user/group/access/" + ug.getId() + "/" + mid);

    }

    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public ModelAndView pTrash(@PathVariable("id") int id,
                               final RedirectAttributes redirectAttributes
    ) {
        UserGroup ug = this.service.findById(id, UserGroup._PARENT);
        if (ug == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + id), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/user/group/desk");
        }
        if (ug.getParent() != null) {
            UserGroup sug = this.service.findById(ug.getParent().getId());
            if (sug != null) {
                sug.minusSubCount();
                this.service.update(sug);
            }
        }
        this.service.trash(id);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.trash.success", ug.getSecretNote(), TtNotice.Success, ug.getTitle())));
        return new ModelAndView("redirect:/panel/user/group/desk");
    }

    @PersianName("تازه سازی آمار")
    @RequestMapping(value = _PANEL_URL + "/refresh/{id}")
    public ModelAndView pRefreshStatistics(@PathVariable("id") int id,
                                           final RedirectAttributes redirectAttributes
    ) {
        if (id == 0) {
            List<UserGroup> groups = this.service.findAll(UserGroup._CHILDS, UserGroup._USERS);
            if (groups != null) {
                for (UserGroup ug : groups) {
                    ug.setSubCount(ug.getChilds() == null ? 0 : ug.getChilds().size());
                    ug.setMemberCount(ug.getUsers() == null ? 0 : ug.getUsers().size());
                    this.service.update(ug);
                }
            }
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.refresh.success", JsonBuilder.toJson("userGrId", "" + id), TtNotice.Success)));
        } else {
            UserGroup ug = this.service.findById(id, UserGroup._CHILDS, UserGroup._USERS);
            if (ug == null) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + id))));
                return new ModelAndView("redirect:/panel/user/group/desk");
            }

            ug.setSubCount(ug.getChilds() == null ? 0 : ug.getChilds().size());
            ug.setMemberCount(ug.getUsers() == null ? 0 : ug.getUsers().size());

            this.service.update(ug);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.refresh.success", ug.getSecretNote(), TtNotice.Success, ug.getTitle())));
        }
        return new ModelAndView("redirect:/panel/user/group/desk");
    }

    @PersianName("آزادسازی اعضای گروه")
    @RequestMapping(value = _PANEL_URL + "/freeMember/{id}")
    public ModelAndView pFreeMemberList(@PathVariable("id") int id,
                                        final RedirectAttributes redirectAttributes
    ) {
        if (id == 0) {
            List<UserGroup> groups = this.service.findAll(UserGroup._USERS);
            for (UserGroup ug : groups) {
                ug.setUsers(null);
                this.service.update(ug);
            }
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.free.success", JsonBuilder.toJson("userGrId", "" + id), TtNotice.Success)));
        } else {
            UserGroup ug = this.service.findById(id, UserGroup._USERS);
            if (ug == null) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + id))));
                return new ModelAndView("redirect:/panel/user/group/desk");
            }
            ug.setUsers(null);
            this.service.update(ug);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.list.free.success", ug.getSecretNote(), TtNotice.Success, ug.getTitle())));
        }
        return new ModelAndView("redirect:/panel/user/group/desk");
    }

    @PersianName("آزادسازی زیرگروه ها")
    @RequestMapping(value = _PANEL_URL + "/freeSub/{id}")
    public ModelAndView pFreeSubList(@PathVariable("id") int id,
                                     final RedirectAttributes redirectAttributes
    ) {
        if (id == 0) {
            List<UserGroup> groups = this.service.findAll(UserGroup._CHILDS);
            for (UserGroup ug : groups) {
                ug.setParent(null);
                this.service.update(ug);
            }
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.free.success", JsonBuilder.toJson("userGrId", "" + id), TtNotice.Success
            )));
        } else {
            UserGroup ug = this.service.findById(id, UserGroup._CHILDS);
            if (ug == null) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.group.not.found", JsonBuilder.toJson("userGrId", "" + id))));
                return new ModelAndView("redirect:/panel/user/group/desk");
            }
            ug.setChilds(null);
            this.service.update(ug);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.list.free.success", ug.getSecretNote(), TtNotice.Success, ug.getTitle())));
        }
        return new ModelAndView("redirect:/panel/user/group/desk");
    }

}
