package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtHttpErrorStatus {

    New("جدید"),
    Solved("حل شده"),
    NotSolved("حل نشده"),
    Verifying("درحال بررسی");

    private final String title;

    private TtHttpErrorStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
