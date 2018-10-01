package org.sadr.web.main._core.propertor._type;


import org.sadr._core.utils.SpringMessager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author masoud
 */
public enum TtPropertorInBootList {

    ///==================== sys config
    HibernateSHowSQL("N.propertor.console.hibernate.show_sql", false, TtPropertorType.OnOff, TtPropertorInBootSection.Console, true),
    OutLogOn("N.propertor.console.outlog.on", true, TtPropertorType.OnOff, TtPropertorInBootSection.Console, false),
    ///
    InitEmergency("N.propertor.deploy.init.emergency", false, TtPropertorType.OnOff, TtPropertorInBootSection.Deploy, true),
    DeployMode("N.propertor.deploy.mode", TtPropertorInBoot_DeployMode.LocalTest, TtPropertorType.TtVariable, TtPropertorInBootSection.Deploy, false),
    ///
    ;
    private final String spMessage;
    private final Object defaultValue;
    private final TtPropertorType type;
    private final TtPropertorInBootSection section;
    private final boolean isNeedRedeploy;

    TtPropertorInBootList(String m, Object dv, TtPropertorType tpt, TtPropertorInBootSection tps, boolean inr) {
        spMessage = m;
        defaultValue = dv;
        type = tpt;
        section = tps;
        isNeedRedeploy = inr;
    }

    public static TtPropertorInBootList getByOrdinal(int ordinal) {
        for (TtPropertorInBootList value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
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

    public TtPropertorInBootSection getSection() {
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
