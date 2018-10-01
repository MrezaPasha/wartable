package org.sadr.web.main.system.log.attempt;

import org.hibernate.Query;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class UserAttemptDaoImp extends GenericDaoImpl<UserAttempt> implements UserAttemptDao {

    @Override
    public void deleteAttempt(User u) {
        Query query = getCurrentSession().createQuery("DELETE FROM " + UserAttempt.class.getName()
            + " WHERE user_id= :ud");
        query.setLong("ud", u.getId());
        query.executeUpdate();
    }

}
