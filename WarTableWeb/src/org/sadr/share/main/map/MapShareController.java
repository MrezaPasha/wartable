package org.sadr.share.main.map;

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
import org.sadr.share.main._utils.ShareUtils;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.layer.LayerShareService;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomShareService;
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
import java.util.Set;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت نقشه ها")
public class MapShareController extends GenericControllerImpl<Map, MapShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/map";

    ////////////////////
    public MapShareController() {
    }

    private Room_MapShareService room_mapShareService;

    private RoomShareService roomShareService;

    private LayerShareService layerShareService;

    @Autowired
    public void setLayerShareService(LayerShareService layerShareService) {
        this.layerShareService = layerShareService;
    }

    @Autowired
    public void setRoomShareService(RoomShareService roomShareService) {
        this.roomShareService = roomShareService;
    }

    @Autowired
    public void setRoom_mapShareService(Room_MapShareService room_mapShareService) {
        this.room_mapShareService = room_mapShareService;
    }


    //=========================== create
    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Map u = (Map) model.asMap().get("map");
        if (u == null) {
            u = new Map();
        }
        model.addAttribute(u);
        return TtTile___.p_service_map_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("map") Map fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                                final RedirectAttributes redirectAttributes) {
//        if (objBindingResult.hasErrors()) {
//            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
//        }

//        if (this.service.isExist(
//                Restrictions.eq(Map.NAME, fObj.getName()))) {
//            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.name.exist", TtNotice.Warning)));
//            return Referer.redirectObjects(TtTaskActionSubType.New_Data, TtTaskActionStatus.Failure, notice2s, request, redirectAttributes, fObj);
//        }
        Notice2[] notice2s = null;

        if (attachment != null && attachment.getSize() > 0) {
            if (!attachment.getOriginalFilename().endsWith(".map")) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.upload.format.invalid", TtNotice.Danger)));
                return Referer.redirectObject(request, redirectAttributes, fObj);
            }

            if (attachment.getSize() > 1024 * 1024 * PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize)) {
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.map.upload.max.size.exceed", TtNotice.Danger, PropertorInWeb.getInstance().getPropertyInt(TtPropertorInWebList.LoadThresholdMaxUploadSize) + "")));
                return Referer.redirectObject(request, redirectAttributes, fObj);
            }

            File upload = Uploader.getInstance().uploadOnTheFly(attachment, PropertorInWeb.getInstance().getProperty(TtPropertorInWebList.ServiceUploadPath_Base));
            //

            Map map = ShareUtils.uploadMap(upload.getOrginalName());
            if (map != null) {

                if (this.service.isExist(Restrictions.eq(Map.NAME, map.getName()))) {
                    Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.map.name.exist", TtNotice.Warning, map.getName())));
                    return Referer.redirectObject(request, redirectAttributes, fObj);
                }

                if (map.getLayers() != null && !map.getLayers().isEmpty()) {
                    Set<Layer> layers = map.getLayers();
                    map.setLayers(null);
                    this.service.save(map);
                    for (Layer layer : layers) {
                        layer.setMap(map);
                        this.layerShareService.save(layer);
                    }
                    notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.register.success", TtNotice.Success)));

                } else {
                    notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.layer.not.found", TtNotice.Danger)));

                }
            } else {
                notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.not.found", TtNotice.Danger)));
            }

        } else {
            notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.not.found", TtNotice.Danger)));
        }

//        fObj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

//        this.service.save(fObj);

        return Referer.redirect(_PANEL_URL + "/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
//        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }


    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {

        Searchee.init(Map.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Map.NAME
                );

        GB.searchTableColumns(model,
                Map.class,
                GB.col(Map.ID),
                GB.col(Map.CREATION_TIME),
                GB.col(Map.NAME),
                GB.col(Map.CATEGORY),
                GB.col(Map.FILE_SIZE),
                GB.col(Map.UPDATE_TIME),
                GB.col(Map.FILE_NAME)
        );
        return TtTile___.p_service_map_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(Map.class)
                .set(
                        Map.CREATION_TIME,
                        Map.NAME,
                        Map.CATEGORY,
                        Map.FILE_SIZE,
                        Map.UPDATE_TIME,
                        Map.FILE_NAME

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Map.CREATION_TIME,
                            Map.NAME,
                            Map.CATEGORY,
                            Map.FILE_SIZE,
                            Map.UPDATE_TIME,
                            Map.FILE_NAME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Map.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        Map dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.map.not.found", JsonBuilder.toJson("mapId", "" + id)))
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
    //=========================== create room
    @PersianName("انتخاب اتاق برای نقشه")
    @RequestMapping(_PANEL_URL + "/room/create/{id}")
    public ModelAndView pRoomCreate(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Map map = this.service.findById(id);
        if (map == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.map.not.found", JsonBuilder.toJson("mapId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.New_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        Room_Map u = (Room_Map) model.asMap().get("room_Map");
        if (u == null) {
            u = new Room_Map();
        }
        model.addAttribute(u);
        model.addAttribute(map);
        model.addAttribute("rlist", roomShareService.findAll());
        model.addAttribute("mlist", service.findAllBy(
                Restrictions.eq(Map.ID, id)
        ));
        return TtTile___.p_service_map_room_create.___getDisModel(_PANEL_URL + "/room/create", TtTaskActionSubType.New_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/room/create", method = RequestMethod.POST)
    public ModelAndView pRoomCreate(@ModelAttribute("room_Map") @Valid Room_Map fObj,
                                    BindingResult objBindingResult,
                                    HttpServletRequest request,
                                    final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        fObj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.room_mapShareService.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/room/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }


    //=========================== edit room
    @PersianName("ویرایش اتاق نقشه")
    @RequestMapping(value = _PANEL_URL + "/room/edit/{uid}")
    public ModelAndView pRoomEdit(Model model, @PathVariable("uid") long uid,
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
        if (dbObj.getMap() == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.map.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        Map map = this.service.findById(dbObj.getMap().getIdi());
        if (map == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.map.not.found", TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }
        model.addAttribute(map);
        model.addAttribute("rlist", roomShareService.findAll());
        model.addAttribute("mlist", service.findAllBy(
                Restrictions.eq(Map.ID, map.getIdi())
        ));
        model.addAttribute(dbObj);
        return TtTile___.p_service_map_room_edit.___getDisModel(_PANEL_URL + "/room/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/room/edit", method = RequestMethod.POST)
    public ModelAndView pRoomEdit(
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

        dbObj.setRoom(fObj.getRoom());

        this.room_mapShareService.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/room/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }


    //=========================== list room
    @PersianName("لیست اتاق های نقشه")
    @RequestMapping(value = _PANEL_URL + "/room/list/{id}")
    public ModelAndView pRoomList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        Map map = this.service.findById(id);
        if (map == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.not.found", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }


        Searchee.init(Room_Map.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Room_Map._ROOM,
                        Searchee.field(Room.ROOM_NAME, Room.class)
                )
        ;

        GB.searchTableColumns(model,
                Room_Map.class,
                GB.col(Room_Map.ID),
                GB.col(Room_Map.CREATION_DATE_TIME),
                GB.col(Room_Map.$ROOM_TITLE)

        );
        Searchee.init(Room_Map.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        Room_Map._MAP,
                        Searchee.field(Map.ID, Map.class)
                );


        model.addAttribute(map);
        return TtTile___.p_service_map_room_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/room/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pRoomList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                     @RequestParam(value = "ixp", required = false) String ixportParam,
                                     HttpServletResponse response) throws IOException {

        GB gb = GB.init(Room_Map.class)
                .set(
                        Room_Map.CREATION_DATE_TIME

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
                            Room_Map.$MAP_TITLE,
                            Room_Map.$ROOM_TITLE,
                            Room_Map.CREATION_DATE_TIME

                    );

            String jSearch = this.room_mapShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Map.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/layer/list/{id}")
    public ModelAndView pLayerList(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Map map = this.service.findById(id);
        if (map == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.map.not.found", TtNotice.Warning)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(Layer.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        Layer.NAME
                );

        Searchee.init(Layer.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        Layer._MAP,
                        Searchee.field(Map.ID, Map.class)
                );


        GB.searchTableColumns(model,
                Layer.class,
                GB.col(Layer.ID),
                GB.col(Layer.CREATE_DATE_TIME),
                GB.col(Layer.MODIFY_DATE_TIME),
                GB.col(Layer.NAME),
                GB.col(Layer.LAYER_STATUS),
                GB.col(Layer.ORDER),
                GB.col(Layer.LAYER_TYPE),
                GB.col(Layer.TYPE),
                GB.col(Layer.DESCRIPTION)
        );
        return TtTile___.p_service_map_layer_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/layer/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pLayerList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                      @RequestParam(value = "ixp", required = false) String ixportParam,
                                      HttpServletResponse response) throws IOException {

        GB gb = GB.init(Layer.class)
                .set(
                        Layer.CREATE_DATE_TIME,
                        Layer.MODIFY_DATE_TIME,
                        Layer.NAME,
                        Layer.ORDER,
                        Layer.LAYER_STATUS,
                        Layer.LAYER_TYPE,
                        Layer.TYPE,
                        Layer.DESCRIPTION

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Layer.CREATE_DATE_TIME,
                            Layer.MODIFY_DATE_TIME,
                            Layer.NAME,
                            Layer.ORDER,
                            Layer.LAYER_STATUS,
                            Layer.LAYER_TYPE,
                            Layer.TYPE,
                            Layer.DESCRIPTION);

            String jSearch = this.layerShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(Layer.class)
                .exportToFileInList(this.layerShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }


    //=========================== Trash room
    @PersianName("حذف اتاق از نقشه")
    @RequestMapping(value = _PANEL_URL + "/room/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pRoomTrash(@PathVariable("id") long id) {

        Room_Map dbObj = this.room_mapShareService.findById(id, Room_Map._ROOM);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getRoomTitle();
        this.room_mapShareService.delete(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }
}
