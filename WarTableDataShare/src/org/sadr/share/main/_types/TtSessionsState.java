package org.sadr.share.main._types;

public enum TtSessionsState {

    Deactive("user session is deactive"),
    Active("user session is active");

    private String name;

    TtSessionsState() {
    }

    TtSessionsState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtSessionsState getByOrdinal(int o) {
        for (TtSessionsState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }


}
