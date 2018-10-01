package org.sadr.web.main.system.log.general;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class LogDaoImp extends GenericDaoImpl<Log> implements LogDao {

    @Override
    public void log(Log l) {
        getCurrentSession().save(l);
    }

}
