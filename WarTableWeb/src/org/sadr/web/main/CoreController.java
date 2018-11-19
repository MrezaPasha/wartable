package org.sadr.web.main;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.Front;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.StandaloneController;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.sadr.web.main._core.utils.CacheStatic;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.note.note.Note;
import org.sadr.web.main.note.note.NoteService;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author masoud
 */
@StandaloneController
@RestController
@PersianName("مدیریت میز جنگ")
public class CoreController {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////


    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    /////////////////////////////////////////////////////// PANEL
    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @PersianName("داشبورد")
    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public ModelAndView pPanelDashboard(Model model, HttpSession session) {
        User suser = (User) session.getAttribute("sUser");
        if (suser == null) {
            return TtHttpErrorCode___.Unauthorized_401.___getFrontDisModel();
        }
        model.addAttribute("slList", CacheStatic.getSigninLog(suser.getId()));

        model.addAttribute("nlist", this.noteService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Note._USER, (User) session.getAttribute("sUser")),
                        Restrictions.eq(Note.IS_VISITED, false),
                        Restrictions.le(Note.DATE_TIME, ParsCalendar.getInstance().getShortDateTime())
                )
        ));

        return TtTile___.p_dashboard.___getDisModel();
    }

    /////////////////////////////////////////////////////// FRONT
    @Front
    @TaskAccessLevel
    @PersianName("صفحه اول")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView fIndex(HttpSession session) {
        if (session.getAttribute("sUser") == null) {
            return new ModelAndView("redirect:/signin");
//            return Referer.redirect("/signin");
        } else if (((User) session.getAttribute("sUser")).getLevel() == TtUserLevel.Client) {
            return TtTile___.f_index.___getDisModel();
        }
        return Referer.redirect("/panel");
    }


}
