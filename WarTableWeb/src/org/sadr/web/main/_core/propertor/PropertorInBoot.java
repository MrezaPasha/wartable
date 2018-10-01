
package org.sadr.web.main._core.propertor;

import org.sadr._core._type.TtProjectPath;
import org.sadr._core.utils.Environment;
import org.sadr.web.main._core.propertor._type.TtPropertorInBootList;
import org.sadr.web.main._core.propertor._type.TtPropertorMode;
import org.sadr.web.main._core.propertor._type.TtPropertorType;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author MSD
 */
public class PropertorInBoot extends PropertorAgent {

    private static PropertorInBoot instance = new PropertorInBoot();

    private PropertorInBoot() {
        init(Environment.getInstance().getProjectRegistryAP() + Environment.FILE_SEPARATOR + "boot.properties",
                TtPropertorMode.File);
    }

    public static PropertorInBoot getInstance() {
        return instance;
    }
    ///////####//////////##///////////////////

    //////=================================================
    @Override
    protected void loadFromFile() {
        try {
            File f = new File(Environment.getInstance().getCoreWebInfAP(TtProjectPath.Config));
            if (!f.exists()) {
                f.mkdirs();
            }
            f = new File(propKey);
            if (!f.exists()) {
                f.createNewFile();
                for (TtPropertorInBootList value : TtPropertorInBootList.values()) {
                    props.setProperty(value.getKey(), value.getDefaultValueStr());
                }
                OutputStream out = new FileOutputStream(f);
                props.store(new OutputStreamWriter(out, "UTF-8"), "IRAS Propertor [CORE]");
            }
            props.load(new InputStreamReader(new FileInputStream(f), Charset.forName("UTF-8")));
        } catch (Exception e) {
        }
    }

    @Override
    public void resetProperties() {
        props.clear();
        for (TtPropertorInBootList value : TtPropertorInBootList.values()) {
            props.setProperty(value.getKey(), value.getDefaultValueStr());
        }
    }

    @Override
    public void updateProperties() {
        for (TtPropertorInBootList value : TtPropertorInBootList.values()) {
            if (!props.containsKey(value.getKey())) {
                props.setProperty(value.getKey(), value.getDefaultValueStr());
            }
        }
    }

    ///////*************************************************
    public boolean setProperty(TtPropertorInBootList trt, String value) {
        return setProperty(trt.getKey(), value);
    }

    public boolean setOn(TtPropertorInBootList trt) {
        return setOn(trt.getKey());
    }

    public boolean setOff(TtPropertorInBootList trt) {
        return setOff(trt.getKey());

    }

    public String getProperty(TtPropertorInBootList trt) {
        return getProperty(trt.getKey());
    }

    public int getPropertyInt(TtPropertorInBootList trt) {
        try {
            return Integer.valueOf(getProperty(trt));

        } catch (Exception e) {
            return (int) trt.getDefaultValue();
        }
    }

    public boolean isOnProperty(TtPropertorInBootList trt) {
        return isOnProperty(trt.getKey());
    }

    public boolean isEqualProperty(TtPropertorInBootList trt, String prop) {
        return isEqualProperty(trt.getKey(), prop);
    }

    public boolean isEqualPropertiesOr(TtPropertorInBootList trt, String... prop) {
        return isEqualPropertiesOr(trt.getKey(), prop);
    }

    ///======================================
    public void addProperty(TtPropertorInBootList trt, String value) {
        if (trt.getType() != TtPropertorType.List) {
            return;
        }
        addProperty(trt.getKey(), value);
    }

    public String[] getPropertyList(TtPropertorInBootList trt) {
        if (trt.getType() != TtPropertorType.List) {
            return null;
        }
        return getPropertyList(trt.getKey());
    }

    public boolean hasProperty(TtPropertorInBootList trt, String prop) {
        if (trt.getType() != TtPropertorType.List) {
            return false;
        }
        return hasProperty(trt.getKey(), prop);
    }

}
