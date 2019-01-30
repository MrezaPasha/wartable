package org.sadr.share.main.SC.SCServerConfig;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class SCServerConfigConfig extends ShareConfig {

    @Bean
    public SCServerConfigDaoImp scServerConfigDaoImp()
    {
        SCServerConfigDaoImp udi = new SCServerConfigDaoImp();
        return udi;
    }

    @Bean
    public SCServerConfigServiceImp scServerConfigServiceImp()
    {
        SCServerConfigServiceImp usi = new SCServerConfigServiceImp();
        usi.setDao(scServerConfigDaoImp());
        return usi;
    }


}
