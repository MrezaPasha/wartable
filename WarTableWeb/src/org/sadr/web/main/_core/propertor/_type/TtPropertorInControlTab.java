package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInControlTab {
    System("سیستمی"),
    User("رفتارشناسی"),
    Developing("در حال توسعه"),

    ;

    private final String title;

    TtPropertorInControlTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
