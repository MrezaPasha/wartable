package org.sadr.web.main.admin.user.confirm;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class UserConfirmConfig extends Config {

    @Bean
    public UserConfirmDaoImp userConfirmDaoImp() {
        UserConfirmDaoImp udi = new UserConfirmDaoImp();
        return udi;
    }

    @Bean
    public UserConfirmServiceImp userConfirmServiceImp() {
        UserConfirmServiceImp usi = new UserConfirmServiceImp();
        usi.setDao(userConfirmDaoImp());
        return usi;
    }

    @Bean
    public UserConfirmController userConfirmController() {
        UserConfirmController uc = new UserConfirmController();
        uc.setService(userConfirmServiceImp());
        return uc;
    }
}
