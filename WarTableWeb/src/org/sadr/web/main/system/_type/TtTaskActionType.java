package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskActionType {

    Login_Logout("ورود/خروج - Login/Logout"),
    Add_Edit("ثبت/ویرایش - Add/Edit"),
    Report("گزارش - Report"),
    Error("خطا - Error"),
    Delete("حذف - Delete"),
    User_Management("مدیریت کاربر - User Management"),;
    private final String title;

    private TtTaskActionType(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
