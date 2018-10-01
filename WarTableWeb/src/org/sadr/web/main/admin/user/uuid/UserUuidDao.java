package org.sadr.web.main.admin.user.uuid;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface UserUuidDao extends GenericDao<UserUuid> {

    public void deleteUUID(String u);
}
