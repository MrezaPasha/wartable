package org.sadr.share.main._types;

public enum TtRoomType {
    FirstModel("circle room"),
    SecondModel("rectangle room");

    private String title;

    TtRoomType(String name) {
        this.title = name;
    }

    TtRoomType() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
