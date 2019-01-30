package org.sadr.share.main._types;

public enum TtMapCategory {
    WarMap("real war map");

    private String title;

    TtMapCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static TtMapCategory getByOrdinal(int o) {
        for (TtMapCategory f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
