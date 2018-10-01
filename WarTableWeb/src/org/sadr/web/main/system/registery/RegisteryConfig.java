package org.sadr.web.main.system.registery;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class RegisteryConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RegisteryDaoImp registeryDaoImp() {
        RegisteryDaoImp udi = new RegisteryDaoImp();
        return udi;
    }

    @Bean
    public RegisteryServiceImp registeryServiceImp() {
        RegisteryServiceImp usi = new RegisteryServiceImp();
        usi.setDao(registeryDaoImp());
        return usi;
    }

    @Bean
    public RegisteryController registeryController() {
        RegisteryController uc = new RegisteryController();
        uc.setService(registeryServiceImp());
        return uc;
    }
}
