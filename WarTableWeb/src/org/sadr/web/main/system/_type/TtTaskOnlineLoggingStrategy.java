package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskOnlineLoggingStrategy {

    Immediately("فوری"),
    WithDelay("ارسال با تاخیر"),
    Scheduling("بر اساس زمان بندی"),
    ;
    private final String title;

    private TtTaskOnlineLoggingStrategy(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
