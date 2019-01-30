package org.sadr._core.utils;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StaticVarBuilder {

    private static final StaticVarBuilder instance = new StaticVarBuilder();

    public static StaticVarBuilder getInstance() {
        return instance;
    }

    private StaticVarBuilder() {
    }


    public void build(List<Class<?>> modelClasses){
        List<File> files = new ArrayList<>();

        FileUtil.fillFileList(Environment.getInstance().getProjectSourceCodeAP(), files);
        for (java.io.File file : files) {
            String fname = file.getName();
            if (fname.endsWith("Config.java")
                || fname.endsWith("Controller.java")
                || fname.endsWith("Service.java")
                || fname.endsWith("ServiceImp.java")
                || fname.endsWith("Dao.java")
                || fname.endsWith("DaoImp.java")
                || fname.startsWith("Tt")) {
                continue;
            }
            Field[] fs;
            Method[] mts;
            String[] ss;
            String nm, st;
            StringBuilder fbb, sb;
            for (Class<?> a : modelClasses) {
                OutLog.p("XX " + a.getSimpleName() + ".java" + "   " + fname);
                if ((a.getSimpleName() + ".java").equals(fname)) {
                    fbb = new StringBuilder(",");
                    sb = new StringBuilder("//#########++++++#######// StaticFields: Start //\n");

                    // == Model
                    fs = a.getDeclaredFields();
                    for (Field f : fs) {
                        if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        st = "";
                        if (f.isAnnotationPresent(ManyToMany.class)
                            || f.isAnnotationPresent(ManyToOne.class)
                            || f.isAnnotationPresent(OneToMany.class)
                            || f.isAnnotationPresent(OneToOne.class)) {
                            st += "_";
                        }
                        ss = f.getName().split("");
                        for (String s : ss) {
                            if (s != null) {
                                st += (s.equals(s.toLowerCase())) ? s.toUpperCase() : "_" + s.toUpperCase();
                            }
                        }
                        fbb.append(f.getName()).append(",");
                        sb.append("public static final String ").append(st).append(" = \"").append(f.getName()).append("\";");
                    }


                    sb.append("\n//#########******#######// StaticFields: End //");

                    boolean isWrite = true, isInjected = false;
                    try {
                        String sCurrentLine;
                        StringBuilder codeStr = new StringBuilder();
                        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                        while ((sCurrentLine = input.readLine()) != null) {
                            if (sCurrentLine.contains("//#########++++++#######// StaticFields: Start //")) {
                                OutLog.pl("ok");
                                isWrite = false;
                                isInjected = true;
                                codeStr.append(sb).append("\n");

                            } else if (sCurrentLine.contains("//#########******#######// StaticFields: End //")) {
                                isWrite = true;
                                continue;
                            }
                            if (isWrite) {
                                codeStr.append(sCurrentLine).append("\n");
                            }
                        }
                        if (!isInjected) {
                            codeStr = new StringBuilder();
                            OutLog.pl("inj");
                            input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                            while ((sCurrentLine = input.readLine()) != null) {
                                OutLog.p(sCurrentLine);
                                codeStr.append(sCurrentLine).append("\n");
                                if (sCurrentLine.contains("public class")) {
                                    codeStr.append(sb).append("\n");
                                }
                            }
                        }
                        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
                            bw.write(codeStr.toString());
                            OutLog.pl(codeStr.toString());
                        }

                    } catch (IOException ex) {
                        OutLog.p(ex.toString());
                    }

                    break;
                }
            }
        }
    }
}
