package org.sadr.web.main._core.propertor._type;


import org.sadr._core.utils.SpringMessager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author masoud
 */
public enum TtPropertorInLogList {

    ///==================== sys config
    SystemName("N.propertor.system.name", "SadrServer", TtPropertorType.String, TtPropertorInLogSection.ServerSpec, false), ///
    SystemHostName("N.propertor.system.host.name", "SadrHost", TtPropertorType.String, TtPropertorInLogSection.ServerSpec, false), ///
    SystemVersion("N.propertor.system.version", "1.0.0", TtPropertorType.String, TtPropertorInLogSection.ServerSpec, false), ///
//    SystemIp("N.propertor.system.ip", "127.0.0.1", TtPropertorType.String, TtPropertorInLogSection.ServerSpec, false), ///

    ///----
    RemoteServerIp("N.propertor.remote.connection.info.ip", "127.0.0.1", TtPropertorType.String, TtPropertorInLogSection.ConnectionInfo, false), ///
    RemoteServerPort("N.propertor.remote.connection.info.port", "1234", TtPropertorType.Integer, TtPropertorInLogSection.ConnectionInfo, false), ///
    RemoteServerUsername("N.propertor.remote.connection.info.username", "username", TtPropertorType.String, TtPropertorInLogSection.ConnectionInfo, false), ///
    RemoteServerPassword("N.propertor.remote.connection.info.password", "password", TtPropertorType.String, TtPropertorInLogSection.ConnectionInfo, false), ///


    ;

    public static TtPropertorInLogList getByOrdinal(int ordinal) {
        for (TtPropertorInLogList value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

    private final String spMessage;
    private final Object defaultValue;
    private final TtPropertorType type;
    private final TtPropertorInLogSection section;
    private final boolean isNeedRedeploy;

    TtPropertorInLogList(String m, Object dv, TtPropertorType tpt, TtPropertorInLogSection tps, boolean inr) {
        spMessage = m;
        defaultValue = dv;
        type = tpt;
        section = tps;
        isNeedRedeploy = inr;
    }


    public Object getDefaultValue() {
        return defaultValue;
    }


    public Object getDefaultValueInForm() {
        if (defaultValue.getClass().getSimpleName().startsWith("Tt")) {
            Method method;
            try {
                method = defaultValue.getClass().getMethod("ordinal");
                return method.invoke(defaultValue);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public String getDefaultValueStr() {
        if (defaultValue.getClass().getModifiers() == 16401) {
            Method method;
            try {
                method = defaultValue.getClass().getMethod("ordinal");
                return String.valueOf(method.invoke(defaultValue));

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(defaultValue);
    }

    public TtPropertorType getType() {
        return type;
    }

    public TtPropertorInLogSection getSection() {
        return section;
    }

    public int getId() {
        return ordinal();
    }

    public HashMap<String, String> getTtValues() {
        if (type == TtPropertorType.TtVariable && defaultValue != null && defaultValue.getClass().getSimpleName().startsWith("Tt")) {
            Method method;
            HashMap<String, String> map = new HashMap<>();
            String id;
            String title;
            try {
                method = defaultValue.getClass().getMethod("values");
                Object[] invoke = (Object[]) method.invoke(defaultValue);
                for (Object o : invoke) {
                    method = defaultValue.getClass().getMethod("ordinal");
                    id = String.valueOf(method.invoke(o));
                    method = defaultValue.getClass().getMethod("getTitle");
                    title = (String) method.invoke(o);
                    map.put(id, title);
                }
                return map;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public String getKey() {
        return spMessage;
    }

    public boolean isIsNeedRedeploy() {
        return isNeedRedeploy;
    }
}
