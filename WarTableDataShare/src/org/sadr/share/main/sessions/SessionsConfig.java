package org.sadr.share.main.sessions;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;


public class SessionsConfig extends ShareConfig {

    @Bean
    public SessionsDaoImp sessionsDaoImp()
    {
        SessionsDaoImp sessionsDaoImp = new SessionsDaoImp();
        return sessionsDaoImp;
    }

    @Bean
    public SessionsServiceImp sessionsServiceImp()
    {
        SessionsServiceImp sessionsServiceImp = new SessionsServiceImp();
        sessionsServiceImp.setDao(sessionsDaoImp());
        sessionsServiceImp.setSessionsServiceImp(new SessionsServiceImp());
        return sessionsServiceImp;
    }
}
