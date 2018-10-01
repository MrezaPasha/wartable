package org.sadr.web.main.admin._type;

/**
 * @author masoud
 */
public enum TtUserPorterSignoutStatus {

    UserSignOut("فعال"),
    Lossed("فعال"),
    Expired("غیرفعال");

    private final String title;

    private TtUserPorterSignoutStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }

}
