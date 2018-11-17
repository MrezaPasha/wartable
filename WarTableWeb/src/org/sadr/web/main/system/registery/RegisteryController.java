package org.sadr.web.main.system.registery;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@RestController
@PersianName("مدیریت اطلاعات پایه (رجیستری)")
public class RegisteryController extends GenericControllerImpl<Registery, RegisteryService> {

}
