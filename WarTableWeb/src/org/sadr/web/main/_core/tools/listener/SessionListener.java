package org.sadr.web.main._core.tools.listener;

import org.sadr._core.utils.OutLog;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author masoud
 */
public class SessionListener implements HttpSessionListener {

    private static final Map<String, Object[]> sessions = new HashMap<>();
//    private static String defaultFont;
//    private static String defaultStyle;

    /////////////////////////////// OVERRIDES
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setMaxInactiveInterval(PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserSessionTimeOut));
        sessions.put(session.getId(), new Object[]{session, null});
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    ///////////////////////////////////// STATIC OPERATIONS
    public static boolean invalidate(String sessionId) {
        try {
            HttpSession session = (HttpSession) sessions.get(sessionId)[0];
            if (session != null) {
                session.invalidate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean invalidate(long userId) {
        ConcurrentHashMap<String, Object[]> chm = new ConcurrentHashMap(sessions);
        Map.Entry<String, Object[]> entry;
        Iterator<Map.Entry<String, Object[]>> it = chm.entrySet().iterator();
        while (it.hasNext()) {
            entry = it.next();
            try {
                if (((Long) entry.getValue()[1]) == userId) {
                    invalidate(entry.getKey());
                }
            } catch (Exception e) {
            }
        }
        return true;
    }

    public static boolean isUserInSession(long userId) {
        ConcurrentHashMap<String, Object[]> chm = new ConcurrentHashMap(sessions);
        Map.Entry<String, Object[]> entry;
        Iterator<Map.Entry<String, Object[]>> it = chm.entrySet().iterator();
        while (it.hasNext()) {
            entry = it.next();
            try {
                if (entry.getValue()[1] != null && ((Long) entry.getValue()[1]) == userId) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean invalidateAll() {
        ConcurrentHashMap<String, Object[]> chm = new ConcurrentHashMap(sessions);
        Map.Entry<String, Object[]> entry;
        Iterator<Map.Entry<String, Object[]>> it = chm.entrySet().iterator();
        while (it.hasNext()) {
            entry = it.next();
            try {
                invalidate(entry.getKey());
            } catch (Exception e) {
            }
        }
        return true;
    }

    public static boolean setUser(String sessionId, long userId) {
        try {
            sessions.get(sessionId)[1] = userId;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeUser(String sessionId) {
        try {
            sessions.get(sessionId)[1] = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void refreshUiSetting(String defaultFont, String defaultStyle, long userId) {
        ConcurrentHashMap<String, Object[]> chm = new ConcurrentHashMap(sessions);
        Map.Entry<String, Object[]> entry;
        Iterator<Map.Entry<String, Object[]>> it = chm.entrySet().iterator();
        while (it.hasNext()) {
            entry = it.next();
            if (entry.getValue()[1] != null && ((Long) entry.getValue()[1]) == userId) {
                ((HttpSession) entry.getValue()[0]).setAttribute("font", defaultFont);
                ((HttpSession) entry.getValue()[0]).setAttribute("style", defaultStyle);
            }
        }
    }

    //////////////////////////////////////// STATIC GETTERS

    public static HttpSession getSession(String sessionId) {
        return (HttpSession) sessions.get(sessionId)[0];
    }


    public static List<Long> getOnlineUsersId() {
        ConcurrentHashMap<String, Object[]> chm = new ConcurrentHashMap(sessions);
        Map.Entry<String, Object[]> entry;
        Iterator<Map.Entry<String, Object[]>> it = chm.entrySet().iterator();
        List<Long> ids = new ArrayList<>();
        while (it.hasNext()) {
            entry = it.next();
            try {
                if (((Long) entry.getValue()[1]) != 0) {
                    ids.add((Long) entry.getValue()[1]);
                }
            } catch (Exception e) {
            }
        }
        return ids;
    }



}
