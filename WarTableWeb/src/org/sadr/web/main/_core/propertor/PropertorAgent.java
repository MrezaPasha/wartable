/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main._core.propertor;

import org.sadr._core.utils.OutLog;
import org.sadr.web.main._core.propertor._type.TtPropertorMode;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author MSD
 */
public class PropertorAgent {

    protected final Properties props = new Properties();

    protected String propKey;
    protected TtPropertorMode mode;

    File f;
    ///////////////////////////////////// ======

    public void init(String propKey, TtPropertorMode mode) {
        this.mode = mode;
        this.propKey = propKey;
        if (mode == TtPropertorMode.Db) {
            initDbMode();
        } else {
            try {
                f = new File(propKey);
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }
            } catch (IOException e) {

            }

        }
    }

    protected void initDbMode() {
    }
    ///////////////////////////////////// ======

    public void store() {
        if (mode == TtPropertorMode.Db) {
            storeInDb();
        } else {
            storeInFile();
        }
    }

    protected void storeInDb() {
        OutLog.pl("Store In DB.");
    }

    protected void storeInFile() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(f);
            props.store(new OutputStreamWriter(out, "UTF-8"), "IRAS configuration settings.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    System.out.println("IOException: Could not close myApp.properties output stream; " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
    ///////////////////////////////////// ======

    public void load() {
        if (mode == TtPropertorMode.Db) {
            loadFromDb();
        } else {
            loadFromFile();
        }
    }

    protected void loadFromDb() {
        OutLog.pl("Load From DB.");
    }

    protected void loadFromFile() {
        OutLog.pl("Load From DB.");
    }

    ///////////////////////////////////// ======
    public void resetProperties() {
    }

    public void updateProperties() {
    }

    public boolean setProperty(String key, String value) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        props.setProperty(key, value);
        return true;
    }

    public boolean setOn(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        props.setProperty(key, "true");
        return true;
    }

    public boolean setOff(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        props.setProperty(key, "false");
        return true;
    }

    public String getProperty(String key) {
        try {
            return props.getProperty(key).trim();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isOnProperty(String key) {
        return "true".equals(props.getProperty(key));
    }

    public boolean isEqualProperty(String key, String prop) {
        return prop != null && prop.equals(props.getProperty(key));
    }

    public boolean isEqualPropertiesOr(String key, String... prop) {
        if (prop == null || prop.length == 0) {
            return false;
        }
        for (String p : prop) {
            if (p != null && p.equals(props.getProperty(key))) {
                return true;
            }
        }
        return false;
    }


    ///======================================
    public void addProperty(String key, String value) {
        String s;
        try {
            s = props.getProperty(key).trim();
        } catch (Exception e) {
            s = null;
        }
        if (s == null) {
            return;
        }
        if (s.isEmpty()) {
            s = value;
        } else {
            s += "," + value;
        }
        props.setProperty(key, s);
    }

    public String[] getPropertyList(String key) {
        try {
            return props.getProperty(key).trim().split(",");
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasProperty(String key, String prop) {
        try {
            return props.getProperty(key).trim().contains(prop);
        } catch (Exception e) {
            return false;
        }
    }

}
