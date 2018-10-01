package org.sadr.web.main.admin.user.uuid;

import org.hibernate.Query;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class UserUuidDaoImp extends GenericDaoImpl<UserUuid> implements UserUuidDao {

    @Override
    public void deleteUUID(String u) {
        Query query = getCurrentSession().createQuery("DELETE FROM " + UserUuid.class.getName()
            + " WHERE uuid= :ud");
        query.setString("ud", u);
        query.executeUpdate();
    }

}
