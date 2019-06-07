package org.sadr.share.main.personModel;

import org.hibernate.criterion.Restrictions;
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
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
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
@PersianName("مدیریت مدل کاربر")
public class PersonModelShareController extends GenericControllerImpl<PersonModel, PersonModelShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/personModel";

    ////////////////////
    public PersonModelShareController() {
    }


    //=========================== create
    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        PersonModel u = (PersonModel) model.asMap().get("personModel");
        if (u == null) {
            u = new PersonModel();
        }
        u.setScale(1);
        model.addAttribute(u);
        return TtTile___.p_service_personModel_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("personModel") @Valid PersonModel fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        if (fObj.getName() == null || fObj.getName().isEmpty()) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.name.is.empty", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (this.service.isExist(
                Restrictions.eq(PersonModel.NAME, fObj.getName()))) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.name.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (attachment != null && attachment.getSize() > 0) {
            //            if (!attachment.getOriginalFilename().endsWith(".model")) {
            if (!attachment.getOriginalFilename().endsWith(".obj")
                    && !attachment.getOriginalFilename().endsWith(".fbx")) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.upload.format.invalid", TtNotice.Danger)));
                return Referer.redirectObject(request, redirectAttributes, fObj);
            }

            if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.personModel.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
                return Referer.redirectObject(request, redirectAttributes, fObj);
            }

            File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));
            //
            fObj.setFileName(upload.getDirectoryRelativePath() + upload.getOrginalName());
//            fObj.setName(upload.getName());
            fObj.setFileId(upload.getId());
            fObj.setSize(attachment.getSize());
        }

        fObj.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.service.save(fObj);


        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        PersonModel dbObj = (PersonModel) model.asMap().get("personModel");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.personModel.not.found", JsonBuilder.toJson("personModelId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute("objectPath",
                dbObj.getFileName() == null || dbObj.getFileName().isEmpty() ? null :
                        "/panel/file/dl/" + dbObj.getFileId()
        );

        model.addAttribute(dbObj);

        return TtTile___.p_service_personModel_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("personModel")
            @Valid PersonModel fObj,
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

        PersonModel dbObj;


        dbObj = this.service.findById(fObj.getIdi());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        if (this.service.isDuplicateWith(
                Restrictions.eq(PersonModel.NAME, fObj.getName()), dbObj.getId())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.name.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (attachment != null) {
            if (attachment.getSize() > 0) {
                //            if (!attachment.getOriginalFilename().endsWith(".model")) {
                if (!attachment.getOriginalFilename().endsWith(".obj")
                        && !attachment.getOriginalFilename().endsWith(".fbx")) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.personModel.upload.format.invalid", TtNotice.Danger)));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }

                if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.model.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }

                File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));

                dbObj.setFileName(upload.getOrginalName());
                dbObj.setSize(upload.getSize());
                dbObj.setFileId(upload.getId());

            }
        } else {
            dbObj.setFileName(null);
            fObj.setFileId(0);

        }

        dbObj.setName(fObj.getName());
        dbObj.setScale(fObj.getScale());

        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getName())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }
    //=========================== details

    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {


        Searchee.init(PersonModel.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        PersonModel.FILENAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        PersonModel.NAME
                );

        GB.searchTableColumns(model,
                PersonModel.class,
                GB.col(PersonModel.ID),
                GB.col(PersonModel.CREATE_DATE_TIME),
                GB.col(PersonModel.MODIFY_DATE_TIME),
                GB.col(PersonModel.NAME),
                GB.col(PersonModel.FILENAME),
                GB.col(PersonModel.SCALE),
                GB.col(PersonModel.SIZE)
        );
        return TtTile___.p_service_personModel_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(PersonModel.class)
                .set(
                        PersonModel.CREATE_DATE_TIME,
                        PersonModel.MODIFY_DATE_TIME,
                        PersonModel.NAME,
                        PersonModel.FILENAME,
                        PersonModel.SCALE,
                        PersonModel.SIZE
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            PersonModel.CREATE_DATE_TIME,
                            PersonModel.MODIFY_DATE_TIME,
                            PersonModel.NAME,
                            PersonModel.FILENAME,
                            PersonModel.SCALE,
                            PersonModel.SIZE                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(PersonModel.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        PersonModel dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.personModel.not.found", JsonBuilder.toJson("personModelId", "" + id)))
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
