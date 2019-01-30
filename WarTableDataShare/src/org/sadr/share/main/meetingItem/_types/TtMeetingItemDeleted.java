package org.sadr.share.main.meetingItem._types;

public enum TtMeetingItemDeleted {
    Deleted("شی حذف شده"),
    Undeleted("شی حذف نشده");


    private String name;

    TtMeetingItemDeleted(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtMeetingItemDeleted getByOrdinal(int o){
        for (TtMeetingItemDeleted f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
