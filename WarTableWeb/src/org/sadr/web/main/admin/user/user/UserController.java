package org.sadr.web.main.admin.user.user;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtEntityState;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.Front;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.*;
import org.sadr._core.utils._type.*;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.OverActiveTask;
import org.sadr.web.main._core.meta.annotation.OverChangePassword;
import org.sadr.web.main._core.meta.annotation.OverDevelopingMode;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools._type.TtIxportRowIndex;
import org.sadr.web.main._core.tools._type.TtIxportSubStrategy;
import org.sadr.web.main._core.tools._type.TtIxportTtStrategy;
import org.sadr.web.main._core.tools._type.TtIxporterDownloadMode;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.uiBag.UiBag;
import org.sadr.web.main._core.uiBag.UiBagService;
import org.sadr.web.main._core.utils.*;
import org.sadr.web.main._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin._type.TtUserAttemptType;
import org.sadr.web.main.admin._type.TtUserIpRangeType;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.confirm.UserConfirm;
import org.sadr.web.main.admin.user.confirm.UserConfirmService;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupService;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.irror.irror.Irror;
import org.sadr.web.main.system.irror.irror.IrrorService;
import org.sadr.web.main.system.irror.notify.IrrorNotify;
import org.sadr.web.main.system.irror.notify.IrrorNotifyService;
import org.sadr.web.main.system.log.attempt.UserAttempt;
import org.sadr.web.main.system.log.attempt.UserAttemptService;
import org.sadr.web.main.system.log.signin.SigninLog;
import org.sadr.web.main.system.log.signin.SigninLogService;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author masoud
 * @version 1.95.10.19
 */
@RestController
@PersianName("مدیریت کاربران")
public class UserController extends GenericControllerImpl<User, UserService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/user";

    ////////////////////
    public UserController() {
    }

    ////////////////////
    private TaskService taskService;
    private ModuleService moduleService;
    private UserGroupService userGroupService;
    private UserAttemptService userAttemptService;
    private SigninLogService signinLogService;
    private UserConfirmService userConfirmService;
    private IrrorService irrorService;
    private UiBagService uiBagService;
    private IrrorNotifyService irrorNotifyService;

    @Autowired
    public void setIrrorNotifyService(IrrorNotifyService irrorNotifyService) {
        this.irrorNotifyService = irrorNotifyService;
    }

    @Autowired
    public void setUiBagService(UiBagService uiBagService) {
        this.uiBagService = uiBagService;
    }

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    @Autowired
    public void setUserConfirmService(UserConfirmService userConfirmService) {
        this.userConfirmService = userConfirmService;
    }

    @Autowired
    public void setSigninLogService(SigninLogService signinLogService) {
        this.signinLogService = signinLogService;
    }

    @Autowired
    public void setUserAttemptService(UserAttemptService userAttemptService) {
        this.userAttemptService = userAttemptService;
    }

    @Autowired
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Set.class, "tasks", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return taskService.findById(new Integer((String) element));
                }
                return null;
            }
        });
    }

    @InitBinder
    protected void initBinderGroup(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Set.class, "userGroups", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    return userGroupService.findById(new Integer((String) element));
                }
                return null;
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////==========================================
    ////////////////////////////////  PANEL

    @PersianName("ثبت کاربر")
    @RequestMapping(value = _PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        User u = (User) model.asMap().get("user");
        if (u == null) {
            u = new User();
        }
        model.addAttribute("user", u);
        model.addAttribute("uglist", this.userGroupService.findAll());
        return TtTile___.p_user_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("user") @Valid User fuser,
                                BindingResult userBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {


        if (userBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Error, request, redirectAttributes, userBindingResult, fuser);
        }

        if (fuser == null || fuser.getIdi() != 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.register.error")));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Error, notice2s, request, redirectAttributes, fuser);
        }


        User dbu;
        // =========== username
        dbu = service.findBy(Restrictions.eq(User.USERNAME, fuser.getUsername()));
        if (dbu != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.username.registered", fuser.getSecretNote(), TtNotice.Warning, dbu.getFullName())));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        // =========== user code
        if (fuser.getUserCode() != null && !fuser.getUserCode().isEmpty()) {
            dbu = service.findBy(Restrictions.eq(User.USER_CODE, fuser.getUserCode()));
            if (dbu != null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.code.registered", fuser.getSecretNote(), TtNotice.Warning, dbu.getFullName())));
                return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
            }
        }

        /////////////////
        if (fuser.getAccessLimitYearlyStart() != null && !fuser.getAccessLimitYearlyStart().isEmpty() && !Validator.persianDateTime(fuser.getAccessLimitYearlyStart())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearlyStart.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitYearlyEnd() != null && !fuser.getAccessLimitYearlyEnd().isEmpty() && !Validator.persianDateTime(fuser.getAccessLimitYearlyEnd())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearlyEnd.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitYearlyStart() != null && !fuser.getAccessLimitYearlyStart().isEmpty() &&
                fuser.getAccessLimitYearlyEnd() != null && !fuser.getAccessLimitYearlyEnd().isEmpty() &&
                ParsCalendar.getInstance().compareDateTime(fuser.getAccessLimitYearlyStart(), fuser.getAccessLimitYearlyEnd()) == TtCompareResult.FirstIsBigger) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearly.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        /////////////////////
        if (fuser.getAccessLimitMonthlyStart() > fuser.getAccessLimitMonthlyEnd()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitMonthly.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        /////////////////////
        if (fuser.getAccessLimitDailyStart() > fuser.getAccessLimitDailyEnd()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitDaily.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        //////////////////////
        if (fuser.getAccessLimitTimelyStart() != null && !fuser.getAccessLimitTimelyStart().isEmpty() && !Validator.persianTime(fuser.getAccessLimitTimelyStart())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimelyStart.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitTimelyEnd() != null && !fuser.getAccessLimitTimelyEnd().isEmpty() && !Validator.persianTime(fuser.getAccessLimitTimelyEnd())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimelyEnd.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitTimelyStart() != null && !fuser.getAccessLimitTimelyStart().isEmpty() &&
                fuser.getAccessLimitTimelyEnd() != null && !fuser.getAccessLimitTimelyEnd().isEmpty() &&
                ParsCalendar.getInstance().compareTime(fuser.getAccessLimitTimelyStart(), fuser.getAccessLimitTimelyEnd()) == TtCompareResult.FirstIsBigger) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimely.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        /////////////////////
        if (!Validator.email(fuser.getEmail(), false)) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.email.invalid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        fuser.setPassword(CodeGenerator.password(TtPasswordType.Mix, 20));
        fuser.setIsNeedToChangePassword(true);
        this.service.save(fuser);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.register.success", fuser.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/create", TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/edit/" + fuser.getIdi(), TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit

    @PersianName("ویرایش کاربر")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") int uid, RedirectAttributes redirectAttributes) {
        User dbuser = (User) model.asMap().get("user");
        if (dbuser == null) {
            dbuser = this.service.findById(uid, User._USER_GROUPS);
        } else {
            User dbuser1 = this.service.findById(uid, User._USER_GROUPS);
            dbuser.setUserGroups(dbuser1.getUserGroups());
        }
        if (dbuser == null) {
            Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + uid), TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Error);
        }
        List<UserGroup> eeclist = new ArrayList<>();
        boolean isExist;
        for (UserGroup lp : this.userGroupService.findAll()) {
            isExist = false;
            for (UserGroup sp : dbuser.getUserGroups()) {
                if (lp.getIdi() == sp.getIdi()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                eeclist.add(lp);
            }
        }
        model.addAttribute("uglist", eeclist);

        model.addAttribute("user", dbuser);
        return TtTile___.p_user_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("user")
            @Valid User fuser,
            BindingResult userBindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes, HttpSession session) {
        if (userBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Error, request, redirectAttributes, userBindingResult, fuser);
        }

        User dbuser;

        //========== username
        dbuser = service.findBy(
                Restrictions.and(
                        Restrictions.eq(User.USERNAME, fuser.getUsername()),
                        Restrictions.ne(User.ID, fuser.getId())
                )
        );
        if (dbuser != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.username.registered", fuser.getSecretNote(), TtNotice.Warning, dbuser.getFullName())));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        //============= user code
        if (fuser.getUserCode() != null && !fuser.getUserCode().isEmpty()) {
            dbuser = service.findBy(
                    Restrictions.and(
                            Restrictions.eq(User.USER_CODE, fuser.getUserCode()),
                            Restrictions.ne(User.ID, fuser.getId())
                    )
            );
            if (dbuser != null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.code.registered", fuser.getSecretNote(), TtNotice.Warning, dbuser.getFullName())));
                return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
            }
        }

        /////////////////
        if (fuser.getAccessLimitYearlyStart() != null && !fuser.getAccessLimitYearlyStart().isEmpty() && !Validator.persianDateTime(fuser.getAccessLimitYearlyStart())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearlyStart.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitYearlyEnd() != null && !fuser.getAccessLimitYearlyEnd().isEmpty() && !Validator.persianDateTime(fuser.getAccessLimitYearlyEnd())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearlyEnd.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitYearlyStart() != null && !fuser.getAccessLimitYearlyStart().isEmpty() &&
                fuser.getAccessLimitYearlyEnd() != null && !fuser.getAccessLimitYearlyEnd().isEmpty() &&
                ParsCalendar.getInstance().compareDateTime(fuser.getAccessLimitYearlyStart(), fuser.getAccessLimitYearlyEnd()) == TtCompareResult.FirstIsBigger) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitYearly.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        /////////////////////
        if (fuser.getAccessLimitMonthlyStart() > fuser.getAccessLimitMonthlyEnd()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitMonthly.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        /////////////////////
        if (fuser.getAccessLimitDailyStart() > fuser.getAccessLimitDailyEnd()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitDaily.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        //////////////////////
        if (fuser.getAccessLimitTimelyStart() != null && !fuser.getAccessLimitTimelyStart().isEmpty() && !Validator.persianTime(fuser.getAccessLimitTimelyStart())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimelyStart.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitTimelyEnd() != null && !fuser.getAccessLimitTimelyEnd().isEmpty() && !Validator.persianTime(fuser.getAccessLimitTimelyEnd())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimelyEnd.not.valid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (fuser.getAccessLimitTimelyStart() != null && !fuser.getAccessLimitTimelyStart().isEmpty() &&
                fuser.getAccessLimitTimelyEnd() != null && !fuser.getAccessLimitTimelyEnd().isEmpty() &&
                ParsCalendar.getInstance().compareTime(fuser.getAccessLimitTimelyStart(), fuser.getAccessLimitTimelyEnd()) == TtCompareResult.FirstIsBigger) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.accessLimitTimely.first.is.bigger", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        ////////////////////
        if (!Validator.email(fuser.getEmail(), false)) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.email.invalid", fuser.getSecretNote(), TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }


        dbuser = this.service.findById(fuser.getId());
        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found")));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        //----------- verify super admin
        User suser = (User) session.getAttribute("sUser");
        if (suser == null) {
            return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel();
        }
        if (!suser.getIsSuperAdmin() && dbuser.getIsSuperAdmin()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.edit.impossible", fuser.getSecretNote(), TtNotice.Danger, dbuser.getFullName())));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }

        if ((dbuser.getLevel() != TtUserLevel.Administrator || !dbuser.getIsSuperAdmin()) && !dbuser.getIsLogManager()) {
            dbuser.setUsername(fuser.getUsername());
            if (dbuser.getLevel() != fuser.getLevel()) {
                if (suser.getLevel() != TtUserLevel.Administrator
                        && dbuser.getLevel() == TtUserLevel.Administrator) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.edit.level.not.allowed", suser.getSecretNote(), TtNotice.Warning)));
                } else {
                    dbuser.setLevel(fuser.getLevel());
                }
            }
        }

        dbuser.setFirstName(fuser.getFirstName());
        dbuser.setGender(fuser.getGender());
        dbuser.setLastName(fuser.getLastName());
        dbuser.setStatus(fuser.getStatus());
        dbuser.setUserGroups(fuser.getUserGroups());
        dbuser.setIsBlocked(fuser.getIsBlocked());
        dbuser.setUserCode(fuser.getUserCode());
        dbuser.setIpRangeType(fuser.getIpRangeType());
        dbuser.setIpAddress(fuser.getIpAddress());
        dbuser.setIpAddressStart(fuser.getIpAddressStart());
        dbuser.setIpAddressEnd(fuser.getIpAddressEnd());
        dbuser.setAccessLimitDailyEnd(fuser.getAccessLimitDailyEnd());
        dbuser.setAccessLimitDailyStart(fuser.getAccessLimitDailyStart());
        dbuser.setAccessLimitMonthlyEnd(fuser.getAccessLimitMonthlyEnd());
        dbuser.setAccessLimitMonthlyStart(fuser.getAccessLimitMonthlyStart());
        dbuser.setAccessLimitTimelyEnd(fuser.getAccessLimitTimelyEnd());
        dbuser.setAccessLimitTimelyStart(fuser.getAccessLimitTimelyStart());
        dbuser.setAccessLimitYearlyEnd(fuser.getAccessLimitYearlyEnd());
        dbuser.setAccessLimitYearlyStart(fuser.getAccessLimitYearlyStart());
        dbuser.setEmail(fuser.getEmail());
        this.service.update(dbuser);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", fuser.getSecretNote(), TtNotice.Success, dbuser.getFullName())));

        SessionListener.invalidate(dbuser.getId());
        return Referer.redirect(_PANEL_URL + "/edit/" + dbuser.getIdi(), TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success, notice2s);
    }

    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @PersianName("ویرایش اطلاعات کاربری")
    @RequestMapping(value = _PANEL_URL + "/your-edit")
    public ModelAndView pEditYourUser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User sUser = (User) session.getAttribute("sUser");

        User dbuser = (User) model.asMap().get("user");
        if (dbuser == null) {
            dbuser = this.service.findById(sUser.getIdi());
        }

        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.user.not.found", TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
        }

        model.addAttribute("user", dbuser);
        return TtTile___.p_user_yourEdit.___getDisModel(_PANEL_URL + "/your-edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @RequestMapping(value = _PANEL_URL + "/your-edit", method = RequestMethod.POST)
    public ModelAndView pEditYourUser(
            @ModelAttribute("user")
            @Valid User fuser,
            BindingResult userBindingResult,
            HttpServletRequest request,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (userBindingResult.hasErrors()
                && !userBindingResult.getFieldError().getField().equals("ipRangeType")) {
            return Referer.redirectBindingError(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Error, request, redirectAttributes, userBindingResult, fuser);
        }

        User dbuser;
        dbuser = this.service.findById(fuser.getId(), User._TASKS, User._USER_GROUPS);
        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found")));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if (this.service.isDuplicateWith(Restrictions.eq(User.USERNAME, fuser.getUsername()), dbuser.getId())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.username.invalid", fuser.getSecretNote())));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fuser);
        }
        if ((dbuser.getLevel() != TtUserLevel.Administrator || !dbuser.getIsSuperAdmin()) && !dbuser.getIsLogManager()) {
            dbuser.setUsername(fuser.getUsername());
        }
        dbuser.setFirstName(fuser.getFirstName());
        dbuser.setGender(fuser.getGender());
        dbuser.setLastName(fuser.getLastName());

        this.service.update(dbuser);

        this.service.updateUserSession(session, dbuser);
        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", fuser.getSecretNote(), TtNotice.Success, dbuser.getFullName())));
        return Referer.redirect(_PANEL_URL + "/your-edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== password
    private ModelAndView changePassRedirect(User fuser, Notice2[] notice2s, RedirectAttributes redirectAttributes, boolean isYour) {
        return Referer.redirectObjects(TtTaskActionSubType.Change_Password, TtTaskActionStatus.Failure, notice2s,
                isYour ? (_PANEL_URL + "/your-pass") : (_PANEL_URL + "/user-pass/" + fuser.getIdi()), redirectAttributes,
                fuser);

    }

    private ModelAndView changePass(User fuser, String newPass, String rePass, RedirectAttributes redirectAttributes, boolean isYour) {
        if (fuser == null || fuser.getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.password.change.error")));
            return changePassRedirect(fuser, notice2s, redirectAttributes, isYour);
        }

        if (!newPass.equals(rePass)) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.repassword.not.equal", TtNotice.Warning)));
            return changePassRedirect(fuser, notice2s, redirectAttributes, isYour);
        }


        User dbu = service.findById(fuser.getId());
        if (dbu == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", fuser.getSecretNote(), TtNotice.Danger)));
            return changePassRedirect(fuser, notice2s, redirectAttributes, isYour);
        }
        int prop;
        boolean boProp, checked;
        checked = false;
        Notice2[] notice2s = null;
        //-------- min length
        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordMinLength);
        if (newPass.length() < prop) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.min.length.violation", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
            checked = true;
        }
        //-------- max length
        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordMaxLength);
        if (newPass.length() > prop) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.max.length.violation", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
            checked = true;
        }
        //-------- Big char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastBigCharacter);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountBigCharacter);
            if (!newPass.matches("^(.*?[A-Z]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.at.least.big.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Small char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastSmallCharacter);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountSmallCharacter);
            if (!newPass.matches("^(.*?[a-z]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.at.least.small.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Number char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastNumber);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountNumber);
            if (!newPass.matches("^(.*?[0-9]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.at.least.number.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Specific char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastSpecific);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountSpecific);
            if (!newPass.matches("^(.*?[!@#$%^&*-+_]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.at.least.specific.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }

        if (checked) {
            return changePassRedirect(fuser, notice2s, redirectAttributes, isYour);
        }

        //-------- duplicate password
        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordLastPassNotToBeSelected);
        if (dbu.isPasswordInHistory(Digester.digestSHA1(newPass), prop)) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.password.not.be.equal.to.last", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
            return changePassRedirect(fuser, notice2s, redirectAttributes, isYour);
        }

        dbu.setPassword(Digester.digestSHA1(newPass));
        dbu.addPasswordToHistory();


        if (isYour) {
            dbu.setPasswordDateTime(ParsCalendar.getInstance().getShortDateTime());
            dbu.setIsNeedToChangePassword(false);
            this.service.update(dbu);
            SessionListener.invalidate(dbu.getId());
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.password.your.change.success", fuser.getSecretNote(), TtNotice.Success)));
            return Referer.redirect("/signin", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success, notice2s);
        } else {
            dbu.setPasswordDateTime(null);
            dbu.setIsNeedToChangePassword(true);
            this.service.update(dbu);
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.password.change.success", fuser.getSecretNote(), TtNotice.Success)));
            SessionListener.invalidate(dbu.getId());
            return Referer.redirect(_PANEL_URL + "/edit/" + fuser.getIdi(), TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success, notice2s);
        }
    }

    @PersianName("تغییر رمز برای کاربر")
    @RequestMapping(value = _PANEL_URL + "/user-pass/{uid}")
    public ModelAndView pChangeUserPassword(Model model, @PathVariable("uid") int uid, RedirectAttributes redirectAttributes) {
        User dbuser = this.service.findById(uid);
        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + uid), TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute("user", dbuser);
        return TtTile___.p_user_changeUserPass.___getDisModel(_PANEL_URL + "/user-pass", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/user-pass", method = RequestMethod.POST)
    public ModelAndView pChangeUserPassword(@ModelAttribute("newPassword") String newPass,
                                            @ModelAttribute("repassword") String rePass,
                                            @ModelAttribute("user") User fuser,
                                            BindingResult userBindingResult,
                                            final RedirectAttributes redirectAttributes) {
        return changePass(fuser, newPass, rePass, redirectAttributes, false);
    }

    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @OverChangePassword
    @PersianName("تغییر رمز")
    @RequestMapping(value = _PANEL_URL + "/your-pass")
    public ModelAndView pChangeYourPassword(
            @RequestParam(value = "expired", required = false) String x,
            @RequestParam(value = "signinNotice", required = false) String signinNotice,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User dbuser = (User) session.getAttribute("sUser");
        dbuser = this.service.findById(dbuser.getIdi());
        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.user.not.found", TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute("user", dbuser);
        if (x != null) {
            Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.you.have.to.change.password", TtNotice.Info)));
        }
        if (signinNotice != null) {
            model.addAttribute("signinNotice", PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.SigninNotice).replaceAll("\r\n", "<br/>"));
        }
        return TtTile___.p_user_changeYourPass.___getDisModel(_PANEL_URL + "/your-pass", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success);
    }

    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @OverChangePassword
    @RequestMapping(value = _PANEL_URL + "/your-pass", method = RequestMethod.POST)
    public ModelAndView pChangeYourPassword(@ModelAttribute("newPassword") String newPass,
                                            @ModelAttribute("repassword") String rePass,
                                            @ModelAttribute("user") User fuser,
                                            BindingResult userBindingResult,
                                            final RedirectAttributes redirectAttributes) {

        return changePass(fuser, newPass, rePass, redirectAttributes, true);
    }

    @PersianName("تولید نام کاربری تصادفی")
    @RequestMapping(value = _PANEL_URL + "/username/random", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pRandomUsername() {
        String username = CodeGenerator.username(TtUsernameType.LittleCharNumber, 6);

        while (this.service.isExist(Restrictions.eq(User.USERNAME, username))) {
            username = CodeGenerator.username(TtUsernameType.LittleCharNumber, 6);
        }

        return Ison.init()
                .setStatus(TtIsonStatus.Ok)
                .setProperty("username", username)
                .toResponse();
    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* LIST P
    @PersianName("لیست کاربران غیرفعال")
    @RequestMapping(value = _PANEL_URL + "/list/inactive")
    public ModelAndView pListInactive(Model model) {

        Searchee.init(User.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.USERNAME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.FIRST_NAME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.LAST_NAME);

        GB.searchTableColumns(model,
                User.class,
                GB.col(User.ID),
                GB.col(User.$FULL_NAME, GB.path(User.FIRST_NAME)),
                GB.col(User.USERNAME),
                GB.col(User.LEVEL),
                GB.col(User.STATUS),
                GB.col(User.LAST_SIGNIN_DATE_TIME),
                GB.col(User.PASSWORD_DATE_TIME),
                GB.col(User.CREATE_DATE_TIME)
        );
        return TtTile___.p_user_listInactive.___getDisModel(null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list/inactive", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pListInactive(@RequestParam(value = "ap", required = false) String ajaxParam,
                                         @RequestParam(value = "ixp", required = false) String ixportParam,
                                         HttpServletResponse response) throws IOException {
        int range = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserDeactivateTimeout);

        GB gb = GB.init(User.class)
                .set(
                        User.CREATE_DATE_TIME,
                        User.GENDER,
                        User.FIRST_NAME,
                        User.LAST_NAME,
                        User.USERNAME,
                        User.STATUS,
                        User.LEVEL,
                        User.LAST_SIGNIN_DATE_TIME,
                        User.PASSWORD_DATE_TIME,
                        User.IS_BLOCKED
                )
                .setSearchParams(ajaxParam).addRestrictionsAnd(
                        Restrictions.le(User.LAST_SIGNIN_DATE_TIME, ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Day, -range))
                );

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.STATUS,
                            User.LEVEL,
                            User.$FULL_NAME,
                            User.$IS_MASTER,
                            User.$IS_NOT_CLIENT,
                            User.LAST_SIGNIN_DATE_TIME,
                            User.PASSWORD_DATE_TIME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb.setIxportParams(ixportParam);
        return Ixporter.init(User.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }


    @PersianName("لیست کاربران")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {
        Searchee.init(User.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.USERNAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.FIRST_NAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.LAST_NAME
                );

        GB.searchTableColumns(model,
                User.class,
                GB.col(User.ID),
                GB.col(User.$FULL_NAME, GB.path(User.FIRST_NAME)),
                GB.col(User.USERNAME),
                GB.col(User.LEVEL),
                GB.col(User.STATUS),
                GB.col(User.USER_CODE),
                GB.col(User.LAST_SIGNIN_DATE_TIME),
                GB.col(User.PASSWORD_DATE_TIME),
                GB.col(User.CREATE_DATE_TIME)
//            ,
//            GB.col(User.$BLOCKED_Y, GB.path(User.BLOCKED))
        );
        return TtTile___.p_user_list.___getDisModel(null, TtTaskActionStatus.Success);
    }


    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb;
        if (ixportParam == null) {
            gb = GB.init(User.class)
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.USER_CODE,
                            User.STATUS,
                            User.LEVEL,
                            User.IS_LOG_MANAGER,
                            User.LAST_SIGNIN_DATE_TIME,
                            User.PASSWORD_DATE_TIME,
                            User.IS_BLOCKED
                    )
                    .setSearchParams(ajaxParam);

            JB jb = JB.init()
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.USER_CODE,
                            User.STATUS,
                            User.LEVEL,
                            User.IS_LOG_MANAGER,
                            User.$FULL_NAME,
                            User.$IS_MASTER,
                            User.$IS_NOT_CLIENT,
                            User.LAST_SIGNIN_DATE_TIME,
                            User.PASSWORD_DATE_TIME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb = GB.init(User.class)
                .set(
                        User.ID,
                        User.CREATE_DATE_TIME,
                        User.GENDER,
                        User.FIRST_NAME,
                        User.LAST_NAME,
                        User.USERNAME,
                        User.STATUS,
                        User.LEVEL,
                        User.LAST_SIGNIN_DATE_TIME,
                        User.PASSWORD_DATE_TIME,
                        User.IS_BLOCKED,
                        User.IP_RANGE_TYPE,
                        User.IP_ADDRESS,
                        User.IP_ADDRESS_FIRST_SIGNIN,
                        User.IP_ADDRESS_START,
                        User.IP_ADDRESS_END
                )
                .setSearchParams(ajaxParam);

        gb.setIxportParams(ixportParam);
        return Ixporter.init(User.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* ONLINE USER LIST
    @PersianName("لیست کاربران آنلاین")
    @RequestMapping(value = _PANEL_URL + "/list/online")
    public ModelAndView pListOnline(Model model) {

        Searchee.init(User.class, model)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.USERNAME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.FIRST_NAME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        User.LAST_NAME);

        GB.searchTableColumns(model,
                User.class,
                GB.col(User.ID),
                GB.col(User.$FULL_NAME, GB.path(User.FIRST_NAME)),
                GB.col(User.USERNAME),
                GB.col(User.LAST_SIGNIN_DATE_TIME)
        );
        return TtTile___.p_user_listOnline.___getDisModel(null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list/online", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pListOnline(@RequestParam(value = "ap", required = false) String ajaxParam,
                                       @RequestParam(value = "ixp", required = false) String ixportParam,
                                       HttpServletResponse response) throws IOException {

        List<Long> onlineUsersId = SessionListener.getOnlineUsersId();

        GB gb;
        if (onlineUsersId.size() == 0) {
            gb = GB.init(User.class)
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.LAST_SIGNIN_DATE_TIME
                    )
                    .setSearchParams(ajaxParam).addRestrictionsAnd(
                            Restrictions.eq(User.ID, 0l));
        } else {
            gb = GB.init(User.class)
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.LAST_SIGNIN_DATE_TIME
                    )
                    .setSearchParams(ajaxParam).addRestrictionsAnd(
                            Restrictions.in(User.ID, onlineUsersId));
        }


        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            User.CREATE_DATE_TIME,
                            User.GENDER,
                            User.FIRST_NAME,
                            User.LAST_NAME,
                            User.USERNAME,
                            User.$FULL_NAME,
                            User.LAST_SIGNIN_DATE_TIME,
                            User.PASSWORD_DATE_TIME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(User.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* DEACTIVATE & EXPIRE

    @PersianName("غیرفعالسازی کاربران بدون فعالیت")
    @RequestMapping(value = _PANEL_URL + "/deactivate")
    public ModelAndView pDeactivateUser(RedirectAttributes redirectAttributes) {

        int range = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserDeactivateTimeout);

        List<User> userList = service.findAllBy(
                Restrictions.le(User.LAST_SIGNIN_DATE_TIME, ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Day, -range))
        );
        for (User user : userList) {
            user.setStatus(TtUserStatus.Deactive);
            this.service.update(user);
        }

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.deactivate.success", TtNotice.Success)));

        return Referer.redirect(_PANEL_URL + "/list/inactive", null, TtTaskActionStatus.Success);
    }

    @PersianName("منقضی کردن نشست کاربر")
    @RequestMapping(value = _PANEL_URL + "/expire/{id}")
    public ModelAndView pExpire(@PathVariable("id") int id,
                                final RedirectAttributes redirectAttributes, HttpSession session) {

        User dbus = this.service.find(id,
                GB.init(User.class)
                        .set(
                                User.GENDER, User.FIRST_NAME, User.LAST_NAME)
        );
        Notice2[] notice2s;
        if (dbus == null) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id))));
            return Referer.redirect(_PANEL_URL + "/list/online", null, TtTaskActionStatus.Failure, notice2s);
        }

        if (SessionListener.invalidate(id)) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.expire.success", dbus.getSecretNote(), TtNotice.Success, dbus.getFullName())));
            return Referer.redirect(_PANEL_URL + "/list/online", null, TtTaskActionStatus.Success, notice2s);
        } else {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.expire.failed", dbus.getSecretNote(), TtNotice.Danger, dbus.getFullName())));
            return Referer.redirect(_PANEL_URL + "/list/online", null, TtTaskActionStatus.Failure, notice2s);
        }


    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* ACCESS
    @PersianName("لیست سطح دسترسی")
    @RequestMapping(value = _PANEL_URL + "/access/list/{id}")
    public ModelAndView pAccessList(Model model,
                                    @PathVariable("id") int id,
                                    final RedirectAttributes redirectAttributes) {
        if (id == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id), TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
        } else {
            User user = this.service.findById(id, User._TASKS, User._TASKS + "." + Task._MODULE);
            if (user == null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id), TtNotice.Danger)));
                return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
            } else if (user.getIsLogManager()) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.log.manager.not.allowed", JsonBuilder.toJson("userId", "" + id), TtNotice.Warning)));
                return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
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
                for (Task task : user.getTasks()) {
                    for (Module module : modules) {
                        if (task.getModule().getIdi() == module.getIdi()) {
                            OutLog.p(task.getMenuIdentity());
                            module.getTasks().add(task);
                            break;
                        }
                    }
                }
                model.addAttribute("mlist", modules);
                model.addAttribute("user", user);
            }
        }
        return TtTile___.p_user_access_list.___getDisModel(TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Success);
    }

    @PersianName("اعمال سطح دسترسی")
    @RequestMapping(value = _PANEL_URL + "/access/{uid}/{mid}")
    public ModelAndView pAccess(Model model,
                                @PathVariable("uid") int uid,
                                @PathVariable("mid") int mid,
                                final RedirectAttributes redirectAttributes) {

        User us;
        if (uid == 0 || mid == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
        }
        us = this.service.findById(uid, User._TASKS);
        if (us == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
        } else if (us.getIsLogManager()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.log.manager.not.allowed", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
        }
        Module mud = this.moduleService.findById(mid);
        if (mud == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + mid, "userId", "" + uid), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
        }

        if (us.getTasks().size() > 0) {
            Set<Task> userTasksOrg = us.getTasks();
            List<Task> allTk = this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq("module", moduleService.findById(mid)),
                            Restrictions.eq("accessLevel", TtTaskAccessLevel.Grant)
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
            us.setTasks(newMyTk);
            model.addAttribute("tasks", newTk);

        } else {
            model.addAttribute("tasks", this.taskService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq("module", moduleService.findById(mid)),
                            Restrictions.eq("accessLevel", TtTaskAccessLevel.Grant)
                    )));

        }
        model.addAttribute("moduleName", mud.getMessageCode());
        model.addAttribute("user", us);
        model.addAttribute("moduleId", mid);

        return TtTile___.p_user_access_assign.___getDisModel(_PANEL_URL + "/access", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/access", method = RequestMethod.POST)
    public ModelAndView pAccess(HttpServletRequest request,
                                @ModelAttribute("moduleId") String sid,
                                @ModelAttribute("user") User u,
                                BindingResult userBindingResult,
                                final RedirectAttributes redirectAttributes) {
        int mid;
        try {
            mid = Integer.valueOf(sid.trim());
        } catch (Exception e) {
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Failure, notice2s);
        }
        if (u.getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Failure, notice2s);
        } else {
            User dbU = service.findById(u.getId(), User._TASKS, User._TASKS + "." + Task._MODULE);
            if (dbU == null) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.not.found", JsonBuilder.toJson("moduleId", "" + sid), TtNotice.Warning)));
                return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Failure, notice2s);
            } else if (dbU.getIsLogManager()) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.log.manager.not.allowed", TtNotice.Warning)));
                return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Error, notice2s);
            }
            Set<Task> tasks = new HashSet<>();
            for (Task dt : dbU.getTasks()) {
                if (dt.getModule().getIdi() != mid) {
                    tasks.add(dt);
                }
            }
            if (u.getTasks() != null) {
                for (Task ut : u.getTasks()) {
                    tasks.add(ut);
                }
            }
            dbU.setTasks(tasks);
            this.service.update(dbU);
            SessionListener.invalidate(dbU.getId());
        }
        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.access.success", u.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/access/" + u.getId() + "/" + mid, TtTaskActionSubType.Change_User_Access, TtTaskActionStatus.Failure, notice2s);
    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* User Re-Signin For Two Level Confirm
    @PersianName("ورود مجدد برای عملیات های سطح دو")
    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @RequestMapping(value = _PANEL_URL + "/reSignin", method = RequestMethod.POST)
    public ModelAndView pReSignin(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session,
                                  @RequestParam(value = "taskSignature") String taskSignature,
                                  @RequestParam(value = "reSignUrl") String reSignUrl,
                                  @RequestParam(value = "userPass") String userPass,
                                  @RequestParam(value = "captchaCode", required = false, defaultValue = "false") String captchaCode,
                                  final RedirectAttributes redirectAttributes) throws UnknownHostException {

        //================================================== Verification && Recaptcha

        String uuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
        UserAttempt uatt;
        User user = (User) session.getAttribute("sUser");
        if (user == null) {
            return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel();
        }

        uatt = this.userAttemptService.findBy(Restrictions.and(
                Restrictions.eq(UserAttempt._USER, user),
                Restrictions.eq(UserAttempt.ATTEMPT_TYPE, TtUserAttemptType.ReSignin)));

        switch (this.userAttemptService.attemptStatus(request, response, model, user, TtUserAttemptType.Signin, uatt, uuid)) {
            case IsBlock:
                return TtTile___.p_user_reSignin.___getDisModel();
            case UuidNotValid:
            case NeedRecaptcha:
                break;
        }
        //-----------------------------------------------------------------------------
        if (userPass == null || userPass.isEmpty() || taskSignature == null || taskSignature.isEmpty()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.reSignin.failed", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
        }
        User aUser = service.authenticateE(user.getUsername(), Digester.digestSHA1(userPass));
        if (aUser == null) {
            //=================================================== Rebuild User Attempt
            this.userAttemptService.rebuildUerAttempt(request, response, TtUserAttemptType.ReSignin, uuid, user);
            //------------------------------------------------------------------------

            Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.reSignin.pass.wrong", user.getSecretNote(), TtNotice.Warning)));
            return TtTile___.p_user_reSignin.___getDisModel("/panel/user/reSignin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure)
                    .addObject("taskSignature", taskSignature)
                    .addObject("reSignUrl", reSignUrl);
        }

        //======================  Confirm
        UserConfirm uc = userConfirmService.findBy(Restrictions.and(
                Restrictions.eq(UserConfirm._USER, user),
                Restrictions.eq(UserConfirm.TASK_SIGNATURE, taskSignature)
        ));
        if (uc == null) {
            uc = new UserConfirm();
            uc.setUser(user);
            uc.setTaskSignature(taskSignature);
            uc.setConfirmDateTime(ParsCalendar.getInstance().getShortDateTime());
            uc.setConfirmDateTimeG(new Date().getTime());
            uc.setCallCount(1);
            userConfirmService.save(uc);
        } else {
            uc.setConfirmDateTime(ParsCalendar.getInstance().getShortDateTime());
            uc.setConfirmDateTimeG(new Date().getTime());
            uc.setCallCount(1);
            userConfirmService.update(uc);
        }
        //============================= Refresh User Attempt
        if (uatt != null) {
            uatt.refreshDateTime();
            uatt.setCount(0);
            this.userAttemptService.update(uatt);
        }
        //------------------------------------------------------------------------

        return Referer.redirect(reSignUrl, TtTaskActionSubType.Success_Login, TtTaskActionStatus.Success);
    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* OTHERS
    @PersianName("حذف کاربر")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id, HttpSession session) {

        User dbus = this.service.findById(id, User._TASKS);
        if (dbus == null) {
            return Ison.init(TtTaskActionSubType.delete_User, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id)))
                    .toResponse();
        }
        //----------- verify super admin
        User suser = (User) session.getAttribute("sUser");
        if (suser == null) {
            return Ison.init(TtTaskActionSubType.delete_User, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id)))
                    .toResponse();
        }
        if (!suser.getIsSuperAdmin() && (dbus.getIsSuperAdmin() || dbus.getIsLogManager())) {
            return Ison.init(TtTaskActionSubType.delete_User, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N1.user.trash.impossible", dbus.getSecretNote(), TtNotice.Danger, dbus.getFullName()))
                    .toResponse();
        }
        String fullName = dbus.getFullName();
        dbus.setTasks(null);
        dbus.setEntityState(TtEntityState.Trash);
        this.service.update(dbus);
        SessionListener.invalidate(id);
        return Ison.init(TtTaskActionSubType.delete_User, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", dbus.getSecretNote(), TtNotice.Success, fullName))
                .toResponse();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////  FRONT

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*  SIGNOUT
    @OverChangePassword
    @Front
    @OverActiveTask
    @TaskAccessLevel
    @OverDevelopingMode
    @PersianName("خروج")
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public ModelAndView fSignOut(
            HttpSession session,
            HttpServletResponse response,
            HttpServletRequest request) {
        this.service.logout(request, response, session);
        SessionListener.invalidate(session.getId());
        return Referer.redirect("/", TtTaskActionSubType.User_LoginLogout, TtTaskActionStatus.Success);
    }

    @OverActiveTask
    @Front
    @TaskAccessLevel
    @OverDevelopingMode
    @PersianName("ورود")
    @RequestMapping(value = "/signin")
    public ModelAndView fSignin(Model model,
                                @RequestParam(value = "limit", required = false) String limit,
                                @RequestParam(value = "inactive", required = false) String inactive,
                                HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnknownHostException {


        if (session.getAttribute("sUser") != null) {
            return Referer.redirect("/");
        }

        if (limit != null) {
            Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.signin.limit", TtNotice.Warning)));
        }
        if (inactive != null) {
            Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.signin.inactive", TtNotice.Warning)));
        }
        //====================================================== Attempt Status
        this.userAttemptService.attemptStatus(request, response, model, null, TtUserAttemptType.Signin);
        //---------------------------------------------------------------------
        return TtTile___.f_user_signin.___getDisModel(TtTaskActionSubType.Success_Login, TtTaskActionStatus.Success);
    }

    @Front
    @TaskAccessLevel
    @OverDevelopingMode
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ModelAndView fSignin(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "password", required = false) String password,
                                @RequestParam(value = "force", required = false) String isForceLogin,
                                @RequestParam(value = "captchaCode", required = false) String captchaCode,
                                final RedirectAttributes redirectAttributes) throws UnknownHostException {

        //================================================== Verification && Recaptcha

        String uuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
        UserAttempt uatt;

        User user = this.service.findByUsername(username);


        uatt = this.userAttemptService.findBy(Restrictions.and(
                Restrictions.eq(UserAttempt.COMPUTER_SIGNATURE, request.getRemoteAddr()),
                Restrictions.isNull(UserAttempt._USER),
                Restrictions.eq(UserAttempt.ATTEMPT_TYPE, TtUserAttemptType.Signin)));

        if (uatt == null && user != null) {
            uatt = this.userAttemptService.findBy(Restrictions.and(
                    Restrictions.eq(UserAttempt._USER, user),
                    Restrictions.eq(UserAttempt.ATTEMPT_TYPE, TtUserAttemptType.Signin)));
        }

        switch (this.userAttemptService.attemptStatus(request, response, model, user, TtUserAttemptType.Signin, uatt, uuid)) {
            case IsBlock:
                return TtTile___.f_user_signin.___getDisModel();
            case UuidNotValid:
            case NeedRecaptcha:
                if (captchaCode == null || captchaCode.isEmpty()) {
                    //----------------------------
                    Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.are.you.robot", TtNotice.Warning)));
                    return TtTile___.f_user_signin.___getDisModel(TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure);
                }
                SimpleCaptcha captcha = SimpleCaptcha.load(request, "exampleCaptcha");
                boolean isHuman;

                isHuman = captcha
                        .validate
                                (captchaCode);

                if (!isHuman) {
                    //----------------------------
                    Notice2.initModelAttr(model, Notice2.addNotices(new Notice2("N.user.are.you.robot", TtNotice.Warning)));
                    return TtTile___.f_user_signin.___getDisModel(TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure);
                }
                break;
        }
        //-----------------------------------------------------------------------------
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.failed", TtNotice.Warning)));
            return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
        }
        user = service.authenticateE(username, Digester.digestSHA1(password));
        if (user == null) {
            user = service.findByUsername(username);
            //=================================================== Rebuild User Attempt
            this.userAttemptService.rebuildUerAttempt(request, response, TtUserAttemptType.Signin, uuid, user);
            //------------------------------------------------------------------------

            //=================================================== Signin Log
            if (user != null) {
                this.signinLogService.persistSigninLog(request, uuid, user, TtSigninLogStatus.Failed);
            }
            //------------------------------------------------------------------------

            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.pass.wrong", JsonBuilder.toJson("pswd", password, "username", username), TtNotice.Warning)));
            return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
        }

        if (SessionListener.isUserInSession(user.getIdi())) {
            if (isForceLogin != null) {
                SessionListener.invalidate(user.getId());
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.previous.signed.out.try.again", user.getSecretNote(), TtNotice.Info)));
                return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
            } else {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.you.are.signedIn.in.other.browser", user.getSecretNote(), TtNotice.Warning)));
                redirectAttributes.addFlashAttribute("username", username);
                redirectAttributes.addFlashAttribute("password", password);
                redirectAttributes.addFlashAttribute("isForceToLogin", true);
                return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Success, notice2s);
            }
        }

        if (SessionListener.getCurrentUserCount() >= PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxConcurrentUser)) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.max.concurrent.user.exceed", user.getSecretNote(), TtNotice.Info)));
            return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);

        }

        //======================  Login
        user = service.updateUserSession(session, user);

        //============================= Refresh User Attempt
        if (uatt != null) {
            uatt.refreshDateTime();
            uatt.setCount(0);
            this.userAttemptService.update(uatt);
        }
        if (uatt != null && uatt.getUser() == null) {
            uatt = this.userAttemptService.findBy(Restrictions.and(
                    Restrictions.eq(UserAttempt._USER, user),
                    Restrictions.eq(UserAttempt.ATTEMPT_TYPE, TtUserAttemptType.Signin)));
            if (uatt != null) {
                uatt.refreshDateTime();
                uatt.setCount(0);
                this.userAttemptService.update(uatt);
            }
        }

        if (user.getIpRangeType() != null) {
            switch (user.getIpRangeType()) {
                case FirstSignin:
                    if (user.getIpAddressFirstSignin() != null
                            && !user.getIpAddressFirstSignin().equals(request.getRemoteAddr())) {
                        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.ip.is.not.allowed", user.getSecretNote(), TtNotice.Danger)));
                        SessionListener.invalidate(user.getId());
                        return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
                    }
                    break;
                case One:
                    if (user.getIpAddress() == null || !user.getIpAddress().equals(request.getRemoteAddr())) {
                        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.ip.is.not.allowed", user.getSecretNote(), TtNotice.Danger)));
                        SessionListener.invalidate(user.getId());
                        return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
                    }
                    break;
                case All:
                    break;
                case Range:
                    String ipp = request.getRemoteAddr();
                    if (ipp != null
                            && user.getIpAddressEnd() != null
                            && user.getIpAddressStart() != null) {
                        if (ipp.compareTo(user.getIpAddressStart()) == -1
                                || ipp.compareTo(user.getIpAddressEnd()) == 1) {
                            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.ip.is.not.allowed", user.getSecretNote(), TtNotice.Danger)));
                            SessionListener.invalidate(user.getId());
                            return Referer.redirect("/signin", TtTaskActionSubType.Unsuccess_Login, TtTaskActionStatus.Failure, notice2s);
                        }
                    }
                    break;
            }
        }

        //=================================================== Show Signin Log
        List<SigninLog> signinLogList = new ArrayList<>();
        List<SigninLog> signinLogs = this.signinLogService.findAllBy(Restrictions.and(
                Restrictions.eq(SigninLog._USER, user)), 20, Order.desc(SigninLog.LAST_DATE_TIME_G));

        if (signinLogs != null && signinLogs.size() > 0) {
            for (SigninLog s : signinLogs) {
                if (s.getStatus() == TtSigninLogStatus.Success) {
                    signinLogList.add(s);
                    break;
                }
            }
            for (SigninLog s : signinLogs) {
                if (s.getStatus() == TtSigninLogStatus.Success) {
                    break;
                }
                signinLogList.add(s);
            }

            CacheStatic.setSigninLog(user.getId(), signinLogList);

        }
        //=================================================== Signin Log
        this.signinLogService.persistSigninLog(request, uuid, user, TtSigninLogStatus.Success);

        //=================================================== Set Signin Date time in User
        if (user.getPasswordDateTime() != null) {
            int dayPeriod = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordPeriodToChange);

            int i = ParsCalendar.getInstance().durationOnTheDayI(user.getPasswordDateTime(), ParsCalendar.getInstance().getShortDateTime());
            if (i > dayPeriod) {
                user.setIsNeedToChangePassword(true);
            } else {
                user.setIsNeedToChangePassword(false);
            }
        }

        //=================================================== set first ip address

        if (user.getIpRangeType() == TtUserIpRangeType.FirstSignin && user.getIpAddressFirstSignin() == null) {
            user.setIpAddressFirstSignin(request.getRemoteAddr());
        }
        //------------------------------------------------------------------------

        user.setLastSigninDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.service.update(user);

        //------------------------------------------------------------------------

        try {
            redirectAttributes.addFlashAttribute("signinNotice", PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.SigninNotice).replaceAll("\r\n|\n", "<br/>"));
        } catch (Exception e) {
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
        }

        //=================================================== show irror notify list for admin users
        if (user.getIsAdmin()) {
            List<IrrorNotify> irrorNotifyList = this.irrorNotifyService.findAllBy(
                    Restrictions.and(
                            Restrictions.eq(IrrorNotify.STATUS, TtIrrorNotifyStatus.New),
                            Restrictions.eq(IrrorNotify._USER, user)
                    ),
                    IrrorNotify._USER, IrrorNotify._IRROR
            );
            for (IrrorNotify notify : irrorNotifyList) {
                notify.setStatus(TtIrrorNotifyStatus.Visited);
                irrorNotifyService.update(notify);
            }
            redirectAttributes.addFlashAttribute("inlist", irrorNotifyList.isEmpty() ? null : irrorNotifyList);
        }



        //------------------------------


        UiBag bag = uiBagService.findBy(
                Restrictions.eq(UiBag._USER, user)
        );
        if (bag != null) {
            SessionListener.refreshUiSetting(bag.getFont().getKey(), bag.getStyle().getKey(), user.getId());
        }

        return Referer.redirect("/panel", TtTaskActionSubType.Success_Login, TtTaskActionStatus.Success);
    }
}
