package org.sadr.web.main.system.log.signin;

import org.sadr.web.config.WebConfig;
import org.sadr.web.main.system.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class SigninLogConfig extends WebConfig {

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
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        return uc;
    }
}
