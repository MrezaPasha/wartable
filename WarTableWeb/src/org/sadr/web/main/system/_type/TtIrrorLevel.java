package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtIrrorLevel {

    Trace("ردگیری [TRACE]"),
    Debug("اشکال زدایی [DEBUG]"),
    Info("اطلاع رسانی [INFO]"),
    Warn("هشدار [WARN]"),
    Error("خطا [ERROR]"),
    Fatal("مهلک [FATAL]");
    private final String title;

    private TtIrrorLevel(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
