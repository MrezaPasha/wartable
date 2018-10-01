package org.sadr.web.main._core.tools.schedule;

import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.system.log.daily.DailyLogConfig;
import org.sadr.web.main.system.log.general.LogConfig;
import org.sadr.web.main.system.log.remote.RemoteLogConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class SchedulerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Scheduler scheduler() {
        Scheduler s = new Scheduler();
        s.setVisitLogService(new DailyLogConfig().dailyLogServiceImp());
        s.setUserService(new UserConfig().userServiceImp());
        s.setRemoteLogService(new RemoteLogConfig().remoteLogServiceImp());
        return s;
    }
}
