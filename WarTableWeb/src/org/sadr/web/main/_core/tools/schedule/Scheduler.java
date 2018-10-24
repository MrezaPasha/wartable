package org.sadr.web.main._core.tools.schedule;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr.web.main._core.propertor.PropertorInBackup;
import org.sadr.web.main._core.propertor.PropertorInControl;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInBackupList;
import org.sadr.web.main._core.propertor._type.TtPropertorInControlList;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools.driver.remoteLogger.RemoteLogger;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtBackupType;
import org.sadr.web.main.system._type.TtLogOnlineSendStatus;
import org.sadr.web.main.system.backup.Backup;
import org.sadr.web.main.system.backup.BackupService;
import org.sadr.web.main.system.log.daily.DailyLogService;
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
    private BackupService backupService;

    @Autowired
    public void setBackupService(BackupService backupService) {
        this.backupService = backupService;
    }

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

    private void startBackup() {
        OutLog.pl("start backup...");
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForClient);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForGuests);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForMasters);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForAdmins);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.backupService.backup(TtBackupType.Scheduling, false);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForClient);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForGuests);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForMasters);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForAdmins);

    }

    private void backupCheck() {
        if (PropertorInBackup.getInstance().isOnProperty(TtPropertorInBackupList.AutoBackupOn)) {
            String day = PropertorInBackup.getInstance().getProperty(TtPropertorInBackupList.AutoBackupDay);
            if (day != null && !day.isEmpty()) {
                String[] split = day.split(",");
                int dayI = ParsCalendar.getInstance().getDayI();
                boolean isInDay = false;
                for (String s : split) {
                    try {
                        if (dayI == Integer.valueOf(s)) {
                            isInDay = true;
                            break;
                        }
                    } catch (Exception e) {
                    }
                }
                if (isInDay) {
                    String hm = PropertorInBackup.getInstance().getProperty(TtPropertorInBackupList.AutoBackupHourMin);
                    if (hm != null && !hm.isEmpty()) {
                        split = hm.split(":");
                        int h, m;
                        h = m = 0;
                        if (split.length > 0) {
                            try {
                                h = Integer.parseInt(split[0]);
                            } catch (Exception e) {
                            }
                        }
                        if (split.length > 1) {
                            try {
                                m = Integer.parseInt(split[1]);
                            } catch (Exception e) {
                            }
                        }

                        if (ParsCalendar.getInstance().getHoursI() == h && ParsCalendar.getInstance().getMinutesI() == m) {
                            startBackup();
                        }
                    }
                }

            }

        }
    }

    private void startRestore() {
        OutLog.pl("start restore...");

        Backup backup = this.backupService.findById(
                PropertorInBackup.getInstance().getPropertyInt(TtPropertorInBackupList.AutoRestoreBackupId)
                , Backup._FILE);

        if (backup == null) {
            return;
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForClient);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForGuests);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForMasters);
        PropertorInControl.getInstance().setOn(TtPropertorInControlList.SiteInDevelopingForAdmins);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //========= auto backup
        this.backupService.backup(TtBackupType.BeforeRestore, false);
        //--------------

        String restore = this.backupService.restore(backup);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (restore != null) {
            PropertorInBackup.getInstance().setOff(TtPropertorInBackupList.AutoRestoreOn);
            PropertorInBackup.getInstance().setProperty(TtPropertorInBackupList.AutoRestoreBackupId, "0");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForClient);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForGuests);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForMasters);
        PropertorInControl.getInstance().setOff(TtPropertorInControlList.SiteInDevelopingForAdmins);
    }

    private void restoreCheck() {
        if (PropertorInBackup.getInstance().isOnProperty(TtPropertorInBackupList.AutoRestoreOn)) {
            String hm = PropertorInBackup.getInstance().getProperty(TtPropertorInBackupList.AutoRestoreHourMin);
            if (hm != null && !hm.isEmpty()) {
                String[] split = hm.split(":");
                int h, m;
                h = m = 0;
                if (split.length > 0) {
                    try {
                        h = Integer.parseInt(split[0]);
                    } catch (Exception e) {
                    }
                }
                if (split.length > 1) {
                    try {
                        m = Integer.parseInt(split[1]);
                    } catch (Exception e) {
                    }
                }
                if (ParsCalendar.getInstance().getHoursI() == h && ParsCalendar.getInstance().getMinutesI() == m) {
                    startRestore();
                }
            }
        }


    }

    @Scheduled(cron = "0 * * * * *")
    public void everyMinuteSchedule() {
        //-------
        backupCheck();

        //-------
        restoreCheck();

    }

    @Scheduled(cron = "*/5 * * * * *")
    public void every5SecondSchedule() {
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
