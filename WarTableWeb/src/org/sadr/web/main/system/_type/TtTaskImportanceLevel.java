package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskImportanceLevel {

    Unknown("نامشخص"),
    Normal("عادی"),
    Important("مهم"),
    Vital("حساس"),;
    private final String title;

    private TtTaskImportanceLevel(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
