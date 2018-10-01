package org.sadr.web.main.archive._type;

/**
 * @author masoud
 */
public enum TtFileFetchManner {

    DirectUpload("بارگذاری مستقیم"),
    UploadLink("لینک بارگذاری");

    private final String title;

    private TtFileFetchManner(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

}
