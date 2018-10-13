package org.sadr.web.main._core.propertor;

import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.propertor._type.TtPropertorInBackupList;
import org.sadr.web.main._core.propertor._type.TtPropertorMode;
import org.sadr.web.main._core.propertor._type.TtPropertorType;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryService;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author MSD
 */
public class PropertorInBackup extends PropertorAgent {

    private static PropertorInBackup instance = new PropertorInBackup();

    private PropertorInBackup() {
        init(this.getClass().getName(), TtPropertorMode.Db);
    }

    public static PropertorInBackup getInstance() {
        return instance;
    }

    ///////####//////////##///////////////////
    private Registery propertor;
    private RegisteryService registeryService;

    //////=================================================

    @Override
    protected void initDbMode() {
        try {
            if (registeryService == null) {
                registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadFromDb() {
        try {
            propertor = registeryService.findByKey(propKey);
            props.clear();
            if (propertor == null) {
                propertor = new Registery();
                propertor.setKey(propKey);
                for (TtPropertorInBackupList value : TtPropertorInBackupList.values()) {
                    props.setProperty(value.getKey(), String.valueOf(value.getDefaultValueStr()));
                }
                StringWriter stringWriter = new StringWriter();
                props.store(stringWriter, "Control Configuration Parameters in DB, Date: " + ParsCalendar.getInstance().getShortDateTime());
                propertor.setValueJson(stringWriter.toString());
                registeryService.save(propertor);
            } else {
                props.load(new StringReader(propertor.getValueJson()));
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void storeInDb() {
        try {
            if (propertor == null) {
                propertor = new Registery();
                propertor.setKey(propKey);
                StringWriter stringWriter = new StringWriter();
                props.store(stringWriter, "Control Configuration Parameters in DB, Date: " + ParsCalendar.getInstance().getShortDateTime());
                propertor.setValueJson(stringWriter.toString());
                registeryService.save(propertor);
            } else {
                StringWriter stringWriter = new StringWriter();
                props.store(stringWriter, "Control Configuration Parameters in DB, Date: " + ParsCalendar.getInstance().getShortDateTime());
                propertor.setValueJson(stringWriter.toString());
                registeryService.update(propertor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetProperties() {
        props.clear();
        for (TtPropertorInBackupList value : TtPropertorInBackupList.values()) {
            props.setProperty(value.getKey(), String.valueOf(value.getDefaultValueStr()));
        }
    }

    @Override
    public void updateProperties() {
        for (TtPropertorInBackupList value : TtPropertorInBackupList.values()) {
            if (!props.containsKey(value.getKey())) {
                props.setProperty(value.getKey(), value.getDefaultValueStr());
            }
        }
    }

    ///////*************************************************
    public boolean setProperty(TtPropertorInBackupList trt, String value) {
        return setProperty(trt.getKey(), value);
    }

    public boolean setOn(TtPropertorInBackupList trt) {
        return setOn(trt.getKey());
    }

    public boolean setOff(TtPropertorInBackupList trt) {
        return setOff(trt.getKey());

    }

    public String getProperty(TtPropertorInBackupList trt) {
        return getProperty(trt.getKey());
    }

    public int getPropertyInt(TtPropertorInBackupList trt) {
        try {
            return Integer.valueOf(getProperty(trt));
        } catch (Exception e) {
            return (int) trt.getDefaultValue();
        }
    }

    public boolean getPropertyBool(TtPropertorInBackupList trt) {
        try {
            return Boolean.parseBoolean(getProperty(trt));
        } catch (Exception e) {
            return (boolean) trt.getDefaultValue();
        }
    }

    public boolean isOnProperty(TtPropertorInBackupList trt) {
        return isOnProperty(trt.getKey());
    }

    public boolean isEqualProperty(TtPropertorInBackupList trt, String prop) {
        return isEqualProperty(trt.getKey(), prop);
    }

    public boolean isEqualPropertiesOr(TtPropertorInBackupList trt, String... prop) {
        return isEqualPropertiesOr(trt.getKey(), prop);
    }

    ///======================================
    public void addProperty(TtPropertorInBackupList trt, String value) {
        if (trt.getType() != TtPropertorType.List) {
            return;
        }
        addProperty(trt.getKey(), value);
    }

    public String[] getPropertyList(TtPropertorInBackupList trt) {
        if (trt.getType() != TtPropertorType.List) {
            return null;
        }
        return getPropertyList(trt.getKey());
    }

    public boolean hasProperty(TtPropertorInBackupList trt, String prop) {
        if (trt.getType() != TtPropertorType.List) {
            return false;
        }
        return hasProperty(trt.getKey(), prop);
    }

}
