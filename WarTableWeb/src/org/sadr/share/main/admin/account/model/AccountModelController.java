package org.sadr.share.main.admin.account.model;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class AccountModelController extends GenericControllerImpl<AccountModel, AccountModelService> {

    public AccountModelController() {
    }
}
