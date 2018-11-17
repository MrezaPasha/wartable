package org.sadr.web.main.system.log.signin;

import org.hibernate.criterion.Restrictions;
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
import org.sadr.web.main._core.utils._type.TtIxportRowIndex;
import org.sadr.web.main._core.utils._type.TtIxportSubStrategy;
import org.sadr.web.main._core.utils._type.TtIxportTtStrategy;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت رویدادنگاری ورود کاربر")
public class SigninLogController extends GenericControllerImpl<SigninLog, SigninLogService> {

    private final String REQUEST_MAPPING_BASE = "/log/signin";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    private IrrorService irrorService;

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }


    public SigninLogController() {
    }


    @MenuIdentity(TtTile___.p_sys_log_list)
    @PersianName("لیست رویدادها")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model
    ) {
        Searchee.getInstance().setAttributeArray(
                model,
                "f_username",
                TtDataType.String,
                TtRestrictionOperator.ILike_ANY,
                true,
                SigninLog._USER, User.USERNAME);
        Searchee.getInstance().setAttributeArray(
                model,
                "f_fromDate",
                TtDataType.String,
                TtRestrictionOperator.GreaterEqual,
                false,
                SigninLog.CREATE_DATE_TIME);

        Searchee.getInstance().setAttributeArray(
                model,
                "f_toDate",
                TtDataType.String,
                TtRestrictionOperator.LessEqual,
                false,
                SigninLog.CREATE_DATE_TIME);

        GB.searchTableColumns(model,
                SigninLog.class,
                GB.col(SigninLog.ID),
                GB.col(SigninLog.CREATE_DATE_TIME),
                GB.col(SigninLog.DOMAIN_NAME),
                GB.col(SigninLog.IP_ADDRESS),
                GB.col(SigninLog.STATUS),
                GB.col(SigninLog.$USER_NAME, User.USERNAME)

        );
        return TtTile___.p_sys_log_signin_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            @RequestParam(value = "submit", required = false) String submit,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            GB gb = GB.init(SigninLog.class)
                    .set(
                            SigninLog.CREATE_DATE_TIME,
                            SigninLog.DOMAIN_NAME,
                            SigninLog.IP_ADDRESS,
                            SigninLog.STATUS
                    )
                    .setGbs(
                            GB.init(User.class, SigninLog._USER)
                                    .set(
                                            User.USERNAME
                                    )
                    )
                    .setSearchParams(ajaxParam)
                    .addRestrictionsAnd(
                            Restrictions.eq(SigninLog.STATUS, TtSigninLogStatus.Failed)
                    );

            JB jb = JB.init()
                    .set(
                            SigninLog.CREATE_DATE_TIME,
                            SigninLog.DOMAIN_NAME,
                            SigninLog.IP_ADDRESS,
                            SigninLog.STATUS,
                            SigninLog.$USER_NAME

                    );
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            String json = this.service.findAllJson(gb, jb);

            if (submit != null && submit.equals("export")) {
                gb = GB.init(SigninLog.class)
                        .set(TtGbColumnFetch.All
                        )
                        .setSearchParams(ajaxParam);
                gb.getPaging().setSize(-1);
                gb.getPaging().setIndex(1);
                List<SigninLog> all = this.service.findAll(gb);

                new Ixporter(SigninLog.class)
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


}
