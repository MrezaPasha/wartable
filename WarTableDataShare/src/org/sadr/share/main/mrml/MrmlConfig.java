package org.sadr.share.main.mrml;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;


public class MrmlConfig extends ShareConfig {

    @Bean
    public MrmlDaoImp mrmlDaoImp()
    {
        MrmlDaoImp udi = new MrmlDaoImp();
        return udi;
    }

    @Bean
    public MrmlServiceImp mrmlServiceImp()
    {
        MrmlServiceImp usi = new MrmlServiceImp();
        usi.setDao(mrmlDaoImp());
        return usi;
    }

}
