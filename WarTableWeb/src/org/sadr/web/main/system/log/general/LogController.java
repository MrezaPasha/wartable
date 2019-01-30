package org.sadr.web.main.system.log.general;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.tools._type.TtIxportRowIndex;
import org.sadr.web.main._core.tools._type.TtIxportSubStrategy;
import org.sadr.web.main._core.tools._type.TtIxportTtStrategy;
import org.sadr.web.main._core.tools._type.TtIxporterDownloadMode;
import org.sadr.web.main._core.tools.ixporter.Ixporter;
import org.sadr.web.main._core.utils.CacheStatic;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtBackupType;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.backup.Backup;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@RestController
@PersianName("رویدادنگاری")
public class LogController extends GenericControllerImpl<Log, LogService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/log";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    public LogController() {
    }


    @MenuIdentity(TtTile___.p_sys_log_list)
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model
    ) {


        Searchee.init(Log.class, model)
                .setAttribute(
                        TtDataType.Long,
                        TtRestrictionOperator.Equal,
                        TtSearcheeStrategy.Normal,
                        Log.USER_ID
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.GreaterEqual,
                        TtSearcheeStrategy.Normal,
                        Log.CREATE_DATE_TIME)

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.LessEqual,
                        TtSearcheeStrategy.Normal,
                        Log.CREATE_DATE_TIME)
        ;

        GB.searchTableColumns(model,
                Log.class,
                GB.col(Log.ID),
                GB.col(Log.CREATE_DATE_TIME),
                GB.col(Log.TASK_TITLE),
                GB.col(Log.USER_ID),
                GB.col(Log.USER_LEVEL),
                GB.col(Log.USER_GROUP_ID),
                GB.col(Log.SENSITIVITY),
                GB.col(Log.IMPORTANCE_LEVEL),
                GB.col(Log.URL),
                GB.col(Log.REQUEST_METHOD),
                GB.col(Log.HOST_PORT_NUMBER)
        );
        return TtTile___.p_sys_log_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            @RequestParam(value = "ixp", required = false) String ixportParam,
            HttpServletResponse response) throws IOException {

        GB gb = GB.init(Log.class)
                .set(
                        Log.CREATE_DATE_TIME,
                        Log.TASK_TITLE,
                        Log.SENSITIVITY,
                        Log.HOST_PORT_NUMBER,
                        Log.URL,
                        Log.REQUEST_METHOD,
                        Log.IMPORTANCE_LEVEL,
                        Log.USER_ID,
                        Log.USER_GROUP_ID,
                        Log.USER_LEVEL
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Log.CREATE_DATE_TIME,
                            Log.TASK_TITLE,
                            Log.SENSITIVITY,
                            Log.HOST_PORT_NUMBER,
                            Log.URL,
                            Log.REQUEST_METHOD,
                            Log.IMPORTANCE_LEVEL,
                            Log.USER_ID,
                            Log.USER_GROUP_ID,
                            Log.USER_LEVEL
                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb.setIxportParams(ixportParam);
        return Ixporter.init(Log.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }


    @MenuIdentity(TtTile___.p_sys_log_details)
    @PersianName("نمایش جزئیات رویدادنگاری")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model,
                                 @PathVariable("id") long id,
                                 RedirectAttributes redirectAttributes
    ) {
        Log i = this.service.findById(id);
        if (i == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.log.not.found", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }
        model.addAttribute("log", i);
        return TtTile___.p_sys_log_details.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

}
