package org.sadr.web.main.system.model;

import org.hibernate.Query;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author masoud
 */
@Repository
@Component
public class ModelDaoImp extends GenericDaoImpl<Model> implements ModelDao {

    @Override
    public Model findByClassName(String className) {
        Query query = getCurrentSession().createQuery("FROM " + Model.class.getName() + " WHERE entityState= :es " + " AND className = :cn");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setString("cn", className);
        //OutLog.pl("**aa** " + query.getQueryString());
        List<Model> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Model findByPackageName(String packageName) {
        Query query = getCurrentSession().createQuery("FROM " + Model.class.getName() + " WHERE entityState= :es " + " AND packageName = :cn");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setString("cn", packageName);
        //OutLog.pl("**aa** " + query.getQueryString());
        List<Model> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
