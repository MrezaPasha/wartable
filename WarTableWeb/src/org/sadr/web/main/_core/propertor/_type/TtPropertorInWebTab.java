package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInWebTab {
    Generic("عمومی"),
    User("کاربری"),
    LoadThreshold("آستانه بار"),
    Irror("مدیریت خطا"),
    Service("مدیریت سرویس"),
    ;

    private final String title;

    TtPropertorInWebTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
