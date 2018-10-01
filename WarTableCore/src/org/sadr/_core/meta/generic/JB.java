/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.meta.generic;

import java.util.Arrays;

/**
 * @author dev1
 */
public class JB {

    private JB() {
    }

    private JB(Class clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }

    private Class clazz;
    private String name;
    private String[] fields;
    private JB[] jbs;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public static JB init(Class clazz, String name) {
        return new JB(clazz, name);
    }

    public static JB init() {
        return new JB();
    }

    public JB set(String... fields) {
        this.fields = fields;
        return this;
    }

    public JB setJbs(JB... jbs) {
        this.jbs = jbs;
        return this;
    }

    @Override
    public String toString() {
        return "JB{" + "fields=" + Arrays.toString(fields) + ", jbs=" + Arrays.toString(jbs) + '}';
    }

    public JB getByClassAndName(Class clazz, String name) {
        if (jbs != null) {
            for (JB jb : jbs) {
//                OutLog.p(clazz.getName(), name, jb.getClazz().getName(), jb.getName());
                if (jb.getClazz() == clazz && name.equals(jb.getName())) {
                    return jb;
                }
            }
        }
        return null;
    }

    public String[] getFieldsAndJbs() {
        if (jbs == null || jbs.length == 0) {
            return fields;
        } else if (fields == null || fields.length == 0) {
            String[] sx = new String[jbs.length];
            for (int i = 0; i < jbs.length; i++) {
                sx[i] = jbs[i].getName();
            }
            return sx;
        } else {
            int ii = jbs.length + fields.length;
            String[] sx = new String[ii];
            int i = 0, j = 0;
            for (; i < fields.length; i++) {
                sx[i] = fields[i];
            }
            for (; i < ii; j++, i++) {
                sx[i] = jbs[j].getName();
            }
            return sx;
        }
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public String[] getFields() {
        return fields;
    }

    public JB[] getJbs() {
        return jbs;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
