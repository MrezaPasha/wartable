package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtModelStatus {

    Unknown("نامشخص"),
    NoChange("بدون تغییر"),
    Changed("تغییر کرده"),
    RemoveFromModel("حذف از مدل"),
    RemoveFromDB("حذف از پایگاه"),
    RemoveFromModelAndDB("حذف از هر دو"),;

    private final String title;

    private TtModelStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
