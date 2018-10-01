package org.sadr.web.main.admin.user.uuid;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UserUuidConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserUuidDaoImp userUuidDaoImp() {
        UserUuidDaoImp udi = new UserUuidDaoImp();
        return udi;
    }

    @Bean
    public UserUuidServiceImp userUuidServiceImp() {
        UserUuidServiceImp usi = new UserUuidServiceImp();
        usi.setDao(userUuidDaoImp());
        return usi;
    }
}
