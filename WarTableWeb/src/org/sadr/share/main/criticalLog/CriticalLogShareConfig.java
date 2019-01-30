package org.sadr.share.main.criticalLog;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class CriticalLogShareConfig extends WebConfig {

    @Bean
    public CriticalLogDaoImp criticalLogDaoImp() {
        CriticalLogDaoImp udi = new CriticalLogDaoImp();
        return udi;
    }

    @Bean
    public CriticalLogShareServiceImp criticalLogShareServiceImp() {
        CriticalLogShareServiceImp usi = new CriticalLogShareServiceImp();
        usi.setDao(criticalLogDaoImp());
        return usi;
    }

    @Bean
    public CriticalLogShareController criticalLogShareController() {
        CriticalLogShareController uc = new CriticalLogShareController();
        uc.setService(criticalLogShareServiceImp());
        return uc;
    }
}
