package org.sadr.web.main.system.log.daily;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface DailyLogDao extends GenericDao<DailyLog> {

    public int totalVisit();

    public int visit(String startDate, String endDate);

}
