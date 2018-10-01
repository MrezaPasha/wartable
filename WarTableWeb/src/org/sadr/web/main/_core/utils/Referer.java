package org.sadr.web.main._core.utils;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dev1
 * @see Referer To createJsps Restrictions Paths
 */
public class Referer {

    public static ModelAndView ref(HttpServletRequest request, String modelString) {
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView(modelString);
        }
    }

    /////////////////////////
    public static ModelAndView redirect(String uri, HttpServletRequest request) {
        try {
            String referer = request.getHeader("Referer");
            if (referer == null || referer.contains("/null")) {
                return new ModelAndView("redirect:" + uri);
            }
            return new ModelAndView("redirect:" + referer);
        } catch (Exception e) {
            return new ModelAndView("redirect:" + uri);
        }
    }

    public static ModelAndView redirect(HttpServletRequest request) {
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }

    public static ModelAndView redirect(String uri) {
        return new ModelAndView("redirect:" + uri);
    }

    ///////////////////
    public static ModelAndView redirectBindingError(HttpServletRequest request, RedirectAttributes redirectAttributes, BindingResult bindingResult, Object... attributes) {
        Notice2.initPostError(redirectAttributes, bindingResult);
        if (attributes != null) {
            for (int i = 0; i < attributes.length; i++) {
                if (i == 0) {
                    String n = attributes[i].getClass().getSimpleName();
                    n = n.substring(0, 1).toLowerCase() + n.substring(1);
                    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + n, bindingResult);
                }
                redirectAttributes.addFlashAttribute(attributes[i]);
            }
        }
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }

    //////////////////
    public static ModelAndView redirectObject(HttpServletRequest request, RedirectAttributes redirectAttributes, Object object) {
        redirectAttributes.addFlashAttribute(object);
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }

    public static ModelAndView redirectObject(HttpServletRequest request, RedirectAttributes redirectAttributes, Object object, String objectName) {
        redirectAttributes.addFlashAttribute(objectName, object);
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }

    public static ModelAndView redirectObjects(HttpServletRequest request, RedirectAttributes redirectAttributes, Object... attributes) {
        if (attributes != null) {
            for (int i = 0; i < attributes.length; i++) {
                redirectAttributes.addFlashAttribute(attributes[i]);
            }
        }
        try {
            return new ModelAndView("redirect:" + request.getHeader("Referer"));
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }

    public static ModelAndView redirectObjects(String uri, RedirectAttributes redirectAttributes, Object... attributes) {
        if (attributes != null) {
            for (int i = 0; i < attributes.length; i++) {
                redirectAttributes.addFlashAttribute(attributes[i]);
            }
        }
        return new ModelAndView("redirect:" + uri);

    }


}
