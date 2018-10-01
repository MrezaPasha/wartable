package org.sadr.share.main.admin.account.account;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class AccountServiceImp extends GenericServiceImpl<Account, AccountDao> implements AccountService {
}
