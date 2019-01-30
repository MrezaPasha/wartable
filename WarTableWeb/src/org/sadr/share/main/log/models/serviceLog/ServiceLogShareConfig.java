package org.sadr.share.main.log.models.serviceLog;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class ServiceLogShareConfig extends WebConfig {

    @Bean
    public ServiceLogDaoImp serviceLogDaoImp() {
        ServiceLogDaoImp udi = new ServiceLogDaoImp();
        return udi;
    }

    @Bean
    public ServiceLogShareServiceImp serviceLogShareServiceImp() {
        ServiceLogShareServiceImp usi = new ServiceLogShareServiceImp();
        usi.setDao(serviceLogDaoImp());
        return usi;
    }

    @Bean
    public ServiceLogShareController serviceLogShareController() {
        ServiceLogShareController uc = new ServiceLogShareController();
        uc.setService(serviceLogShareServiceImp());
        return uc;
    }
}
