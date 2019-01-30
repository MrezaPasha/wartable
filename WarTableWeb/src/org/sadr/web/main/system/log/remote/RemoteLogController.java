package org.sadr.web.main.system.log.remote;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils.SpringMessager;
import org.sadr._core.utils.Validator;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.LogManagerTask;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.tools._type.TtIxportRowIndex;
import org.sadr.web.main._core.tools._type.TtIxportSubStrategy;
import org.sadr.web.main._core.tools._type.TtIxportTtStrategy;
import org.sadr.web.main._core.tools._type.TtIxporterDownloadMode;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system._type.TtTaskOnlineLoggingStrategy;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.sadr.web.main.system.task.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("کنترل امنیت")
public class RemoteLogController extends GenericControllerImpl<RemoteLog, RemoteLogService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/log/remote";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////


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
    public ModelAndView pList(Model model
    ) {


        Searchee.init(RemoteLog.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.Normal,
                        RemoteLog.USER_ID)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.GreaterEqual,
                        TtSearcheeStrategy.Normal,
                        RemoteLog.CREATE_DATE_TIME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.LessEqual,
                        TtSearcheeStrategy.Normal,
                        RemoteLog.CREATE_DATE_TIME)
        ;


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
                GB.col(RemoteLog.REQUEST_METHOD),
                GB.col(RemoteLog.HOST_PORT_NUMBER)
        );
        return TtTile___.p_sys_log_remote_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @LogManagerTask
    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            @RequestParam(value = "ixp", required = false) String ixportParam,
            HttpServletResponse response) throws IOException {
        GB gb = GB.init(RemoteLog.class)
                .set(
                        RemoteLog.CREATE_DATE_TIME,
                        RemoteLog.TASK_TITLE,
                        RemoteLog.SENSITIVITY,
                        RemoteLog.HOST_PORT_NUMBER,
                        RemoteLog.URL,
                        RemoteLog.REQUEST_METHOD,
                        RemoteLog.IMPORTANCE_LEVEL,
                        RemoteLog.USER_ID,
                        RemoteLog.USER_GROUP_ID,
                        RemoteLog.USER_LEVEL
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            RemoteLog.CREATE_DATE_TIME,
                            RemoteLog.TASK_TITLE,
                            RemoteLog.SENSITIVITY,
                            RemoteLog.HOST_PORT_NUMBER,
                            RemoteLog.URL,
                            RemoteLog.REQUEST_METHOD,
                            RemoteLog.IMPORTANCE_LEVEL,
                            RemoteLog.USER_ID,
                            RemoteLog.USER_GROUP_ID,
                            RemoteLog.USER_LEVEL
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb.setIxportParams(ixportParam);
        return Ixporter.init(RemoteLog.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }


    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_details)
    @PersianName("نمایش جزئیات رویدادها")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model,
                                 @PathVariable("id") long id,
                                 RedirectAttributes redirectAttributes
    ) {
        RemoteLog i = this.service.findById(id);
        if (i == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.log.not.found", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/upload", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute("log", i);
        return TtTile___.p_sys_log_remote_details.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }


    @LogManagerTask
    @MenuIdentity(TtTile___.p_sys_log_remote_moduleList)
    @PersianName("تنظیم سطح عملیات")
    @RequestMapping(value = _PANEL_URL + "/module/list")
    public ModelAndView pListModule(Model model
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
        return TtTile___.p_sys_log_remote_moduleList.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }


    @LogManagerTask
    @PersianName("اعمال سطح عملیات")
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
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.task.not.found", JsonBuilder.toJson("taskId", id + ""), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/module/list", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute("task", task);
        return TtTile___.p_sys_log_remote_taskConfig.___getDisModel(_PANEL_URL + "/task/config", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @LogManagerTask
    @RequestMapping(value = _PANEL_URL + "/task/config", method = RequestMethod.POST)
    public ModelAndView pTaskConfig(
            @ModelAttribute("task") Task formObj,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {

        if (formObj.getSensitivity() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.sensitivity.is.null", formObj.getSecretNote(), TtNotice.Danger)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
        }

        if (formObj.getImportanceLevel() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.importance.is.null", formObj.getSecretNote(), TtNotice.Danger)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
        }
        if (formObj.getIsOnlineLogging() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.onlineLogging.is.null", formObj.getSecretNote(), TtNotice.Danger)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
        }

        if (formObj.getIsOnlineLogging()) {
            if (formObj.getActionType() == null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.actionType.is.null", formObj.getSecretNote(), TtNotice.Danger)));
                return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
            }
            if (formObj.getOnlineLoggingStrategy() == null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.onlineStrategy.is.null", formObj.getSecretNote(), TtNotice.Danger)));
                return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
            } else {
                if (formObj.getOnlineLoggingStrategy() == TtTaskOnlineLoggingStrategy.Scheduling) {
                    if (formObj.getOnlineSchedulingTime() == null) {
                        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.time.is.null", formObj.getSecretNote(), TtNotice.Danger)));
                        return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
                    } else if (!Validator.persianTime(formObj.getOnlineSchedulingTime())) {
                        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.remoteLog.task.time.is.valid", formObj.getSecretNote(), TtNotice.Danger)));
                        return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, formObj);
                    }
                }
            }

        }

        Task dbObj = this.taskService.findBy(
                Restrictions.and(
                        Restrictions.eq(Task.ID, formObj.getId()),
                        Restrictions.eq(Task.IS_SUPER_ADMIN, false)
                ));
        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.task.not.found", formObj.getSecretNote(), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/module/list", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
        }


        dbObj.setSensitivity(formObj.getSensitivity());
        dbObj.setImportanceLevel(formObj.getImportanceLevel());
        dbObj.setIsOnlineLogging(formObj.getIsOnlineLogging());
        dbObj.setOnlineLoggingStrategy(formObj.getOnlineLoggingStrategy());
        dbObj.setOnlineSchedulingTime(formObj.getOnlineSchedulingTime());
        dbObj.setOnlineLoggingDelay(formObj.getOnlineLoggingDelay());
        dbObj.setActionType(formObj.getActionType());
        this.taskService.update(dbObj);
        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.log.task.config.success", formObj.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/task/config/" + formObj.getId(), TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success, notice2s);

    }


    ///------------------------------ online logging
}
