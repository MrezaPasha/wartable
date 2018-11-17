package org.sadr.web.main.system.log.general;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtGbColumnFetch;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.Searchee;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.utils.Ixporter;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIxportRowIndex;
import org.sadr.web.main._core.utils._type.TtIxportSubStrategy;
import org.sadr.web.main._core.utils._type.TtIxportTtStrategy;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@RestController
@PersianName("مدیریت رویدادنگاری محلی")
public class LogController extends GenericControllerImpl<Log, LogService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/log";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    public LogController() {
    }


    @MenuIdentity(TtTile___.p_sys_log_list)
    @PersianName("لیست رویدادها")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model
    ) {
        Searchee.getInstance().setAttributeArray(
                model,
                "f_userId",
                TtDataType.Long,
                TtRestrictionOperator.Equal,
                false,
                Log.USER_ID);
        Searchee.getInstance().setAttributeArray(
                model,
                "f_fromDate",
                TtDataType.String,
                TtRestrictionOperator.GreaterEqual,
                false,
                Log.CREATE_DATE_TIME);

        Searchee.getInstance().setAttributeArray(
                model,
                "f_toDate",
                TtDataType.String,
                TtRestrictionOperator.LessEqual,
                false,
                Log.CREATE_DATE_TIME);

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
                GB.col(Log.PORT_NUMBER)
        );
        return TtTile___.p_sys_log_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            @RequestParam(value = "submit", required = false) String submit,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            GB gb = GB.init(Log.class)
                    .set(
                            Log.CREATE_DATE_TIME,
                            Log.TASK_TITLE,
                            Log.SENSITIVITY,
                            Log.PORT_NUMBER,
                            Log.URL,
                            Log.REQUEST_METHOD,
                            Log.IMPORTANCE_LEVEL,
                            Log.USER_ID,
                            Log.USER_GROUP_ID,
                            Log.USER_LEVEL
                    )
                    .setSearchParams(ajaxParam);

            JB jb = JB.init()
                    .set(
                            Log.CREATE_DATE_TIME,
                            Log.TASK_TITLE,
                            Log.SENSITIVITY,
                            Log.PORT_NUMBER,
                            Log.URL,
                            Log.REQUEST_METHOD,
                            Log.IMPORTANCE_LEVEL,
                            Log.USER_ID,
                            Log.USER_GROUP_ID,
                            Log.USER_LEVEL
                    );
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            String json = this.service.findAllJson(gb, jb);

            if (submit != null && submit.equals("export")) {
                gb = GB.init(Log.class)
                        .set(TtGbColumnFetch.All
                        )
                        .setSearchParams(ajaxParam);
                gb.getPaging().setSize(-1);
                gb.getPaging().setIndex(1);
                List<Log> all = this.service.findAll(gb);

                new Ixporter(Log.class)
                        .exportToFile(all, response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On);
            }

            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }


    @MenuIdentity(TtTile___.p_sys_log_details)
    @PersianName("نمایش جزئیات رویدادنگاری")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pShow(Model model,
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
