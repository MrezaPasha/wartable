package org.sadr.web.main.system.registery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class RegisteryDaoImp extends GenericDaoImpl<Registery> implements RegisteryDao {

    @Override
    public Registery findByKey(String key) {
        try {
            Criteria crt = getCurrentSession().createCriteria(Registery.class)
                .add(Restrictions.eq("entityState", TtEntityState.Exist))
                .add(Restrictions.eq("key", key));
            return (Registery) crt.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Registery findByKeyAndType(String key, String type) {
        try {
            Criteria crt = getCurrentSession().createCriteria(Registery.class)
                .add(Restrictions.eq("entityState", TtEntityState.Exist))
                .add(Restrictions.eq("key", key))
                .add(Restrictions.eq("type", type));
            return (Registery) crt.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

}
