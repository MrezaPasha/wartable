package org.sadr.web.main._core.uiBag;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author masoud
 */
@RestController
@PersianName("تنظیمات ظاهر")
public class UiBagController extends GenericControllerImpl<UiBag, UiBagService> {

    private final String _PANEL_URL = "/panel/ui";


    public UiBagController() {
    }

    @PersianName("تغییر تنظیمات")
    @RequestMapping(value = _PANEL_URL + "/set", method = RequestMethod.GET)
    public ModelAndView pSet(Model model, HttpSession session) {
        User sUser = (User) session.getAttribute("sUser");

        UiBag uiBag = (UiBag) model.asMap().get("uiBag");
        if (uiBag == null) {
            uiBag = this.service.findBy(
                    Restrictions.eq("user", sUser)
            );
            if (uiBag == null) {
                uiBag = new UiBag();
            }

        }

        model.addAttribute(uiBag);
        return TtTile___.p_uiBag_set.___getDisModel(_PANEL_URL + "/set", null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/set", method = RequestMethod.POST)
    public ModelAndView pSet(@ModelAttribute("uiBag") UiBag uiBag,
                             BindingResult userBindingResult,
                             HttpServletRequest request,
                             HttpSession session,
                             final RedirectAttributes redirectAttributes) {

        User sUser = (User) session.getAttribute("sUser");
        if (sUser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.user.not.found", TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/set", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s);
        }

        if (uiBag.getFont() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.select.font", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }

        if (uiBag.getStyle() == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.select.style", TtNotice.Warning)));
            return Referer.redirectObjects(null, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, uiBag);
        }

        if (uiBag.getIdi() == 0) {
            uiBag.setUser(sUser);
            this.service.save(uiBag);
        } else {
            UiBag dbUi = this.service.findById(uiBag.getId());
            dbUi.setFont(uiBag.getFont());
            dbUi.setStyle(uiBag.getStyle());
            this.service.update(dbUi);
        }


        SessionListener.refreshUiSetting(uiBag.getFont().getKey(), uiBag.getStyle().getKey(), sUser.getIdi());

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.ui.set.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/set", null, TtTaskActionStatus.Success, notice2s);
    }

}
