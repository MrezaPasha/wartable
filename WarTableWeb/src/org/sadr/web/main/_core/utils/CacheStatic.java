package org.sadr.web.main._core.utils;

import org.sadr.web.main.system.log.signin.SigninLog;

/**
 * @author MSD
 */
public class CacheStatic {


    ///==================================== OTHERS
    public static final int LOOP_PREVENTION_THRESHOLD = 10;
    ///
    ///==================================== SIGNIN LOG
    private static SigninLog signinLog;
    private static String signinMainIp;
    private static boolean signinIsIpChanged;
    private static int SigninLogFailedCount;

    public static SigninLog getSigninLog() {
        return signinLog;
    }

    public static void setSigninLog(SigninLog signinLog) {
        CacheStatic.signinLog = signinLog;
    }

    public static int getSigninLogFailedCount() {
        return SigninLogFailedCount;
    }

    public static void setSigninLogFailedCount(int signinLogFailedCount) {
        SigninLogFailedCount = signinLogFailedCount;
    }

    public static boolean isSigninIsIpChanged() {
        return signinIsIpChanged;
    }

    public static void setSigninIsIpChanged(boolean signinIsIpChanged) {
        CacheStatic.signinIsIpChanged = signinIsIpChanged;
    }

    public static String getSigninMainIp() {
        return signinMainIp;
    }

    public static void setSigninMainIp(String signinMainIp) {
        CacheStatic.signinMainIp = signinMainIp;
    }

    ///==================================== SESSION
    private static int sessionCount = 0;
    private static int userCount = 0;
    private static int visitTotal;
    private static int visitThisMonth;
    private static int visitLastMonth;
    private static int visitThisWeek;
    ///
    private static int visitLastWeek;
    private static int visitLastDay;

    ///==================================== VISIT COUNT
    public static int getVisitTotal() {
        return visitTotal;
    }

    public static int getVisitThisMonth() {
        return visitThisMonth;
    }

    public static int getVisitLastMonth() {
        return visitLastMonth;
    }

    public static int getVisitThisWeek() {
        return visitThisWeek;
    }

    public static int getVisitLastWeek() {
        return visitLastWeek;
    }

    public static int getVisitLastDay() {
        return visitLastDay;
    }

    ///==================================== SESSION
    public static void addUserCount() {
        userCount++;
    }

    public static void addSessionCount() {
        sessionCount++;
    }

    public static int getTotalSessionCount() {
        return sessionCount;
    }

    public static int getTotalGuestCount() {
        return sessionCount - userCount;
    }

    public static int getTotalUserCount() {
        return userCount;
    }

    public static void updateSessionStatistics(int tot, int currentMon, int lastMon, int currentWe, int lastWe, int lastD) {
        sessionCount = 0;
        userCount = 0;
        visitTotal = tot;
        visitThisMonth = currentMon;
        visitLastMonth = lastMon;
        visitThisWeek = currentWe;
        visitLastWeek = lastWe;
        visitLastDay = lastD;
    }

}
