package org.sadr.share.main.room._types;

public enum  TtRoomAccessSetting {
    NotchangeVideoNotchangeSound("تنظیمات پیش فرض ویدیو و صوت تغییر نکند"),
    ChangeVideoNotchangeSound("تنظیمات پیش فرض ویدیو تغییر کند و تنظیمات پیش فرض صوت تغییر نکند"),
    NotchangeVideoChangeSound("تنظیمات پیش فرض ویدیو تغییر نکند و تنظیمات پیش فرض صوت تغییر کند"),
    ChangeVideoChangeSound("تنظیمات پیش فرض ویدیو و صوت تغییر کند");

    private String description;

    TtRoomAccessSetting(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
