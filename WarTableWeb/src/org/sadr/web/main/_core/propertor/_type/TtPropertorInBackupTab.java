package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInBackupTab {
    Backup("پشتیبان گیری"),
    Restore("بازیابی"),
    Upload("بارگذاری"),
    ;

    private final String title;

    TtPropertorInBackupTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
