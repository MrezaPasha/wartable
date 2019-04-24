package org.sadr.web.main.system.module;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.sadr.web.main.system.task.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@PersianName("عملیات های دوسطحی")
@RestController
public class ModuleController extends GenericControllerImpl<Module, ModuleService> {
    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/module";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;

    ////////////////////
    public ModuleController() {
    }

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(List.class, "tasks", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return taskService.findById(Long.parseLong((String) element));
                }
                return null;
            }
        });
    }


    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list/confirm")
    public ModelAndView pListForTwoLevelConfirm(Model model) {
        List<Module> modules = this.service.findAll(Module._TASKS);
        List<Module> newList = new ArrayList<>();
        boolean isExist;
        for (Module m : modules) {
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
        return TtTile___.p_sys_module_listConfirm.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }


    @PersianName("اعمال تایید دوسطحی")
    @RequestMapping(value = _PANEL_URL + "/confirm/{mid}")
    public ModelAndView pTwoLevelConfirm(org.springframework.ui.Model model,
                                         @PathVariable("mid") long mid,
                                         final RedirectAttributes redirectAttributes
    ) {

        Module mud = this.service.findById(mid);
        if (mud == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.module.not.found", JsonBuilder.toJson("moduleId", ""), TtNotice.Warning)));
            return Referer.redirect("/panel/module/list", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);

        }

        List<Task> tasksNotConfirmed = this.taskService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Task._MODULE, mud),
                        Restrictions.eq(Task.IS_TWO_LEVEL_CONFIRM, false),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false),
                        Restrictions.eq(Task.IS_AJAX, false)

                ));

        TaskViewModel taskViewModel = new TaskViewModel();

        taskViewModel.setTasks(this.taskService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Task._MODULE, mud),
                        Restrictions.eq(Task.IS_TWO_LEVEL_CONFIRM, true),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false),
                        Restrictions.eq(Task.IS_AJAX, false)

                )));

        taskViewModel.setModuleId(mid);
        taskViewModel.setModuleName(SpringMessager.get(mud.getMessageCode()));

        model.addAttribute(taskViewModel);
        model.addAttribute("tlist", tasksNotConfirmed);

        return TtTile___.p_sys_task_confirm.___getDisModel(_PANEL_URL + "/confirm", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/confirm", method = RequestMethod.POST)
    public ModelAndView pTwoLevelConfirm(@ModelAttribute TaskViewModel taskViewModel,
                                         BindingResult taskViewModelBindingResult,
                                         final RedirectAttributes redirectAttributes
    ) {

        Module module = this.service.findById(taskViewModel.getModuleId());
        if (module == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.module.not.found", taskViewModel.getSecretNote(), TtNotice.Warning)));
            return Referer.redirect("/panel/module/list", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
        }

        if (taskViewModel.getTasks() != null && !taskViewModel.getTasks().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (Task t : taskViewModel.getTasks()) {
                if (t != null) {
                    if (t.getIsTwoLevelConfirm() != true) {
                        t.setIsTwoLevelConfirm(true);
                        this.taskService.update(t);
                    }
                    ids.add(t.getId());
                }
            }
            List<Task> naTasks = this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq(Task._MODULE, module),
                            Restrictions.eq(Task.IS_TWO_LEVEL_CONFIRM, true),
                            Restrictions.eq(Task.IS_SUPER_ADMIN, false),
                            Restrictions.eq(Task.IS_AJAX, false),
                            Restrictions.not(
                                    Restrictions.in(Task.ID, ids)
                            )
                    ));
            if (naTasks != null && !naTasks.isEmpty()) {
                for (Task t : naTasks) {
                    t.setIsTwoLevelConfirm(false);
                    this.taskService.update(t);
                }
            }
        } else {
            List<Task> tasks = this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq(Task._MODULE, module),
                            Restrictions.eq(Task.IS_TWO_LEVEL_CONFIRM, true),
                            Restrictions.eq(Task.IS_SUPER_ADMIN, false),
                            Restrictions.eq(Task.IS_AJAX, false)

                    ));
            if (tasks != null && !tasks.isEmpty()) {
                for (Task t : tasks) {
                    t.setIsTwoLevelConfirm(false);
                    this.taskService.update(t);
                }
            }
        }


        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.task.confirm.success", module.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/confirm/" + taskViewModel.getModuleId(), TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success, notice2s);

    }



}
