package org.sadr.share.main._types;

public enum TtSequenceState {
    TRUE("enable permission or state","1"),
    FALSE("disable permission or state ","0");

    private String description;
    private String value;

    TtSequenceState(String description, String value) {
        this.description = description;
        this.value = value;
    }

    TtSequenceState() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static TtSequenceState getByOrdinal(int o) {
        for (TtSequenceState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
