package org.sadr.web.main.system.log.daily;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.ParsCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("لاگ روزانه")
@Entity
@Table(name = "Web.System.Log.DailyLog")
public class DailyLog extends GenericDataModel<DailyLog> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String CHROME = "chrome";public static final String INTERNET_EXPLORER = "internetExplorer";public static final String FIREFOX = "firefox";public static final String OPERA = "opera";public static final String NETSCAPE = "netscape";public static final String MOBILE = "mobile";public static final String DESKTOP = "desktop";public static final String PAGE_COUNT = "pageCount";public static final String VISIT_COUNT = "visitCount";public static final String USER_VISIT_COUNT = "userVisitCount";public static final String GUEST_VISIT_COUNT = "guestVisitCount";public static final String DAY_DATE = "dayDate";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $DAY_DATE_S_M = "dayDateSM";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    public DailyLog() {
    }

    public DailyLog(int visitCount, int userVisitCount, int guestVisitCount) {
        this.visitCount = visitCount;
        this.userVisitCount = userVisitCount;
        this.guestVisitCount = guestVisitCount;
        dayDate = ParsCalendar.getInstance().getShortDate();
    }

    @PersianName("کروم")
    private int chrome;

    @PersianName("اینترنت اکسپلورر")
    private int internetExplorer;

    @PersianName("فایر فاکس")
    private int firefox;

    @PersianName("اپرا")
    private int opera;

    @PersianName("نت اسکیپ")
    private int netscape;

    @PersianName("تلفن همراه")
    private int mobile;

    @PersianName("دسکتاپ")
    private int desktop;

    @PersianName("تعداد صفحه")
    private int pageCount;

    @PersianName("تعداد مشاهده")
    private int visitCount;

    @PersianName("تعداد مشاهده کاربر")
    private int userVisitCount;

    @PersianName("تعداد مشاهده مهمان")
    private int guestVisitCount;

    @PersianName("تاریخ")
    private String dayDate;

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void addCount(int count) {
        this.visitCount += count;
    }

    public int getUserVisitCount() {
        return userVisitCount;
    }

    public void setUserVisitCount(int userVisitCount) {
        this.userVisitCount = userVisitCount;
    }

    public void addUserCount(int userCount) {
        this.userVisitCount += userCount;
    }

    public int getGuestVisitCount() {
        return guestVisitCount;
    }

    public void setGuestVisitCount(int guestVisitCount) {
        this.guestVisitCount = guestVisitCount;
    }

    public void addGuestCount(int guestCount) {
        this.guestVisitCount += guestCount;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayDateSM() {
        return ParsCalendar.getInstance().getDateInMonthString(dayDate);
    }

    public int getChrome() {
        return chrome;
    }

    public void setChrome(int chrome) {
        this.chrome = chrome;
    }

    public int getInternetExplorer() {
        return internetExplorer;
    }

    public void setInternetExplorer(int internetExplorer) {
        this.internetExplorer = internetExplorer;
    }

    public int getFirefox() {
        return firefox;
    }

    public void setFirefox(int firefox) {
        this.firefox = firefox;
    }

    public int getOpera() {
        return opera;
    }

    public void setOpera(int opera) {
        this.opera = opera;
    }

    public int getNetscape() {
        return netscape;
    }

    public void setNetscape(int netscape) {
        this.netscape = netscape;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getDesktop() {
        return desktop;
    }

    public void setDesktop(int desktop) {
        this.desktop = desktop;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
