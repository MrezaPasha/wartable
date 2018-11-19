package org.sadr.web.main._core.uiBag;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class UiBagConfig extends Config {

    @Bean
    public UiBagDaoImp uiBagDaoImp() {
        UiBagDaoImp udi = new UiBagDaoImp();
        return udi;
    }

    @Bean
    public UiBagServiceImp uiBagServiceImp() {
        UiBagServiceImp usi = new UiBagServiceImp();
        usi.setDao(uiBagDaoImp());
        return usi;
    }

    @Bean
    public UiBagController uiBagController() {
        UiBagController uc = new UiBagController();
        uc.setService(uiBagServiceImp());
        return uc;
    }
}
