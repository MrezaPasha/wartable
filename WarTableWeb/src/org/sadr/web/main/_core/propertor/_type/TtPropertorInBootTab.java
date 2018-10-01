package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInBootTab {
    Startup("راه اندازی"),
 ;

    private final String title;

    TtPropertorInBootTab(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
