package org.sadr.share.main.admin.account.group;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class AccountGroupConfig extends Config {

    @Bean
    public AccountGroupDaoImp accountGroupDaoImp() {
        AccountGroupDaoImp udi = new AccountGroupDaoImp();
        return udi;
    }

    @Bean
    public AccountGroupServiceImp accountGroupServiceImp() {
        AccountGroupServiceImp usi = new AccountGroupServiceImp();
        usi.setDao(accountGroupDaoImp());
        return usi;
    }

    @Bean
    public AccountGroupController accountGroupController() {
        AccountGroupController uc = new AccountGroupController();
        uc.setService(accountGroupServiceImp());
        return uc;
    }
}
