package org.sadr.share.main.criticalLog;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class CriticalLogConfig extends ShareConfig {

    @Bean
    public CriticalLogDaoImp criticalLogDaoImp()
    {
        CriticalLogDaoImp udi = new CriticalLogDaoImp();
        return udi;
    }

    @Bean
    public CriticalLogServiceImp criticalLogServiceImp()
    {
        CriticalLogServiceImp usi = new CriticalLogServiceImp();
        usi.setDao(criticalLogDaoImp());
        return usi;
    }

}
