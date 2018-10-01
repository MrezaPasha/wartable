package org.sadr.web.main.system.log.daily;

import org.hibernate.criterion.Order;
import org.sadr._core._type.TtGbColumnFetch;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main._core._type.TtTile___;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author masoud
 * @version 1.95.10.07
 */
@RestController
@PersianName("مدیریت لاگ روزانه")
public class DailyLogController extends GenericControllerImpl<DailyLog, DailyLogService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/log";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    @PersianName("مشاهده آمار بازدید")
    @RequestMapping(_PANEL_URL + "/visit")
    public ModelAndView pLogVisit(Model model) {

        List<DailyLog> dls = this.service.findAll(GB.init(DailyLog.class).set(TtGbColumnFetch.All).setOrder(Order.desc(DailyLog.ID)));
        int i = 0, wu = 0, wg = 0, wt = 0, mu = 0, mg = 0, mt = 0;
        StringBuilder dailyU = new StringBuilder("[");
        StringBuilder dailyG = new StringBuilder("[");
        StringBuilder dailyT = new StringBuilder("[");
        StringBuilder weeklyU = new StringBuilder("[");
        StringBuilder weeklyG = new StringBuilder("[");
        StringBuilder weeklyT = new StringBuilder("[");
        StringBuilder monthlyU = new StringBuilder("[");
        StringBuilder monthlyG = new StringBuilder("[");
        StringBuilder monthlyT = new StringBuilder("[");

        if (dls == null || dls.isEmpty()) {
            return TtTile___.p_sys_log_visit.___getDisModel();

        }
        int loopW = ParsCalendar.getInstance().durationOnTheDayI(
            ParsCalendar.getInstance().getStartWeekDate(dls.get(0).getDayDate()),
            dls.get(0).getDayDate());

        int loopM = ParsCalendar.getInstance().durationOnTheDayI(
            ParsCalendar.getInstance().getStartMonthDate(dls.get(0).getDayDate()),
            dls.get(0).getDayDate());

        for (DailyLog dl : dls) {
            if (i < 30) {
                dailyU.replace(0, 1, "[[\"" + dl.getDayDate().substring(5) + "\"," + dl.getUserVisitCount() + "],");
                dailyG.replace(0, 1, "[[\"" + dl.getDayDate().substring(5) + "\"," + dl.getGuestVisitCount() + "],");
                dailyT.replace(0, 1, "[[\"" + dl.getDayDate().substring(5) + "\"," + dl.getVisitCount() + "],");
            }

            if (i % loopW == 0) {
                weeklyU.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + wu + "],");
                weeklyG.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + wg + "],");
                weeklyT.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + wt + "],");
                loopW = 7;
                wu = wt = wg = 0;
            } else {
                wu += dl.getUserVisitCount();
                wg += dl.getGuestVisitCount();
                wt += dl.getVisitCount();
            }

            if (i % loopM == 0) {
                monthlyU.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + mu + "],");
                monthlyG.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + mg + "],");
                monthlyT.replace(0, 1, "[[\"" + dl.getDayDate() + "\"," + mt + "],");
                // گرفتن طول ماه به صورت خودکار
                loopM = 30;
                mu = mt = mg = 0;
            } else {
                mu += dl.getUserVisitCount();
                mg += dl.getGuestVisitCount();
                mt += dl.getVisitCount();
            }
            i++;
        }

        if (dailyU.length() > 1) {
            dailyU.replace(dailyU.length() - 1, dailyU.length(), "]");
        } else {
            dailyU.append("]");
        }
        if (dailyG.length() > 1) {
            dailyG.replace(dailyG.length() - 1, dailyG.length(), "]");
        } else {
            dailyG.append("]");
        }
        if (dailyT.length() > 1) {
            dailyT.replace(dailyT.length() - 1, dailyT.length(), "]");
        } else {
            dailyT.append("]");
        }
        if (weeklyU.length() > 1) {
            weeklyU.replace(weeklyU.length() - 1, weeklyU.length(), "]");
        } else {
            weeklyU.append("]");
        }
        if (weeklyG.length() > 1) {
            weeklyG.replace(weeklyG.length() - 1, weeklyG.length(), "]");
        } else {
            weeklyG.append("]");
        }
        if (weeklyT.length() > 1) {
            weeklyT.replace(weeklyT.length() - 1, weeklyT.length(), "]");
        } else {
            weeklyT.append("]");
        }
        if (monthlyU.length() > 1) {
            monthlyU.replace(monthlyU.length() - 1, monthlyU.length(), "]");
        } else {
            monthlyU.append("]");
        }
        if (monthlyG.length() > 1) {
            monthlyG.replace(monthlyG.length() - 1, monthlyG.length(), "]");
        } else {
            monthlyG.append("]");
        }
        if (monthlyT.length() > 1) {
            monthlyT.replace(monthlyT.length() - 1, monthlyT.length(), "]");
        } else {
            monthlyT.append("]");
        }

        model.addAttribute("daily",
            "{\"u\":" + dailyU + ",\"g\":" + dailyG + ",\"t\":" + dailyT + "}"
        );
        model.addAttribute("weekly",
            "{\"u\":" + weeklyU + ",\"g\":" + weeklyG + ",\"t\":" + weeklyT + "}"
        );
        model.addAttribute("monthly",
            "{\"u\":" + monthlyU + ",\"g\":" + monthlyG + ",\"t\":" + monthlyT + "}"
        );

        OutLog.pl("d{\"u\":" + dailyU + ",\"g\":" + dailyG + ",\"t\":" + dailyT + "}");
        OutLog.pl("w{\"u\":" + weeklyU + ",\"g\":" + weeklyG + ",\"t\":" + weeklyT + "}");
        OutLog.pl("m{\"u\":" + monthlyU + ",\"g\":" + monthlyG + ",\"t\":" + monthlyT + "}");

        return TtTile___.p_sys_log_visit.___getDisModel();

    }
}
