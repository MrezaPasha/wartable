package org.sadr.web.main.admin.user.uuid;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface UserUuidService extends GenericService<UserUuid> {

    public void deleteUUID(String u);

}
