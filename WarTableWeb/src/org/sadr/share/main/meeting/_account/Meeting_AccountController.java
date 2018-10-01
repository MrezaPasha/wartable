package org.sadr.share.main.meeting._account;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Meeting_AccountController extends GenericControllerImpl<Meeting_Account, Meeting_AccountService> {

    public Meeting_AccountController() {
    }
}
