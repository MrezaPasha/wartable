package org.sadr.share.main._types;

public enum TtServiceUserState {
    DeletedUser("کاربر حذف شده"),
    UnDeletedUser("کاربر حذف نشده"),
    BannedUser("کاربر غیر مجاز"),
    UnBannedUser("کاربر مجاز");

    private String name;

    TtServiceUserState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtServiceUserState getByOrdinal(int o) {
        for (TtServiceUserState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
