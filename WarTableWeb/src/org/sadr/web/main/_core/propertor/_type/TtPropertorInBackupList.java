package org.sadr.web.main._core.propertor._type;


import org.sadr._core.utils.SpringMessager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author masoud
 */
public enum TtPropertorInBackupList {

    ///==================== backup
    AutoBackupOn("N.propertor.auto.backup.on", false, TtPropertorType.OnOff, TtPropertorInBackupSection.AutoBackup, false), ///
    AutoBackupDay("N.propertor.auto.backup.day", "", TtPropertorType.String, TtPropertorInBackupSection.AutoBackup, false), ///
    AutoBackupHourMin("N.propertor.auto.backup.hour.min", "09:30", TtPropertorType.String, TtPropertorInBackupSection.AutoBackup, false), ///
    //
    AutoRestoreOn("N.propertor.auto.restore.on", false, TtPropertorType.OnOff, TtPropertorInBackupSection.AutoRestore, false), ///
    AutoRestoreHourMin("N.propertor.auto.restore.hour.min", "09:30", TtPropertorType.String, TtPropertorInBackupSection.AutoRestore, false), ///
    AutoRestoreBackupId("N.propertor.auto.restore.backup.id", 0, TtPropertorType.Integer, TtPropertorInBackupSection.AutoRestore, false), ///


    ;

    public static TtPropertorInBackupList getByOrdinal(int ordinal) {
        for (TtPropertorInBackupList value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

    private final String spMessage;
    private final Object defaultValue;
    private final TtPropertorType type;
    private final TtPropertorInBackupSection section;
    private final boolean isNeedRedeploy;

    TtPropertorInBackupList(String m, Object dv, TtPropertorType tpt, TtPropertorInBackupSection tps, boolean inr) {
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

    public TtPropertorInBackupSection getSection() {
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
