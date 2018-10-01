package org.sadr.share.main.admin.account.model;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class AccountModelConfig extends Config {

    @Bean
    public AccountModelDaoImp accountModelDaoImp() {
        AccountModelDaoImp udi = new AccountModelDaoImp();
        return udi;
    }

    @Bean
    public AccountModelServiceImp accountModelServiceImp() {
        AccountModelServiceImp usi = new AccountModelServiceImp();
        usi.setDao(accountModelDaoImp());
        return usi;
    }

    @Bean
    public AccountModelController accountModelController() {
        AccountModelController uc = new AccountModelController();
        uc.setService(accountModelServiceImp());
        return uc;
    }
}
