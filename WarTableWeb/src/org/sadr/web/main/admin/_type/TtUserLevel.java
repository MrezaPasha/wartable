package org.sadr.web.main.admin._type;

/**
 * @author masoud
 */
public enum TtUserLevel {

    Client("کاربر عادی"),
    Master("کاربر ارشد"),
    Administrator("مدیر کل");

    private final String title;

    private TtUserLevel(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
