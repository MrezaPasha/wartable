package org.sadr.share.main._types;

public enum TtMeetingState {
    Deleted("meeting is delete"),
    Undeleted("meeting is undelete");

    private String name;

    TtMeetingState(String name) {
        this.name = name;
    }

    TtMeetingState() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static TtMeetingState getByOrdinal(int o) {
        for (TtMeetingState f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
