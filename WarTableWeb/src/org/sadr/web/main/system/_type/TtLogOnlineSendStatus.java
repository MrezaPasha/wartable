package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtLogOnlineSendStatus {

    NotSend("ارسال نشده"),
    Sending("درحال ارسال"),
    Sent("ارسال شده");


    private final String title;

    private TtLogOnlineSendStatus(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
