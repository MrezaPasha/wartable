package org.sadr._core._type;

/**
 * @author masoud
 */
public enum TtSecurityTag {

    WithoutTag("<فاقد برچسب>"),
    Normal("معمولی"),
    Secure("محرمانه"),
    HighSecure("خیلی محرمانه"),
    Secret("سری"),
    HighSecret("فوق سری"),
    ;

    private final String title;

    TtSecurityTag(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }

    public int getOrdinal() {
        return ordinal();
    }


    public static TtSecurityTag getByOrdinal(int ordinal) {
        for (TtSecurityTag value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
