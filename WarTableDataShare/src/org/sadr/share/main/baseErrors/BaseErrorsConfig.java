package org.sadr.share.main.baseErrors;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class BaseErrorsConfig extends ShareConfig {

    @Bean
    public BaseErrorsDaoImp baseErrorsDaoImp()
    {
        BaseErrorsDaoImp udi = new BaseErrorsDaoImp();
        return udi;
    }

    @Bean
    public BaseErrorsServiceImp baseErrorsServiceImp()
    {
        BaseErrorsServiceImp usi = new BaseErrorsServiceImp();
        usi.setDao(baseErrorsDaoImp());
        return usi;
    }

}
