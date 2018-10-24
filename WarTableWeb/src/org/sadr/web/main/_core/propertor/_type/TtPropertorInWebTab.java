package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInWebTab {
    Generic("عمومی"),
    User("کاربری"),
    ;

    private final String title;

    TtPropertorInWebTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
