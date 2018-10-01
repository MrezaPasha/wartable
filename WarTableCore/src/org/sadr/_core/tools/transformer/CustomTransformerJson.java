/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.tools.transformer;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.sadr._core._type.TtYesNo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
public class CustomTransformerJson<T extends Serializable> extends AliasedTupleSubsetResultTransformer {

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return true;
    }

    public CustomTransformerJson(Class<T> clazz) {
        this.clazz = clazz;
    }

    private final Class<T> clazz;

    @Override
    public Object transformTuple(Object[] tups, String[] alis) {
        try {
            HashMap<String, List<Integer>> relObjHashMap = new HashMap<>();

            StringBuffer json = new StringBuffer("{");
            //========================
//            OutLog.pl(Arrays.toString(tups));
//            for (int i = 0; i < tups.length; i++) {
//                if (tups[i] == null) {
//                    OutLog.p(alis[i]
//                            + " -> N");
//                } else {
//                    OutLog.p(alis[i]
//                            + " -> "
//                            + tups[i].toString());
//                }
//            }
//            OutLog.pl("***** ");
            for (int i = 0; i < alis.length; i++) {
                String ali = alis[i];
//                OutLog.p(ali + "    ");
                if (ali == null) {
                    continue;
                }
                if (ali.contains(".")) {
                    String[] sp = ali.split("\\.");
                    if (!relObjHashMap.containsKey(sp[0])) {
//                        OutLog.pl("");
                        List<Integer> lss = new ArrayList<>();
                        lss.add(i);
                        relObjHashMap.put(sp[0], lss);
                    } else {
//                        OutLog.pl("");
                        relObjHashMap.get(sp[0]).add(i);
                    }
                    continue;
                }
                Object tup = tups[i];
                if (tup == null) {
                    continue;
                }
//                OutLog.pl(ali + "     " + tup.getClass().getTypeName()
//                        + "   " + tup.getClass().getModifiers());
                if (tup.getClass().getSimpleName().startsWith("Tt")) {
                    String invoke;
                    try {
                        invoke = (String) tup.getClass().getMethod("getTitle").invoke(tup);
                    } catch (NoSuchMethodException ex) {
                        invoke = (String) tup.getClass().getMethod("getKey").invoke(tup);
                    }
                    json.append("\"").append(ali).append("\":\"").append(invoke).append("\",");
                } else if (tup instanceof Boolean) {
                    json.append("\"").append(ali).append("\":\"").append(TtYesNo.getValueByBool((Boolean) tup).getTitle()).append("\",");
                } else {
                    json.append("\"").append(ali).append("\":\"").append(tup).append("\",");
                }
            }

//            if (1 == 1) {
//                return obj;
//            }
            boolean isTuped;
            for (Map.Entry<String, List<Integer>> entity : relObjHashMap.entrySet()) {
                String relObjName = entity.getKey();
                json.append("\"").append(relObjName).append("\":{");
                List<Integer> relFieldIndes = entity.getValue();
//                OutLog.pl(relObjName + "   " + relFieldIndes.toString());
//                Class<?> relClazz = clazz.getDeclaredField(relObjName).getType();
//                OutLog.pl(relClazz.getName() + " --------");
                isTuped = false;
                for (Integer ix : relFieldIndes) {
                    Object tup = tups[ix];
                    if (tup != null) {
                        isTuped = true;
                        String relField = alis[ix].split("\\.")[1];
                        if (tup.getClass().getSimpleName().startsWith("Tt")) {
                            String invoke;
                            try {
                                invoke = (String) tup.getClass().getMethod("getTitle").invoke(tup);
                            } catch (NoSuchMethodException ex) {
                                invoke = (String) tup.getClass().getMethod("getKey").invoke(tup);
                            }
                            json.append("\"").append(relField).append("\":\"").append(invoke).append("\",");

                        } else if (tup instanceof Boolean) {
                            json.append("\"").append(relField).append("\":\"").append(TtYesNo.getValueByBool((Boolean) tup).getTitle()).append("\",");
                        } else {
                            json.append("\"").append(relField).append("\":\"").append(tup).append("\",");
                        }

                    }
                }
                if (isTuped) {
                    json = json.replace(json.length() - 1, json.length(), "},");
                } else {
                    json.append("},");
                }
            }

            if (json.length() > 1) {
                json = json.replace(json.length() - 1, json.length(), "}");
            } else {
                json.append("}");
            }

//            OutLog.pl(json.toString());
            return json.toString();
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(CustomTransformerJson.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
