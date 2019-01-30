package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskActionStatus {

    Success("موفق - Success"),
    Failure("ناموفق - Failure"),
    Error("خطا - Error"),;
    private final String title;

    private TtTaskActionStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }

    public static TtTaskActionStatus getValue(String stringValue) {
        if (stringValue != null) {
            for (TtTaskActionStatus v : values()) {
                if (stringValue.equals(v.toString())) {
                    return v;
                }
            }
        }
        return null;
    }
}
