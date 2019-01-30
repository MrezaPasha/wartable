package org.sadr.share.main.invite;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;


public class InviteConfig extends ShareConfig {

    @Bean
    public InviteDaoImp inviteDaoImp()
    {
        InviteDaoImp udi = new InviteDaoImp();
        return udi;

    }

    @Bean
    public InviteServiceImp inviteServiceImp()
    {
        InviteServiceImp usi = new InviteServiceImp();
        usi.setDao(inviteDaoImp());
        return usi;
    }


}
