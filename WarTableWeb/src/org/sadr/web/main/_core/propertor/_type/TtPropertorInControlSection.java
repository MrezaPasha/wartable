package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInControlSection {
    Log("N.propertor.system.log", TtPropertorInControlTab.System),
    File("N.propertor.system.file", TtPropertorInControlTab.System),
    Developing("N.propertor.system.developing", TtPropertorInControlTab.Developing),
    ;

    private final String spMessage;
    private final TtPropertorInControlTab tab;

    TtPropertorInControlSection(String m, TtPropertorInControlTab p) {
        spMessage = m;
        tab = p;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public TtPropertorInControlTab getTab() {
        return tab;
    }
}
