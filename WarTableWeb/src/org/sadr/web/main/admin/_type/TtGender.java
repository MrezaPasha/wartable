package org.sadr.web.main.admin._type;

/**
 * @author masoud
 */
public enum TtGender {

    Male("آقا", "آقای"),
    Female("خانم", "خانم");

    private final String key;
    private final String title;

    private TtGender(String k, String t) {
        key = k;
        title = t;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public static TtGender getByOrdinal(int ordinal) {
        for (TtGender value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

}
