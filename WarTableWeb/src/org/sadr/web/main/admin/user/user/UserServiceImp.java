package org.sadr.web.main.admin.user.user;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Cookier;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupService;
import org.sadr.web.main.admin.user.uuid.UserUuid;
import org.sadr.web.main.admin.user.uuid.UserUuidService;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * @author masoud
 */
@Service
//@Component
public class UserServiceImp extends GenericServiceImpl<User, UserDao> implements UserService {

    private UserUuidService userUuidService;
    private UserGroupService userGroupService;
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Autowired
    public void setUserUuidService(UserUuidService userUuidService) {
        this.userUuidService = userUuidService;
    }

    /////////////////////////////////////////////////////=====

    ////----------------- UPDATE USER SESSION
    @Override
    public User updateUserSession(HttpSession session, User user) {
        if (user == null) {
            session.removeAttribute("sUser");
            session.removeAttribute("userMenu");
            return null;
        }
        if (user.getIsLogManager() && !user.getIsSuperAdmin()) {
            session.setAttribute("sUser", user);
            List<Task> tasks = this.taskService.findAllBy(Restrictions.and(
                    Restrictions.eq(Task.IS_LOG_MANAGER, true),
                    Restrictions.eq(Task.IS_ACTIVE, true)
            ));
            String autoMenu = "";
            for (Task t : tasks) {
                autoMenu += t.getMenuIdentity() + ",";
            }
            session.setAttribute("userMenu", autoMenu.isEmpty() ? "" : autoMenu.substring(0, autoMenu.length() - 1));
            return user;

        }
        if (!user.getIsMaster()) {
            session.setAttribute("sUser", user);
            session.removeAttribute("userMenu");
            return user;
        }
        Hibernate.initialize(user.getTasks());
        Hibernate.initialize(user.getUserGroups());

        String autoMenu = "";
        for (UserGroup ug : user.getUserGroups()) {
            UserGroup group = this.userGroupService.findById(ug.getId(), User._TASKS);
            autoMenu += group.getAuthorizedMenu();
        }
        autoMenu += user.getAuthorizedMenu();
        session.setAttribute("userMenu", autoMenu.isEmpty() ? "" : autoMenu.substring(0, autoMenu.length() - 1));
        session.setAttribute("sUser", user);
        return user;
    }

    ////----------------- AUTHENTICATION
    @Override
    public User authenticateE(String username, String password) {
        return dao.authenticateE(username, password);
    }

    @Override
    public User authenticateEAndLogin(String username, String password, HttpSession session) {
        User u = dao.authenticateE(username, password);
        if (u == null) {
            return null;
        }
        updateUserSession(session, u);
        return u;
    }

    @Override
    public boolean authenticateE(String username) {
        return dao.authenticateE(username);
    }

    ////----------------- AUTO LOGIN
    @Override
    public User autoLogin(HttpServletRequest request) {
        String uuid = Cookier.getValue(request, TtCookierVariable.UserAutoLoginUUID.getKey());
        if (uuid == null) {
            return null;
        }
        UserUuid uu = this.userUuidService.findBy(Restrictions.eq(UserUuid.UUID, uuid), UserUuid._USER);
        if (uu == null) {
            return null;
        }
        // security check
        if (!uu.isPassSecureCheck(request)) {
            return null;
        }
        return updateUserSession(request.getSession(), findById(uu.getUser().getId()));
    }

    @Override
    public User autoLogin(HttpServletRequest request, User suser) {
        if (suser == null) {
            return null;
        }
        return updateUserSession(request.getSession(), findById(suser.getId()));
    }

    @Override
    public User autoLogin(HttpServletRequest request, int userId) {
        if (userId < 1) {
            return null;
        }
        return updateUserSession(request.getSession(), findById(userId));
    }

    ////----------------- LOGOUT

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User suser = (User) session.getAttribute("sUser");
        if (suser == null) {
            return;
        }
        User duser;
        try {
            duser = this.findById(suser.getId(), User._USER_UUIDS);
        } catch (Exception e) {
            duser = null;
        }
        if (duser == null) {
            session.removeAttribute("sUser");
            Enumeration<String> ss = session.getAttributeNames();
            while (ss.hasMoreElements()) {
                String s = ss.nextElement();
                session.removeAttribute(s);
            }
            return;
        }

        String value = Cookier.getValue(request, TtCookierVariable.UserAutoLoginUUID.getKey());
        if (value != null && !value.isEmpty()) {
            UserUuid uui = userUuidService.findBy(Restrictions.and(
                    Restrictions.eq(UserUuid._USER, duser),
                    Restrictions.eq(UserUuid.UUID, value)));
            if (uui != null) {
                this.userUuidService.deleteUUID(uui.getUuid());
            }
        }
        Cookier.deleteCookie(response, TtCookierVariable.UserAutoLoginUUID.getKey());

        session.removeAttribute("sUser");
        Enumeration<String> ss = session.getAttributeNames();
        while (ss.hasMoreElements()) {
            String s = ss.nextElement();
            session.removeAttribute(s);
        }
    }

    @Override
    public void logoutAll(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User suser = (User) session.getAttribute("sUser");
        if (suser == null) {
            return;
        }
        User duser;
        try {
            duser = this.findById(suser.getId(), User._USER_UUIDS);
        } catch (Exception e) {
            duser = null;
        }
        if (duser == null) {
            session.removeAttribute("sUser");
            Enumeration<String> ss = session.getAttributeNames();
            while (ss.hasMoreElements()) {
                String s = ss.nextElement();
                session.removeAttribute(s);
            }
            return;
        }

        userUuidService.deleteAllBy(0163, duser.getUserUuids());

        Cookier.deleteCookie(response, TtCookierVariable.UserAutoLoginUUID.getKey());

        session.removeAttribute("sUser");
        SessionListener.invalidate(duser.getId());
        Enumeration<String> ss = session.getAttributeNames();
        while (ss.hasMoreElements()) {
            String s = ss.nextElement();
            session.removeAttribute(s);
        }

    }

    @Override
    public void logoutAll(User user) {
        User duser;
        try {
            duser = this.findById(user.getId(), User._USER_UUIDS);
        } catch (Exception e) {
            return;
        }
        userUuidService.deleteAllBy(0163, duser.getUserUuids());

        SessionListener.invalidate(duser.getId());
    }

    ////----------------- GO TO
    @Override
    public ModelAndView goToSignin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Cookier.setCookie(response, TtCookierVariable.ReturnUrlAfterSignin, request.getServletPath() + "?" + request.getQueryString());
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.signin.for.continue", JsonBuilder.toJson("path", request.getServletPath() + "?" + request.getQueryString()), TtNotice.Warning)));
        redirectAttributes.addFlashAttribute("isShowRegister", true);
        return new ModelAndView("redirect:/signin");
    }

    @Override
    public boolean isAccessLimitPassed(User user) {
        int dayI = ParsCalendar.getInstance().getDayI();
        int yearI = ParsCalendar.getInstance().getYearI();
        int monthI = ParsCalendar.getInstance().getMonthI();
        String shortDateTime = ParsCalendar.getInstance().getShortDateTime();
        if (user.getAccessLimitYearlyStart() != null
                && !user.getAccessLimitYearlyStart().isEmpty()
                && ParsCalendar.getInstance().compareDateTime(user.getAccessLimitYearlyStart(), shortDateTime) == TtCompareResult.FirstIsBigger
                ) {
            return true;
        }
        if (user.getAccessLimitYearlyEnd() != null
                && !user.getAccessLimitYearlyEnd().isEmpty()
                && ParsCalendar.getInstance().compareDateTime(user.getAccessLimitYearlyEnd(), shortDateTime) == TtCompareResult.SecondIsBigger
                ) {
            return true;
        }
        if (user.getAccessLimitMonthlyStart() != 0
                && user.getAccessLimitMonthlyStart() > monthI
                ) {
            return true;
        }
        if (user.getAccessLimitMonthlyEnd() != 0
                && user.getAccessLimitMonthlyEnd() < monthI
                ) {
            return true;
        }
        if (user.getAccessLimitDailyStart() != 0
                && user.getAccessLimitDailyStart() > dayI
                ) {
            return true;
        }
        if (user.getAccessLimitDailyEnd() != 0
                && user.getAccessLimitDailyEnd() < dayI
                ) {
            return true;
        }

        String shortTime = ParsCalendar.getInstance().getShortTime();

        if (user.getAccessLimitTimelyStart() != null
                && !user.getAccessLimitTimelyStart().isEmpty()
                && ParsCalendar.getInstance().compareTime(user.getAccessLimitTimelyStart(), shortTime) == TtCompareResult.FirstIsBigger
                ) {
            return true;
        }
        if (user.getAccessLimitTimelyEnd() != null
                && !user.getAccessLimitTimelyEnd().isEmpty()
                && ParsCalendar.getInstance().compareDateTime(user.getAccessLimitTimelyEnd(), shortTime) == TtCompareResult.SecondIsBigger
                ) {
            return true;
        }

        return false;
    }

    ////----------------- FIND

    @Override
    public User findByUsername(String username, String... relatedClass) {
        return dao.findByUsername(username, relatedClass);
    }


}
