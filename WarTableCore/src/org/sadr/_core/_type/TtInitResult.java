package org.sadr._core._type;

/**
 * @author masoud
 */
public enum TtInitResult {

    Success("موفق"),
    Exception("خطا"),
    NotDefined("تعریف نشده"),;

    private final String title;

    private TtInitResult(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
