package org.sadr.web.main.system.log.general;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface LogService extends GenericService<Log> {

    public void log(Log l);
}
