package org.sadr.share.main.baseErrors;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class BaseErrorsShareConfig extends WebConfig {

    @Bean
    public BaseErrorsDaoImp baseErrorsDaoImp() {
        BaseErrorsDaoImp udi = new BaseErrorsDaoImp();
        return udi;
    }

    @Bean
    public BaseErrorsShareServiceImp baseErrorsShareServiceImp() {
        BaseErrorsShareServiceImp usi = new BaseErrorsShareServiceImp();
        usi.setDao(baseErrorsDaoImp());
        return usi;
    }

    @Bean
    public BaseErrorsShareController baseErrorsShareController() {
        BaseErrorsShareController uc = new BaseErrorsShareController();
        uc.setService(baseErrorsShareServiceImp());
        return uc;
    }
}
