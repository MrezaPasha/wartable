package org.sadr.web.main.system.log.daily;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface DailyLogService extends GenericService<DailyLog> {

    public int totalVisit();

    public int visit(String startDate, String endDate);

}
