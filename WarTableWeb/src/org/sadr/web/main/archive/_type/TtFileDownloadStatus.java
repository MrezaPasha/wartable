package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtFileDownloadStatus {

    Downloaded("دانلود شده"),
    ReadyToDownload("آماده دانلود"),
    Downloading("در حال دانلود"),
    Canceled("لغو شده");

    private final String title;

    private TtFileDownloadStatus(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}
