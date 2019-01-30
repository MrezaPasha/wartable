package org.sadr.web.main._core.utils;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtLogHandler;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.log.general.Log;
import org.sadr.web.main.system.log.general.LogService;
import org.sadr.web.main.system.log.validation.ValidationLog;
import org.sadr.web.main.system.log.validation.ValidationLogService;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dev1
 * @see Referer To createJsps Restrictions Paths
 */
@PersianName
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

    public static ModelAndView redirect(String uri, TtTaskActionSubType subType, TtTaskActionStatus actionStatus, Notice2[] notice2) {
        return new ModelAndView("redirect:" + uri)
                .addObject("actionSubType", subType)
                .addObject("actionStatus", actionStatus)
                .addObject("noticeList", notice2);
    }

    public static ModelAndView redirect(String uri, TtTaskActionSubType subType, TtTaskActionStatus actionStatus) {
        return new ModelAndView("redirect:" + uri)
                .addObject("actionStatus", actionStatus)
                .addObject("actionSubType", subType);
    }


    ///////////////////
    public static ModelAndView redirectBindingError(HttpServletRequest request, RedirectAttributes redirectAttributes, BindingResult bindingResult, Object... attributes) {
        try {
            User sUser = (User) request.getSession().getAttribute("sUser");
            if (sUser != null) {
                ValidationLogService validationLogService = WebConfigHandler.getWebApplicationContext().getBean(ValidationLogService.class);
                LogService logService = WebConfigHandler.getWebApplicationContext().getBean(LogService.class);

                ValidationLog vl = validationLogService.findBy(
                        Restrictions.and(
                                Restrictions.eq(ValidationLog._USER, sUser),
                                Restrictions.eq(ValidationLog.URL, request.getRequestURI())
                        )
                );
                if (vl == null) {
                    vl = new ValidationLog();
                    vl.setUser(sUser);
                    vl.setUrl(request.getRequestURI());
                    vl.setTryCount(1);
                    validationLogService.save(vl);
                } else {
                    if (vl.getTryCount() > 2) {
                        vl.setTryCount(0);
                        validationLogService.update(vl);
                        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.all.validation.try.exceed")));
                        logService.log(
                                new Log(
                                        null, request,
                                        " Dispatched to < panel > [Attempt Exceed] ",
                                        TtLogHandler.AuthorizerAspect, null, sUser)
                        );
                        return Referer.redirect("/panel", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
                    } else {
                        vl.addTryCount();
                        validationLogService.update(vl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        OutLog.pl(bindingResult.toString());
        OutLog.pl(bindingResult.getTarget().toString());
        OutLog.pl(request.getRequestURI());
        Notice2[] notice2s = Notice2.initPostError(redirectAttributes, bindingResult);
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
            return new ModelAndView("redirect:" + request.getHeader("Referer"))
                    .addObject("noticeList", notice2s);
        } catch (Exception e) {
            return new ModelAndView("redirect:/")
                    .addObject("noticeList", notice2s);
        }
    }

    public static ModelAndView redirectBindingError(TtTaskActionSubType subType, TtTaskActionStatus actionStatus, HttpServletRequest request, RedirectAttributes redirectAttributes, BindingResult bindingResult, Object... attributes) {

        return redirectBindingError(request, redirectAttributes, bindingResult, attributes)
                .addObject("actionSubType", subType)
                .addObject("actionStatus", actionStatus);
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

    public static ModelAndView redirectObjects(TtTaskActionSubType subType, TtTaskActionStatus actionStatus, Notice2[] notice2, HttpServletRequest request, RedirectAttributes redirectAttributes, Object... attributes) {
        return redirectObjects(request, redirectAttributes, attributes)
                .addObject("actionSubType", subType)
                .addObject("actionStatus", actionStatus)
                .addObject("noticeList", notice2);
    }

    public static ModelAndView redirectObjects(TtTaskActionSubType subType, TtTaskActionStatus actionStatus, Notice2[] notice2s, String uri, RedirectAttributes redirectAttributes, Object... attributes) {
        if (attributes != null) {
            for (int i = 0; i < attributes.length; i++) {
                redirectAttributes.addFlashAttribute(attributes[i]);
            }
        }
        return new ModelAndView("redirect:" + uri)
                .addObject("actionSubType", subType)
                .addObject("actionStatus", actionStatus)
                .addObject("noticeList", notice2s);

    }


}
