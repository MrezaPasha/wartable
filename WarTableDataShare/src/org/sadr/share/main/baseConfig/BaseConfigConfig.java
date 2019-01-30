package org.sadr.share.main.baseConfig;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class BaseConfigConfig extends ShareConfig {

    @Bean
    public BaseConfigDaoImp baseConfigDaoImp()
    {
        BaseConfigDaoImp udi = new BaseConfigDaoImp();
        return udi;
    }

    @Bean
    public BaseConfigServiceImp baseConfigServiceImp()
    {
        BaseConfigServiceImp usi = new BaseConfigServiceImp();
        usi.setDao(baseConfigDaoImp());
        return usi;
    }


}
