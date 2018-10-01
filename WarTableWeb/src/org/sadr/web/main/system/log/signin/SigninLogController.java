package org.sadr.web.main.system.log.signin;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("مدیریت رویدادنگاری ورود کاربر")
public class SigninLogController extends GenericControllerImpl<SigninLog, SigninLogService> {

    public SigninLogController() {
    }
}
