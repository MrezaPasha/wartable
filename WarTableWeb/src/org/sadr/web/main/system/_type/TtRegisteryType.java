package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtRegisteryType {

    Ixport("ixport", "خروجی گزارش"),
    Backup("backup", "پشتیبانی"),
    ;

    private final String key;
    private final String title;

    private TtRegisteryType(String k, String t) {
        key = k;
        title = t;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public static TtRegisteryType getByOrdinal(int ordinal) {
        for (TtRegisteryType value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
