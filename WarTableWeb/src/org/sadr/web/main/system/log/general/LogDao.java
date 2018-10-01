package org.sadr.web.main.system.log.general;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface LogDao extends GenericDao<Log> {

    public void log(Log l);

}
