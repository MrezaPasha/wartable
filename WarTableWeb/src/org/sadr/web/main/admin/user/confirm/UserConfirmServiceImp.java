package org.sadr.web.main.admin.user.confirm;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class UserConfirmServiceImp extends GenericServiceImpl<UserConfirm, UserConfirmDao> implements UserConfirmService {
    @Override
    public boolean isConfirmed(User u, String taskSignature) {
        return dao.isConfirmed(u, taskSignature);
    }
}
