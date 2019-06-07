package org.sadr.share.main.meeting;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.*;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main._utils.ShareUtils;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.item.vector.Vector;
import org.sadr.share.main.item.vector.VectorShareService;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemShareService;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.meetingSetting.MeetingSettingShareService;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.privateTalk.PrivateTalkShareService;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomShareService;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت جلسات")
public class MeetingShareController extends GenericControllerImpl<Meeting, MeetingShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/meeting";

    ////////////////////
    public MeetingShareController() {
    }

    private RoomShareService roomShareService;
    private PrivateTalkShareService privateTalkShareService;
    private MeetingSettingShareService meetingSettingShareService;
    private MeetingItemShareService meetingItemShareService;
    private VectorShareService vectorShareService;

    @Autowired
    public void setVectorShareService(VectorShareService vectorShareService) {
        this.vectorShareService = vectorShareService;
    }

    @Autowired
    public void setMeetingItemShareService(MeetingItemShareService meetingItemShareService) {
        this.meetingItemShareService = meetingItemShareService;
    }

    @Autowired
    public void setMeetingSettingShareService(MeetingSettingShareService meetingSettingShareService) {
        this.meetingSettingShareService = meetingSettingShareService;
    }

    @Autowired
    public void setPrivateTalkShareService(PrivateTalkShareService privateTalkShareService) {
        this.privateTalkShareService = privateTalkShareService;
    }

    @Autowired
    public void setRoomShareService(RoomShareService roomShareService) {
        this.roomShareService = roomShareService;
    }

    //=========================== create
   /* @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Meeting u = (Meeting) model.asMap().get("meeting");
        if (u == null) {
            u = new Meeting();
        }
        model.addAttribute(u);
        return TtTile___.p_service_meeting_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }
    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("meeting") @Valid Meeting fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }*/
    //=========================== edit
    @PersianName("جلسه - ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Meeting dbObj = (Meeting) model.asMap().get("meeting");
        if (dbObj == null) {
            dbObj = this.service.findById(uid, Meeting._ROOM);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.meeting.not.found", JsonBuilder.toJson("meetingId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        model.addAttribute("rlist", roomShareService.findAll());
        return TtTile___.p_service_meeting_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("meeting")
            @Valid Meeting fObj,
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

        Meeting dbObj;

        dbObj = this.service.findById(fObj.getIdi(), Meeting._ROOM);

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        dbObj.setDescription(fObj.getDescription());
        dbObj.setRoom(fObj.getRoom());
        dbObj.setGoal(fObj.getGoal());

        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //=========================== details
    @PersianName("جلسه - جزئیات")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Meeting dbObj = this.service.findById(id, Meeting._ROOM, RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM, Room._ROOM_SERVICEUSERS, Room_ServiceUser._SERVICE_USER));
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.meeting.not.found", JsonBuilder.toJson("meetingId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_meeting_details.___getDisModel(_PANEL_URL + "/details", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list
    @PersianName("جلسه - لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {

        Searchee.init(Meeting.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Meeting.NAME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Meeting._ROOM,
                        Searchee.field(Room.ROOM_NAME, Room.class)

                )

        ;

        GB.searchTableColumns(model,
                Meeting.class,
                GB.col(Meeting.ID),
                GB.col(Meeting.CREATE_DATE_TIME),
                GB.col(Meeting.NAME),
                GB.col(Meeting.STATUS),
                GB.col(Meeting.$ROOM_TITLE, GB.path(Meeting._ROOM, Room.ROOM_NAME))
        );
        return TtTile___.p_service_meeting_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb = GB.init(Meeting.class)
                .set(
                        Meeting.CREATE_DATE_TIME,
                        Meeting.NAME,
                        Meeting.STATUS
                )
                .setGbs(
                        GB.init(Room.class, Meeting._ROOM)
                                .set(
                                        Room.ROOM_NAME
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {
            JB jb = JB.init()
                    .set(
                            Meeting.CREATE_DATE_TIME,
                            Meeting.NAME,
                            Meeting.STATUS,
                            Meeting.$ROOM_TITLE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Meeting.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);
    }

    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  PRIVATE TALK

    @PersianName("تماس خصوصی - پخش فایل صوتی")
    @RequestMapping(_PANEL_URL + "/talk/sound/{id}")
    @ResponseBody
    public void fTalkDownloadSourceById(
            @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {

        PrivateTalk talk = privateTalkShareService.findById(id);

        if (talk == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Path fpath = Paths.get(talk.getSoundPath());
        if (Files.exists(fpath)) {
            OutLog.pl("");
            response.setContentType("audio/x-ms-wma");
            response.addHeader("Content-Disposition", "attachment; filename=" + talk.getFileName());
            try {
                Files.copy(fpath, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    //=========================== details private talk
    @PersianName("تماس خصوصی - مشاهده جزئیات")
    @RequestMapping(value = _PANEL_URL + "/talk/details/{uid}")
    public ModelAndView pTalkDetails(Model model, @PathVariable("uid") long uid,
                                     RedirectAttributes redirectAttributes) {


        PrivateTalk dbObj = this.privateTalkShareService.findById(uid, PrivateTalk._SERVICE_USERS, PrivateTalk._MEETING, PrivateTalk._JOINED_USERS, PrivateTalk._REQUEST_USER);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.talk.not.found", JsonBuilder.toJson("talkId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);

        File f = new File("D:\\_proji_sadr\\ftp\\voice\\001.mp3");

        model.addAttribute("file", f);

        return TtTile___.p_service_meeting_talk_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list talk
    @PersianName("تماس خصوصی - لیست")
    @RequestMapping(value = _PANEL_URL + "/talk/list/{id}")
    public ModelAndView pTalkList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Meeting meeting = this.service.findById(id);
        if (meeting == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.not.found", TtNotice.Warning)));
            return Referer.redirect("/panel/meeting/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(PrivateTalk.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        PrivateTalk.FILE_NAME)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        PrivateTalk._MEETING,
                        Searchee.field(Meeting.ID, Meeting.class)

                );

        GB.searchTableColumns(model,
                PrivateTalk.class,
                GB.col(PrivateTalk.ID),
                GB.col(PrivateTalk.START_DATE_TIME),
                GB.col(PrivateTalk.END_DATE_TIME),
                GB.col(PrivateTalk.FILE_NAME),
                GB.col(PrivateTalk.SIZE),
                GB.col(PrivateTalk.STATUS)

        );

        model.addAttribute(meeting);

        return TtTile___.p_service_meeting_talk_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/talk/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pTalkList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                     @RequestParam(value = "ixp", required = false) String ixportParam,
                                     HttpServletResponse response) throws IOException {

        GB gb = GB.init(PrivateTalk.class)
                .set(
                        PrivateTalk.END_DATE_TIME,
                        PrivateTalk.START_DATE_TIME,
                        PrivateTalk.FILE_NAME,
                        PrivateTalk.SIZE,
                        PrivateTalk.STATUS

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            PrivateTalk.END_DATE_TIME,
                            PrivateTalk.START_DATE_TIME,
                            PrivateTalk.FILE_NAME,
                            PrivateTalk.SIZE,
                            PrivateTalk.STATUS
                    );

            String jSearch = this.privateTalkShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(PrivateTalk.class)
                .exportToFileInList(this.privateTalkShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  MEETING SETTING

    @PersianName("مکالمات - پخش فایل صوتی")
    @RequestMapping(_PANEL_URL + "/setting/sound/{id}")
    @ResponseBody
    public void fSettingDownloadSourceById(
            @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {

        MeetingSetting setting = meetingSettingShareService.findById(id);

        if (setting == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Path fpath = Paths.get(setting.getSoundPath());
        if (Files.exists(fpath)) {
            OutLog.pl("");
            response.setContentType("audio/x-ms-wma");
            response.addHeader("Content-Disposition", "attachment; filename=" + setting.getSoundRecFileName());
            try {
                Files.copy(fpath, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    //=========================== details meeting setting
    @PersianName("مکالمات - مشاهده جزئیات")
    @RequestMapping(value = _PANEL_URL + "/setting/details/{uid}")
    public ModelAndView pSettingDetails(Model model, @PathVariable("uid") long uid,
                                        RedirectAttributes redirectAttributes) {


        MeetingSetting dbObj = this.meetingSettingShareService.findById(uid, MeetingSetting._MEETING);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.setting.not.found", JsonBuilder.toJson("settingId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_meeting_setting_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list setting
    @PersianName("مکالمات - لیست")
    @RequestMapping(value = _PANEL_URL + "/setting/list/{id}")
    public ModelAndView pSettingList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Meeting meeting = this.service.findById(id);
        if (meeting == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.not.found", TtNotice.Warning)));
            return Referer.redirect("/panel/meeting/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(MeetingSetting.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        MeetingSetting.NAME)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        MeetingSetting._MEETING,
                        Searchee.field(Meeting.ID, Meeting.class)
                )


        ;

        GB.searchTableColumns(model,
                MeetingSetting.class,
                GB.col(MeetingSetting.ID),
                GB.col(MeetingSetting.START_DATE_TIME),
                GB.col(MeetingSetting.END_DATE_TIME),
                GB.col(MeetingSetting.NAME),
                GB.col(MeetingSetting.SOUND_REC_FILE_NAME),
                GB.col(MeetingSetting.SOUND_REC_FILE_SIZE)
        );

        model.addAttribute(meeting);

        return TtTile___.p_service_meeting_setting_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/setting/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pSettingList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                        @RequestParam(value = "ixp", required = false) String ixportParam,
                                        HttpServletResponse response) throws IOException {

        GB gb = GB.init(MeetingSetting.class)
                .set(
                        MeetingSetting.END_DATE_TIME,
                        MeetingSetting.START_DATE_TIME,
                        MeetingSetting.NAME,
                        MeetingSetting.SOUND_REC_FILE_NAME,
                        MeetingSetting.SOUND_REC_FILE_SIZE

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            MeetingSetting.END_DATE_TIME,
                            MeetingSetting.START_DATE_TIME,
                            MeetingSetting.NAME,
                            MeetingSetting.SOUND_REC_FILE_NAME,
                            MeetingSetting.SOUND_REC_FILE_SIZE
                    );

            String jSearch = this.meetingSettingShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(MeetingSetting.class)
                .exportToFileInList(this.meetingSettingShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }


    //=========================== list area

    @PersianName("ناحیه - جزئیات")
    @RequestMapping(value = _PANEL_URL + "/vector/details/{id}")
    public ModelAndView pVectorDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        MeetingItem dbObj = this.meetingItemShareService.findById(id, RePa.p__(MeetingItem._VECTOR, Vector._UPLOADER_USER), RePa.p__(MeetingItem._MEETING, Meeting._ROOM));
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.meetingItem.not.found", JsonBuilder.toJson("meetingItemId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_meeting_vector_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @PersianName("ناحیه - افزودن")
    @RequestMapping(value = _PANEL_URL + "/vector/add/{id}")
    public ModelAndView pVectorAdd(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Meeting dbObj = this.service.findById(id);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.meeting.not.found", JsonBuilder.toJson("meetingId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        model.addAttribute("vector", new Vector());
        return TtTile___.p_service_meeting_vector_add.___getDisModel(_PANEL_URL + "/vector/add", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/vector/add", method = RequestMethod.POST)
    public ModelAndView pVectorAdd(
            @ModelAttribute("meeting")
            @Valid Meeting fObj,
            BindingResult suBindingResult,
            HttpServletRequest request,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment,
            RedirectAttributes redirectAttributes) {


        if (attachment == null || attachment.getSize() == 0) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.vector.attachment.null", TtNotice.Danger)));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }

        if (!attachment.getOriginalFilename().endsWith(".vector")) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.vector.attachment.invalid.format", TtNotice.Danger)));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }

        if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.meeting.vector.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }


        Meeting meeting = this.service.findById(fObj.getId());

        if (meeting == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.not.found", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }

        org.sadr.web.main.archive.file.file.File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));

        if (upload == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.vector.upload.failed", TtNotice.Danger)));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }


        MeetingItem meetingItem = ShareUtils.UploadVector(upload.getSourceFile(), PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Vector));

        if (meetingItem != null && meetingItem.getVector() != null) {
            vectorShareService.save(meetingItem.getVector());
            meetingItem.setMeeting(meeting);
            meetingItemShareService.save(meetingItem);
        }

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.vector.add.success", TtNotice.Success)));

        return Referer.redirect(
                _PANEL_URL + "/vector/list/" + meeting.getId(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }


    @PersianName("ناحیه - لیست ناحیه های جلسه")
    @RequestMapping(value = _PANEL_URL + "/vector/list/{id}")
    public ModelAndView pVectorList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Meeting meeting = this.service.findById(id);
        if (meeting == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.meeting.not.found", TtNotice.Warning)));
            return Referer.redirect("/panel/meeting/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(MeetingItem.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        MeetingItem._VECTOR, Searchee.field(Vector.NAME, Vector.class)
                )
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        MeetingItem._MEETING,
                        Searchee.field(Meeting.ID, Meeting.class)
                )


        ;

        GB.searchTableColumns(model,
                MeetingItem.class,
                GB.col(MeetingItem.ID),
                GB.col(MeetingItem.CREATE_DATE_TIME),
                GB.col(MeetingItem.$VECTOR_NAME),
                GB.col(MeetingItem.$VECTOR_FILE_NAME),
                GB.col(MeetingItem.$VECTOR_TYPE)
        );

        model.addAttribute(meeting);

        return TtTile___.p_service_meeting_vector_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/vector/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pVectorList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                       @RequestParam(value = "ixp", required = false) String ixportParam,
                                       HttpServletResponse response) throws IOException {

        GB gb = GB.init(MeetingItem.class)
                .set(
                        MeetingItem.ITEM_TYPE,
                        MeetingItem.CREATE_DATE_TIME
                ).setGbs(
                        GB.init(Vector.class, MeetingItem._VECTOR)
                                .set(
                                        Vector.NAME,
                                        Vector.FILE_NAME,
                                        Vector.VECTOR_TYPE
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            MeetingItem.CREATE_DATE_TIME,
                            MeetingItem.ITEM_TYPE,
                            MeetingItem.$VECTOR_NAME,
                            MeetingItem.$VECTOR_FILE_NAME,
                            MeetingItem.$VECTOR_TYPE
                    );

            String jSearch = this.meetingItemShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(MeetingItem.class)
                .exportToFileInList(this.meetingItemShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=========================== Trash
    @PersianName("ناحیه - حذف")
    @RequestMapping(value = _PANEL_URL + "/vector/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pVectorTrash(@PathVariable("id") long id) {

        MeetingItem dbObj = this.meetingItemShareService.findById(id, MeetingItem._VECTOR);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.vector.not.found", JsonBuilder.toJson("meetingItemId", "" + id)))
                    .toResponse();
        }
        if (dbObj.getVector() != null) {
            this.vectorShareService.trash(dbObj.getVector().getIdi());
        }
        this.meetingItemShareService.trash(dbObj.getIdi());


        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, dbObj.getVector().getName()))
                .toResponse();
    }
}
