package org.sadr.share.main.serviceUser;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.*;
import org.sadr._core.utils._type.TtPasswordType;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.share.main._types.TtMemberStatus;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeShareService;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.orgPosition.OrgPositionShareService;
import org.sadr.share.main.personModel.PersonModel;
import org.sadr.share.main.personModel.PersonModelShareService;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareService;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsShareService;
import org.sadr.share.main.textChat.TextChat;
import org.sadr.share.main.textChat.TextChatShareService;
import org.sadr.share.main.textChat._types.TtTextChatType;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools._type.TtIxportRowIndex;
import org.sadr.web.main._core.tools._type.TtIxportSubStrategy;
import org.sadr.web.main._core.tools._type.TtIxportTtStrategy;
import org.sadr.web.main._core.tools._type.TtIxporterDownloadMode;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.tools.uploader.Uploader;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
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
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت کاربران میز جنگ")
public class ServiceUserShareController extends GenericControllerImpl<ServiceUser, ServiceUserShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/user";
    ////////////////////

    public ServiceUserShareController() {
    }
    /////////////////////////////////////////////////////// PANEL

    private GradeShareService gradeShareService;
    private OrgPositionShareService orgPositionShareService;
    private SessionsShareService sessionsShareService;
    private Room_ServiceUserShareService room_serviceUserShareService;
    private PersonModelShareService personModelShareService;
    private TextChatShareService textChatShareService;

    @Autowired
    public void setTextChatShareService(TextChatShareService textChatShareService) {
        this.textChatShareService = textChatShareService;
    }

    @Autowired
    public void setPersonModelShareService(PersonModelShareService personModelShareService) {
        this.personModelShareService = personModelShareService;
    }

    @Autowired
    public void setRoom_serviceUserShareService(Room_ServiceUserShareService room_serviceUserShareService) {
        this.room_serviceUserShareService = room_serviceUserShareService;
    }

    @Autowired
    public void setSessionsShareService(SessionsShareService sessionsShareService) {
        this.sessionsShareService = sessionsShareService;
    }

    @Autowired
    public void setGradeShareService(GradeShareService gradeShareService) {
        this.gradeShareService = gradeShareService;
    }

    @Autowired
    public void setOrgPositionShareService(OrgPositionShareService orgPositionShareService) {
        this.orgPositionShareService = orgPositionShareService;
    }

    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        ServiceUser u = (ServiceUser) model.asMap().get("serviceUser");
        if (u == null) {
            u = new ServiceUser();
        }

        PersonModel userModel = this.personModelShareService.findById(PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.ServiceUserDefaultModelId));
        if (userModel == null) {
            userModel = new PersonModel();
        } else {
            model.addAttribute("objectPath",
                    "/panel/file/dl/" + userModel.getFileId()
            );
        }

        u.setUserModel(userModel);

        model.addAttribute(u);
        model.addAttribute("glist", gradeShareService.findAll());
        model.addAttribute("oplist", orgPositionShareService.findAll());
        model.addAttribute("umlist", personModelShareService.findAll());
        return TtTile___.p_service_serviceUser_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }


    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("serviceUser") @Valid ServiceUser fObj,
                                BindingResult suBindingResult,
                                HttpServletRequest request,
                                @RequestParam(name = "uploadModel", required = false, defaultValue = "false") Boolean uploadModel,
                                @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                                final RedirectAttributes redirectAttributes) {
        OutLog.pl(suBindingResult.toString());
        if (suBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, suBindingResult, fObj);
        }

        if (fObj.getUserModel() != null && fObj.getUserModel().getId() == 0) {
            fObj.setUserModel(null);
        }
        if (fObj.getGrade() == null || fObj.getGrade().getId() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.grade.is.empty", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }
        if (fObj.getOrgPosition() == null || fObj.getOrgPosition().getId() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.orgPosition.is.empty", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        ServiceUser dbu;
        // =========== username
        dbu = service.findBy(Restrictions.eq(ServiceUser.USERNAME, fObj.getUserName()));
        if (dbu != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.username.registered", TtNotice.Warning, dbu.getName())));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (uploadModel) {
            if (attachment != null && attachment.getSize() > 0) {
                if (!attachment.getOriginalFilename().endsWith(".obj")
                        && !attachment.getOriginalFilename().endsWith(".fbx")) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.upload.format.invalid", TtNotice.Danger)));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }

                if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }

                File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));
                //
                if (upload != null) {
                    PersonModel model = new PersonModel();
                    model.setFileName(upload.getAbsolutePathName());
                    model.setName("مدل " + fObj.getFullName() + "_" + CodeGenerator.code(4));
                    model.setSize(upload.getSize());
                    model.setScale(fObj.getUserModel().getScale());
                    model.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
                    model.setFileId(upload.getId());
                    personModelShareService.save(model);
                    fObj.setUserModel(model);
                }
            }
        } else if (fObj.getUserModel() != null) {
            PersonModel um = this.personModelShareService.findById(fObj.getUserModel().getId());
            if (um == null) {
                fObj.setUserModel(null);
            } else {
                um.setScale(fObj.getUserModel().getScale());
                this.personModelShareService.update(um);
                fObj.setUserModel(um);
            }
        } else {
            fObj.setUserModel(null);
        }

        fObj.setPassword(CodeGenerator.password(TtPasswordType.Mix, 12));
        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        ServiceUser dbObj = (ServiceUser) model.asMap().get("serviceUser");
        if (dbObj == null) {
            dbObj = this.service.findById(uid, ServiceUser._GRADE, ServiceUser._ORG_POSITION, ServiceUser._USER_MODEL);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        model.addAttribute("objectPath",
                dbObj.getUserModel() == null || dbObj.getUserModel().getFileName() == null || dbObj.getUserModel().getFileName().isEmpty() ? null :
                        "/panel/file/dl/" + dbObj.getUserModel().getFileId()
        );
//        model.addAttribute("objectPath",
//                dbObj.getUserModel() == null || dbObj.getUserModel().getFileName() == null || dbObj.getUserModel().getFileName().isEmpty() ? null :
//                        PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath) + "/user/" + dbObj.getUserModel().getFileName()
//        );
//        model.addAttribute("userMaterialPath",
//                dbObj.getUserModel() == null || dbObj.getUserModel().getFileName() == null || dbObj.getUserModel().getFileName().isEmpty() ? null :
//                        PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath) + "/user/" + dbObj.getUserModel().getFileName().substring(0, dbObj.getUserModel().getFileName().lastIndexOf(".")) + ".mtl"
//        );
        model.addAttribute("glist", gradeShareService.findAll());
        model.addAttribute("oplist", orgPositionShareService.findAll());
        model.addAttribute("umlist", personModelShareService.findAll());
        model.addAttribute("serviceUser", dbObj);
        return TtTile___.p_service_serviceUser_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("serviceUser")
            @Valid ServiceUser fObj,
            BindingResult suBindingResult,
            HttpServletRequest request,
            @RequestParam(name = "uploadModel", required = false, defaultValue = "false") Boolean uploadModel,
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

        if (fObj.getUserModel() != null && fObj.getUserModel().getId() == 0) {
            fObj.setUserModel(null);
        }
        if (fObj.getGrade() == null || fObj.getGrade().getId() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.grade.is.empty", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }
        if (fObj.getOrgPosition() == null || fObj.getOrgPosition().getId() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.orgPosition.is.empty", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.Add_New_User, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }


        ServiceUser dbObj;

        //========== username
        dbObj = service.findBy(
                Restrictions.and(
                        Restrictions.eq(ServiceUser.USERNAME, fObj.getUserName()),
                        Restrictions.ne(ServiceUser.ID, fObj.getId())
                )
        );
        if (dbObj != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.username.registered", TtNotice.Warning, dbObj.getName())));
            return Referer.redirectObjects(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        dbObj = this.service.findById(fObj.getIdi(), ServiceUser._GRADE, ServiceUser._ORG_POSITION, ServiceUser._USER_MODEL);

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        if (uploadModel) {
            if (attachment != null) {
                if (attachment.getSize() > 0) {
                    if (!attachment.getOriginalFilename().endsWith(".obj")
                            && !attachment.getOriginalFilename().endsWith(".fbx")) {
                        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.upload.format.invalid", TtNotice.Danger)));
                        return Referer.redirectObject(request, redirectAttributes, fObj);
                    }

                    if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
                        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
                        return Referer.redirectObject(request, redirectAttributes, fObj);
                    }
                    File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));
                    if (upload != null) {
                        PersonModel model = new PersonModel();
                        model.setFileName(upload.getAbsolutePathName());
                        model.setSize(upload.getSize());
                        model.setName("مدل " + fObj.getFullName() + "_" + CodeGenerator.code(4));
                        model.setScale(fObj.getUserModel().getScale());
                        model.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
                        model.setFileId(upload.getId());
                        personModelShareService.save(model);
                        dbObj.setUserModel(model);
                    }
                } else {
                    dbObj.getUserModel().setScale(fObj.getUserModel().getScale());
                    personModelShareService.update(dbObj.getUserModel());
                }
            } else {
                dbObj.setUserModel(null);
            }
        } else if (fObj.getUserModel() != null) {
            PersonModel um = this.personModelShareService.findById(fObj.getUserModel().getId());
            if (um == null) {
                dbObj.setUserModel(null);
            } else {
                um.setScale(fObj.getUserModel().getScale());
                this.personModelShareService.update(um);
                dbObj.setUserModel(um);
            }
        } else {
            dbObj.setUserModel(null);
        }
        dbObj.setName(fObj.getName());
        dbObj.setFamily(fObj.getFamily());
        dbObj.setUserName(fObj.getUserName());
        dbObj.setGrade(fObj.getGrade());
        dbObj.setOrgPosition(fObj.getOrgPosition());
        dbObj.setBanned(fObj.getBanned());
        dbObj.setDeleted(fObj.getDeleted());

        this.service.update(dbObj);


        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, dbObj.getUserName())));

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


        Searchee.init(ServiceUser.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        ServiceUser.USERNAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        ServiceUser.NAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        ServiceUser.FAMILY
                );

        GB.searchTableColumns(model,
                ServiceUser.class,
                GB.col(ServiceUser.ID),
                GB.col(ServiceUser.CREATE_DATE_TIME),
                GB.col(ServiceUser.NAME),
                GB.col(ServiceUser.FAMILY),
                GB.col(ServiceUser.USERNAME),
                GB.col(ServiceUser.DELETED),
                GB.col(ServiceUser.BANNED),
                GB.col(ServiceUser.LAST_IP_ADDRESS),
                GB.col(ServiceUser.LAST_SIGNIN_DATETIME),
                GB.col(ServiceUser.$GRAGE_TITLE, ServiceUser.MODIFY_DATE_TIME),
                GB.col(ServiceUser.$ORG_POSITION_TITLE, ServiceUser.CREATION_TIME)
        );
        return TtTile___.p_service_serviceUser_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(ServiceUser.class)
                .set(
                        ServiceUser.CREATE_DATE_TIME,
                        ServiceUser.NAME,
                        ServiceUser.FAMILY,
                        ServiceUser.USERNAME,
                        ServiceUser.DELETED,
                        ServiceUser.BANNED,
                        ServiceUser.MODIFY_DATE_TIME,
                        ServiceUser.LAST_SIGNIN_DATETIME,
                        ServiceUser.LAST_IP_ADDRESS,
                        ServiceUser.CREATION_TIME
                )
                .setGbs(
                        GB.init(Grade.class, ServiceUser._GRADE)
                                .set(Grade.VALUE),
                        GB.init(OrgPosition.class, ServiceUser._ORG_POSITION)
                                .set(OrgPosition.VALUE)
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            ServiceUser.CREATE_DATE_TIME,
                            ServiceUser.NAME,
                            ServiceUser.FAMILY,
                            ServiceUser.USERNAME,
                            ServiceUser.DELETED,
                            ServiceUser.BANNED,
                            ServiceUser.LAST_IP_ADDRESS,
                            ServiceUser.LAST_SIGNIN_DATETIME,
                            ServiceUser.$GRAGE_TITLE,
                            ServiceUser.$ORG_POSITION_TITLE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(ServiceUser.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* ONLINE USER LIST
    @PersianName("لیست کاربران آنلاین")
    @RequestMapping(value = _PANEL_URL + "/list/online")
    public ModelAndView pListOnline(Model model) {


        Searchee.init(Sessions.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Sessions.SESSION_ID
                );

        GB.searchTableColumns(model,
                Sessions.class,
                GB.col(Sessions.ID),
                GB.col(Sessions.CREATION_DATE_TIME),
                GB.col(Sessions.SESSION_ID),
                GB.col(Sessions.STATUS),
                GB.col(Sessions.$SERVICE_USER_FULL_NAME, GB.path(Sessions._SERVICE_USER, ServiceUser.NAME)),
                GB.col(Sessions.UPDATE_DATE_TIME),
                GB.col(Sessions.$ONLINE_ROOM_NAME),
                GB.col(Sessions.PRIVILAGE_FLAG)
        );
        return TtTile___.p_service_serviceUser_listOnline.___getDisModel(null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list/online", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pListOnline(@RequestParam(value = "ap", required = false) String ajaxParam,
                                       @RequestParam(value = "ixp", required = false) String ixportParam,
                                       HttpServletResponse response) throws IOException {

        GB gb = GB.init(Sessions.class)
                .set(
                        Sessions.CREATION_DATE_TIME,
                        Sessions.PRIVILAGE_FLAG,
                        Sessions.SESSION_ID,
                        Sessions.STATUS,
                        Sessions.UPDATE_DATE_TIME

                )
                .setGbs(
                        GB.init(ServiceUser.class, Sessions._SERVICE_USER)
                                .set(
                                        ServiceUser.NAME,
                                        ServiceUser.FAMILY
                                )
                                .setGbs(
                                        GB.init(Room_ServiceUser.class, ServiceUser._ONLINE_ROOM)
                                                .setGbs(
                                                        GB.init(Room.class, Room_ServiceUser._ROOM)
                                                                .set(Room.ROOM_NAME)
                                                )
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Sessions.CREATION_DATE_TIME,
                            Sessions.PRIVILAGE_FLAG,
                            Sessions.SESSION_ID,
                            Sessions.STATUS,
                            Sessions.UPDATE_DATE_TIME,
                            Sessions.$SERVICE_USER_FULL_NAME,
                            Sessions.$ONLINE_ROOM_NAME
                    )
                    .setJbs(
                            JB.init(ServiceUser.class, Sessions._SERVICE_USER)
                                    .set(
                                            ServiceUser.ID
                                    )
                    );

            String jSearch = this.sessionsShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Sessions.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* Active Room list
    @PersianName("اتاق فعال")
    @RequestMapping(value = _PANEL_URL + "/room/{uid}")
    public ModelAndView pActiveRoom(Model model, @PathVariable("uid") long uid,
                                    RedirectAttributes redirectAttributes) {

        ServiceUser dbObj = this.service.findById(uid);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list/online",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        Room_ServiceUser sess = this.room_serviceUserShareService.findBy(
                Restrictions.and(
                        Restrictions.eq(Room_ServiceUser._SERVICE_USER, dbObj),
                        Restrictions.eq(Room_ServiceUser.USER_STATUS, TtMemberStatus.Active)
                )
                , Room_ServiceUser._SERVICE_USER, Room_ServiceUser._ROOM
        );
        if (sess == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.roomServiceUser.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list/online",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute("room_ServiceUser", sess);
        return TtTile___.p_service_serviceUser_room.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @PersianName("خروج از اتاق آنلاین")
    @RequestMapping(value = _PANEL_URL + "/room/exit/{uid}")
    public ModelAndView pExitFromOnlineRoom(@PathVariable("uid") long uid,
                                            RedirectAttributes redirectAttributes) {

        ServiceUser dbObj = this.service.findById(uid, ServiceUser._ONLINE_ROOM);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list/online",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        if (dbObj.getOnlineRoom() == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.online.room.not.found", JsonBuilder.toJson("suId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list/online",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }


        dbObj.setOnlineRoom(null);
        this.service.update(dbObj);

        Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.online.room.exit.success", JsonBuilder.toJson("suId", "" + uid), TtNotice.Success));
        return Referer.redirect(
                _PANEL_URL + "/list/online",
                TtTaskActionSubType.Take_Report,
                TtTaskActionStatus.Success,
                noteIds);
    }

    //=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* DEACTIVATE & EXPIRE
    @PersianName("منقضی کردن نشست کاربر")
    @RequestMapping(value = _PANEL_URL + "/expire/{id}")
    public ModelAndView pExpire(@PathVariable("id") int id,
                                final RedirectAttributes redirectAttributes) {

        Sessions dbus = this.sessionsShareService.find(id,
                GB.init(Sessions.class)
                        .set(
                                Sessions.SESSION_ID
                        )
        );
        Notice2[] notice2s;
        if (dbus == null) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.user.not.found", JsonBuilder.toJson("userId", "" + id))));
            return Referer.redirect(_PANEL_URL + "/list/online", null, TtTaskActionStatus.Failure, notice2s);
        }

        this.sessionsShareService.delete(dbus.getIdi());

        notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.user.expire.success", TtNotice.Success, dbus.getSessionId())));

        return Referer.redirect(_PANEL_URL + "/list/online", null, TtTaskActionStatus.Success, notice2s);


    }

    //=========================== password
    private ModelAndView changePassRedirect(User fuser, Notice2[] notice2s, RedirectAttributes redirectAttributes) {
        return Referer.redirectObjects(TtTaskActionSubType.Change_Password, TtTaskActionStatus.Failure, notice2s,
                (_PANEL_URL + "/user-pass/" + fuser.getIdi()), redirectAttributes,
                fuser);

    }

    private ModelAndView changePass(User fuser, String newPass, String rePass, RedirectAttributes redirectAttributes) {
        if (fuser == null || fuser.getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.password.change.error")));
            return changePassRedirect(fuser, notice2s, redirectAttributes);
        }

        if (!newPass.equals(rePass)) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.repassword.not.equal", TtNotice.Warning)));
            return changePassRedirect(fuser, notice2s, redirectAttributes);
        }


        ServiceUser dbu = service.findById(fuser.getId());
        if (dbu == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.not.found", fuser.getSecretNote(), TtNotice.Danger)));
            return changePassRedirect(fuser, notice2s, redirectAttributes);
        }
        int prop;
        boolean boProp, checked;
        checked = false;
        Notice2[] notice2s = null;
        //-------- min length
        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordMinLength);
        if (newPass.length() < prop) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.min.length.violation", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
            checked = true;
        }
        //-------- max length
        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordMaxLength);
        if (newPass.length() > prop) {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.max.length.violation", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
            checked = true;
        }
        //-------- Big char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastBigCharacter);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountBigCharacter);
            if (!newPass.matches("^(.*?[A-Z]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.at.least.big.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Small char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastSmallCharacter);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountSmallCharacter);
            if (!newPass.matches("^(.*?[a-z]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.at.least.small.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Number char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastNumber);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountNumber);
            if (!newPass.matches("^(.*?[0-9]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.at.least.number.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }
        //-------- Specific char
        boProp = PropertorInWeb.getInstance().getPropertyBool(TtPropertorInWebList.UserPasswordAtLeastSpecific);
        if (boProp) {
            prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordCountSpecific);
            if (!newPass.matches("^(.*?[!@#$%^&*-+_]){" + prop + ",}.*$")) {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.at.least.specific.char", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
                checked = true;
            }
        }

        if (checked) {
            return changePassRedirect(fuser, notice2s, redirectAttributes);
        }

        //-------- duplicate password
//        prop = PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.UserPasswordLastPassNotToBeSelected);
//        if (dbu.isPasswordInHistory(Digester.digestSHA1(newPass), prop)) {
//            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.serviceUser.password.not.be.equal.to.last", fuser.getSecretNote(), TtNotice.Warning, prop + "")));
//            return changePassRedirect(fuser, notice2s, redirectAttributes);
//        }

        dbu.setLastPassword(dbu.getPassword());
        dbu.setPassword(Digester.digestSHA1(newPass));

        dbu.setCreationDateTime(null);
        this.service.update(dbu);
        notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.password.change.success", fuser.getSecretNote(), TtNotice.Success)));

        return Referer.redirect(_PANEL_URL + "/edit/" + fuser.getIdi(), TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success, notice2s);
    }

    @PersianName("تغییر رمز برای کاربر")
    @RequestMapping(value = _PANEL_URL + "/user-pass/{uid}")
    public ModelAndView pChangeUserPassword(Model model, @PathVariable("uid") int uid, RedirectAttributes redirectAttributes) {
        ServiceUser dbuser = this.service.findById(uid);
        if (dbuser == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("serviceUserId", "" + uid), TtNotice.Warning));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute(dbuser);
        return TtTile___.p_service_serviceUser_changeUserPass.___getDisModel(_PANEL_URL + "/user-pass", TtTaskActionSubType.Change_Password, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/user-pass", method = RequestMethod.POST)
    public ModelAndView pChangeUserPassword(@ModelAttribute("newPassword") String newPass,
                                            @ModelAttribute("repassword") String rePass,
                                            @ModelAttribute("user") User fuser,
                                            BindingResult userBindingResult,
                                            final RedirectAttributes redirectAttributes) {
        return changePass(fuser, newPass, rePass, redirectAttributes);
    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        ServiceUser dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("serviceUserId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getName();

        //TODO: - S-8: remove all access of users

        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }

    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  CHAT
    //=========================== details chat
    @PersianName("چت - مشاهده جزئیات")
    @RequestMapping(value = _PANEL_URL + "/chat/details/{uid}/{cid}")
    public ModelAndView pChatDetails(Model model,
                                     @PathVariable("uid") long uid,
                                     @PathVariable("cid") long cid,
                                     RedirectAttributes redirectAttributes) {

        TextChat dbObj = this.textChatShareService.findById(cid, TextChat._ROOM, TextChat._SENDER, TextChat._PRIVATE_CHAT_RECEIVER);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.chat.not.found", JsonBuilder.toJson("chatId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        ServiceUser user = this.service.findById(uid);
        if (user == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceUser.not.found", JsonBuilder.toJson("serviceUserId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(user);
        model.addAttribute(dbObj);
        return TtTile___.p_service_serviceUser_chat_details.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @PersianName("چت - مشاهده گفتگو")
    @RequestMapping(value = _PANEL_URL + "/chat/conversation/{id}")
    public ModelAndView pChatConversation(Model model, @PathVariable("id") long id,
                                          RedirectAttributes redirectAttributes) {

        ServiceUser user = this.service.findById(id);
        if (user == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.serviceUser.not.found", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        List<TextChat> clist = textChatShareService.findAllBy(Restrictions.and(
                Restrictions.or(
                        Restrictions.eq(TextChat._SENDER, user),
                        Restrictions.eq(TextChat._PRIVATE_CHAT_RECEIVER, user)
                ),
                Restrictions.eq(TextChat.CHAT_TYPE, TtTextChatType.Private)
        ), TextChat._SENDER, TextChat._PRIVATE_CHAT_RECEIVER);
        model.addAttribute("clist", clist);
        model.addAttribute(user);

        return TtTile___.p_service_serviceUser_chat_conversation.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }


}
