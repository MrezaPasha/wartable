package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInBackupSection {
    AutoBackup("N.propertor.backup.auto.backup", TtPropertorInBackupTab.Backup),
    AutoRestore("N.propertor.backup.auto.restore", TtPropertorInBackupTab.Restore),
    ;

    private final String spMessage;
    private final TtPropertorInBackupTab tab;

    TtPropertorInBackupSection(String m, TtPropertorInBackupTab p) {
        spMessage = m;
        tab = p;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public TtPropertorInBackupTab getTab() {
        return tab;
    }


}
