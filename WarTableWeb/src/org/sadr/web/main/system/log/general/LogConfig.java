package org.sadr.web.main.system.log.general;

import org.sadr.web.main.system.log.remote.RemoteLogConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class LogConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LogDaoImp logDaoImp() {
        LogDaoImp udi = new LogDaoImp();
        return udi;
    }

    @Bean
    public LogServiceImp logServiceImp() {
        LogServiceImp usi = new LogServiceImp();
        usi.setDao(logDaoImp());
        usi.setRemoteLogService(new RemoteLogConfig().remoteLogServiceImp());
        return usi;
    }

    @Bean
    public LogController logController() {
        LogController uc = new LogController();
        uc.setService(logServiceImp());
        return uc;
    }
}
