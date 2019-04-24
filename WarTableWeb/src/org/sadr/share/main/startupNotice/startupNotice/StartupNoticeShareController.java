package org.sadr.share.main.startupNotice.startupNotice;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.RePa;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserShareService;
import org.sadr.share.main.startupNotice._type.TtStartupNoticeSendStatus;
import org.sadr.share.main.startupNotice.item.StartupNoticeItem;
import org.sadr.share.main.startupNotice.item.StartupNoticeItemShareService;
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
import org.sadr.web.main.note.note.Note;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class StartupNoticeShareController extends GenericControllerImpl<StartupNotice, StartupNoticeShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/notice";

    ////////////////////
    public StartupNoticeShareController() {
    }


    private StartupNoticeItemShareService startupNoticeItemShareService;
    private ServiceUserShareService serviceUserShareService;

    @Autowired
    public void setServiceUserShareService(ServiceUserShareService serviceUserShareService) {
        this.serviceUserShareService = serviceUserShareService;
    }

    @Autowired
    public void setStartupNoticeItemShareService(StartupNoticeItemShareService startupNoticeItemShareService) {
        this.startupNoticeItemShareService = startupNoticeItemShareService;
    }


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Set.class, "receiverTemps", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element != null) {
                    ServiceUser byId = serviceUserShareService.findById(Long.parseLong((String) element));
                    return byId;
                }
                return null;
            }
        });
    }

    //=========================== create
    @PersianName("ثبت")
    @RequestMapping(_PANEL_URL + "/create")
    public ModelAndView pCreate(Model model) {
        StartupNotice u = (StartupNotice) model.asMap().get("startupNotice");
        if (u == null) {
            u = new StartupNotice();
        }
        model.addAttribute(u);
        model.addAttribute("sulist", serviceUserShareService.findAll());
        return TtTile___.p_service_startupNotice_create.___getDisModel(_PANEL_URL + "/create", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/create", method = RequestMethod.POST)
    public ModelAndView pCreate(@ModelAttribute("startupNotice") @Valid StartupNotice fObj,
                                BindingResult objBindingResult,
                                HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (objBindingResult.hasErrors()) {
            return Referer.redirectBindingError(TtTaskActionSubType.New_Data, TtTaskActionStatus.Error, request, redirectAttributes, objBindingResult, fObj);
        }

        Set<ServiceUser> receiverTemps = new HashSet<>(fObj.getReceiverTemps());
        if (receiverTemps == null || receiverTemps.isEmpty()) {
            Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.startupNotice.receiver.is.null", JsonBuilder.toJson("startupNoticeId", "" + fObj.getId()), TtNotice.Warning));
            return Referer.redirectObject(
                    request, redirectAttributes, fObj);
        }

        fObj.setReceiverTemps(null);
        this.service.save(fObj);
        fObj = this.service.findById(fObj.getId(), StartupNotice._RECEIVERS, StartupNotice._RECEIVER_TEMPS);

        for (ServiceUser r : receiverTemps) {
            StartupNoticeItem s = new StartupNoticeItem();
            s.setSendStatus(TtStartupNoticeSendStatus.NotDelivered);
            s.setServiceUser(r);
            s.setStartupNotice(fObj);
            s.setDeliverDateTime(null);
            startupNoticeItemShareService.save(s);
        }


        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.startupNotice.register.success", TtNotice.Success)));
        return Referer.redirect(_PANEL_URL + "/edit/" + fObj.getIdi(), TtTaskActionSubType.New_Data, TtTaskActionStatus.Success, notice2s);
    }

    //=========================== edit
    @PersianName("ویرایش")
    @RequestMapping(value = _PANEL_URL + "/edit/{uid}")
    public ModelAndView pEdit(Model model, @PathVariable("uid") long uid,
                              RedirectAttributes redirectAttributes) {

        StartupNotice dbObj = (StartupNotice) model.asMap().get("startupNotice");
        if (dbObj == null) {
            dbObj = this.service.findById(uid, StartupNotice._RECEIVER_TEMPS, RePa.p__(StartupNotice._RECEIVERS, StartupNoticeItem._SERVICE_USER));
        }
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.startupNotice.not.found", JsonBuilder.toJson("startupNoticeId", "" + uid), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }


        List<ServiceUser> items = this.serviceUserShareService.findAll();
        List<ServiceUser> newUsers = new ArrayList<>();
        boolean ise;
        dbObj.setReceiverTemps(new HashSet<>());
        for (ServiceUser dbu : items) {
            ise = false;
            if (dbObj.getReceivers() != null) {
                for (StartupNoticeItem getu : dbObj.getReceivers()) {
                    if (dbu.getIdi() == getu.getServiceUser().getIdi()) {
                        ise = true;
                        dbObj.getReceiverTemps().add(dbu);
                        break;
                    }
                }
            }
            if (!ise) {
                newUsers.add(dbu);
            }
        }

        model.addAttribute("sulist", newUsers);

        model.addAttribute(dbObj);
        return TtTile___.p_service_startupNotice_edit.___getDisModel(_PANEL_URL + "/edit", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pEdit(
            @ModelAttribute("startupNotice")
            @Valid StartupNotice fObj,
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

        Set<ServiceUser> receiverTemps = new HashSet<>(fObj.getReceiverTemps());
        if (receiverTemps == null || receiverTemps.isEmpty()) {
            Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.startupNotice.receiver.is.null", JsonBuilder.toJson("startupNoticeId", "" + fObj.getId()), TtNotice.Warning));
            return Referer.redirectObject(request, redirectAttributes, fObj);
        }

        List<StartupNoticeItem> dbIs = this.startupNoticeItemShareService.findAllBy(
                Restrictions.and(
                        Restrictions.eq(StartupNoticeItem._STARTUP_NOTICE, fObj)
                ), StartupNoticeItem._SERVICE_USER);
        int i;
        boolean isExist;
        for (ServiceUser r : receiverTemps) {
            i = 0;
            isExist = false;
            for (StartupNoticeItem d : dbIs) {
                if (d != null && r.getIdi() == d.getServiceUser().getIdi()) {
                    dbIs.set(i, null);
                    isExist = true;
                    i++;
                    continue;
                }
                i++;
            }
            if (!isExist) {
                StartupNoticeItem s = new StartupNoticeItem();
                s.setSendStatus(TtStartupNoticeSendStatus.NotDelivered);
                s.setServiceUser(r);
                s.setStartupNotice(fObj);
                s.setDeliverDateTime(null);
                startupNoticeItemShareService.save(s);
            }
        }
        for (StartupNoticeItem d : dbIs) {
            if (d != null) {
                this.startupNoticeItemShareService.delete(d.getIdi());
            }
        }

        StartupNotice dbObj;

        dbObj = this.service.findById(fObj.getIdi(), StartupNotice._RECEIVER_TEMPS, StartupNotice._RECEIVERS);

        if (dbObj == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.startupNotice.not.found")));
            return Referer.redirectObjects(
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Error,
                    notice2s,
                    request,
                    redirectAttributes,
                    fObj);
        }

        dbObj.setReceiverTemps(null);
        dbObj.setMessageBody(fObj.getMessageBody());
        dbObj.setTitle(fObj.getTitle());

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

        StartupNotice dbObj = this.service.findById(id);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.startupNotice.not.found", JsonBuilder.toJson("startupNoticeId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_startupNotice_details.___getDisModel(_PANEL_URL + "/details", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {
        Searchee.init(StartupNotice.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        StartupNotice.TITLE
                )
        ;

        GB.searchTableColumns(model,
                StartupNotice.class,
                GB.col(StartupNotice.ID),
                GB.col(StartupNotice.TITLE),
                GB.col(StartupNotice.SEND_DATE_TIME)
        );
        return TtTile___.p_service_startupNotice_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {

        GB gb = GB.init(StartupNotice.class)
                .set(
                        StartupNotice.CREATE_DATE_TIME,
                        StartupNotice.SEND_DATE_TIME,
                        StartupNotice.TITLE

                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            StartupNotice.CREATE_DATE_TIME,
                            StartupNotice.SEND_DATE_TIME,
                            StartupNotice.TITLE
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(StartupNotice.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);
    }

    //=========================== Trash
    @PersianName("حذف")
    @RequestMapping(value = _PANEL_URL + "/trash/{id}")
    public @ResponseBody
    ResponseEntity<String> pTrash(@PathVariable("id") long id) {

        StartupNotice dbObj = this.service.findById(id, StartupNotice._RECEIVERS);
        if (dbObj == null) {
            return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Failure)
                    .setStatus(TtIsonStatus.Nok)
                    .setMessages(new Notice2("N.startupNotice.not.found", JsonBuilder.toJson("startupNoticeId", "" + id)))
                    .toResponse();
        }
        if (dbObj.getReceivers() != null && dbObj.getReceivers().isEmpty()) {
            for (StartupNoticeItem r : dbObj.getReceivers()) {
                this.startupNoticeItemShareService.trash(id);
            }
        }
        long name = dbObj.getId();
        this.service.trash(id);

        return Ison.init(TtTaskActionSubType.Delete_From_DB, TtTaskActionStatus.Success)
                .setStatus(TtIsonStatus.Ok)
                .setMessages(new Notice2("N1.all.trash.success", TtNotice.Success, name + ""))
                .toResponse();
    }


    ///###########################################################################

    @PersianName("لیست گیرندگان فراخوان")
    @RequestMapping(value = _PANEL_URL + "/item/list/{id}")
    public ModelAndView pItemList(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        StartupNotice notice = this.service.findById(id);
        if (notice == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.startupNotice.not.found", TtNotice.Warning)));
            return Referer.redirect("/panel/notice/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }

        Searchee.init(StartupNoticeItem.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        StartupNoticeItem._SERVICE_USER, Searchee.field(ServiceUser.NAME, ServiceUser.class)
                )
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.Normal,
                        StartupNoticeItem._SERVICE_USER, Searchee.field(ServiceUser.FAMILY, ServiceUser.class)
                )
        ;

        Searchee.init(StartupNoticeItem.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.HiddenAutoFill,
                        id,
                        StartupNoticeItem._STARTUP_NOTICE,
                        Searchee.field(StartupNotice.ID, StartupNotice.class)

                );


        GB.searchTableColumns(model,
                StartupNoticeItem.class,
                GB.col(StartupNoticeItem.ID),
                GB.col(StartupNoticeItem.$RECEIVER_NAME, StartupNoticeItem.DELIVER_DATE_TIME),
                GB.col(StartupNoticeItem.SEND_STATUS),
                GB.col(StartupNoticeItem.DELIVER_DATE_TIME)
        );

        model.addAttribute(notice);
        return TtTile___.p_service_startupNotice_item_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/item/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pItemList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                     @RequestParam(value = "ixp", required = false) String ixportParam,
                                     HttpServletResponse response) throws IOException {

        GB gb = GB.init(StartupNoticeItem.class)
                .set(
                        StartupNoticeItem.CREATE_DATE_TIME,
                        StartupNoticeItem.MODIFY_DATE_TIME,
                        StartupNoticeItem.DELIVER_DATE_TIME,
                        StartupNoticeItem.SEND_STATUS
                )
                .setGbs(
                        GB.init(ServiceUser.class, StartupNoticeItem._SERVICE_USER)
                                .set(
                                        ServiceUser.NAME,
                                        ServiceUser.FAMILY
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            StartupNoticeItem.CREATE_DATE_TIME,
                            StartupNoticeItem.MODIFY_DATE_TIME,
                            StartupNoticeItem.DELIVER_DATE_TIME,
                            StartupNoticeItem.SEND_STATUS,
                            StartupNoticeItem.$RECEIVER_NAME
                    );

            String jSearch = this.startupNoticeItemShareService.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(StartupNoticeItem.class)
                .exportToFileInList(this.startupNoticeItemShareService.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IncludeSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    //=========================== Trash item
}
