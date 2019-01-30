package org.sadr.share.main.room._types;

public enum TtRoomRecSetting {
    NotRecVideoNotRecSound("ویدیو و صوت ضیط نشود"),
    RecVideoNotRecSound("ویدیو ضیط شود و صوت ضبط نشود"),
    NotRecVideoRecSound("ویدیو ضبط نشود و صوت ضیط شود"),
    RecVideoRecSound("ویدیو و صوت ضبط شود");


    private String description;

    TtRoomRecSetting(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
