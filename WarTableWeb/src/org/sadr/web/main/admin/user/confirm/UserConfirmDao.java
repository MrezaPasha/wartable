package org.sadr.web.main.admin.user.confirm;

import org.sadr._core.meta.generic.GenericDao;
import org.sadr.web.main.admin.user.user.User;

/**
 * @author masoud
 */
public interface UserConfirmDao extends GenericDao<UserConfirm> {

    public boolean isConfirmed(User u, String taskSignature);
}
