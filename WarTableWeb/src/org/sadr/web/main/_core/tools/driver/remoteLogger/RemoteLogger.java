package org.sadr.web.main._core.tools.driver.remoteLogger;


import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main.system._type.TtLogOnlineSendStatus;
import org.sadr.web.main.system.log.remote.RemoteLog;
import org.sadr.web.main.system.log.remote.RemoteLogService;

import java.util.List;

public class RemoteLogger {

    private RemoteLogService remoteLogService;

    private RemoteLogger() {
        if (remoteLogService == null) {
            remoteLogService = WebConfigHandler.getWebApplicationContext().getBean(RemoteLogService.class);
        }
    }

    private static RemoteLogger instance;

    public static RemoteLogger getInstance() {
        if (instance == null) {
            instance = new RemoteLogger();
        }
        return instance;
    }
    ///==================================

    public List<RemoteLog> send(List<RemoteLog> sendList) {
        for (RemoteLog l : sendList) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l.setSendStatus(TtLogOnlineSendStatus.Sent);
            OutLog.p("sent   " + l.getId() + "    " + l.getSendStatus() + "     " + l.getSendDateTime());
            remoteLogService.update(l);
        }
        return sendList;
    }

}
