package org.sadr.share.main.admin.account.account;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class AccountController extends GenericControllerImpl<Account, AccountService> {

    public AccountController() {
    }
}
