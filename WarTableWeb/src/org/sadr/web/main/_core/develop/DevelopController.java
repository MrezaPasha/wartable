package org.sadr.web.main._core.develop;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.*;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("تنظیمات سامانه")
@RequestMapping(value = "/panel/develop")
public class DevelopController {

    ///=//////////////////////////////////////////////////////////////
    public DevelopController() {
    }

    @Autowired
    private ListableBeanFactory beanFactory;
    ///=//////////////////////////////////////////////////////////////

    ///=////////////////////////////////////////////////////////////// DEVELOPING
    @SuperAdminTask
    @PersianName("پیشخوان توسعه")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pDevelop(Model model, final RedirectAttributes redirectAttributes) {
        Method[] ms = DevelopController.class.getMethods();
        RequestMapping an;
        PersianName pn;
        List<String[]> ls = new ArrayList<>();
        for (Method m : ms) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                an = m.getAnnotation(RequestMapping.class);
                if ((an.method().length == 0 || an.method()[0] == null || an.method()[0] == RequestMethod.GET)
                        && an.value() != null && an.value().length > 0 && !an.value()[0].isEmpty()) {
                    if (m.isAnnotationPresent(PersianName.class)) {
                        pn = m.getAnnotation(PersianName.class);
                        ls.add(new String[]{pn.value(), an.value()[0]});
                    }
                }
            }
        }
        ls.sort((String[] o1, String[] o2) -> {
            return (o1[0]).compareTo(o2[0]);
        });
        model.addAttribute("list", ls);
        return TtTile___.p_develop_index.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("بازسازی متغیرهای ایستای مدل")
    @RequestMapping(value = "/model/static", method = RequestMethod.GET)
    public ModelAndView pDevelopRebuildStaticsOfModel(final RedirectAttributes redirectAttributes) {

        List<java.io.File> files = new ArrayList<>();

        FileUtil.fillFileList(Environment.getInstance().getProjectSourceCodeAP(), files);
        List<Class<?>> ac = WebConfigHandler.getModelClasses();
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
            for (Class<?> a : ac) {
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

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.develop.model.static.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/develop");
    }

}
