package org.sadr.share.main.meeting._types;


public enum TtMeetingFields {
    MeetingIds("SessionIds"),
    MeetingNames("SessionNames"),
    MeetingDescriptions("SessionDescriptions"),
    MeetingGoals("SessionGoals"),
    MeetingResults("SessionResults"),
    MeetingMapNames("MapNames"),
    MeetingCreationTimes("SessionCreationTimes"),
    Success("Success");



    private String responseFieldName;

    TtMeetingFields(String responseFieldName) {
        this.responseFieldName = responseFieldName;
    }

    public String getResponseFieldName() {
        return responseFieldName;
    }

    public void setResponseFieldName(String responseFieldName) {
        this.responseFieldName = responseFieldName;
    }

    public static TtMeetingFields getByOrdinal(int o) {
        for (TtMeetingFields f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
