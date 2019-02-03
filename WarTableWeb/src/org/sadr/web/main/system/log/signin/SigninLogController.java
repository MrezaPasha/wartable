package org.sadr.web.main.system.log.signin;

import org.hibernate.criterion.Restrictions;
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
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtSigninLogStatus;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.irror.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author masoud
 */
@RestController
@PersianName("ورودهای ناموفق")
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
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model
    ) {


        Searchee.init(SigninLog.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.ILike_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        SigninLog._USER,
                        Searchee.field(User.USERNAME, User.class)
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.GreaterEqual,
                        TtSearcheeStrategy.Normal,
                        SigninLog.CREATE_DATE_TIME
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.LessEqual,
                        TtSearcheeStrategy.Normal,
                        SigninLog.CREATE_DATE_TIME
                )

        ;

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
            @RequestParam(value = "ixp", required = false) String ixportParam,
            HttpServletResponse response) throws IOException {

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

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            SigninLog.CREATE_DATE_TIME,
                            SigninLog.DOMAIN_NAME,
                            SigninLog.IP_ADDRESS,
                            SigninLog.STATUS,
                            SigninLog.$USER_NAME

                    );

            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }
        gb.setIxportParams(ixportParam);
        return Ixporter.init(SigninLog.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);

    }


}
