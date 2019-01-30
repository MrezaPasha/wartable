package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtFieldStatus {

    Unknown("نامشخص"),
    NoChange("بدون تغییر"),
    Changed("تغییر کرده"),
    New("فیلد جدید"),
    RemoveFromModel("حذف از مدل"),
    RemoveFromDB("حذف از پایگاه"),
    RemoveFromModelAndDB("حذف از هر دو"),;

    private final String title;

    private TtFieldStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
