/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.tools.transformer;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.sadr._core._type.TtTransformerType;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.JB;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author dev1
 */
public class CustomTransformer<T extends Serializable> extends AliasedTupleSubsetResultTransformer {

    private final Class<T> rootClazz;
    private final TtTransformerType transformerType;
    private final GB gb;
    private final JB jb;

    public CustomTransformer(Class<T> clazz, TtTransformerType type) {
//        OutLog.pl();
        this.rootClazz = clazz;
        this.transformerType = type;
        this.gb = null;
        this.jb = null;
    }

    public CustomTransformer(Class<T> clazz, TtTransformerType type, GB gb) {
//        OutLog.pl();
        this.rootClazz = clazz;
        this.transformerType = type;
        this.gb = gb;
        this.jb = null;
    }

    public CustomTransformer(Class<T> clazz, TtTransformerType type, JB jb) {
//        OutLog.pl();
        this.rootClazz = clazz;
        this.transformerType = type;
        this.jb = jb;
        this.gb = null;
    }

    ByteArrayOutputStream baos;
    ObjectOutputStream oos;
    ByteArrayInputStream bais;
    ObjectInputStream ois;
    private TraSetter[] setters;
    private T rootObj;
    private Class<?> relClazz;

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return true;
    }

    @Override
    public Object transformTuple(Object[] tups, String[] alis) {
        if (tups == null || tups.length == 0) {
//            OutLog.pl();
            return null;
        }
//        OutLog.pl(Arrays.toString(alis));
//        OutLog.pl(Arrays.toString(tups));
//        if (1 == 1) {
//            return null;
//        }
        if (setters == null) {
            initAliases(alis);
        }

        for (TraSetter setter : setters) {
//            OutLog.p(setter.getKey() + "   ++>   " + setter.getName());
            setter.invoke(tups);
        }

//        OutLog.pl();
        if (transformerType == TtTransformerType.Json) {
            try {
                if (jb != null) {
                    Object oo = rootClazz.getMethod("toJson", JB.class).invoke(rootObj, jb);
                    return oo.toString();
                } else {
                    Object oo = rootClazz.getMethod("toJson", GB.class).invoke(rootObj, gb);
                    return oo.toString();
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ex.printStackTrace();
                return "خطا";
            }
        }
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(rootObj);

            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);

            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initAliases(String[] alis) {
        try {
//            OutLog.pl(alis.length);
            int ix,
                aliasDepth,             // aliasDepth = aliasParts
                depth,                  // current depth in while loop
                repeatInDepth,          // increase according to items of a depth
                setterIndex,
                zeroSetterIndex;
            boolean isAnyAliasToVerify,          // condition for WHILE loop
                isIncreaseDepth;                   // do for next depth
            String aliKey,
                ali,
                currentKey,
                rootObjName;
            Method method;
            //########################### init

            rootObjName = rootClazz.getSimpleName().substring(0, 1).toLowerCase() + rootClazz.getSimpleName().substring(1);

            rootObj = rootClazz.newInstance();
            setters = new TraSetter[alis.length];
            //========================
            depth = 0;
            zeroSetterIndex = 0;
            setterIndex = 0;
            isAnyAliasToVerify = true;

            while (isAnyAliasToVerify) {
                isIncreaseDepth = false;
                isAnyAliasToVerify = false;
                repeatInDepth = 0;
                currentKey = "";
                for (int i = 0; i < alis.length; i++) {
                    if (alis[i] == null) {
                        continue;
                    }

                    isAnyAliasToVerify = true;
                    ali = alis[i];

                    ix = ali.lastIndexOf(".");
                    aliKey = ix == -1 ? rootObjName : rootObjName + "_" + ali.substring(0, ix);
                    String[] aliParts = aliKey.split("_");


                    aliasDepth = aliParts.length - 1;

                    /***********************
                     if depth of current alias != depth in while loop   ###  xx -> alias depth=0   ###  xx_yy -> alias depth=1
                     **********************/
                    if (aliasDepth != depth) {
                        continue;
                    }

                    isIncreaseDepth = true;

                    /***********************
                     first field of the object  ###  Init object while receive to the first field of it...
                     **********************/
                    if (repeatInDepth == 0) {
                        currentKey = aliKey;
                        zeroSetterIndex = setterIndex;

                        setters[setterIndex] = new TraSetter();
                        TraSetter traSet = setters[setterIndex];
                        traSet.setName(ix == -1 ? ali : ali.substring(ix + 1));
                        traSet.setKey(aliKey);
                        traSet.setTupleIndex(i);
                        traSet.setClassName(aliParts[aliasDepth]);
                        try {
                            if (aliasDepth == 0) {
                                traSet.setClazz(rootClazz);
                                traSet.setObject(rootObj);

                            } else if (aliasDepth == 1) {
                                relClazz = rootClazz.getDeclaredField(traSet.getClassName()).getType();
                                //*** detecting if object is a SET ATTAY
                                if (relClazz == Set.class) {
                                    Type t = rootClazz.getDeclaredField(traSet.getClassName()).getGenericType();
                                    ParameterizedType pt = (ParameterizedType) t;
                                    Class aClass = (Class) pt.getActualTypeArguments()[0];

                                    traSet.setClazz(aClass);
                                    traSet.setObject(new HashSet<>());
                                    traSet.setCollClazz(relClazz);
                                    traSet.setCollObject(new HashSet<>());
                                    traSet.setCollClassName(aliParts[aliasDepth]);
                                    method = rootClazz.getMethod(traSet.getClassNameSetter(), traSet.getCollClazz());
                                    method.invoke(rootObj, traSet.getCollObject());

                                } else {
                                    traSet.setClazz(relClazz);
                                    traSet.setObject(relClazz.newInstance());
                                    method = rootClazz.getMethod(traSet.getClassNameSetter(), traSet.getClazz());
                                    method.invoke(rootObj, traSet.getObject());
                                }
                            } else {
                                for (TraSetter parentSetter : setters) {
                                    if (parentSetter.getKey() != null && parentSetter.getKey().equals(traSet.getParentKey())) {
                                        relClazz = parentSetter.getClazz().getDeclaredField(traSet.getClassName()).getType();
                                        traSet.setClazz(relClazz);
                                        traSet.setObject(relClazz.newInstance());

                                        method = parentSetter.getClazz().getMethod(traSet.getClassNameSetter(), traSet.getClazz());
                                        method.invoke(parentSetter.getObject(), traSet.getObject());
                                        break;
                                    }
                                }
                            }
                        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
                            ex.printStackTrace();
                            continue;
                        }

                    } else if (!aliKey.equals(currentKey)) {
//                       OutLog.p(aliKey, currentKey);
                        continue;
                    } else {
                        setters[setterIndex] = new TraSetter(setters[zeroSetterIndex]);
                        TraSetter traSet = setters[setterIndex];
                        traSet.setName(ix == -1 ? ali : ali.substring(ix + 1));
                        traSet.setKey(aliKey);
                        traSet.setTupleIndex(i);

                    }
//                    OutLog.p(setters[setterIndex].toString());
                    repeatInDepth++;
                    setterIndex++;
                    /***********************
                     after verifying alias[i] it set to NULL for ignoring in other loops
                     **********************/
                    alis[i] = null;

                }
                if (!isIncreaseDepth) {
                    depth++;
                }
            }
//
//            for (TraSetter sl : setters) {
//                OutLog.p(sl.toString());
//            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CustomTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
