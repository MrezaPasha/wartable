package org.sadr.web.main.system.log.remote;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class RemoteLogServiceImp extends GenericServiceImpl<RemoteLog, RemoteLogDao> implements RemoteLogService {
    @Override
    public void log(RemoteLog l) {
        this.dao.log(l);
    }
}
