package org.sadr._core._type;

import org.hibernate.annotations.Type;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author masoud
 */
public enum TtDataType {

    Boolean_JL("bit(1)", "java.lang.Boolean", "b"),
    Boolean("bit(1)", "boolean", "b"),
    Integer("int(11)", "int", "i"),
    Integer_JL("int(11)", "java.lang.Integer", "i"),
    Double("double", "double", "d"),
    Double_JL("double", "java.lang.Double", "d"),
    Long("bigint(20)", "long", "l"),
    Long_JL("bigint(20)", "java.lang.Long", "l"),
    String("varchar", "java.lang.String", "s"),
    String_LT("longtext", "java.lang.StringLT", "s"),
    Text("longtext", "text", "t"),
    Float("float", "float", "f"),
    Float_JL("float", "java.lang.Float", "f"),
    Enum("enum", "enum", "e"),
    Enum_JL("enum", "java.lang.Enum", "e");

    public static TtDataType getByDbType(String dt) {
        for (TtDataType value : values()) {
            if (value.getSqlType().equals(dt)) {
                return value;
            }
        }
        return null;
    }

    public static TtDataType getByMoType(String mt) {
        if (mt != null && mt.startsWith("ir.co.iras")) {
            return Integer_JL;
        }
        for (TtDataType value : values()) {
            if (value.getModelType().equals(mt)) {
                return value;
            }
        }
        return null;
    }

    public static boolean isEqual(String dk, String mk) {
        if (mk != null && mk.startsWith("ir.co.iras")) {
            return "int(11)".equals(dk);
        }
        for (TtDataType value : values()) {
            if (value.getSqlType().equals(dk)
                && value.getModelType().equals(mk)) {
                return true;
            }
        }
        return false;
    }

    public static TtDataType getByBrifKey(String key) {
        switch (key) {
            case "s":
                return String;
            case "i":
                return Integer;
            case "b":
                return Boolean;
            case "d":
                return Double;
            case "f":
                return Float;
            case "t":
                return Text;
            case "e":
                return Enum;
            default:
                return null;
        }
    }

    @SuppressWarnings({"unchecked", "unchecked", "fallthrough"})
    public static Object convertValueByBrifKey(String type, Object value, String path, Class<?> c) {
        if (type == null || value == null) {
            return type;
        }
//        OutLog.pl(type);
        switch (type) {
            case "s":
            case "t":
                if (value instanceof String) {
                    return value;
                } else {
                    if (value instanceof ArrayList) {
                        return value;
                    }
                    return value.toString();
                }
            case "i":
//                OutLog.pl(value+"");
                if (value instanceof Integer) {
//                    OutLog.pl();
                    return value;
                } else {
//                    OutLog.pl(value.toString());
                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof Integer) {
                                continue;
                            }
                            its.add(java.lang.Integer.parseInt(object.toString()));
                        }
                        return its;
                    }
                    return java.lang.Integer.parseInt(value.toString());
                }
            case "b":
                if (value instanceof Boolean) {
                    return value;
                } else {
                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof Boolean) {
                                continue;
                            }
                            its.add(java.lang.Boolean.parseBoolean(object.toString()));
                        }
                        return its;
                    }
                    return java.lang.Boolean.parseBoolean(value.toString());
                }
            case "d":
                if (value instanceof Double) {
                    return value;
                } else {
                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof Double) {
                                continue;
                            }
                            its.add(java.lang.Double.parseDouble(object.toString()));
                        }
                        return its;
                    }
                    return java.lang.Double.parseDouble(value.toString());
                }
            case "l":
                if (value instanceof Long) {
                    return value;
                } else {
                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof Long) {
                                continue;
                            }
                            its.add(java.lang.Long.parseLong(object.toString()));
                        }
                        return its;
                    }
                    return java.lang.Long.parseLong(value.toString());
                }
            case "f":
                if (value instanceof Float) {
                    return value;
                } else {
                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
                            if (object instanceof Float) {
                                continue;
                            }
                            its.add(java.lang.Float.parseFloat(object.toString()));
                        }
                        return its;
                    }
                    return java.lang.Float.parseFloat(value.toString());
                }
            case "e":
                if (value instanceof Enum) {
                    return value;
                } else {
                    int ix = path.indexOf(".");
                    String ss;
                    Class<?> oclazz = c;
                    if (ix == -1) {
                        ss = path;
                    } else {
                        ss = path.substring(ix + 1);
                        String[] sd = path.substring(0, ix).split("__");
                        for (String s : sd) {
                            try {
                                oclazz = oclazz.getDeclaredField(s).getType();
                            } catch (IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
                                Logger.getLogger(TtDataType.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    try {
                        oclazz = oclazz.getDeclaredField(ss).getType();
                    } catch (SecurityException | NoSuchFieldException ex) {
                        Logger.getLogger(TtDataType.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (value instanceof ArrayList) {
                        List<Object> objects, its;
                        Object o;
                        objects = (List<java.lang.Object>) value;
                        its = new ArrayList<>();
                        for (Object object : objects) {
//                            OutLog.p(object.getClass().getName());
//                            OutLog.p(object.toString());
                            try {
                                o = oclazz.getMethod("getByOrdinal", int.class)
                                    .invoke(null, java.lang.Integer.parseInt(object.toString()));
                                its.add(o);
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                Logger.getLogger(TtDataType.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        return its;
                    }
                    try {
                        return oclazz.getMethod("getByOrdinal", int.class)
                            .invoke(null, java.lang.Integer.parseInt(value.toString()));
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(TtDataType.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            default:
                return null;
        }
    }

    /**
     * @param key
     * @return
     */
    public static Class getClassByBrifKey(String key) {
//        OutLog.pl(key);
        switch (key) {
            case "s":
                return java.lang.String.class;
            case "i":
                return java.lang.Integer.class;
            case "b":
                return java.lang.Boolean.class;
            case "d":
                return java.lang.Double.class;
            case "f":
                return java.lang.Float.class;
            case "t":
                return java.lang.String.class;
            case "e":
                return java.lang.Enum.class;
            default:
                return null;
        }
    }

    private final String sqlType;
    private final String modelType;
    private final String brifKey;

    private TtDataType(String dk, String mk, String bk) {
        sqlType = dk;
        modelType = mk;
        brifKey = bk;

    }

    public String getSqlType() {
        return sqlType;
    }

    public String getModelType() {
        return modelType;
    }

    public boolean isPrimaryType() {
        return this == Boolean
            || this == Integer
            || this == Double
            || this == Long;
    }

    public int getModifiers() {
        switch (this) {
            case Boolean:
                return boolean.class.getModifiers();
            case Boolean_JL:
                return java.lang.Boolean.class.getModifiers();
            case Integer:
                return int.class.getModifiers();
            case Integer_JL:
                return java.lang.Integer.class.getModifiers();
            case Text:
                return Type.class.getModifiers();
            case Long:
                return long.class.getModifiers();
            case Long_JL:
                return java.lang.Long.class.getModifiers();
            case String:
                return java.lang.String.class.getModifiers();
        }
        return -1;
    }

    public String getBrifKey() {
        return brifKey;
    }
}
