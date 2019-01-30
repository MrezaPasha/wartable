package org.sadr.share.main.criticalLog;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.JsonBuilder;
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
@PersianName("مدیریت خطاها")
public class CriticalLogShareController extends GenericControllerImpl<CriticalLog, CriticalLogShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/criticalLog";

    ////////////////////
    public CriticalLogShareController() {
    }


    //=========================== create


    //=========================== details
    @PersianName("جزئیات")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        CriticalLog dbObj = this.service.findById(id);
        if (dbObj == null) {
            Notice2[] noteIds = Notice2.initRedirectAttr(redirectAttributes, new Notice2("N.criticalLog.not.found", JsonBuilder.toJson("criticalLogId", "" + id), TtNotice.Warning));
            return Referer.redirect(
                    _PANEL_URL + "/list",
                    TtTaskActionSubType.Edit_Data,
                    TtTaskActionStatus.Failure,
                    noteIds);
        }

        model.addAttribute(dbObj);
        return TtTile___.p_service_criticalLog_details.___getDisModel(_PANEL_URL + "/details", TtTaskActionSubType.Edit_Data, TtTaskActionStatus.Success);
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
//                CriticalLog.ID
//        );
        GB.searchTableColumns(model,
                CriticalLog.class,
                GB.col(CriticalLog.ID),
                GB.col(CriticalLog.CREATION_DATE_TIME),
                GB.col(CriticalLog.LOG_DETAIL_POSITION)
        );
        return TtTile___.p_service_criticalLog_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb = GB.init(CriticalLog.class)
                .set(
                        CriticalLog.CREATION_DATE_TIME,
                        CriticalLog.LOG_DETAIL_POSITION
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {
            JB jb = JB.init()
                    .set(
                            CriticalLog.CREATION_DATE_TIME,
                            CriticalLog.LOG_DETAIL_POSITION);

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(CriticalLog.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }

//=========================== Trash

}
