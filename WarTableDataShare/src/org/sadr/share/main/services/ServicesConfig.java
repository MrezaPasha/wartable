package org.sadr.share.main.services;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class ServicesConfig extends ShareConfig {

    @Bean
    public ServicesDaoImp servicesDaoImp()
    {
        ServicesDaoImp udi = new ServicesDaoImp();
        return udi ;
    }

    @Bean
    public ServicesServiceImp servicesServiceImp()
    {
        ServicesServiceImp usi = new ServicesServiceImp();
        usi.setDao(servicesDaoImp());
        return usi;

    }
}
