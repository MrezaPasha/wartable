package org.sadr.web.main._core._type;

/**
 * @author masoud
 */
public enum TtUiFonts {

    IranSans("ایران سنس", "iransans"),
    Yekan("یکان", "yekan"),;

    private final String title;
    private final String key;

    private TtUiFonts(String t, String k) {
        title = t;
        key = k;
    }

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public static TtUiFonts getByKey(String key) {
        for (TtUiFonts value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return IranSans;
    }
}
