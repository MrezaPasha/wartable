package org.sadr.web.main.system.log.attempt;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.utils.Cookier;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin._type.TtUserAttemptResult;
import org.sadr.web.main.admin._type.TtUserAttemptType;
import org.sadr.web.main.admin.user.porter.UserPorter;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author masoud
 */
@Service
//@Component
public class UserAttemptServiceImp extends GenericServiceImpl<UserAttempt, UserAttemptDao> implements UserAttemptService {

    @Override
    public void deleteUUID(User u) {
        dao.deleteAttempt(u);
    }

    @Override
    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType) {
        UserAttempt uatt = null;
        String uuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
        return attemptStatus(request, response, model, user, attemptType, uatt, uuid, true);
    }

    @Override
    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, boolean mergeNotice) {
        UserAttempt uatt = null;
        String uuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
        return attemptStatus(request, response, model, user, attemptType, uatt, uuid, mergeNotice);
    }

    /**
     * تعیین وضعیت برای نمایش یا عدم نمایش
     *
     * @param request
     * @param response
     * @param model
     * @param user
     * @param attemptType
     * @param uatt
     * @param uuid
     * @return
     */
    @Override
    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, UserAttempt uatt, String uuid) {
        return attemptStatus(request, response, model, user, attemptType, uatt, uuid, true);
    }

    @Override
    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, UserAttempt uatt, String uuid, boolean mergeNotice) {
        if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.UserAttemptOn)) {
            model.addAttribute("noRecaptcha", true);
            return TtUserAttemptResult.Ok;
        }
        if (!mergeNotice) {
            Notice2.releaseModelNotice(model);
        }
        if (uuid != null && !uuid.isEmpty()) {
            if (uatt == null) {
                if (user != null) {
                    uatt = this.findBy(Restrictions.and(
                            Restrictions.eq(UserAttempt._USER, user),
                            Restrictions.eq(UserAttempt.ATTEMPT_TYPE, attemptType)));

                } else {
                    uatt = this.findBy(Restrictions.and(
                            Restrictions.eq(UserAttempt.UUID, uuid),
                            Restrictions.eq(UserAttempt.ATTEMPT_TYPE, attemptType)));

                }
            }
            if (uatt != null) {
                if (uatt.isBlocked()) {
                    Notice2.initModelAttr(mergeNotice, model, Notice2.addNotices(new Notice2("N1.user.attempt.blocked", JsonBuilder.toJson("sttType", attemptType.toString()), TtNotice.Danger, ParsCalendar.getInstance().toRemindingTimeString(uatt.getDateTimeG(), uatt.getAttemptType().getBlockSec()))));
                    model.addAttribute("noRecaptcha", false);
                    model.addAttribute("blocked", true);
                    return TtUserAttemptResult.IsBlock;
                }
                model.addAttribute("blocked", false);
                if (uatt.isInAttemptRange()) {
                    if (uatt.isInAttemptCount()) {
                        model.addAttribute("noRecaptcha", true);
                        return TtUserAttemptResult.Ok;
                    }
                    model.addAttribute("noRecaptcha", false);
                    return TtUserAttemptResult.NeedRecaptcha;
                }
                uatt.setCount(0);
                this.update(uatt);
                model.addAttribute("noRecaptcha", true);
                return TtUserAttemptResult.Ok;
            }
            if (!UserPorter.isValidateUUID(request.getHeader("User-Agent"), request.getRemoteAddr(), uuid)) {
                Cookier.deleteCookie(response, TtCookierVariable.UserPorterUUID.getKey());
                model.addAttribute("noRecaptcha", false);
                return TtUserAttemptResult.UuidNotValid;
            }
            // checkup integrity in post method
            model.addAttribute("noRecaptcha", true);
            return TtUserAttemptResult.Ok;
        }
        model.addAttribute("noRecaptcha", false);
        return TtUserAttemptResult.NeedRecaptcha;
    }

    @Override
    /**
     * بروزرسانی تعداد تلاش ها
     */
    public void rebuildUerAttempt(HttpServletRequest request, HttpServletResponse response, UserAttempt uatt, TtUserAttemptType attemptType, String uuid, User user) {
        if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.UserAttemptOn)) {
        } else if (uatt != null) {
            if (uatt.isInAttemptRange()) {
                uatt.addCount();
            } else {
                uatt.setCount(1);
            }
            uatt.setIsSuccess(false);
            uatt.refreshDateTime();
            if (user != null) {
                uatt.setUser(user);
            }
            this.update(uatt);
        } else {
            uatt = new UserAttempt();
            uatt.setUser(user);
            uatt.setAgentSignature(request.getHeader("User-Agent"));
            uatt.setComputerSignature(request.getRemoteAddr());
            uatt.refreshDateTime();
            uatt.setAttemptType(attemptType);
            uatt.setIsSuccess(false);
            if (uuid == null || uuid.isEmpty()) {
                uuid = uatt.generateSecureUUID();
                Cookier.setCookie(response, TtCookierVariable.UserPorterUUID, uuid);
            } else {
                uatt.setUuid(uuid);
            }
            this.save(uatt);
        }
    }

    /**
     * برای مواردی که هم نیاز به بروزرسانی تعداد تلاشها است هم نیاز به تعیین
     * وضعیت
     *
     * @param request
     * @param response
     * @param uatt
     * @param attemptType
     * @param uuid
     * @param user
     * @param model
     * @return
     */
    @Override
    public TtUserAttemptResult rebuildUerAttempt(HttpServletRequest request, HttpServletResponse response, UserAttempt uatt, TtUserAttemptType attemptType, String uuid, User user, Model model) {
        if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.UserAttemptOn)) {
            model.addAttribute("noRecaptcha", true);
            return TtUserAttemptResult.Ok;
        } else if (uatt != null) {
            if (uatt.isInAttemptRange()) {
                uatt.addCount();
            } else {
                uatt.setCount(1);
            }
            uatt.setIsSuccess(false);
            uatt.refreshDateTime();
            if (user != null) {
                uatt.setUser(user);
            }
            this.update(uatt);
        } else {
            uatt = new UserAttempt();
            uatt.setUser(user);
            uatt.setAgentSignature(request.getHeader("User-Agent"));
            uatt.setComputerSignature(request.getRemoteAddr());
            uatt.refreshDateTime();
            uatt.setAttemptType(attemptType);
            uatt.setIsSuccess(false);
            if (uuid == null || uuid.isEmpty()) {
                uuid = uatt.generateSecureUUID();
                Cookier.setCookie(response, TtCookierVariable.UserPorterUUID, uuid);
            } else {
                uatt.setUuid(uuid);
            }
            this.save(uatt);
        }
        return attemptStatus(request, response, model, user, attemptType, uatt, uuid);
    }

}
