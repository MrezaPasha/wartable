package org.sadr.web.main.note.note;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.*;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.tools._type.TtIxportRowIndex;
import org.sadr.web.main._core.tools._type.TtIxportSubStrategy;
import org.sadr.web.main._core.tools._type.TtIxportTtStrategy;
import org.sadr.web.main._core.tools._type.TtIxporterDownloadMode;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت یادآور")
public class NoteController extends GenericControllerImpl<Note, NoteService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/note";

    ////////////////////
    public NoteController() {
    }
    ////////////////////


    @PersianName("ثبت")
    @RequestMapping(value = _PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Note u = (Note) model.asMap().get("note");
        if (u == null) {
            u = new Note();
        }
        model.addAttribute("note", u);
        return TtTile___.p_note_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("note") @Valid Note fnote,
                                BindingResult noteBindingResult,
                                HttpServletRequest request,
                                HttpSession session,
                                final RedirectAttributes redirectAttributes) {
        if (noteBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, noteBindingResult, fnote);
        }

        if (!Validator.persianDateTime(fnote.getDateTime())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.dateTime.not.valid")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fnote);
        }

        fnote.setUser((User) session.getAttribute("sUser"));
        fnote.setIsNotified(false);
        fnote.setIsVisited(false);
        this.service.save(fnote);
        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.register.success", fnote.getSecretNote(), TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fnote.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    @PersianName("مشاهده")
    @RequestMapping(value = _PANEL_URL + "/details/{uid}")
    public ModelAndView pDetails(Model model, @PathVariable("uid") long uid,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        User sUser = (User) session.getAttribute("sUser");
        Note dbnote;
        dbnote = this.service.findBy(
                Restrictions.and(
                        Restrictions.eq(Note.ID, uid),
                        Restrictions.eq(Note._USER, sUser)
                )
        );

        if (dbnote == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.note.not.found", JsonBuilder.toJson("noteId", "" + uid), TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Failure, notice2s);
        }

        dbnote.setIsVisited(true);
        this.service.update(dbnote);
        model.addAttribute("note", dbnote);
        return TtTile___.p_note_details.___getDisModel(null, TtTaskActionStatus.Success);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              HttpSession session,
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
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.note.not.found", JsonBuilder.toJson("noteId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute("note", dbnote);
        return TtTile___.p_note_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("note")
            @Valid Note fnote,
            BindingResult noteBindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes, HttpSession session) {

        if (noteBindingResult.hasErrors()) {
            return Referer.redirectBindingError(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    request,
                    redirectAttributes,
                    noteBindingResult,
                    fnote);
        }

        if (!Validator.persianDateTime(fnote.getDateTime())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.dateTime.not.valid")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fnote);
        }

        User sUser = (User) session.getAttribute("sUser");

        Note dbnote = this.service.findBy(
                Restrictions.and(
                        Restrictions.eq(Note.ID, fnote.getId()),
                        Restrictions.eq(Note._USER, sUser)
                )
        );

        if (dbnote == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.note.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fnote);
        }

        dbnote.setDateTime(fnote.getDateTime());
        dbnote.setTitle(fnote.getTitle());
        dbnote.setMessage(fnote.getMessage());
        dbnote.setImportance(fnote.getImportance());

        if (ParsCalendar.getInstance().compareDateTime(fnote.getDateTime(), ParsCalendar.getInstance().getShortDateTime()) == TtCompareResult.FirstIsBigger) {
            dbnote.setIsNotified(false);
            dbnote.setIsVisited(false);
        }

        this.service.update(dbnote);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, dbnote.getTitle())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbnote.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {


        Searchee.init(Note.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Note.TITLE
                )
        ;

        GB.searchTableColumns(model,
                Note.class,
                GB.col(Note.ID),
                GB.col(Note.CREATE_DATE_TIME),
                GB.col(Note.TITLE),
                GB.col(Note.DATE_TIME),
                GB.col(Note.IMPORTANCE)
        );
        return TtTile___.p_note_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response, HttpSession session) throws IOException {
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

        if (ixportParam == null) {
            JB jb = JB.init()
                    .set(
                            Note.CREATE_DATE_TIME,
                            Note.TITLE,
                            Note.DATE_TIME,
                            Note.IMPORTANCE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb.setIxportParams(ixportParam);
        return Ixporter.init(Note.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id, HttpSession session) {
        Note dbObj = this.service.find(
                GB.init(Note.class)
                        .set(Note.TITLE)
                        .setRest(
                                Restrictions.and(
                                        Restrictions.eq(Note.ID, id),
                                        Restrictions.eq(Note._USER, session.getAttribute("sUser"))
                                )
                        )
        );
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.note.not.found", JsonBuilder.toJson("noteId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getTitle();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }

    @PersianName("اطلاع رسانی سررسید هشدار به صورت خودکار")
    @RequestMapping(value = _PANEL_URL + "/sync", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pSync(HttpSession session) {
        User sUser = (User) session.getAttribute("sUser");

        if (sUser == null) {
            return Ison.init()
                    .setStatus(TtIsonStatus.Nok)
                    .toResponse();
        }

        List<Note> list = this.service.findAllBy(
                Restrictions.and(
                        Restrictions.eq(Note._USER, sUser),
                        Restrictions.eq(Note.IS_NOTIFIED, false),
                        Restrictions.le(Note.DATE_TIME, ParsCalendar.getInstance().getShortDateTime())
                )
        );
        if (list.isEmpty()) {
            return Ison.init()
                    .setStatus(TtIsonStatus.Nok)
                    .toResponse();
        }
        String json = "";
        for (Note note : list) {
            json += ",{";
            json += "\"id\":" + note.getId() + "";
            json += ",\"title\":\"" + note.getTitle() + "\"";
            json += ",\"dateTime\":\"" + note.getDateTime() + "\"";
            json += ",\"importance\":\"" + note.getImportance().getTitle() + "\"";
            note.setIsNotified(true);
            this.service.update(note);
            json += "}";
        }

        json = "[" + json.substring(1) + "]";
        return Ison.init()
                .setStatus(TtIsonStatus.Ok)
                .setProperty("header", SpringMessager.get("N1.note.you.have.note", list.size()))
                .setPropertyJson("notes", json)
                .toResponse();
    }

}
