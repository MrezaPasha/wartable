package org.sadr.share.main.grade;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.share.main.orgPosition.OrgPosition;
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
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.irror.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت درجه سازمانی")
public class GradeShareController extends GenericControllerImpl<Grade, GradeShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/grade";

    ////////////////////
    public GradeShareController() {
    }


    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Grade u = (Grade) model.asMap().get("grade");
        if (u == null) {
            u = new Grade();
        }
        model.addAttribute(u);
        return TtTile___.p_service_grade_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }


    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("grade") @Valid Grade fObj,
                                BindingResult suBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (suBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, suBindingResult, fObj);
        }

        Grade dbu;
        // =========== Code
        dbu = service.findBy(Restrictions.eq(Grade.CODE, fObj.getCode()));
        if (dbu != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.grade.code.registered", TtNotice.Warning, dbu.getValue())));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.grade.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Grade dbObj = (Grade) model.asMap().get("grade");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.grade.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute("grade", dbObj);
        return TtTile___.p_service_grade_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("grade")
            @Valid Grade fObj,
            BindingResult suBindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        if (suBindingResult.hasErrors()) {
            return Referer.redirectBindingError(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    request,
                    redirectAttributes,
                    suBindingResult,
                    fObj);
        }

        Grade dbObj;

        //========== code
        dbObj = service.findBy(
                Restrictions.and(
                        Restrictions.eq(Grade.CODE, fObj.getCode()),
                        Restrictions.ne(Grade.ID, fObj.getId())
                )
        );
        if (dbObj != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.grade.code.registered", TtNotice.Warning, dbObj.getValue())));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }


        dbObj = this.service.findById(fObj.getIdi());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.grade.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        dbObj.setCode(fObj.getCode());
        dbObj.setValue(fObj.getValue());

        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, dbObj.getValue())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //=========================== list

    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {


        Searchee.init(Grade.class, model)
                .setAttribute(
                        TtDataType.Integer,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        OrgPosition.CODE
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Grade.VALUE
                );

        GB.searchTableColumns(model,
                Grade.class,
                GB.col(Grade.ID),
                GB.col(Grade.CREATE_DATE_TIME),
                GB.col(Grade.CODE),
                GB.col(Grade.VALUE)
        );
        return TtTile___.p_service_grade_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(Grade.class)
                .set(
                        Grade.CREATE_DATE_TIME,
                        Grade.CODE,
                        Grade.VALUE
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Grade.CREATE_DATE_TIME,
                            Grade.CODE,
                            Grade.VALUE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Grade.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);
    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        Grade dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.grade.not.found", JsonBuilder.toJson("gradeId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getValue();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }
}
