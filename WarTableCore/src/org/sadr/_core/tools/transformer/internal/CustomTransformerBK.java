/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.tools.transformer.internal;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.sadr._core.tools.transformer.TraSetter;
import org.sadr._core.utils.OutLog;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author dev1
 */
public class CustomTransformerBK<T extends Serializable> extends AliasedTupleSubsetResultTransformer {

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return true;
    }

    public CustomTransformerBK(Class<T> clazz) {
        OutLog.pl();
        this.rootClazz = clazz;
        hMap = new HashMap<>();
    }

    private final Class<T> rootClazz;
    private final HashMap<String, List<Integer>> hMap;
    private TraSetter[] setters;
    private T rootObj;
    private Class<?> relClazz, relParentClazz;

    @Override
    public Object transformTuple(Object[] tups, String[] alis) {
        if (hMap.isEmpty()) {
            initAlieses(alis);
        }
        OutLog.pl();
        try {
            rootObj = rootClazz.newInstance();

            int ix, ronpCount, hmIndex, hmCycle, hmLen;
            String relObjName, relField;
            Method m;
            List<Integer> relFieldIndes;
            String[] relObjNameParts;

            Object relParentObj, relObjInstance;

            boolean isNullObj;

            // OutLog.p(Arrays.toString(tups));
            // OutLog.pl("rootObjName: " + rootObjName);
            // OutLog.p(Arrays.toString(alis));
            hmIndex = 0;
            hmCycle = 0;
            hmLen = hMap.size();
            while (hmCycle < hmLen) {
                for (Map.Entry<String, List<Integer>> entity : hMap.entrySet()) {
                    isNullObj = false;
                    relObjName = entity.getKey();

                    relObjNameParts = relObjName.split("_");
                    ronpCount = relObjNameParts.length - 1;

                    // OutLog.p("round: " + hmIndex + "   count: " + ronpCount + "  > " + relObjName);
                    if (ronpCount != hmIndex) {
                        continue;
                    }
                    hmCycle++;

                    relFieldIndes = entity.getValue();
                    // OutLog.pl(relObjName + "   " + relFieldIndes.toString());
                    relClazz = relParentClazz = rootClazz;
                    relParentObj = rootObj;
                    // if relObject in an internal object- get parent of object and clazz
                    // asnd refresh relClazz
                    if (ronpCount != 0) {
                        for (int i = 1; i < ronpCount; i++) {
                            String ss = "get" + relObjNameParts[i].substring(0, 1).toUpperCase() + relObjNameParts[i].substring(1);
                            // OutLog.p(ss + "    " + relParentClazz.getDeclaredField(relObjNameParts[i]).getType()
                            //       + "   " + relParentObj.getClass().getName());
                            relParentObj = relParentClazz.getMethod(ss)
                                .invoke(relParentObj);
                            if (relParentObj == null) {
                                isNullObj = true;
                                break;
                            }
                            // OutLog.pl(relParentObj.getClass().getName());
                            relParentClazz = relParentObj.getClass();
                        }
                        if (isNullObj) {
                            continue;
                        }
                        relClazz = relParentClazz.getDeclaredField(relObjNameParts[ronpCount]).getType();

                    }
                    // OutLog.pl(relClazz.getName());

                    // get rel object
                    if (ronpCount == 0) {
                        relObjInstance = rootObj;
                    } else {
                        relObjInstance = relClazz.newInstance();
                    }

                    // fill rel object fields
                    for (Integer iy : relFieldIndes) {
                        // OutLog.p(iy + "   " + tups[iy] + "  " + alis[iy]);
                        Object tup = tups[iy];
                        if (tup != null) {
                            ix = alis[iy].lastIndexOf(".");
                            relField = alis[iy].substring(ix == -1 ? 0 : ix + 1);
                            // OutLog.p("set" + relField.substring(0, 1).toUpperCase() + relField.substring(1) + "   " + relClazz.getName());
                            try {
                                m = relClazz.getMethod("set" + relField.substring(0, 1).toUpperCase() + relField.substring(1), tup.getClass());
                                m.invoke(relObjInstance, tup);
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
                                // OutLog.p("set" + relField.substring(0, 1).toUpperCase() + relField.substring(1));
                                m = relClazz.getMethod("set" + relField.substring(0, 1).toUpperCase() + relField.substring(1), tClazz);
                                m.invoke(relObjInstance, tup);
//                            // OutLog.p("EE: " + ee);
                            }
                        } else if (alis[iy].endsWith(".id")) {
                            OutLog.pl("null object");
                            isNullObj = true;
                            break;
                        }
                    }
                    if (isNullObj) {
                        continue;
                    }
                    if (ronpCount != 0) {
                        ix = relObjName.lastIndexOf("_");
                        relField = relObjName.substring(ix == -1 ? 0 : ix + 1);
                        // OutLog.p(relParentObj.getClass().getName()
                        //        + "     " + relObjInstance.getClass().getName()
                        //       + "    | " + relClazz.getName() + "   "
                        //       + " set" + relField.substring(0, 1).toUpperCase() + relField.substring(1));
                        m = relParentClazz.getMethod("set" + relField.substring(0, 1).toUpperCase() + relField.substring(1), relClazz);
                        m.invoke(relParentObj, relObjInstance);
                    }
                }
                hmIndex++;
            }
            return rootObj;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomTransformerBK.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {
            Logger.getLogger(CustomTransformerBK.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void initAlieses(String[] alis) {
        int ix;
        String sx, ali,
            rootObjName = rootClazz.getSimpleName().substring(0, 1).toLowerCase() + rootClazz.getSimpleName().substring(1);

//        setters = new TraSetter[alis.length];

        //========================
        // OutLog.pl();
        for (int i = 0; i < alis.length; i++) {
            ali = alis[i];
//                OutLog.pl(ali);
            if (ali != null) {
//                    ali=ali.replaceAll("_", ".");
                ix = ali.lastIndexOf(".");
                sx = ix == -1 ? rootObjName : rootObjName + "_" + ali.substring(0, ix);
//                setters[i].setKey(sx);
//                    OutLog.p(ix + "   " + i + "    |" + sx + "|");
                if (!hMap.containsKey(sx)) {
                    List<Integer> lss = new ArrayList<>();
                    lss.add(i);
//                        OutLog.p("<>");
                    hMap.put(sx, lss);
                } else {
//                        OutLog.p("><");
                    hMap.get(sx).add(i);
                }
            }
        }

        for (Map.Entry<String, List<Integer>> entity : hMap.entrySet()) {
            // OutLog.p(entity.getKey() + "  " + entity.getValue().toString());
        }

    }

}
