package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtFileUploadStatus {

    Uploaded("بارگذاری شده"),
    ReadyToUpload("آماده بارگذاری"),
    Uploading("در حال بارگذاری"),
    Failed("ناموفق");

    private final String title;

    private TtFileUploadStatus(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}
