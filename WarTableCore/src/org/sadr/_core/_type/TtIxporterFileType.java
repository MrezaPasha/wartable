package org.sadr._core._type;

public enum TtIxporterFileType {

    Excel("excel")
    ;
    private final String key;

    TtIxporterFileType(String a) {
        key = a;
    }

    public String getKey() {
        return key;
    }

    public static TtIxporterFileType getByKey(String key) {
        for (TtIxporterFileType value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }
}
