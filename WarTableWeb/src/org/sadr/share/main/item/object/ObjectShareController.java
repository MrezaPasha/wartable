package org.sadr.share.main.item.object;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools._type.*;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.tools.uploader.Uploader;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
@PersianName("مدیریت اشیاء")
public class ObjectShareController extends GenericControllerImpl<Object, ObjectShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/object";

    ////////////////////
    public ObjectShareController() {
    }

    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    //=========================== create
    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Object u = (Object) model.asMap().get("object");
        if (u == null) {
            u = new Object();
        }
        model.addAttribute(u);
        return TtTile___.p_service_object_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("object") @Valid Object fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        if (attachment != null && attachment.getSize() > 0) {
            if (!attachment.getOriginalFilename().endsWith(".model")) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.object.upload.format.invalid", TtNotice.Danger)));
                return Referer.redirectObject(request, redirectAttributes, fObj);
            }
            File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath));
            //
            fObj.setFileName(upload.getOrginalName());
        }

        fObj.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.service.save(fObj);


        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.object.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Object dbObj = (Object) model.asMap().get("object");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.object.not.found", JsonBuilder.toJson("objectId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }


        model.addAttribute(dbObj);
        model.addAttribute("objectPath",
                dbObj.getFileName() == null || dbObj.getFileName().isEmpty() ? null :
                        PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath) + "/object/" + dbObj.getFileName()
        );
        return TtTile___.p_service_object_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("object")
            @Valid Object fObj,
            BindingResult suBindingResult,
            HttpServletRequest request,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment,
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

        Object dbObj;


        dbObj = this.service.findById(fObj.getIdi());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.object.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        if (attachment != null) {
            if (attachment.getSize() > 0) {
                if (!attachment.getOriginalFilename().endsWith(".model")) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.object.upload.format.invalid", TtNotice.Danger)));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }
                File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath));
                //
                dbObj.setFileName(upload.getOrginalName());
            }
        } else {
            dbObj.setFileName(null);
        }

        dbObj.setName(fObj.getName());
        dbObj.setDescription(fObj.getDescription());
        dbObj.setCategory(fObj.getCategory());
        dbObj.setArea(fObj.getArea());

        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

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

        Searchee.init(Object.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Object.NAME
                );

        GB.searchTableColumns(model,
                Object.class,
                GB.col(Object.ID),
                GB.col(Object.UPLOAD_DATE_TIME),
                GB.col(Object.NAME),
                GB.col(Object.AREA),
                GB.col(Object.CATEGORY),
                GB.col(Object.SIZE)
        );
        return TtTile___.p_service_object_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(Object.class)
                .set(
                        Object.NAME,
                        Object.AREA,
                        Object.CATEGORY,
                        Object.UPLOAD_DATE_TIME,
                        Object.SIZE

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Object.NAME,
                            Object.AREA,
                            Object.CATEGORY,
                            Object.UPLOAD_DATE_TIME,
                            Object.SIZE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Object.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);
    }

    //=========================== preview
    @PersianName("نمایش")
    @RequestMapping(value = _PANEL_URL + "/preview/{uid}")
    public ModelAndView pPreview(Model model, @PathVariable("uid") long uid,
                                 RedirectAttributes redirectAttributes) {

        Object dbObj;
        dbObj = this.service.findById(uid);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.object.not.found", JsonBuilder.toJson("objectId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    null,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute("objectPath",
                dbObj.getFileName() == null || dbObj.getFileName().isEmpty() ? null :
                        PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath) + "/object/" + dbObj.getFileName()
        );
        model.addAttribute(dbObj);

        return TtTile___.p_service_object_preview.___getDisModel(null, TtTaskActionStatus.Success);
    }


    //=========================== Trash

    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        Object dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.object.not.found", JsonBuilder.toJson("objectId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getName();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }
}
