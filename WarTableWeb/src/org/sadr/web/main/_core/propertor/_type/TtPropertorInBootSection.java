package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInBootSection {
    Console("N.propertor.startup.console", TtPropertorInBootTab.Startup),
    Deploy("N.propertor.startup.deploy", TtPropertorInBootTab.Startup),
    ;

    private final String spMessage;
    private final TtPropertorInBootTab tab;

    TtPropertorInBootSection(String m, TtPropertorInBootTab p) {
        spMessage = m;
        tab = p;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public TtPropertorInBootTab getTab() {
        return tab;
    }


}
