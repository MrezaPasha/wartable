package org.sadr.share.main.admin.notification.notification;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class NotificationConfig extends Config {

    @Bean
    public NotificationDaoImp notificationDaoImp() {
        NotificationDaoImp udi = new NotificationDaoImp();
        return udi;
    }

    @Bean
    public NotificationServiceImp notificationServiceImp() {
        NotificationServiceImp usi = new NotificationServiceImp();
        usi.setDao(notificationDaoImp());
        return usi;
    }

    @Bean
    public NotificationController notificationController() {
        NotificationController uc = new NotificationController();
        uc.setService(notificationServiceImp());
        return uc;
    }
}
