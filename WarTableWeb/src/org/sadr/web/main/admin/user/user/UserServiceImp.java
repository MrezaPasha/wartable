package org.sadr.web.main.admin.user.user;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Cookier;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupService;
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


        OutLog.pl();
        session.removeAttribute("sUser");
        Enumeration<String> ss = session.getAttributeNames();
        while (ss.hasMoreElements()) {
            String s = ss.nextElement();
            session.removeAttribute(s);
        }
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

    ////----------------- LOGOUT



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
