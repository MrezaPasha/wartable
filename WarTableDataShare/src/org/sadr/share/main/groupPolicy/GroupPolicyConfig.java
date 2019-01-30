package org.sadr.share.main.groupPolicy;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class GroupPolicyConfig extends ShareConfig {

    @Bean
    public GroupPolicyDaoImp groupPolicyDaoImp()
    {
        GroupPolicyDaoImp udi = new GroupPolicyDaoImp();
        return udi ;
    }

    @Bean
    public GroupPolicyServiceImp groupPolicyServiceImp()
    {
        GroupPolicyServiceImp usi = new GroupPolicyServiceImp();
        usi.setDao(groupPolicyDaoImp());
        return usi;
    }
}
