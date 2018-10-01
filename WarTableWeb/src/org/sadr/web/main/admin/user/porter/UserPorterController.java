package org.sadr.web.main.admin.user.porter;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.RePa;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@RestController
@PersianName("مدیریت حامل اطلاعات کاربر")
public class UserPorterController extends GenericControllerImpl<UserPorter, UserPorterService> {
    private final String REQUEST_MAPPING_BASE = "/user/porter";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    private final String _MANAGE_URL = "/manage" + REQUEST_MAPPING_BASE;

    public UserPorterController() {
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /////////////////////////////////////@PersianName("مرورگرهای فعال")
    @SuperAdminTask
    @PersianName("لیست نشست های کاربر")
    @RequestMapping(value = _PANEL_URL + "/list/{uid}", method = RequestMethod.GET)
    public ModelAndView pUserPorterList(@PathVariable("uid") long uid, Model model,
                                        HttpSession session, final RedirectAttributes redirectAttributes) {

        User u = this.userService.findById(uid);
        if (u == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + uid), TtNotice.Success)));
            return new ModelAndView("redirect:/panel/user/list");
        }

        List<UserPorter> ups = this.service.findAllBy(Restrictions.eq(UserPorter._USER, u));
        OutLog.pl(u.getPorterUuid());
        if (ups != null && !ups.isEmpty()) {
            for (UserPorter up : ups) {
                OutLog.p(up.getUuid());
                if (u.getPorterUuid() != null && !u.getPorterUuid().isEmpty()
                    && u.getPorterUuid().contains(up.getUuid())) {
                    if (up.getIsActiveTwoStepConfirm() != Boolean.TRUE) {
                        up.setIsActiveTwoStepConfirm(true);
                        this.service.update(up);
                        OutLog.p();
                    }
                } else if (up.getIsActiveTwoStepConfirm() != Boolean.FALSE) {
                    up.setIsActiveTwoStepConfirm(false);
                    this.service.update(up);
                    OutLog.p();
                }
            }
        }
        model.addAttribute("user", u);
        model.addAttribute("uplist", ups);
        return TtTile___.p_user_porter_list.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("جزئیات نشست کاربر")
    @RequestMapping(value = _PANEL_URL + "/details/{uid}/{pid}", method = RequestMethod.GET)
    public ModelAndView pUserPorterDetails(@PathVariable("uid") long uid, @PathVariable("pid") long pid, Model model,
                                           HttpSession session, final RedirectAttributes redirectAttributes) {

        UserPorter up = this.service.findBy(
            Restrictions.and(
                Restrictions.eq(UserPorter.ID, pid),
                Restrictions.eq(RePa.p__(UserPorter._USER, User.ID), uid)
            ), UserPorter._USER
        );
        if (up == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.userPorter.not.found", JsonBuilder.toJson("userId", "" + uid), TtNotice.Success)));
            return new ModelAndView("redirect:/panel/user/porter/list/" + uid);
        }

        model.addAttribute("userPorter", up);
        return TtTile___.p_user_porter_details.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("لغو تاییدیه دو مرحله ای")
    @RequestMapping(value = _PANEL_URL + "/unconfirm/{uid}/{pid}", method = RequestMethod.GET)
    public ModelAndView pUserPorterUnconfirm(@PathVariable("uid") int uid, @PathVariable("pid") int pid, Model model,
                                             HttpSession session, final RedirectAttributes redirectAttributes) {

        UserPorter up = this.service.findBy(
            Restrictions.and(
                Restrictions.eq(UserPorter.ID, pid),
                Restrictions.eq(RePa.p__(UserPorter._USER, User.ID), uid)
            ), UserPorter._USER
        );
        if (up == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.userPorter.not.found", JsonBuilder.toJson("userId", "" + uid), TtNotice.Success)));
            return new ModelAndView("redirect:/panel/user/porter/list/" + uid);
        }

        if (up.getIsActiveTwoStepConfirm()) {
            up.getUser().removePorterUuid(up.getUuid());
            this.userService.update(up.getUser());
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.userPorter.unconfirm.success", JsonBuilder.toJson("userId", "" + uid), TtNotice.Success)));
        } else {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.userPorter.unconfirm.previously", JsonBuilder.toJson("userId", "" + uid), TtNotice.Info)));
        }
        return new ModelAndView("redirect:/panel/user/porter/list/" + uid);

    }


}
