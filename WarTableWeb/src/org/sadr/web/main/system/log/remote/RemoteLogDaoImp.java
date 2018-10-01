package org.sadr.web.main.system.log.remote;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author masoud
 */
@Repository
@Component
public class RemoteLogDaoImp extends GenericDaoImpl<RemoteLog> implements RemoteLogDao {

    @Override
    public void log(RemoteLog l) {
        getCurrentSession().save(l);
    }
}
