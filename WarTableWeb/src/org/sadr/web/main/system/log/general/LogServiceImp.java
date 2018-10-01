package org.sadr.web.main.system.log.general;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.web.main.system.log.remote.RemoteLog;
import org.sadr.web.main.system.log.remote.RemoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
//@Component
public class LogServiceImp extends GenericServiceImpl<Log, LogDao> implements LogService {

    private RemoteLogService remoteLogService;

    @Autowired
    public void setRemoteLogService(RemoteLogService remoteLogService) {
        this.remoteLogService = remoteLogService;
    }

    @Override
    public void log(Log l) {
        this.dao.log(l);
        remoteLogService.log(new RemoteLog(l));
    }

}
