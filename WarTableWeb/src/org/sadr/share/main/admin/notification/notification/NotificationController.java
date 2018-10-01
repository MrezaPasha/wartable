package org.sadr.share.main.admin.notification.notification;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class NotificationController extends GenericControllerImpl<Notification, NotificationService> {

    public NotificationController() {
    }
}
