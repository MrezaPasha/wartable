package org.sadr.web.main.system.log.validation;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("مدیریت رویدادنگاری اعتبارسنجی")
public class ValidationLogController extends GenericControllerImpl<ValidationLog, ValidationLogService> {

    public ValidationLogController() {
    }
}
