package org.sadr.share.main.admin.notification._account;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Notification_AccountController extends GenericControllerImpl<Notification_Account, Notification_AccountService> {

    public Notification_AccountController() {
    }
}
