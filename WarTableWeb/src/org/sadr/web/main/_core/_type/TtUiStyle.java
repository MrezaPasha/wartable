package org.sadr.web.main._core._type;

/**
 * @author masoud
 */
public enum TtUiStyle {

    Light("روشن", "light"),
    Dark("تیره", "dark"),;

    private final String title;
    private final String key;

    private TtUiStyle(String t, String k) {
        title = t;
        key = k;
    }

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public static TtUiStyle getByKey(String key) {
        for (TtUiStyle value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return Light;
    }
}
