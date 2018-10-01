package org.sadr.web.main.system.log.attempt;

import org.sadr._core.meta.generic.GenericDao;
import org.sadr.web.main.admin.user.user.User;

/**
 * @author masoud
 */
public interface UserAttemptDao extends GenericDao<UserAttempt> {

    public void deleteAttempt(User u);
}
