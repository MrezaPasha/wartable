package org.sadr.web.main.system.log.remote;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface RemoteLogService extends GenericService<RemoteLog> {

    public void log(RemoteLog l);
}
