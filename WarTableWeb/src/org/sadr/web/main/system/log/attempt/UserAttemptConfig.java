package org.sadr.web.main.system.log.attempt;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UserAttemptConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserAttemptDaoImp userAttemptDaoImp() {
        UserAttemptDaoImp udi = new UserAttemptDaoImp();
        return udi;
    }

    @Bean
    public UserAttemptServiceImp userAttemptServiceImp() {
        UserAttemptServiceImp usi = new UserAttemptServiceImp();
        usi.setDao(userAttemptDaoImp());
        return usi;
    }
}
