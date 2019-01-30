package org.sadr.share.main.meetingItemLayer._types;

public enum TtMeetingItemLayerDeleted {

    Undeleted("شی حذف نشده"),
    Deleted("شی حذف شده");

    private String name;

    TtMeetingItemLayerDeleted() {
    }

    TtMeetingItemLayerDeleted(String name) {
        this.name = name;
    }


    // Getter and Setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
