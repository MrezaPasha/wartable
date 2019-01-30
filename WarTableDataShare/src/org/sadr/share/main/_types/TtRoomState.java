package org.sadr.share.main._types;

public enum TtRoomState {

    DeletedRoom("room is deleted"),
    UnDeletedRoom("room is not deleted");

    private String name;

    TtRoomState(String name) {
        this.name = name;
    }

    TtRoomState() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtRoomState getByOrdinal(int o) {
        for (TtRoomState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
