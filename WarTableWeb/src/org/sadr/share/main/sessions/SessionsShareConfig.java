package org.sadr.share.main.sessions;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class SessionsShareConfig extends WebConfig {

    @Bean
    public SessionsDaoImp sessionsDaoImp() {
        SessionsDaoImp udi = new SessionsDaoImp();
        return udi;
    }

    @Bean
    public SessionsShareServiceImp sessionsShareServiceImp() {
        SessionsShareServiceImp usi = new SessionsShareServiceImp();
        usi.setDao(sessionsDaoImp());
        return usi;
    }

    @Bean
    public SessionsShareController sessionsShareController() {
        SessionsShareController uc = new SessionsShareController();
        uc.setService(sessionsShareServiceImp());
        return uc;
    }
}
