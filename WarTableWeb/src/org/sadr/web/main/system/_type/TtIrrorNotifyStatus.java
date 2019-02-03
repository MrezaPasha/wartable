package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtIrrorNotifyStatus {

    New("جدید"),
    Visited("مشاهده شده"),
    ;
    private final String title;

    private TtIrrorNotifyStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
