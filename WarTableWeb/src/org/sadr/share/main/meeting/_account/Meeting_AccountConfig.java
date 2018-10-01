package org.sadr.share.main.meeting._account;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Meeting_AccountConfig extends Config {

    @Bean
    public Meeting_AccountDaoImp meeting_AccountDaoImp() {
        Meeting_AccountDaoImp udi = new Meeting_AccountDaoImp();
        return udi;
    }

    @Bean
    public Meeting_AccountServiceImp meeting_AccountServiceImp() {
        Meeting_AccountServiceImp usi = new Meeting_AccountServiceImp();
        usi.setDao(meeting_AccountDaoImp());
        return usi;
    }

    @Bean
    public Meeting_AccountController meeting_AccountController() {
        Meeting_AccountController uc = new Meeting_AccountController();
        uc.setService(meeting_AccountServiceImp());
        return uc;
    }
}
