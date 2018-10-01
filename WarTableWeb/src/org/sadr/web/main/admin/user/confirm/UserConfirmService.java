package org.sadr.web.main.admin.user.confirm;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.admin.user.user.User;

/**
 *
 * @author masoud
 */
public interface UserConfirmService extends GenericService<UserConfirm> {
    public boolean isConfirmed(User u, String taskSignature);

}
