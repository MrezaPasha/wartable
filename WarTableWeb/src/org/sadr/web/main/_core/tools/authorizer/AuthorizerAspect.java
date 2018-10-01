package org.sadr.web.main._core.tools.authorizer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.Front;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.OverActiveTask;
import org.sadr.web.main._core.meta.annotation.OverChangePassword;
import org.sadr.web.main._core.meta.annotation.OverDevelopingMode;
import org.sadr.web.main._core.propertor.PropertorInControl;
import org.sadr.web.main._core.propertor._type.TtPropertorInControlList;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.confirm.UserConfirmService;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupService;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system._type.TtLogHandler;
import org.sadr.web.main.system.irror.Irror;
import org.sadr.web.main.system.irror.IrrorService;
import org.sadr.web.main.system.log.general.Log;
import org.sadr.web.main.system.log.general.LogService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author 1.95.10.19
 */
@PersianName("اعتبارسنج")
@Aspect
public class AuthorizerAspect {

    public AuthorizerAspect() {
    }

    private TaskService taskService;

    private UserService userService;

    private LogService logService;

    private IrrorService irrorService;

    private UserGroupService userGroupService;

    private UserConfirmService userConfirmService;

    @Autowired
    public void setUserConfirmService(UserConfirmService userConfirmService) {
        this.userConfirmService = userConfirmService;
    }

    @Autowired
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private ModelAndView fillAndView(HttpServletRequest request, ModelAndView andView) {
        return andView;
    }

    private ModelAndView fillNeedTwoLevelConfirm(String taskSignature, HttpServletRequest request) {
        return TtTile___.p_user_reSignin.___getDisModel("/panel/user/reSignin")
                .addObject("taskSignature", taskSignature)
                .addObject("reSignUrl", request.getServletPath() + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));
    }

    private ModelAndView fillPasswordForm(HttpServletRequest request) {
        return Referer.redirect("/panel/user/your-pass?expired=true");
    }

    private ModelAndView guest(HttpServletRequest request, User u, ProceedingJoinPoint joinPoint) throws Throwable {
        //#########(developing mode)######### guest
        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.SiteInDevelopingForGuests)
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverDevelopingMode.class)) {
            return fillAndView(request, TtTile___.f_develop_developing.___getDisModel());
        }
        ModelAndView andView;
        Task task;
        task = taskService.fetchGuestTask(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if (task != null) {
            andView = (ModelAndView) joinPoint.proceed();
            //*********(log)********* guest
            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogGuest)) {
                this.logService.log(new Log(
                        joinPoint,
                        request,
                        "As Guest- Dispatched to <" + andView.getViewName() + ">",
                        TtLogHandler.AuthorizerAspect,
                        task, u));
            }
            return fillAndView(request, andView);
        }
        //*********(log)********* invalid guest
//        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogGuest)) {
//            this.logService.log(new Log(
//                    joinPoint, request,
//                    "As Guest- Dispatched to <" + joinPoint.getSignature().getName() + "> User is null.401. #1 ",
//                    TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                    TtLogLevel.Trace, TtSecurityThreatLevel.Substantial, u));
//        }
        String cname = joinPoint.getSignature().getDeclaringTypeName();
        int ix = cname.lastIndexOf('.') + 1;
        cname = cname.substring(ix, ix + 1).toLowerCase()
                + cname.substring(ix + 1, cname.lastIndexOf("Controller"));
        return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel()
                .addObject("pageTitle", cname + ".c." + joinPoint.getSignature().getName())
                .addObject("returnToUrl", request.getServletPath() + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));

    }

    private ModelAndView client(HttpServletRequest request, User u, ProceedingJoinPoint joinPoint) throws Throwable {
        //#########(developing mode)######### client
        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.SiteInDevelopingForClient)
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverDevelopingMode.class)) {
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to <f.developing>",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, TtTile___.f_develop_developing.___getDisModel());
        }
        if (userService.isAccessLimitPassed(u)) {
            //*********(log)********* limit client
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to < Access Limit Time>.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?limit=true");
        }
        //==========================  CHANGE PASSWORD
        if (u.getIsNeedToChangePassword() == true
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverChangePassword.class)) {
            //*********(log)********* change password client
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to < change password >.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillPasswordForm(request);

        }

        //==========================  BLOCKED CLIENT
        if (u.getIsBlocked() == true) {
            //*********(log)********* blocked client
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is blocked.403.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Substantial, u));
//            }
            return TtHttpErrorCode___.Forbidden_403.___getFrontDisModel();
        }

        //==========================  DEACTIVED CLIENT
        if (u.getStatus() == TtUserStatus.Deactive
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverActiveTask.class)) {
            //*********(log)********* diactived client
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is deactived.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//            }

            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?inactive=true");
        }

        ModelAndView andView;
        String taskSignature = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (taskService.authorizeGuest(taskSignature)) {
            andView = (ModelAndView) joinPoint.proceed();
            //*********(log)********* guest
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Client- Dispatched to <" + andView.getViewName() + ">",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, andView);
        }
        if (taskService.authorizeUser(taskSignature)) {
            if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                //*********(log)********* guest
//                if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                    this.logService.log(new Log(
//                            joinPoint, request,
//                            "As Client- Dispatched to < ReSignin >",
//                            TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                            TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                }
                return fillNeedTwoLevelConfirm(taskSignature, request);
            }

            andView = (ModelAndView) joinPoint.proceed();
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "Client Activity: Dispatched to <" + andView.getViewName() + ">",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, andView);
        }
        return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel()
                .addObject("returnToUrl", request.getServletPath() + request.getQueryString() == null ? "" : ("?" + request.getQueryString()));
    }

    private ModelAndView master(HttpServletRequest request, User u, ProceedingJoinPoint joinPoint) throws Throwable {
        //#########(developing mode)######### master
        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.SiteInDevelopingForMasters)) {
            //*********(log)********* developing user
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to <f.developing>",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, TtTile___.f_develop_developing.___getDisModel());
        }
        if (userService.isAccessLimitPassed(u)) {
            //*********(log)********* limit master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to < Access Limit Time>.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?limit=true");
        }
        //==========================  CHANGE PASSWORD
        if (u.getIsNeedToChangePassword() == true
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverChangePassword.class)) {
            //*********(log)********* change password master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to < change password >.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillPasswordForm(request);
        }
        //==========================  BLOCKED MASTER
        if (u.getIsBlocked() == true) {
            //*********(log)********* blocked master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is blocked.403.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Substantial, u));
//            }
            return TtHttpErrorCode___.Forbidden_403.___getFrontDisModel();
        }
        //==========================  DEACTIVED MASTER
        if (u.getStatus() == TtUserStatus.Deactive
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverActiveTask.class)) {
            //*********(log)********* diactived master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is deactived.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//            }
            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?inactive=true");
        }

        ModelAndView andView;
        String taskSignature = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (taskService.authorizeGuest(taskSignature)) {
            andView = (ModelAndView) joinPoint.proceed();
            //*********(log)********* guest
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to <" + andView.getViewName() + ">",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, andView);
        }
        if (taskService.authorizeUser(taskSignature)) {
            if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                //*********(log)********* Master
//                if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                    this.logService.log(new Log(
//                            joinPoint, request,
//                            "As Master- Dispatched to < ReSignin >",
//                            TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                            TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                }
                return fillNeedTwoLevelConfirm(taskSignature, request);
            }

            andView = (ModelAndView) joinPoint.proceed();
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "Client Activity: Dispatched to <" + andView.getViewName() + ">",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, andView);
        }
        ///////////////////////////
        for (Task tk : u.getTasks()) {
            if (tk.getSignature().equals(taskSignature)) {
                if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                    //*********(log)********* Master
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "As Master- Dispatched to < ReSignin >",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                    }
                    return fillNeedTwoLevelConfirm(taskSignature, request);
                }

                andView = (ModelAndView) joinPoint.proceed();
                //*********(log)********* master user
//                if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                    this.logService.log(new Log(
//                            joinPoint, request,
//                            "Master Activity: Dispatched to <" + andView.getViewName() + ">",
//                            TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                            TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//                }
                return fillAndView(request, andView);
            }
        }
        for (UserGroup ug : u.getUserGroups()) {
            UserGroup group = this.userGroupService.findById(ug.getId(), UserGroup._TASKS);
            for (Task tk : group.getTasks()) {
                if (tk.getSignature().equals(taskSignature)) {
                    if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                        //*********(log)********* Master
//                        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                            this.logService.log(new Log(
//                                    joinPoint, request,
//                                    "As Master- Dispatched to < ReSignin >",
//                                    TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                    TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                        }
                        return fillNeedTwoLevelConfirm(taskSignature, request);
                    }

                    andView = (ModelAndView) joinPoint.proceed();
                    //*********(log)********* master master
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "Master Group Activity: Dispatched to <" + andView.getViewName() + ">",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Low, u, ug));
//                    }
                    //     OutLog.pl("");
                    return fillAndView(request, andView);
                }
            }
        }
        if (((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(Front.class)) {
            //*********(log)********* master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "Unauthorized Master Front.401. #2",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing, TtLogLevel.Trace,
//                        TtSecurityThreatLevel.Moderate, u));
//            }
            return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel()
                    .addObject("returnToUrl", request.getServletPath() + (request.getQueryString() == null ? "" : "?" + request.getQueryString()));
        } else {
            //*********(log)********* master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogMaster)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "Unauthorized Master panel.401. #3",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//            }
            return TtHttpErrorCode___.Unauthorized_401.___getPanelDisModel();
        }

    }

    private ModelAndView admin(HttpServletRequest request, User u, ProceedingJoinPoint joinPoint) throws Throwable {
        //#########(developing mode)######### admin

        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.SiteInDevelopingForAdmins) && !u.getIsSuperAdmin()) {
            //*********(log)********* developing user
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Admin- Dispatched to <f.developing>",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillAndView(request, TtTile___.f_develop_developing.___getDisModel());
        }
        if (userService.isAccessLimitPassed(u)) {
            //*********(log)********* limit master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogClient)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Master- Dispatched to < Access Limit Time>.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?limit=true");
        }
        //==========================  CHANGE PASSWORD
        if (u.getIsNeedToChangePassword() == true
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverChangePassword.class)) {
            //*********(log)********* change password master
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Admin- Dispatched to < change password >.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//            }
            return fillPasswordForm(request);
        }
        //==========================  BLOCKED ADMIN
        if (u.getIsBlocked() == true) {
            //*********(log)********* blocked admin
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Admin- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is blocked.403.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Substantial, u));
//            }
            return TtHttpErrorCode___.Forbidden_403.___getFrontDisModel();
        }

        //==========================  DEACTIVED ADMIN
        if (u.getStatus() == TtUserStatus.Deactive
                && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverActiveTask.class)) {
            //*********(log)********* diactived admin
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Admin- Dispatched to <" + TtHttpErrorCode___.Forbidden_403.getFrontTile() + "> User is deactived.",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//            }
            SessionListener.invalidate(u.getId());
            return Referer.redirect("/signin?inactive=true");
        }

        String taskSignature = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
            //*********(log)********* Admin
//            if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
//                this.logService.log(new Log(
//                        joinPoint, request,
//                        "As Admin- Dispatched to < ReSignin >",
//                        TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                        TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//            }
            return fillNeedTwoLevelConfirm(taskSignature, request);
        }


        ModelAndView andView = (ModelAndView) joinPoint.proceed();
        //*********(log)*********  admin
        Task task;
        task = taskService.findBy(
                Restrictions.eq(Task.SIGNATURE, joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
        );

        if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogAdmin)) {
            this.logService.log(new Log(
                    joinPoint,
                    request,
                    "As Guest- Dispatched to <" + andView.getViewName() + ">",
                    TtLogHandler.AuthorizerAspect,
                    task, u));
//            this.logService.log(new Log(
//                    joinPoint, request,
//                    "Admin Activity: Dispatched to <" + andView.getViewName() + ">",
//                    TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                    TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
        }
        ///////////////////////////
        return fillAndView(request, andView);
    }

    private ModelAndView catchException(Exception e, ProceedingJoinPoint joinPoint, HttpServletRequest request, TtIrrorPlace place) {
        ModelAndView andView;
        e.printStackTrace();
        if (((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(Front.class)) {
            andView = TtHttpErrorCode___.InternalServerError_500.___getFrontDisModel();
        } else {
            andView = TtHttpErrorCode___.InternalServerError_500.___getPanelDisModel();
        }

        Irror irror = irrorService.submit(e, joinPoint, request, place);
        if (irror != null) {
            andView.addObject("errorMsg", irror.getMessage());
            andView.addObject("errorId", irror.getId());
        } else {
            andView.addObject("errorMsg", e.toString());
        }

        return andView;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.web.servlet.ModelAndView *(..))"
            + " && @annotation(org.sadr.web.main._core.meta.annotation.SuperAdminTask)")
    public ModelAndView superAdminAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User u = (User) request.getSession().getAttribute("sUser");
        try {
            //==========================  AUTO LOGIN
            if (u == null) {
                u = this.userService.autoLogin(request);
            }
            if (u == null) {
                return TtHttpErrorCode___.Forbidden_403.___getFrontDisModel();
            }
            if (u.getIsSuperAdmin()) {
                //==========================  CHANGE PASSWORD
                if (u.getIsNeedToChangePassword() == true
                        && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverChangePassword.class)) {
                    //*********(log)********* change password master
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "As SuperAdmin- Dispatched to < change password >.",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//                    }
                    return fillPasswordForm(request);
                }

                String taskSignature = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
                if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                    //*********(log)********* Admin
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "As SuperAdmin- Dispatched to < ReSignin >",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                    }
                    return fillNeedTwoLevelConfirm(taskSignature, request);
                }

                ModelAndView andView = (ModelAndView) joinPoint.proceed();
                //*********(log)********* superadmin
//                if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                    this.logService.log(new Log(
//                            joinPoint,
//                            request,
//                            "As SuperAdmin in superAdminAspect- Dispatched to <" + andView.getViewName() + ">",
//                            TtLogHandler.AuthorizerAspect,
//                            TtLogInforming.NoThing,
//                            TtLogLevel.Trace,
//                            TtSecurityThreatLevel.Substantial,
//                            u
//                    ));
//                }
                return fillAndView(request, andView);
            }
            if (u.getLevel() != TtUserLevel.Client
                    && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(Front.class)) {
                return TtHttpErrorCode___.Forbidden_403.___getPanelDisModel();
            }
            return TtHttpErrorCode___.NotFound_404.___getFrontDisModel();
        } catch (Exception e) {
            return catchException(e, joinPoint, request, TtIrrorPlace.AspectSuperAdmin);
        }
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.web.servlet.ModelAndView *(..))"
            + " && !@annotation(org.sadr.web.main._core.meta.annotation.SuperAdminTask)"
            + " && !@annotation(org.sadr.web.main._core.meta.annotation.LogManagerTask)"
    )
    public ModelAndView mainAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User u = (User) request.getSession().getAttribute("sUser");
        try {
            if (u == null) {
                u = this.userService.autoLogin(request);
                if (u == null) {
                    return guest(request, u, joinPoint);
                }
            }
            switch (u.getLevel()) {
                case Client:
                default:
                    return client(request, u, joinPoint);
                case Master:
                    return master(request, u, joinPoint);
                case Administrator:
                    return admin(request, u, joinPoint);
            }
        } catch (Exception e) {
            return catchException(e, joinPoint, request, TtIrrorPlace.AspectMain);
        }
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.http.ResponseEntity *(..))"
            + " && @annotation(org.sadr.web.main._core.meta.annotation.SuperAdminTask)")
    public ResponseEntity<String> jsonSuperAdminAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User u = (User) request.getSession().getAttribute("sUser");
        try {
            if (u == null || !u.getIsSuperAdmin()) {
                return null;
            }
            return (ResponseEntity<String>) joinPoint.proceed();

        } catch (Exception e) {
            catchException(e, joinPoint, request, TtIrrorPlace.JsonAspectSuperAdmin);
            return null;
        }
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.http.ResponseEntity *(..))"
            + " && !@annotation(org.sadr.web.main._core.meta.annotation.SuperAdminTask)"
            + " && !@annotation(org.sadr.web.main._core.meta.annotation.LogManagerTask)")
    public ResponseEntity<String> jsonAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
        User u = (User) session.getAttribute("sUser");
        try {

            if (taskService.authorizeGuest(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())) {
                return (ResponseEntity<String>) joinPoint.proceed();
            }
            if (u == null) {
                return null;
            } else {
                if (u.getIsBlocked() == true) {
                    return null;

                }
                if (u.getStatus() == TtUserStatus.Deactive
                        && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverActiveTask.class
                )) {
                    return null;
                }
                if (u.getLevel() == TtUserLevel.Administrator) {
                    return (ResponseEntity<String>) joinPoint.proceed();
                }
                if (taskService.authorizeUser(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())) {
                    return (ResponseEntity<String>) joinPoint.proceed();
                }
                if (u.getLevel() == TtUserLevel.Master) {
                    for (Task tk : u.getTasks()) {
                        if (tk.getSignature().equals(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())) {
                            return (ResponseEntity<String>) joinPoint.proceed();
                        }
                    }
                    for (UserGroup ug : u.getUserGroups()) {
                        UserGroup group = this.userGroupService.findById(ug.getId(), UserGroup._TASKS);
                        for (Task tk : group.getTasks()) {
                            if (tk.getSignature().equals(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())) {
                                return (ResponseEntity<String>) joinPoint.proceed();
                            }
                        }
                    }
                }
                return null;
            }
        } catch (Exception e) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            catchException(e, joinPoint, request, TtIrrorPlace.JsonAspectMain);
            return null;
        }
    }


    ///====================================================== LOG MANAGER
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.web.servlet.ModelAndView *(..))"
            + " && @annotation(org.sadr.web.main._core.meta.annotation.LogManagerTask)")
    public ModelAndView logManagerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User u = (User) request.getSession().getAttribute("sUser");
        try {
            //==========================  AUTO LOGIN
            if (u == null) {
                u = this.userService.autoLogin(request);
            }
            if (u == null) {
                return TtHttpErrorCode___.Forbidden_403.___getFrontDisModel();
            }
            if (u.getIsLogManager()) {
                //==========================  CHANGE PASSWORD
                if (u.getIsNeedToChangePassword() == true
                        && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(OverChangePassword.class)) {
                    //*********(log)********* change password master
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "As SuperAdmin- Dispatched to < change password >.",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Low, u));
//                    }
                    return fillPasswordForm(request);
                }

                String taskSignature = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
                if (taskService.isNeedToConfirm(taskSignature) && !userConfirmService.isConfirmed(u, taskSignature)) {
                    //*********(log)********* Admin
//                    if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                        this.logService.log(new Log(
//                                joinPoint, request,
//                                "As SuperAdmin- Dispatched to < ReSignin >",
//                                TtLogHandler.AuthorizerAspect, TtLogInforming.NoThing,
//                                TtLogLevel.Trace, TtSecurityThreatLevel.Moderate, u));
//                    }
                    return fillNeedTwoLevelConfirm(taskSignature, request);
                }

                ModelAndView andView = (ModelAndView) joinPoint.proceed();
                //*********(log)********* superadmin
//                if (PropertorInControl.getInstance().isOnProperty(TtPropertorInControlList.LogSuperAdmin)) {
//                    this.logService.log(new Log(
//                            joinPoint,
//                            request,
//                            "As SuperAdmin in superAdminAspect- Dispatched to <" + andView.getViewName() + ">",
//                            TtLogHandler.AuthorizerAspect,
//                            TtLogInforming.NoThing,
//                            TtLogLevel.Trace,
//                            TtSecurityThreatLevel.Substantial,
//                            u
//                    ));
//                }
                return fillAndView(request, andView);
            }
            if (u.getLevel() != TtUserLevel.Client
                    && !((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(Front.class)) {
                return TtHttpErrorCode___.Forbidden_403.___getPanelDisModel();
            }
            return TtHttpErrorCode___.NotFound_404.___getFrontDisModel();
        } catch (Exception e) {
            return catchException(e, joinPoint, request, TtIrrorPlace.AspectLogManager);
        }
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " && execution(org.springframework.http.ResponseEntity *(..))"
            + " && @annotation(org.sadr.web.main._core.meta.annotation.LogManagerTask)")
    public ResponseEntity<String> jsonLogManagerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User u = (User) request.getSession().getAttribute("sUser");
        try {
            if (u == null || (!u.getIsSuperAdmin() && !u.getIsLogManager())) {
                return null;
            }
            return (ResponseEntity<String>) joinPoint.proceed();

        } catch (Exception e) {
            catchException(e, joinPoint, request, TtIrrorPlace.JsonAspectSuperAdmin);
            return null;
        }
    }

}
