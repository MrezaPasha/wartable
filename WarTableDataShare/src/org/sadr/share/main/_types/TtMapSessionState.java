package org.sadr.share.main._types;

public enum  TtMapSessionState {

    DeletedSession("room is deleted"),
    UnDeletedSession("room is not deleted");

    private String name;

    TtMapSessionState(String name) {
        this.name = name;
    }

    TtMapSessionState() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtMapSessionState getByOrdinal(int o) {
        for (TtMapSessionState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
