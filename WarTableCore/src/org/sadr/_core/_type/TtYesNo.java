package org.sadr._core._type;

/**
 * @author masoud
 */
public enum TtYesNo {

    No("خیر", "ناسالم", "ندارد", "خاموش"),
    Yes("بله", "سالم", "دارد", "روشن");

    private final String title;
    private final String health;
    private final String has;
    private final String offOn;

    private TtYesNo(String k, String h, String s, String o) {
        title = k;
        health = h;
        has = s;
        offOn = o;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getBoolean() {
        return (ordinal() == 1);
    }

    public static TtYesNo getValueByBool(Boolean b) {
        return (b == null ? null
            : (b ? TtYesNo.Yes : TtYesNo.No));
    }

    public static String toArrayString() {
        String s = "";
        for (TtYesNo enu : values()) {
            s += "'" + enu.getTitle() + "',";
        }
        return s.substring(0, s.length() - 1);
    }

    public static TtYesNo getByValue(int v) {
        for (TtYesNo enu : values()) {
            if (enu.ordinal() == v) {
                return enu;
            }
        }
        return null;
    }

    public String getHealth() {
        return health;
    }

    public String getHas() {
        return has;
    }

    public String getOffOn() {
        return offOn;
    }
}
