package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInLogSection {
    ServerSpec("N.propertor.log.system.serverSpec", TtPropertorInLogTab.System),
    //    ServerUserLevelLogging("N.propertor.log.system.userLevel", TtPropertorInLogTab.System),
//    ServerUserLevelLogging("N.propertor.log.system.userLevel", TtPropertorInLogTab.System),
    ConnectionInfo("N.propertor.log.connection.info", TtPropertorInLogTab.Connection),;

    private final String spMessage;
    private final TtPropertorInLogTab tab;

    TtPropertorInLogSection(String m, TtPropertorInLogTab p) {
        spMessage = m;
        tab = p;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public TtPropertorInLogTab getTab() {
        return tab;
    }


}
