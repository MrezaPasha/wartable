package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtBackupType {

    Manual("پشتیبان گیری دستی"),
    Scheduling("پشتیبان گیری خودکار بر اساس زمان بندی"),
    BeforeRestore("پشتیبان گیری خودکار قبل از بازیابی"),

    ;

    private final String title;

    private TtBackupType(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
