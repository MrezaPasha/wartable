package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskSensitivity {

    Trace("ردگیری"),
    Debug("اشکال زدایی"),
    Info("اطلاع رسانی"),
    Warn("هشدار"),
    Error("خطا"),
    Fatal("مهلک")
    ;
    private final String title;

    private TtTaskSensitivity(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
