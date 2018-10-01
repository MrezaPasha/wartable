package org.sadr.web.main.admin.user.user;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author masoud
 */
@Repository
@Component
public class UserDaoImp extends GenericDaoImpl<User> implements UserDao {

    private Criteria attachRelation(Criteria criteria, String[] relatedClass) {
        if (relatedClass == null) {
            return criteria;
        }
        for (String r : relatedClass) {
            criteria.setFetchMode(r, FetchMode.JOIN);
            if (r.contains(".")) {
                continue;
            }
            try {
                if (User.class.getDeclaredField(r).isAnnotationPresent(OneToOne.class) && User.class.getDeclaredField(r).getAnnotation(OneToOne.class).mappedBy().isEmpty()) {
                    criteria.setFetchMode(r + "." + User.class.getSimpleName().substring(0, 1).toLowerCase() + User.class.getSimpleName().substring(1), FetchMode.JOIN);
                }
            } catch (NoSuchFieldException | SecurityException ex) {
                Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Override
    public User authenticateE(String username, String password) {
        Criteria criteria = getCurrentSession().createCriteria(User.class)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .add(Restrictions.eq("username", username))
            .add(Restrictions.eq("password", password));

        List<User> list = criteria.list();
        if (list.isEmpty()) {
            return null;
        }
        User u = list.get(0);

        Hibernate.initialize(u.getTasks());
        Hibernate.initialize(u.getUserGroups());
        return list.get(0);
    }

    @Override
    public boolean authenticateE(String username) {
        Criteria criteria = getCurrentSession().createCriteria(User.class)
            .add(Restrictions.eq("username", username))
            .add(Restrictions.eq("entityState", TtEntityState.Exist));

        List<User> list = criteria.list();
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }


    @Override
    public User findByUsername(String username, String... relatedClass) {
        Criteria criteria = getCurrentSession().createCriteria(User.class)
            .add(Restrictions.eq("username", username))
            .add(Restrictions.eq("entityState", TtEntityState.Exist));

        return (User) attachRelation(criteria, relatedClass).uniqueResult();
    }

}
