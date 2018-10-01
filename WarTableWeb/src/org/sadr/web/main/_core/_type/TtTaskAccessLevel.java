package org.sadr.web.main._core._type;

/**
 * @author masoud
 */
public enum TtTaskAccessLevel {

    Free4Gusts("آزاد برای مهمان و بالاتر"),
    Free4Users("آزاد برای همه کاربران"),
    Grant("قابل مجوزدهی"),
    OnlyAdminAndSuserAdmin("فقط مدیران"),
    OnlySuperAdmin("فقط مدیر ارشد"),
    OnlyFree4Gusts("فقط برای مهمان");

    private final String title;

    private TtTaskAccessLevel(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
