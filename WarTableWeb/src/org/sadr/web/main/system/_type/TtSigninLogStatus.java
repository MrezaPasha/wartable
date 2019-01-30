package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtSigninLogStatus {

    Success("ورود موفق"),
    Failed("ورود ناموفق"),;

    private final String title;

    private TtSigninLogStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
