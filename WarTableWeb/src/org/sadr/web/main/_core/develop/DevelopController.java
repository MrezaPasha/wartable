package org.sadr.web.main._core.develop;

import org.sadr._core.meta.annotation.Front;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.*;
import org.sadr.web.config.WebConfig;
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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

        StaticVarBuilder.getInstance().build(WebConfigHandler.getModelClasses());

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.develop.model.static.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/develop");
    }

    @SuperAdminTask
    @PersianName("چاپ اطلاعات لیست کنترلرها")
    @RequestMapping(value = "/print/controller", method = RequestMethod.GET)
    public ModelAndView pPrintControllerAndMethods(final RedirectAttributes redirectAttributes, HttpServletRequest request) throws InvocationTargetException, FileNotFoundException, UnsupportedEncodingException, IOException {

        int ix;
        String modelName, pname;
        Method[] methods;
        for (String bean : beanFactory.getBeanDefinitionNames()) {
            if (bean.endsWith("Controller")) {
                try {
                    Class<?> c = beanFactory.getBean(bean).getClass();
                    ix = c.getCanonicalName().indexOf("$");

                    if (ix > 0) {
                        c = c.getSuperclass();
                    }

                    String classSimpleName = c.getSimpleName().substring(0, 1).toLowerCase()
                            + c.getSimpleName().substring(1);

                    System.out.println();
                    System.out.println(c.getName());

                    // ============  get method persian names
                    methods = c.getMethods();
                    for (Method m : methods) {
                        if (!m.isAnnotationPresent(RequestMapping.class)) {
                            continue;
                        }

                        System.out.println(m.getName());


                    }

                } catch (Exception e) {
                }
            }
        }

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.develop.sync.jsp.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/develop");
    }


    @SuperAdminTask
    @PersianName("چاپ رمز ها")
    @RequestMapping(value = "/c/{type}", method = RequestMethod.GET)
    public ModelAndView pDevelopCryptor(final RedirectAttributes redirectAttributes,
                                        @PathVariable("type") int type,
                                        @RequestParam("v") String value
    ) {
        if (type == 0) {
            String cr = "|" + Cryptor.encrypt(value) + "|";
            OutLog.pl(value);
            OutLog.p(cr);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N2.develop.crypto.enc.success", TtNotice.Success, value, cr)));
        } else {
            String cr = "|" + Cryptor.decrypt(value) + "|";
            OutLog.pl(value);
            OutLog.p(cr);
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N2.develop.crypto.dec.success", TtNotice.Success, value, cr)));

        }

        return new ModelAndView("redirect:/panel/develop");
    }

}
