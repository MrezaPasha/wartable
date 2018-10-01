package org.sadr.web.main._core.utils._type;

/**
 * @author masoud
 */
public enum TtNotice {

    Info("alert-info", "اعلان"),
    Success("alert-success", "موفق"),
    Danger("alert-danger", "خطا"),
    Warning("alert-warning", "هشدار");

    private final String title;
    private final String cssClss;

    private TtNotice(String c, String k) {
        title = k;
        cssClss = c;
    }

    public String getCssClass() {
        return cssClss;
    }

    public String getTitle() {
        return title;
    }

}
