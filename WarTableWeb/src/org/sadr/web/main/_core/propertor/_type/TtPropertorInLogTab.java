package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInLogTab {
    System("وب"),
    Connection("ارتباط با سرور رویدادنگاری"),
    ;

    private final String title;

    TtPropertorInLogTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
