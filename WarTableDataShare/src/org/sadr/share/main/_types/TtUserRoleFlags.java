package org.sadr.share.main._types;

public enum TtUserRoleFlags {

    User("normal user"),
    Admin("admin user"),
    ViewSource("see war table as another member"),
    AdminViewSource("both admin and viewsource role flag"),
    TempAdmin("temperory admin of the room"),
    TempAdminViewSource("both temp admin and view source role flag");
    //TempAdmin("temporary admin");

    private String name;

    TtUserRoleFlags(String name) {
        this.name = name;
    }

    TtUserRoleFlags() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtUserRoleFlags getByOrdinal(int o) {
        for (TtUserRoleFlags f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
