package org.sadr._core.utils._type;

/**
 * @author masoud
 */
public enum TtJsonBuilderActivityHistoryType {

    UserActivity("فعالیت کاربر"),
    SystemActivity("عملیات سیستمی"),
    Message("پیام"),;

    private final String title;

    private TtJsonBuilderActivityHistoryType(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}
