package org.sadr.share.main.room;

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
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.Room_Map.Room_MapShareService;
import org.sadr.share.main._types.TtUserRoleFlags;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapShareService;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareService;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserShareService;
import org.sadr.share.main.textChat.TextChat;
import org.sadr.share.main.textChat.TextChatShareService;
import org.sadr.share.main.textChat._types.TtTextChatType;
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
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت اتاق")
public class RoomShareController extends GenericControllerImpl<Room, RoomShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/room";

    ////////////////////
    public RoomShareController() {
    }

    private Room_ServiceUserShareService room_serviceUserShareService;
    private Room_MapShareService room_mapShareService;
    private MapShareService mapShareService;
    private ServiceUserShareService serviceUserShareService;
    private TextChatShareService textChatShareService;

    @Autowired
    public void setTextChatShareService(TextChatShareService textChatShareService) {
        this.textChatShareService = textChatShareService;
    }


    @Autowired
    public void setServiceUserShareService(ServiceUserShareService serviceUserShareService) {
        this.serviceUserShareService = serviceUserShareService;
    }

    @Autowired
    public void setMapShareService(MapShareService mapShareService) {
        this.mapShareService = mapShareService;
    }

    @Autowired
    public void setRoom_mapShareService(Room_MapShareService room_mapShareService) {
        this.room_mapShareService = room_mapShareService;
    }

    @Autowired
    public void setRoom_serviceUserShareService(Room_ServiceUserShareService room_serviceUserShareService) {
        this.room_serviceUserShareService = room_serviceUserShareService;
    }

    //=========================== create
    @PersianName("اتاق - ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Room u = (Room) model.asMap().get("room");
        if (u == null) {
            u = new Room();
        }
        model.addAttribute(u);
        return TtTile___.p_service_room_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("room") @Valid Room fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("اتاق - ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Room dbObj = (Room) model.asMap().get("room");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", JsonBuilder.toJson("roomId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("room")
            @Valid Room fObj,
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

        Room dbObj;

        dbObj = this.service.findById(fObj.getIdi());
        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        dbObj.setName(fObj.getName());
        dbObj.setDescriptions(fObj.getDescriptions());
        dbObj.setTableLenght(fObj.getTableLenght());
        dbObj.setTableWidth(fObj.getTableWidth());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //===========================  list
    @PersianName("اتاق - لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {

        Searchee.init(Room.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Room.ROOM_NAME
                );

        GB.searchTableColumns(model,
                Room.class,
                GB.col(Room.ID),
                GB.col(Room.CREATE_DATE_TIME),
                GB.col(Room.MODIFY_DATE_TIME),
                GB.col(Room.ROOM_NAME),
                GB.col(Room.TABLE_LENGHT),
                GB.col(Room.TABLE_WIDTH),
                GB.col(Room.ROOM_TYPE),
                GB.col(Room.$CURRENT_MEETING_NAME, Room.ROOM_TYPE)
        );
        return TtTile___.p_service_room_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(Room.class)
                .set(
                        Room.CREATE_DATE_TIME,
                        Room.MODIFY_DATE_TIME,
                        Room.ROOM_NAME,
                        Room.TABLE_WIDTH,
                        Room.TABLE_LENGHT,
                        Room.ROOM_TYPE,
                        Room.DESCRIPTIONS,
                        Room.BUSY_TYPE,
                        Room.ACCESS_SETTING,
                        Room.REC_SETTING

                )
                .setGbs(
                        GB.init(Meeting.class, Room._CURRENT_MEETING)
                                .set(
                                        Meeting.NAME
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Room.CREATE_DATE_TIME,
                            Room.MODIFY_DATE_TIME,
                            Room.ROOM_NAME,
                            Room.TABLE_WIDTH,
                            Room.TABLE_LENGHT,
                            Room.ROOM_TYPE,
                            Room.$CURRENT_MEETING_NAME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Room.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=========================== Trash
    @PersianName("اتاق - حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        Room dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room.not.found", JsonBuilder.toJson("roomId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getName();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }

    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  ROOM_MAP

    //=========================== create map
    @PersianName("نقشه - افزودن به اتاق")
    @RequestMapping(_PANEL_URL + "/map/create/{id}")
    public ModelAndView pMapCreate(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room room = this.service.findById(id);
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", JsonBuilder.toJson("roomId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        Room_Map u = (Room_Map) model.asMap().get("room_Map");
        if (u == null) {
            u = new Room_Map();
        }
        model.addAttribute(u);
        model.addAttribute("room", room);
        model.addAttribute("mlist", mapShareService.findAll());
        model.addAttribute("rlist", service.findAllBy(
                Restrictions.eq(Room.ID, id)
        ));
        return TtTile___.p_service_room_map_create.___getDisModel(_PANEL_URL + "/map/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/map/create", method = RequestMethod.POST)
    public ModelAndView pMapCreate(@ModelAttribute("room_Map") @Valid Room_Map fObj,
                                   BindingResult objBindingResult,
                                   HttpServletRequest request,
                                   final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        if (fObj.getMap() == null || fObj.getMap().getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.select.map")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        if (this.room_mapShareService.isExist(
                Restrictions.and(
                        Restrictions.eq(Room_Map._MAP, fObj.getMap()),
                        Restrictions.eq(Room_Map._ROOM, fObj.getRoom())))) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.record.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        fObj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.room_mapShareService.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/map/create/" + fObj.getRoom().getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/map/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit map
    @PersianName("نقشه - ویرایش")
    @RequestMapping(value = _PANEL_URL + "/map/edit/{uid}")
    public ModelAndView pMapEdit(Model model, @PathVariable("uid") long uid,
                                 RedirectAttributes redirectAttributes) {

        Room_Map dbObj = (Room_Map) model.asMap().get("room_Map");
        if (dbObj == null) {
            dbObj = this.room_mapShareService.findById(uid, Room_Map._ROOM, Room_Map._MAP);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        if (dbObj.getRoom() == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        Room room = this.service.findById(dbObj.getRoom().getIdi());
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        model.addAttribute(dbObj);
        model.addAttribute("room", room);
        model.addAttribute("mlist", mapShareService.findAll());
        model.addAttribute("rlist", service.findAllBy(
                Restrictions.eq(Room.ID, room.getIdi())
        ));
        model.addAttribute(dbObj);
        return TtTile___.p_service_room_map_edit.___getDisModel(_PANEL_URL + "/map/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/map/edit", method = RequestMethod.POST)
    public ModelAndView pMapEdit(
            @ModelAttribute("room_Map")
            @Valid Room_Map fObj,
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

        if (fObj.getMap() == null || fObj.getMap().getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.select.map")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        Room_Map dbObj;


        dbObj = this.room_mapShareService.findById(fObj.getIdi());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        if (this.room_mapShareService.isDuplicateWith(
                Restrictions.and(
                        Restrictions.eq(Room_Map._MAP, fObj.getMap()),
                        Restrictions.eq(Room_Map._ROOM, fObj.getRoom())), dbObj.getIdi())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.record.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

//        dbObj.setRoom(fObj.getRoom());
//        dbObj.setServiceUser(fObj.getServiceUser());
        dbObj.setMap(fObj.getMap());

        this.room_mapShareService.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/map/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //=========================== list map
    @PersianName("نقشه - لیست")
    @RequestMapping(value = _PANEL_URL + "/map/list/{id}")
    public ModelAndView pMapList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Room room = this.service.findById(id);
        if (room == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.not.found", TtNotice.Warning)));
            return Referer.redirect("/panel/room/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(Room_Map.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        Room_Map._ROOM,
                        Searchee.field(Room.ID, Room.class)

                );


        GB.searchTableColumns(model,
                Room_Map.class,
                GB.col(Room_Map.ID),
                GB.col(Room_Map.$MAP_TITLE, Room_Map.MODIFY_DATE_TIME),
                GB.col(Room_Map.CREATION_DATE_TIME)
        );

        model.addAttribute(room);
        return TtTile___.p_service_room_map_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/map/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pMapList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                    @RequestParam(value = "ixp", required = false) String ixportParam,
                                    HttpServletResponse response) throws IOException {

        GB gb = GB.init(Room_Map.class)
                .set(
                        Room_Map.CREATION_DATE_TIME,
                        Room_Map.MODIFY_DATE_TIME
                )
                .setGbs(
                        GB.init(Map.class, Room_Map._MAP)
                                .set(
                                        Map.NAME
                                ),
                        GB.init(Room.class, Room_Map._ROOM)
                                .set(
                                        Room.ROOM_NAME
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Room_Map.CREATION_DATE_TIME,
                            Room_Map.$MAP_TITLE
                    );

            String jSearch = this.room_mapShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Room_Map.class)
                .exportToFileInList(this.room_mapShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=========================== Trash map
    @PersianName("نقشه - حذف")
    @RequestMapping(value = _PANEL_URL + "/map/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pMapTrash(@PathVariable("id") long id) {

        Room_Map dbObj = this.room_mapShareService.findById(id, Room_Map._MAP);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + id)))
                    .toResponse();
        }


        String name = dbObj.getMapTitle();
        this.room_mapShareService.delete(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }


    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  ROOM_SERVICE_USER
    //=========================== create user
    @PersianName("کاربر - افزودن به اتاق")
    @RequestMapping(_PANEL_URL + "/user/create/{id}")
    public ModelAndView pUserCreate(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room room = this.service.findById(id);
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", JsonBuilder.toJson("roomId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        Room_ServiceUser u = (Room_ServiceUser) model.asMap().get("room_ServiceUser");
        if (u == null) {
            u = new Room_ServiceUser();
        }
        model.addAttribute(u);
        model.addAttribute("room", room);
        model.addAttribute("ulist", serviceUserShareService.findAll());
        model.addAttribute("rlist", service.findAllBy(
                Restrictions.eq(Room.ID, id)
        ));
        return TtTile___.p_service_room_user_create.___getDisModel(_PANEL_URL + "/user/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/user/create", method = RequestMethod.POST)
    public ModelAndView pUserCreate(@ModelAttribute("room_ServiceUser") @Valid Room_ServiceUser fObj,
                                    BindingResult objBindingResult,
                                    HttpServletRequest request,
                                    final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }


        if (fObj.getServiceUser() == null || fObj.getServiceUser().getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.select.user")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        if (this.room_serviceUserShareService.isExist(
                Restrictions.and(
                        Restrictions.eq(Room_ServiceUser._SERVICE_USER, fObj.getServiceUser()),
                        Restrictions.eq(Room_ServiceUser._ROOM, fObj.getRoom())))) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.record.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (fObj.getPermanentUserRoleFlag() == TtUserRoleFlags.Admin) {
            if (this.room_serviceUserShareService.isExist(
                    Restrictions.and(
                            Restrictions.eq(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG, TtUserRoleFlags.Admin),
                            Restrictions.eq(Room_ServiceUser._ROOM, fObj.getRoom())))) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.admin.exist", TtNotice.Warning)));
                return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
            }
        }

        if (fObj.getPermanentUserRoleFlag() != TtUserRoleFlags.Admin && fObj.getPermanentUserRoleFlag() != TtUserRoleFlags.User) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.only.admin.user", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }


        this.room_serviceUserShareService.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/user/create/" + fObj.getRoom().getId(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/user/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }


    //=========================== edit user
    @PersianName("کاربر - ویرایش")
    @RequestMapping(value = _PANEL_URL + "/user/edit/{uid}")
    public ModelAndView pUserEdit(Model model, @PathVariable("uid") long uid,
                                  RedirectAttributes redirectAttributes) {

        Room_ServiceUser dbObj = (Room_ServiceUser) model.asMap().get("room_ServiceUser");
        if (dbObj == null) {
            dbObj = this.room_serviceUserShareService.findById(uid, Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        if (dbObj.getRoom() == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        Room room = this.service.findById(dbObj.getRoom().getIdi());
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        model.addAttribute("room", room);
        model.addAttribute("ulist", serviceUserShareService.findAll());
        model.addAttribute("rlist", service.findAllBy(
                Restrictions.eq(Room.ID, room.getIdi())
        ));
        model.addAttribute(dbObj);
        return TtTile___.p_service_room_user_edit.___getDisModel(_PANEL_URL + "/user/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/user/edit", method = RequestMethod.POST)
    public ModelAndView pUserEdit(
            @ModelAttribute("room_ServiceUser")
            @Valid Room_ServiceUser fObj,
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

        if (fObj.getServiceUser() == null || fObj.getServiceUser().getIdi() == 0) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.select.user")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        Room_ServiceUser dbObj;


        dbObj = this.room_serviceUserShareService.findById(fObj.getIdi());

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }


        if (this.room_serviceUserShareService.isDuplicateWith(
                Restrictions.and(
                        Restrictions.eq(Room_ServiceUser._SERVICE_USER, fObj.getServiceUser()),
                        Restrictions.eq(Room_ServiceUser._ROOM, fObj.getRoom())), dbObj.getIdi())) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.record.exist", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        if (fObj.getPermanentUserRoleFlag() == TtUserRoleFlags.Admin) {
            if (this.room_serviceUserShareService.isDuplicateWith(
                    Restrictions.and(
                            Restrictions.eq(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG, TtUserRoleFlags.Admin),
                            Restrictions.eq(Room_ServiceUser._ROOM, fObj.getRoom())), dbObj.getIdi())) {
                Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.admin.exist", TtNotice.Warning)));
                return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
            }
        }

        if (fObj.getPermanentUserRoleFlag() != TtUserRoleFlags.Admin && fObj.getPermanentUserRoleFlag() != TtUserRoleFlags.User) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.only.admin.user", TtNotice.Warning)));
            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
        }

        dbObj.setAcceptPrivateChat(fObj.getAcceptPrivateChat());
        dbObj.setAcceptPrivateTalk(fObj.getAcceptPrivateTalk());
        dbObj.setJoinDateTime(fObj.getJoinDateTime());
        dbObj.setPermanentUserRoleFlag(fObj.getPermanentUserRoleFlag());
//        dbObj.setRoom(fObj.getRoom());
        dbObj.setServiceUser(fObj.getServiceUser());
        dbObj.setPresence(fObj.getPresence());
        dbObj.setTemporaryUserRoleFlag(fObj.getTemporaryUserRoleFlag());
        dbObj.setUserStatus(fObj.getUserStatus());

        this.room_serviceUserShareService.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/user/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //=========================== details user
    @PersianName("کاربر - جزئیات")
    @RequestMapping(value = _PANEL_URL + "/user/details/{id}")
    public ModelAndView pUserDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room_ServiceUser dbObj = this.room_serviceUserShareService.findById(id, Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_user_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list user
    @PersianName("کاربر - لیست")
    @RequestMapping(value = _PANEL_URL + "/user/list/{id}")
    public ModelAndView pUserList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Room room = this.service.findById(id);
        if (room == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.not.found", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }


        Searchee.init(Room_ServiceUser.class, model)

                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        Room_ServiceUser._ROOM,
                        Searchee.field(Room.ID, Room.class)

                );


        GB.searchTableColumns(model,
                Room_ServiceUser.class,
                GB.col(Room_ServiceUser.ID),
                GB.col(Room_ServiceUser.$SERVICE_USER_FULL_NAME, GB.path(Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG)),
                GB.col(Room_ServiceUser.JOIN_DATE_TIME),
                GB.col(Room_ServiceUser.USER_STATUS),
                GB.col(Room_ServiceUser.PRESENCE),
                GB.col(Room_ServiceUser.ACCEPT_PRIVATE_CHAT),
                GB.col(Room_ServiceUser.ACCEPT_PRIVATE_TALK),
                GB.col(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG)
        );

        model.addAttribute(room);
        return TtTile___.p_service_room_user_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/user/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pUserList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                     @RequestParam(value = "ixp", required = false) String ixportParam,
                                     HttpServletResponse response) throws IOException {

        GB gb = GB.init(Room_ServiceUser.class)
                .set(
                        Room_ServiceUser.JOIN_DATE_TIME,
                        Room_ServiceUser.USER_STATUS,
                        Room_ServiceUser.ACCEPT_PRIVATE_CHAT,
                        Room_ServiceUser.ACCEPT_PRIVATE_TALK,
                        Room_ServiceUser.PRESENCE,
                        Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG,
                        Room_ServiceUser.PERMANENT_USER_ROLE_FLAG
                )
                .setGbs(
                        GB.init(ServiceUser.class, Room_ServiceUser._SERVICE_USER)
                                .set(
                                        ServiceUser.NAME,
                                        ServiceUser.FAMILY,
                                        ServiceUser.USERNAME
                                ),
                        GB.init(Room.class, Room_ServiceUser._ROOM)
                                .set(
                                        Room.ROOM_NAME
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Room_ServiceUser.JOIN_DATE_TIME,
                            Room_ServiceUser.USER_STATUS,
                            Room_ServiceUser.ACCEPT_PRIVATE_CHAT,
                            Room_ServiceUser.ACCEPT_PRIVATE_TALK,
                            Room_ServiceUser.$SERVICE_USER_FULL_NAME,
                            Room_ServiceUser.PRESENCE,
                            Room_ServiceUser.PERMANENT_USER_ROLE_FLAG
                    );

            String jSearch = this.room_serviceUserShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Room_ServiceUser.class)
                .exportToFileInList(this.room_serviceUserShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=========================== Trash user
    @PersianName("کاربر - حذف از اتاق")
    @RequestMapping(value = _PANEL_URL + "/user/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pUserTrash(@PathVariable("id") long id) {

        Room_ServiceUser dbObj = this.room_serviceUserShareService.findById(id, Room_ServiceUser._SERVICE_USER);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getServiceUserFullName();
        this.room_serviceUserShareService.delete(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }


    //#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#  CHAT
//=========================== details chat
    @PersianName("چت - مشاهده جزئیات")
    @RequestMapping(value = _PANEL_URL + "/chat/details/{uid}")
    public ModelAndView pChatDetails(Model model, @PathVariable("uid") long uid,
                                     RedirectAttributes redirectAttributes) {


        TextChat dbObj = this.textChatShareService.findById(uid, TextChat._ROOM, TextChat._SENDER, TextChat._RECEIVERS);

        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.chat.not.found", JsonBuilder.toJson("chatId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Take_Report,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_chat_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @PersianName("چت - مشاهده گفتگو")
    @RequestMapping(value = _PANEL_URL + "/chat/conversation/{id}")
    public ModelAndView pChatConversation(Model model, @PathVariable("id") long id,
                                          RedirectAttributes redirectAttributes) {

        Room room = this.service.findById(id);
        if (room == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.not.found", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        List<TextChat> clist = textChatShareService.findAllBy(Restrictions.and(
                Restrictions.eq(TextChat._ROOM, room),
                Restrictions.eq(TextChat.CHAT_TYPE, TtTextChatType.Public)
        ), TextChat._SENDER, TextChat._RECEIVERS);
        model.addAttribute("clist", clist);
        model.addAttribute(room);

        return TtTile___.p_service_room_chat_conversation.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

}
