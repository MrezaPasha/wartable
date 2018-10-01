package org.sadr.web.main.system.log.signin;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class SigninLogConfig extends Config {

    @Bean
    public SigninLogDaoImp signinLogDaoImp() {
        SigninLogDaoImp udi = new SigninLogDaoImp();
        return udi;
    }

    @Bean
    public SigninLogServiceImp signinLogServiceImp() {
        SigninLogServiceImp usi = new SigninLogServiceImp();
        usi.setDao(signinLogDaoImp());
        return usi;
    }

    @Bean
    public SigninLogController signinLogController() {
        SigninLogController uc = new SigninLogController();
        uc.setService(signinLogServiceImp());
        return uc;
    }
}
