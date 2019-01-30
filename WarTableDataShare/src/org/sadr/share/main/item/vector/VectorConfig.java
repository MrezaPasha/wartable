package org.sadr.share.main.item.vector;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

public class VectorConfig extends ShareConfig {

    @Bean
    public VectorDaoImp vectorDaoImp()
    {
        VectorDaoImp udi = new VectorDaoImp();
        return udi;
    }

    @Bean
    public VectorServiceImp vectorServiceImp()
    {
        VectorServiceImp usi = new VectorServiceImp();
        usi.setDao(vectorDaoImp());
        return usi;
    }

}
