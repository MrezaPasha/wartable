package org.sadr.web.main.admin.user.uuid;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
//@Component
public class UserUuidServiceImp extends GenericServiceImpl<UserUuid, UserUuidDao> implements UserUuidService {

    @Override
    public void deleteUUID(String u) {
        dao.deleteUUID(u);
    }

}
