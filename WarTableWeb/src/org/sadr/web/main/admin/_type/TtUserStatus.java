package org.sadr.web.main.admin._type;

/**
 * @author masoud
 */
public enum TtUserStatus {

    Active("فعال"),
    Deactive("غیرفعال");

    private final String title;

    private TtUserStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }

}
