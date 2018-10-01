package org.sadr.web.main.system.module;

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
public class ModuleDaoImp extends GenericDaoImpl<Module> implements ModuleDao {

    @Override
    public Module findByClassName(String className) {
        Query query = getCurrentSession().createQuery("FROM " + Module.class.getName() + " WHERE entityState= :es " + " AND className = :cn");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setString("cn", className);
        //OutLog.pl("**aa** " + query.getQueryString());
        List<Module> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Module findByPackageName(String packageName) {
        Query query = getCurrentSession().createQuery("FROM " + Module.class.getName() + " WHERE entityState= :es " + " AND packageName = :cn");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setString("cn", packageName);
        //OutLog.pl("**aa** " + query.getQueryString());
        List<Module> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
