package org.sadr.web.main.admin.user.user;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface UserDao extends GenericDao<User> {

    public boolean authenticateE(String username);

    public User authenticateE(String username, String password);

    public User findByUsername(String username, String... relatedClass);

}
