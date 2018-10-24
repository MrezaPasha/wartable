package org.sadr.web.main._core.ui;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core._type.TtUiFonts;
import org.sadr.web.main._core._type.TtUiStyle;
import org.sadr.web.main._core.meta.annotation.StandaloneController;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.sadr.web.main.system._type.TtRegisteryValueType;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author masoud
 */
@StandaloneController
@RestController
@PersianName("تنظیمات ظاهر")
public class UiController {

    private final String _PANEL_URL = "/panel/ui";

    private RegisteryService registeryService;

    @Autowired
    public void setRegisteryService(RegisteryService registeryService) {
        this.registeryService = registeryService;
    }

    ///=//////////////////////////////////////////////////////////////
    public UiController() {
    }

    public void init() {
        Registery registery = registeryService.findByKey(TtRegisteryKey.UiFont);
        if (registery == null) {
            return;
        }

        SessionListener.setDefaultFont(registery.getValue());
        SessionListener.refreshUiSetting();
    }

    ///=////////////////////////////////////////////////////////////// SETTING
    @PersianName("تغییر تنظیمات")
    @RequestMapping(value = _PANEL_URL + "/set", method = RequestMethod.GET)
    public ModelAndView pSet(Model model) {
        UiBag uiBag = (UiBag) model.asMap().get("uiBag");
        if (uiBag == null) {
            uiBag = new UiBag();
            Registery registery = registeryService.findByKey(TtRegisteryKey.UiFont);
            if (registery == null) {
                registery = new Registery();
                registery.setKey(TtRegisteryKey.UiFont.getKey());
                registery.setTitle(TtRegisteryKey.UiFont.getTitle());
                registery.setValueType(TtRegisteryValueType.Normal);
                registery.setValue(TtUiFonts.IranSans.getKey());
                registeryService.save(registery);
            }
            uiBag.setFont(TtUiFonts.getByKey(registery.getValue()));

             registery = registeryService.findByKey(TtRegisteryKey.UiStyle);
            if (registery == null) {
                registery = new Registery();
                registery.setKey(TtRegisteryKey.UiStyle.getKey());
                registery.setTitle(TtRegisteryKey.UiStyle.getTitle());
                registery.setValueType(TtRegisteryValueType.Normal);
                registery.setValue(TtUiStyle.Light.getKey());
                registeryService.save(registery);
            }
            uiBag.setStyle(TtUiStyle.getByKey(registery.getValue()));

        }

        model.addAttribute(uiBag);
        return TtTile___.p_ui_set.___getDisModel(_PANEL_URL + "/set", null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/set", method = RequestMethod.POST)
    public ModelAndView pSet(Model model,
                             @ModelAttribute("uiBag") UiBag uiBag,
                             BindingResult userBindingResult,
                             HttpServletRequest request,
                             final RedirectAttributes redirectAttributes) {

        if (uiBag.getFont() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.select.font", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }
        Registery registery = registeryService.findByKey(TtRegisteryKey.UiFont);
        if (registery == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.info.not.found", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }
        registery.setValue(uiBag.getFont().getKey());
        this.registeryService.update(registery);


        if (uiBag.getStyle() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.select.style", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }
        registery = registeryService.findByKey(TtRegisteryKey.UiStyle);
        if (registery == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.info.not.found", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }
        registery.setValue(uiBag.getStyle().getKey());
        this.registeryService.update(registery);

        SessionListener.setDefaultFont(uiBag.getFont().getKey());
        SessionListener.setDefaultStyle(uiBag.getStyle().getKey());
        SessionListener.refreshUiSetting();

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.set.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/set", null, TtTaskActionStatus.Success, notice2s);
    }


}
