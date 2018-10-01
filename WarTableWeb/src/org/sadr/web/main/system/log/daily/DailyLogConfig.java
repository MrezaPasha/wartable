package org.sadr.web.main.system.log.daily;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class DailyLogConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DailyLogDaoImp dailyLogDaoImp() {
        DailyLogDaoImp udi = new DailyLogDaoImp();
        return udi;
    }

    @Bean
    public DailyLogServiceImp dailyLogServiceImp() {
        DailyLogServiceImp usi = new DailyLogServiceImp();
        usi.setDao(dailyLogDaoImp());
        return usi;
    }

    @Bean
    public DailyLogController dailyLogController() {
        DailyLogController uc = new DailyLogController();
        uc.setService(dailyLogServiceImp());
        return uc;
    }
}
