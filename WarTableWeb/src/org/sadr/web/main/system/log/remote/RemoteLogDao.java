package org.sadr.web.main.system.log.remote;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface RemoteLogDao extends GenericDao<RemoteLog> {

    public void log(RemoteLog l);
}
