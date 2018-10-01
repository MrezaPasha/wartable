package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtDirectoryOrigin {

    Repository("بایگانی"),
    Assets("منابع طراحی");

    private final String title;

    private TtDirectoryOrigin(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}
