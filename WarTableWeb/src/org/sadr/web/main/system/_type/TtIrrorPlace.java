package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtIrrorPlace {

    AspectMain("مجوزدهنده اصلی"),
    AspectSuperAdmin("مجوزدهنده مدیر ارشد"),
    JsonAspectMain("مجوزدهنده اصلی (اجکس)"),
    JsonAspectSuperAdmin("مجوزدهنده مدیر ارشد (اجکس)"),
    IrrorController("مرکز مدیریت خطا"),
    Controller("کنترلر"),
    AspectLogManager("مجوز دهنده مدیریت رویداد"),
    ;
    private final String title;

    private TtIrrorPlace(String k) {
        title = k;
    }

    public String getTitle() {
        return title;
    }
}
