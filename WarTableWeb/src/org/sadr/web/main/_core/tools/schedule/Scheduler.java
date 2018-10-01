package org.sadr.web.main._core.tools.schedule;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools.driver.remoteLogger.RemoteLogger;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.CacheStatic;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtLogOnlineSendStatus;
import org.sadr.web.main.system.log.daily.DailyLog;
import org.sadr.web.main.system.log.daily.DailyLogService;
import org.sadr.web.main.system.log.general.Log;
import org.sadr.web.main.system.log.general.LogService;
import org.sadr.web.main.system.log.remote.RemoteLog;
import org.sadr.web.main.system.log.remote.RemoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * @author MSD
 */
@PersianName("زمان شمار")
public class Scheduler {

    private DailyLogService visitLogService;
    private UserService userService;
    private RemoteLogService remoteLogService;

    @Autowired
    public void setRemoteLogService(RemoteLogService remoteLogService) {
        this.remoteLogService = remoteLogService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setVisitLogService(DailyLogService visitLogService) {
        this.visitLogService = visitLogService;
    }


    @Scheduled(cron = "0 55 * * * *")
    public void hourlySchedule() {
        //========================== deactivate users
        if (PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.UserDeactivateOn)) {
            int range = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserDeactivateTimeout);

            List<User> userList = userService.findAllBy(
                    Restrictions.le(User.LAST_SIGNIN_DATE_TIME, ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Day, -range))
            );
            for (User user : userList) {
                user.setStatus(TtUserStatus.Deactive);
                this.userService.update(user);
            }
        }
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void every5SecondSchedule() {
//        OutLog.pl();
        List<RemoteLog> relogs = remoteLogService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(RemoteLog.SEND_STATUS, TtLogOnlineSendStatus.NotSend),
                        Restrictions.ge(RemoteLog.SEND_DATE_TIME_G, new Date().getTime() - 8640000000l),
                        Restrictions.le(RemoteLog.SEND_DATE_TIME_G, new Date().getTime() + 2000)
                )
        );

        for (RemoteLog l : relogs) {
            l.setSendStatus(TtLogOnlineSendStatus.Sending);
            remoteLogService.update(l);
            OutLog.p("SENDING UPDATE: " + l.getId() + "  " + l.getSendStatus());
        }
        try {
            RemoteLogger.getInstance().send(relogs);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
