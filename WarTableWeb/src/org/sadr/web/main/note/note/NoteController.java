package org.sadr.web.main.note.note;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.Searchee;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت یادداشت")
public class NoteController extends GenericControllerImpl<Note, NoteService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/note";

    ////////////////////
    public NoteController() {
    }
    ////////////////////

    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }


    @PersianName("ثبت کاربر")
    @RequestMapping(value = _PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Note u = (Note) model.asMap().get("note");
        if (u == null) {
            u = new Note();
        }
        model.addAttribute("note", u);
        return TtTile___.p_note_create.___getDisModel(_PANEL_URL + "/create");
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(Model model,
                                    @ModelAttribute("note") @Valid Note fnote,
                                    BindingResult noteBindingResult,
                                    HttpServletRequest request,
                                    HttpSession session,
                                    final RedirectAttributes redirectAttributes) {
        if (noteBindingResult.hasErrors()) {
            return Referer.redirectBindingError(request, redirectAttributes, noteBindingResult, fnote);
        }
        fnote.setUser((User) session.getAttribute("sUser"));
        this.service.save(fnote);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.register.success", fnote.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fnote.getIdi());
    }

    //=========================== edit

    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                                  HttpSession session,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) {
        User sUser = (User) session.getAttribute("sUser");
        Note dbnote = (Note) model.asMap().get("note");
        if (dbnote == null) {
            dbnote = this.service.findBy(
                    Restrictions.and(
                            Restrictions.eq(Note.ID, uid),
                            Restrictions.eq(Note._USER, sUser)
                    )
            );
        }
        if (dbnote == null) {
            Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.note.not.found", JsonBuilder.toJson("noteId", "" + uid), TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", request);
        }

        model.addAttribute("note", dbnote);
        return TtTile___.p_note_edit.___getDisModel(_PANEL_URL + "/edit");
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("note")
            @Valid Note fnote,
            BindingResult noteBindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes, HttpSession session) {

        if (noteBindingResult.hasErrors()) {
            return Referer.redirectBindingError(request, redirectAttributes, noteBindingResult, fnote);
        }

        User sUser = (User) session.getAttribute("sUser");

        Note dbnote = this.service.findBy(
                Restrictions.and(
                        Restrictions.eq(Note.ID, fnote.getId()),
                        Restrictions.eq(Note._USER, sUser)
                )
        );

        if (dbnote == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.not.found")));
            return Referer.redirectObjects(request, redirectAttributes, fnote);
        }

        dbnote.setDateTime(fnote.getDateTime());
//        dbnote.setDateTimeG(ParsCalendar.getInstance().gre);
        dbnote.setTitle(fnote.getTitle());
        dbnote.setMessage(fnote.getMessage());
        dbnote.setImportance(fnote.getImportance());
        this.service.update(dbnote);

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", fnote.getSecretNote(), TtNotice.Success, dbnote.getTitle())));

        return Referer.redirect(_PANEL_URL + "/edit/" + dbnote.getIdi());
    }

    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {
//int i=1/0;

        Searchee.getInstance().setAttributeArray(
                model,
                "f_title",
                TtDataType.String,
                TtRestrictionOperator.Like_ANY,
                true,
                Note.TITLE
        );

        GB.searchTableColumns(model,
                Note.class,
                GB.col(Note.ID),
                GB.col(Note.CREATE_DATE_TIME),
                GB.col(Note.TITLE),
                GB.col(Note.DATE_TIME),
                GB.col(Note.IMPORTANCE)
        );
        return TtTile___.p_note_list.___getDisModel();
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(HttpServletRequest request, HttpSession session,
                                 @RequestParam(value = "ap", required = false) String ajaxParam) {
        try {
            GB gb = GB.init(Note.class)
                    .set(
                            Note.CREATE_DATE_TIME,
                            Note.TITLE,
                            Note.DATE_TIME,
                            Note.IMPORTANCE
                    )
                    .setSearchParams(ajaxParam)
                    .addRestrictionsAnd(
                            Restrictions.eq(Note._USER, (User) session.getAttribute("sUser"))
                    );
            JB jb = JB.init()
                    .set(
                            Note.CREATE_DATE_TIME,
                            Note.TITLE,
                            Note.DATE_TIME,
                            Note.IMPORTANCE
                    );

            String json = this.service.findAllJson(gb, jb);
            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Error);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public ModelAndView pTrash(@PathVariable("id") long id,
                               final RedirectAttributes redirectAttributes, HttpSession session) {

        Note dbus = this.service.find(
                GB.init(Note.class)
                        .set(Note.TITLE)
                        .setRest(
                                Restrictions.and(
                                        Restrictions.eq(Note.ID, id),
                                        Restrictions.eq(Note._USER, (User) session.getAttribute("sUser"))
                                )
                        )
        );
        if (dbus == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.not.found", JsonBuilder.toJson("noteId", "" + id))));
            return Referer.redirect(_PANEL_URL + "/list");
        }

        this.service.trash(id);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.trash.success", dbus.getSecretNote(), TtNotice.Success, dbus.getTitle())));
        return Referer.redirect(_PANEL_URL + "/list");
    }

}
