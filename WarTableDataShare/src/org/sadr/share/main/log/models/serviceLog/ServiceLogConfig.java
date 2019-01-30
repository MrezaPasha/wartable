package org.sadr.share.main.log.models.serviceLog;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class ServiceLogConfig extends ShareConfig {

    @Bean
    public ServiceLogDaoImp serviceLogDaoImp()
    {
        ServiceLogDaoImp serviceLogDaoImp = new ServiceLogDaoImp();
        return serviceLogDaoImp;

    }

    @Bean
    public ServiceLogServiceImp serviceLogServiceImp()
    {
        ServiceLogServiceImp serviceLogServiceImp = new ServiceLogServiceImp();
        serviceLogServiceImp.setDao(serviceLogDaoImp());
        serviceLogServiceImp.setServiceLogServiceImp(new ServiceLogServiceImp());
        serviceLogServiceImp.setServiceUserServiceImp(new ServiceUserServiceImp());
        serviceLogServiceImp.setSessionsServiceImp(new SessionsServiceImp());
        serviceLogServiceImp.setBaseConfigServiceImp(new BaseConfigServiceImp());
        return serviceLogServiceImp;
    }
}
