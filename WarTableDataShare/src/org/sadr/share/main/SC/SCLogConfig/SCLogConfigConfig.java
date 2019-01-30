package org.sadr.share.main.SC.SCLogConfig;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class SCLogConfigConfig extends ShareConfig {

    @Bean
    public SCLogConfigDaoImp scLogConfigDaoImp()
    {
        SCLogConfigDaoImp udi = new SCLogConfigDaoImp();
        return udi;
    }

    @Bean
    public SCLogConfigServiceImp scLogConfigServiceImp()
    {
        SCLogConfigServiceImp usi = new SCLogConfigServiceImp();
        usi.setDao(scLogConfigDaoImp());
        return usi;
    }


}
