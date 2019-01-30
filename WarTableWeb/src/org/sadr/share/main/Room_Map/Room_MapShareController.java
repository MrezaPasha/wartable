package org.sadr.share.main.Room_Map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class Room_MapShareController extends GenericControllerImpl<Room_Map, Room_MapShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/room_Map";

    ////////////////////
    public Room_MapShareController() {
    }


    //=========================== create
   /* @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        Room_Map u = (Room_Map) model.asMap().get("room_Map");
        if (u == null) {
            u = new Room_Map();
        }
        model.addAttribute(u);
        return TtTile___.p_service_room_Map_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }
    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("room_Map") @Valid Room_Map fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        this.service.save(fObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.room_Map.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }
    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        Room_Map dbObj = (Room_Map) model.asMap().get("room_Map");
        if (dbObj == null) {
            dbObj = this.service.findById(uid);
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_Map_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
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


        dbObj = this.service.findById(fObj.getIdi());

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


        this.service.update(dbObj);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.all.edit.success", TtNotice.Success, "" + dbObj.getIdi())));

        return Referer.redirect(
                _PANEL_URL + "/edit/" + dbObj.getIdi(),
                TtTaskActionSubType.Edit_Data,
                TtTaskActionStatus.Success,
                notice2s);
    }
    //=========================== details
    @PersianName("جزئیات")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Room_Map dbObj = this.service.findById(id);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_room_Map_details.___getDisModel(_PANEL_URL + "/details", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }
    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {


//        Searchee.getInstance().setAttribute(
//                model,
//                "f_id",
//                TtDataType.Integer,
//                TtRestrictionOperator.Equal,
//                true,
//                Room_Map.ID
//        );
        GB.searchTableColumns( model,
                Room_Map.class,
                GB.col(Room_Map.ID),
                GB.col(Room_Map.CREATE_DATE_TIME)
        );
        return TtTile___.p_service_room_Map_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb = GB.init(Room_Map.class)
                .set(
                        Room_Map.CREATE_DATE_TIME
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {
            JB jb = JB.init()
                    .set(
                            Room_Map.CREATE_DATE_TIME
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
            gb.setIxportParams(ixportParam);
            List<Room_Map> all = this.service.findAll(gb);
            String fileName = Ixporter.init(Room_Map.class)
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

        Room_Map dbObj = this.service.findById(id);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.room_Map.not.found", JsonBuilder.toJson("room_MapId", "" + id)))
                    .toResponse();
        }
        String name = dbObj.getValue();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name))
                .toResponse();
    }
*/
}
