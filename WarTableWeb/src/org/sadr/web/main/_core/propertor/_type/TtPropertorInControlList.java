package org.sadr.web.main._core.propertor._type;


import org.sadr._core.utils.SpringMessager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author masoud
 */
public enum TtPropertorInControlList {

    ///==================== sys config
    ///
    LogGuest("N.propertor.log.guest", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    LogClient("N.propertor.log.client", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    LogMaster("N.propertor.log.master", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    LogAdmin("N.propertor.log.admin", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    LogSuperAdmin("N.propertor.log.super.admin", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    LogLogManager("N.propertor.log.log.manager", true, TtPropertorType.OnOff, TtPropertorInControlSection.Log, false),
    ///
    FileMaxUploadSize("N.propertor.file.max.upload.size", 500000, TtPropertorType.Integer, TtPropertorInControlSection.File, true),
    ///

    SiteInDevelopingForGuests("N.propertor.developing.site.in.developing.guests", false, TtPropertorType.OnOff, TtPropertorInControlSection.Developing, false),
    SiteInDevelopingForClient("N.propertor.developing.site.in.developing.client", false, TtPropertorType.OnOff, TtPropertorInControlSection.Developing, false),
    SiteInDevelopingForMasters("N.propertor.developing.site.in.developing.masters", false, TtPropertorType.OnOff, TtPropertorInControlSection.Developing, false),
    SiteInDevelopingForAdmins("N.propertor.developing.site.in.developing.admins", false, TtPropertorType.OnOff, TtPropertorInControlSection.Developing, false),;



    private final String spMessage;
    private final Object defaultValue;
    private final TtPropertorType type;
    private final TtPropertorInControlSection section;
    private final boolean isNeedRedeploy;

    TtPropertorInControlList(String m, Object dv, TtPropertorType tpt, TtPropertorInControlSection tps, boolean inr) {
        spMessage = m;
        defaultValue = dv;
        type = tpt;
        section = tps;
        isNeedRedeploy = inr;
    }

    public static TtPropertorInControlList getByOrdinal(int ordinal) {
        for (TtPropertorInControlList value : values()) {
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

    public TtPropertorInControlSection getSection() {
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
