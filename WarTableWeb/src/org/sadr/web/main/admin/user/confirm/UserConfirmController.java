package org.sadr.web.main.admin.user.confirm;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت تایید دوسطحی کاربر")
public class UserConfirmController extends GenericControllerImpl<UserConfirm, UserConfirmService> {

    public UserConfirmController() {
    }
}
