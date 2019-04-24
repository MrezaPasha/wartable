package org.sadr.share.main.roomServiceUser;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr.share.main.room.RoomShareService;
import org.sadr.share.main.serviceUser.ServiceUserShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت کاربر/اتاق")
public class Room_ServiceUserShareController extends GenericControllerImpl<Room_ServiceUser, Room_ServiceUserShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/room/user";

    ////////////////////
    public Room_ServiceUserShareController() {
    }

    private RoomShareService roomShareService;
    private ServiceUserShareService serviceUserShareService;

    @Autowired
    public void setServiceUserShareService(ServiceUserShareService serviceUserShareService) {
        this.serviceUserShareService = serviceUserShareService;
    }

    @Autowired
    public void setRoomShareService(RoomShareService roomShareService) {
        this.roomShareService = roomShareService;
    }

    //=========================== create
  /*  @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Room_ServiceUser u = (Room_ServiceUser) model.asMap().get("room_ServiceUser");
        if (u == null) {
            u = new Room_ServiceUser();
        }
        model.addAttribute(u);
        model.addAttribute("ulist", serviceUserShareService.findAll());
        model.addAttribute("rlist", roomShareService.findAll(
        ));
        return TtTile___.p_service_room_ServiceUser_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("room_ServiceUser") @Valid Room_ServiceUser fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }
*/
    //=========================== edit
  /*  @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Room_ServiceUser dbObj = (Room_ServiceUser) model.asMap().get("room_ServiceUser");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_ServiceUser_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
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

        Room_ServiceUser dbObj;


        dbObj = this.service.findById(fObj.getIdi());

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


        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }
*/
   /* //=========================== create for room
    @PersianName("افزودن کاربر به اتاق")
    @RequestMapping(_PANEL_URL + "/create-r/{id}")
    public ModelAndView pCreateForRoom(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room room = this.roomShareService.findById(id);
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", JsonBuilder.toJson("roomId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    "/panel/room/list",
                    TtTaskActionSubType.Edit_Data,
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
        model.addAttribute("rlist", roomShareService.findAllBy(
                Restrictions.eq(Room.ID, id)
        ));
        return TtTile___.p_service_room_ServiceUser_createR.___getDisModel(_PANEL_URL + "/create-r", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create-r", method = RequestMethod.POST)
    public ModelAndView pCreateForRoom(@ModelAttribute("room_ServiceUser") @Valid Room_ServiceUser fObj,
                                       BindingResult objBindingResult,
                                       HttpServletRequest request,
                                       final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_ServiceUser.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit-r/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }


    //=========================== edit for room
    @PersianName("ویرایش کاربر اتاق")
    @RequestMapping(value = _PANEL_URL + "/edit-r/{uid}")
    public ModelAndView pEditForRoom(Model model, @PathVariable("uid") long uid,
                                     RedirectAttributes redirectAttributes) {

        Room_ServiceUser dbObj = (Room_ServiceUser) model.asMap().get("room_ServiceUser");
        if (dbObj == null) {
            dbObj = this.service.findById(uid, Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER);
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
                    "/panel/room/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        Room room = this.roomShareService.findById(dbObj.getRoom().getIdi());
        if (room == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room.not.found", TtNotice.Warning));
            return Referer.redirect(
                    "/panel/room/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        model.addAttribute("room", room);
        model.addAttribute("ulist", serviceUserShareService.findAll());
        model.addAttribute("rlist", roomShareService.findAllBy(
                Restrictions.eq(Room.ID, room.getIdi())
        ));
        model.addAttribute(dbObj);
        return TtTile___.p_service_room_ServiceUser_editR.___getDisModel(_PANEL_URL + "/edit-r", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit-r", method = RequestMethod.POST)
    public ModelAndView pEditForRoom(
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

        Room_ServiceUser dbObj;


        dbObj = this.service.findById(fObj.getIdi());

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

        dbObj.setAcceptPrivateChat(fObj.getAcceptPrivateChat());
        dbObj.setAcceptPrivateTalk(fObj.getAcceptPrivateTalk());
        dbObj.setJoinDateTime(fObj.getJoinDateTime());
        dbObj.setPermanentUserRoleFlag(fObj.getPermanentUserRoleFlag());
//        dbObj.setRoom(fObj.getRoom());
        dbObj.setServiceUser(fObj.getServiceUser());
        dbObj.setPresence(fObj.getPresence());
        dbObj.setTemporaryUserRoleFlag(fObj.getTemporaryUserRoleFlag());
        dbObj.setUserStatus(fObj.getUserStatus());

        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/edit-r/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }

    //=========================== details
    @PersianName("جزئیات")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room_ServiceUser dbObj = this.service.findById(id, Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_ServiceUser_details.___getDisModel(TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list
    @PersianName("لیست کاربران اتاق")
    @RequestMapping(value = _PANEL_URL + "/list/{id}")
    public ModelAndView pList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Room room = this.roomShareService.findById(id);
        if (room == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room.register.success", TtNotice.Success)));
            return Referer.redirect("/panel/room/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }



Searchee.init(, model)
.setAttribute(
                TtDataType.String,
                TtRestrictionOperator.ILike_ANY,
TtSearcheeStrategy.Normal,
                Room_ServiceUser._SERVICE_USER,
                ServiceUser.USERNAME
        )

.setAttribute(
                TtDataType.Long,
                TtRestrictionOperator.Equal,
TtSearcheeStrategy.Normal,
                Room_ServiceUser._ROOM,
                Room.ID
        );


        GB.searchTableColumns( model,
                Room_ServiceUser.class,
                GB.col(Room_ServiceUser.ID),
                GB.col(Room_ServiceUser.$SERVICE_USER_FULL_NAME),
                GB.col(Room_ServiceUser.JOIN_DATE_TIME),
                GB.col(Room_ServiceUser.USER_STATUS),
                GB.col(Room_ServiceUser.PRESENCE),
                GB.col(Room_ServiceUser.ACCEPT_PRIVATE_CHAT),
                GB.col(Room_ServiceUser.ACCEPT_PRIVATE_TALK)
        );

        model.addAttribute(room);
        return TtTile___.p_service_room_ServiceUser_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(Room_ServiceUser.class)
                .set(
                        Room_ServiceUser.JOIN_DATE_TIME,
                        Room_ServiceUser.USER_STATUS,
                        Room_ServiceUser.ACCEPT_PRIVATE_CHAT,
                        Room_ServiceUser.ACCEPT_PRIVATE_TALK,
                        Room_ServiceUser.PRESENCE
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
                            Room_ServiceUser.PRESENCE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
            gb.setIxportParams(ixportParam);
            List<Room_ServiceUser> all = this.service.findAll(gb);
            String fileName = Ixporter.init(Room_ServiceUser.class)
                    .exportToFile(all, response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress);
            return Ison.init(TtTaskActionSubType.Export_File, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setMessages(new Notice2("N1.ixporter.export.excel.success", TtNotice.Success, fileName))
                    .toResponse();

    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        Room_ServiceUser dbObj = this.service.findById(id, Room_ServiceUser._SERVICE_USER);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room_ServiceUser.not.found", JsonBuilder.toJson("room_ServiceUserId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getServiceUserFullName();
        this.service.delete(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }*/
}
