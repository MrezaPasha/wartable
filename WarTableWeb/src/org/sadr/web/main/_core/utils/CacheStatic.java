package org.sadr.web.main._core.utils;

import org.sadr.web.main.system.log.signin.SigninLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MSD
 */
public class CacheStatic {


    ///==================================== OTHERS
    public static final int LOOP_PREVENTION_THRESHOLD = 10;
    ///
    ///==================================== SIGNIN LOG
    private static Map<Long, List<SigninLog>> signinLogListMap = new HashMap<>();

    public static void setSigninLog(Long userId, List<SigninLog> signinLogs) {
        if (signinLogListMap.containsKey(userId)) {
            signinLogListMap.replace(userId, signinLogs);
        } else {
            signinLogListMap.put(userId, signinLogs);
        }
    }

    public static void removeSigninLog(Long userId) {
        signinLogListMap.remove(userId);
    }

    public static List<SigninLog> getSigninLog(Long userId) {
        return signinLogListMap.get(userId);
    }

    ///==================================== SESSION
    private static int sessionCount = 0;
    private static int userCount = 0;

    private static boolean developingMode = false;


    ///==================================== SESSION
    public static void addUserCount() {
        userCount++;
    }

    public static void addSessionCount() {
        sessionCount++;
    }


    public static boolean isDevelopingMode() {
        return developingMode;
    }

    public static void setDevelopingMode(boolean developingMode) {
        CacheStatic.developingMode = developingMode;
    }
}
