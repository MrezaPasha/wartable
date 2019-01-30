package org.sadr.share.main.baseConfig;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class BaseConfigShareConfig extends WebConfig {

    @Bean
    public BaseConfigDaoImp baseConfigDaoImp() {
        BaseConfigDaoImp udi = new BaseConfigDaoImp();
        return udi;
    }

    @Bean
    public BaseConfigShareServiceImp baseConfigShareServiceImp() {
        BaseConfigShareServiceImp usi = new BaseConfigShareServiceImp();
        usi.setDao(baseConfigDaoImp());
        return usi;
    }

    @Bean
    public BaseConfigShareController baseConfigShareController() {
        BaseConfigShareController uc = new BaseConfigShareController();
        uc.setService(baseConfigShareServiceImp());
        return uc;
    }
}
