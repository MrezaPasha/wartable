package org.sadr.web.main._core.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;

import java.lang.reflect.Method;
import java.util.List;

public class Fmap {
    private Fmap() {
        map = HashBiMap.create();
    }

    private final static Fmap instance = new Fmap();

    private BiMap<String, String> map;

    public static Fmap instance() {
        return instance;
    }

    public static BiMap<String, String> map() {
        return instance.map;
    }


    public void init() {
        String pname, fieldName,
            modelName, topHeader, middleHeader, subHeader;
        List<Class<?>> ac = WebConfigHandler.getModelClasses();
        for (Class<?> a : ac) {
            modelName = a.getSimpleName().substring(0, 1).toLowerCase() + a.getSimpleName().substring(1);


            for (Method m : a.getMethods()) {
                OutLog.pl(m.toString());
                OutLog.p(m.getName());
                if (m.getName().startsWith("get")) {
                    OutLog.p("OK");
                }
            }


        }
    }
}
