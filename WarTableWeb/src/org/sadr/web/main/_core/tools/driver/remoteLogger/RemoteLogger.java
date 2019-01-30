package org.sadr.web.main._core.tools.driver.remoteLogger;


import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.propertor.PropertorInLog;
import org.sadr.web.main._core.propertor._type.TtPropertorInLogList;
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
//            OutLog.p(toJson(l));
            remoteLogService.update(l);
        }
        return sendList;
    }

    private String toJson(RemoteLog r) {

        return
                "{\"Time\":\"" + r.getDateTimeG() +
                        "\",\"SoftwareID\":\"" + r.getServerId() +
                        "\",\"SoftwareName\":\"" + PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemName) +
                        "\",\"SoftwareVersion\":\"" + PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemVersion) +
                        "\",\"serverHostname\":\"" + PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemHostName) +
                        "\",\"ServerIP\":\"" + r.getHostIpAddress() +
                        "\",\"PortNumber\":\"" + r.getHostPortNumber() +
                        "\",\"clientHostname\":\"" + r.getClientName() +
                        "\",\"ClientIP\":\"" + r.getClientIpAddress() +
                        "\",\"PageTitle\":\"" + r.getTaskTitle() +
                        "\",\"URL\":\"" + r.getUrl() +
                        "\",\"Username\":\"" + r.getUsername() +
                        "\",\"UserUniqueID\":\"" + r.getUserCode() +
                        "\",\"ActionType\":\"" + r.getActionType() +
                        "\",\"Sensitivity\":\"" + r.getSensitivity() +
                        "\",\"Importance\":\"" + r.getImportanceLevel() +
                        "\",\"Flag\":\"" + r.getActionStatus() +
                        "\",\"SubType\":\"" + r.getActionSubType() +
                        "\",\"SubTypeDescription\":{" +
                        "},\"Description\":" + r.getMessage()
                        + "}"

                ;


    }

}
