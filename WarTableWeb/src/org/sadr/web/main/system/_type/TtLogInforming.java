package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtLogInforming {

    NoThing("هیچ"),
    Calling("تماس"),
    Email("ارسال ایمیل"),
    Email_Calling("ارسال ایمیل و تماس"),
    Sms("ارسال پیامک"),
    Sms_Email("ارسال پیامک و ایمیل"),
    Sms_Calling("ارسال پیامک و تماس"),
    Telegram("تلگرام");

    private final String title;

    private TtLogInforming(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
