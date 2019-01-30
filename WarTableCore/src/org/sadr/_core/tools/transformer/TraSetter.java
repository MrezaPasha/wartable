/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.tools.transformer;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dev1
 */
public class TraSetter {

    public TraSetter() {
    }

    public TraSetter(TraSetter ts) {
        if (ts != null) {
            this.clazz = ts.getClazz();
            this.className = ts.className;
            this.object = ts.getObject();
            this.collObject = ts.getCollObject();
            this.collClassName = ts.getCollClassName();
            this.collClazz = ts.getCollClazz();

        }
    }

    private String name;
    private String setterName;
    private String getterName;
    private String key;
    private Class<?> clazz;
    private String className;
    private Object object;
    private int tupleIndex;
    /// for items that are SetCollections
    private Object collObject;
    private String collClassName;
    private Class<?> collClazz;


    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public void invoke(Object[] os) {
        Object tup = os[tupleIndex];
        if (tup == null) {
            try {
                Class<?> tClazz = clazz.getMethod(getterName).getReturnType();
                if (tClazz.isPrimitive()) {
                    clazz.getMethod(setterName, clazz.getMethod(getterName).getReturnType()).
                        invoke(object, 0);
                } else {
                    clazz.getMethod(setterName, clazz.getMethod(getterName).getReturnType()).
                        invoke(object, (Object) null);
                }
            } catch (NoSuchMethodException e) {
                try {
                    Class<?> tClazz = clazz.getMethod(getterName).getReturnType();
                    if (tClazz.equals(Integer.class)) {
                        tClazz = int.class;
                    } else if (tClazz.equals(int.class)) {
                        tClazz = Integer.class;
                    } else if (tClazz.equals(Long.class)) {
                        tClazz = long.class;
                    } else if (tClazz.equals(long.class)) {
                        tClazz = Long.class;
                    }
                    try {
                        clazz.getMethod(setterName, tClazz).
                            invoke(object, tup);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                } catch (NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(TraSetter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

            }
            return;
        }

        try {
            if (collClazz != null) {

            }
            clazz.getMethod(setterName, tup.getClass()).
                invoke(object, tup);
        } catch (NoSuchMethodException ee) {
            Class tClazz;
            if (tup instanceof Integer) {
                tClazz = int.class;
            } else if (tup instanceof Long) {
                tClazz = long.class;
            } else if (tup instanceof Double) {
                tClazz = double.class;
            } else if (tup instanceof Boolean) {
                tClazz = boolean.class;
            } else if (tup instanceof Byte) {
                tClazz = byte.class;
            } else if (tup instanceof Float) {
                tClazz = float.class;
            } else if (tup instanceof Short) {
                tClazz = short.class;
            } else if (tup instanceof Character) {
                tClazz = char.class;
            } else {
                tClazz = tup.getClass();
            }
            try {
                clazz.getMethod(setterName, tClazz).
                    invoke(object, tup);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ex.printStackTrace();
            }

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public String getClassNameSetter() {
        return "set" + className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    public String getParentKey() {
        int h = key.lastIndexOf("__");
        if (h != -1) {
            return key.substring(0, h);
        }
        return key;
    }

    public int getTupleIndex() {
        return tupleIndex;
    }

    public void setTupleIndex(int tupleIndex) {
        this.tupleIndex = tupleIndex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        String s = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.setterName = "set" + s;
        this.getterName = "get" + s;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getGetterName() {
        return getterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getCollObject() {
        return collObject;
    }

    public void setCollObject(Object collObject) {
        this.collObject = collObject;
    }

    public String getCollClassName() {
        return collClassName;
    }

    public void setCollClassName(String collClassName) {
        this.collClassName = collClassName;
    }

    public Class<?> getCollClazz() {
        return collClazz;
    }

    public void setCollClazz(Class<?> collClazz) {
        this.collClazz = collClazz;
    }

    @Override
    public String toString() {
        return "TraSetter{" + "name=" + name + ", setterName=" + setterName + ", getterName=" + getterName + ", key=" + key + ", clazz=" + clazz + ", className=" + className + ", object=" + object + ", tupleIndex=" + tupleIndex + '}';
    }

}
