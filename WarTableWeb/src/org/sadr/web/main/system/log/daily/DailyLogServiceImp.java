package org.sadr.web.main.system.log.daily;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
//@Component
public class DailyLogServiceImp extends GenericServiceImpl<DailyLog, DailyLogDao> implements DailyLogService {

    @Override
    public int totalVisit() {
        return dao.totalVisit();
    }

    @Override
    public int visit(String startDate, String endDate) {
        return dao.visit(startDate, endDate);
    }

}
