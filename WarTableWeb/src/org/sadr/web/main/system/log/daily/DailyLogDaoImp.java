package org.sadr.web.main.system.log.daily;

import org.hibernate.Query;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class DailyLogDaoImp extends GenericDaoImpl<DailyLog> implements DailyLogDao {

    @Override
    public int totalVisit() {
        Query query = getCurrentSession().createQuery("SELECT SUM(V." + DailyLog.VISIT_COUNT + ") FROM " + DailyLog.class.getName() + " V WHERE V.entityState= :es");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        Object ur = query.uniqueResult();
        return (ur == null ? 0 : (int) (long) ur);
    }

    @Override
    public int visit(String startDate, String endDate) {
        Query query = getCurrentSession().createQuery("SELECT SUM(V." + DailyLog.VISIT_COUNT + ") FROM " + DailyLog.class.getName() + " V WHERE V.entityState= :es AND V." + DailyLog.DAY_DATE + " >= :sd AND V." + DailyLog.DAY_DATE + " <= :ed");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setString("sd", startDate);
        query.setString("ed", endDate);
        Object ur = query.uniqueResult();
        return (ur == null ? 0 : (int) (long) ur);
    }
}
