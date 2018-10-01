package org.sadr.web.main.admin._type;

/**
 * @author masoud
 */
public enum TtUserIpRangeType {

    All("همه آدرس ها"),
    FirstSignin("آدرس اولین ورود"),
    One("تعیین یک آدرس"),
    Range("تعیین بازه مجاز");

    private final String title;

    private TtUserIpRangeType(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
