package org.sadr.share.main.admin.account.account;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class AccountConfig extends Config {

    @Bean
    public AccountDaoImp accountDaoImp() {
        AccountDaoImp udi = new AccountDaoImp();
        return udi;
    }

    @Bean
    public AccountServiceImp accountServiceImp() {
        AccountServiceImp usi = new AccountServiceImp();
        usi.setDao(accountDaoImp());
        return usi;
    }

    @Bean
    public AccountController accountController() {
        AccountController uc = new AccountController();
        uc.setService(accountServiceImp());
        return uc;
    }
}
