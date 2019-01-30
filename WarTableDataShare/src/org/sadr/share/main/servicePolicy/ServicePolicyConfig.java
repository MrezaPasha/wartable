package org.sadr.share.main.servicePolicy;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class ServicePolicyConfig extends ShareConfig {

    @Bean
    public ServicePolicyDaoImp servicePolicyDaoImp()
    {
        ServicePolicyDaoImp udi = new ServicePolicyDaoImp();
        return udi;
    }

    @Bean
    public ServicePolicyServiceImp servicePolicyServiceImp()
    {
        ServicePolicyServiceImp usi = new ServicePolicyServiceImp();
        usi.setDao(servicePolicyDaoImp());
        return usi;
    }


}
