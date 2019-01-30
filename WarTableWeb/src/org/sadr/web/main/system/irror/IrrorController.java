package org.sadr.web.main.system.irror;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.Searchee;
import org.sadr._core.utils._type.TtSearcheeStrategy;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.OverChangePassword;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
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
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مرکز خطا")
public class IrrorController extends GenericControllerImpl<Irror, IrrorService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/e";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    ///////////////////////////////////////////////////
    @OverChangePassword
    @TaskAccessLevel
    @PersianName("صفحه خطا")
    @RequestMapping(value = "/er/{code}")
    public ModelAndView gServletErrorHandler(@PathVariable("code") String code,
                                             HttpServletRequest request,
                                             HttpSession session) {

        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");

        TtHttpErrorCode___ eCode = TtHttpErrorCode___.getByKey(code);
        if (eCode == null) {
            return TtHttpErrorCode___.NotFound_404.___getFrontDisModel();
        }
        ModelAndView andView;
        if (request.getAttribute("javax.servlet.forward.request_uri") != null && request.getAttribute("javax.servlet.forward.request_uri").toString().contains("/panel/")) {
            User u = (User) session.getAttribute("sUser");
            if (u != null && (u.getLevel() == TtUserLevel.Administrator || u.getLevel() == TtUserLevel.Master)) {
                andView = eCode.___getPanelDisModel();
            } else {
                andView = eCode.___getFrontDisModel();
            }
        } else {
            andView = eCode.___getFrontDisModel();
        }
        andView.addObject("pageTitle", "irror.c.gServletErrorHandler");

        if (throwable != null && statusCode != null && statusCode == 500) {
            Irror irror = this.service.submit(new Exception(throwable), request, TtIrrorPlace.IrrorController, TtIrrorLevel.Fatal);
            if (irror != null) {
                andView.addObject("errorMsg", irror.getMessage())
                        .addObject("errorId", irror.getId());
            } else {
                andView.addObject("errorMsg", throwable.toString());
            }
        }
        return andView;
    }

    /////////////////////////////////////////////////// PANEL

    @PersianName("لیست خطاها")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model
    ) {


        Searchee.init(Irror.class, model)
                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Searchee.field(Irror._USER),
                        Searchee.field(User.USERNAME, User.class)
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Irror._USER,
                        Searchee.field(User.FIRST_NAME, User.class)
                )

                .setAttribute(
                        TtDataType.String,
                        TtRestrictionOperator.Like_ANY,
                        TtSearcheeStrategy.IgnoreWhiteSpaces,
                        Irror._USER,
                        Searchee.field(User.LAST_NAME, User.class)
                );

        GB.searchTableColumns(model,
                Irror.class,
                GB.col(Irror.ID),
                GB.col(Irror.CREATE_DATE_TIME),
                GB.col(Irror.$USER_FULL_NAME, GB.path(Irror._USER, User.FIRST_NAME)),
                GB.col(Irror.HTTP_ERROR_CODE),
                GB.col(Irror.CAUSE),
                GB.col(Irror.LEVEL),
                GB.col(Irror.PLACE)
        );
        return TtTile___.p_e_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(@RequestParam(value = "ap", required = false) String ajaxParam,
                                 @RequestParam(value = "ixp", required = false) String ixportParam,
                                 HttpServletResponse response) throws IOException {
        GB gb = GB.init(Irror.class)
                .set(
                        Irror.CAUSE,
                        Irror.HTTP_ERROR_CODE,
                        Irror.IS_VISITED,
                        Irror.STATUS,
                        Irror.CREATE_DATE_TIME,
                        Irror.LEVEL,
                        Irror.PLACE
                )
                .setGbs(
                        GB.init(User.class, Irror._USER)
                                .set(User.GENDER,
                                        User.FIRST_NAME,
                                        User.LAST_NAME,
                                        User.USERNAME
                                )
                )
                .setSearchParams(ajaxParam);

        if (ixportParam == null) {

            JB jb = JB.init()
                    .set(
                            Irror.CAUSE,
                            Irror.HTTP_ERROR_CODE,
                            Irror.IS_VISITED,
                            Irror.STATUS,
                            Irror.CREATE_DATE_TIME,
                            Irror.LEVEL,
                            Irror.PLACE,
                            Irror.$USER_FULL_NAME
                    );
            String jSearch = this.service.findAllJson(gb, jb);

            return Ison.init(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success)
                    .setStatus(TtIsonStatus.Ok)
                    .setPropertySearch(jSearch)
                    .toResponse();
        }

        gb.setIxportParams(ixportParam);
        return Ixporter.init(Irror.class)
                .exportToFileInList(this.service.findAll(gb), response, gb, TtIxportTtStrategy.TitleThenKeyMode, TtIxportSubStrategy.IgnoreSubs, TtIxportRowIndex.On, TtIxporterDownloadMode.FileControllerAddress, ixportParam);


    }

    @PersianName("نمایش جزئیات خطا")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(Model model,
                                 @PathVariable("id") long id,
                                 RedirectAttributes redirectAttributes) {
        Irror i = this.service.findById(id, Irror._USER);
        if (i == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.irror.not.found", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/list", TtTaskActionSubType.Take_Report, TtTaskActionStatus.Failure, notice2s);
        }
        i.addVisitCount();
        i.setIsVisited(true);
        this.service.update(i);
        model.addAttribute("irror", i);
        return TtTile___.p_e_details.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success).addObject("pageTitle", "irror.c.pErrorShow");
    }
    //////////////////////////////////
}
