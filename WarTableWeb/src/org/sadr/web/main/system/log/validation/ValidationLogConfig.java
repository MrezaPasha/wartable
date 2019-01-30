package org.sadr.web.main.system.log.validation;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class ValidationLogConfig extends WebConfig {

    @Bean
    public ValidationLogDaoImp validationLogDaoImp() {
        ValidationLogDaoImp udi = new ValidationLogDaoImp();
        return udi;
    }

    @Bean
    public ValidationLogServiceImp validationLogServiceImp() {
        ValidationLogServiceImp usi = new ValidationLogServiceImp();
        usi.setDao(validationLogDaoImp());
        return usi;
    }

    @Bean
    public ValidationLogController validationLogController() {
        ValidationLogController uc = new ValidationLogController();
        uc.setService(validationLogServiceImp());
        return uc;
    }
}
