package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
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
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت لاگ سرویس")
public class ServiceLogShareController extends GenericControllerImpl<ServiceLog, ServiceLogShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/serviceLog";

    ////////////////////
    public ServiceLogShareController() {
    }


    //=========================== create

    //=========================== details
    @PersianName("جزئیات")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        ServiceLog dbObj = this.service.findById(id);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.serviceLog.not.found", JsonBuilder.toJson("serviceLogId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_serviceLog_details.___getDisModel(_PANEL_URL + "/details", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
    }

    //=========================== list
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {

        Searchee.init(ServiceLog.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        ServiceLog.SOFTWARE_ID
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.Normal,
                        ServiceLog.SOFTWARE_NAME
                );

        GB.searchTableColumns(model,
                ServiceLog.class,
                GB.col(ServiceLog.ID),
                GB.col(ServiceLog.CREATE_DATE_TIME),
                GB.col(ServiceLog.SOFTWARE_ID),
                GB.col(ServiceLog.SOFTWARE_NAME),
                GB.col(ServiceLog.ACTION_TYPE),
                GB.col(ServiceLog.CLIENT_IP),
                GB.col(ServiceLog.SENSITIVITY),
                GB.col(ServiceLog.URLL),
                GB.col(ServiceLog.IMPORTANCE)
        );
        return TtTile___.p_service_serviceLog_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb = GB.init(ServiceLog.class)
                .set(
                        ServiceLog.CREATE_DATE_TIME,
                        ServiceLog.ACTION_TYPE,
                        ServiceLog.SOFTWARE_ID,
                        ServiceLog.SOFTWARE_NAME,
                        ServiceLog.CLIENT_IP,
                        ServiceLog.SENSITIVITY,
                        ServiceLog.IMPORTANCE,
                        ServiceLog.CLIENT_HOSTNAME,
                        ServiceLog.URLL
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {
            JB jb = JB.init()
                    .set(
                            ServiceLog.CREATE_DATE_TIME,
                            ServiceLog.ACTION_TYPE,
                            ServiceLog.SOFTWARE_ID,
                            ServiceLog.SOFTWARE_NAME,
                            ServiceLog.CLIENT_IP,
                            ServiceLog.SENSITIVITY,
                            ServiceLog.IMPORTANCE,
                            ServiceLog.CLIENT_HOSTNAME,
                            ServiceLog.URLL
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(ServiceLog.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

    //=========================== Trash

}
