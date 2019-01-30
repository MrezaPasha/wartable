package org.sadr.share.main.roomServiceUser._types;

public enum TtRoomServiceUserResponse {

    Users("Users"),
    Names("Names"),
    Families("Families"),
    Grades("Grades"),
    OrgPositions("OrgPositions"),
    Ids("Ids"),
    RoomRoleFlags("RoomRoleFlags"),
    UserStatus("UserStatus"),
    OnOff("OnOff"),
    AccessList("AccessList"),
    DefaultRecValues("DefaultRecValues"),
    ModeratorRecAccess("ModeratorRecAccess");

    private String name ;

    TtRoomServiceUserResponse(String name) {
        this.name = name;
    }


    // METHODS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static TtRoomServiceUserResponse getByOrdinal(int o) {
        for (TtRoomServiceUserResponse f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
