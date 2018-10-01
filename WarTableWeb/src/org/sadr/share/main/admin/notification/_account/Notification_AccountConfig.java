package org.sadr.share.main.admin.notification._account;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Notification_AccountConfig extends Config {

    @Bean
    public Notification_AccountDaoImp notification_AccountDaoImp() {
        Notification_AccountDaoImp udi = new Notification_AccountDaoImp();
        return udi;
    }

    @Bean
    public Notification_AccountServiceImp notification_AccountServiceImp() {
        Notification_AccountServiceImp usi = new Notification_AccountServiceImp();
        usi.setDao(notification_AccountDaoImp());
        return usi;
    }

    @Bean
    public Notification_AccountController notification_AccountController() {
        Notification_AccountController uc = new Notification_AccountController();
        uc.setService(notification_AccountServiceImp());
        return uc;
    }
}
