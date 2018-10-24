package org.sadr.web.main.system.irror;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.OverChangePassword;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت خطا")
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
    @TaskAccessLevel(TtTaskAccessLevel.Free4Users)
    @PersianName("آزمایش صفحه خطا")
    @RequestMapping(value = _PANEL_URL + "/{code}")
    public ModelAndView pServletErrorHandler(@PathVariable("code") String code
    ) {
        TtHttpErrorCode___ eCode = TtHttpErrorCode___.getByKey(code);
        if (eCode == null) {
            return TtHttpErrorCode___.NotFound_404.___getPanelDisModel();
        }
        return eCode.___getPanelDisModel();
    }

    @PersianName("لیست خطاها")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pErrorList(Model model
    ) {
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
        return TtTile___.p_e_list.___getDisModel();
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pErrorList(
            @RequestParam(value = "ap", required = false) String ajaxParam,
            HttpServletRequest request,
            HttpSession session,
            HttpServletResponse response) {
        try {
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
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity<>(this.service.findAllJson(gb, jb), headers, HttpStatus.OK);
        } catch (Exception e) {
            service.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Warn);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

    @PersianName("نمایش جزئیات خطا")
    @RequestMapping(value = _PANEL_URL + "/show/{id}")
    public ModelAndView pErrorShow(Model model,
                                   @PathVariable("id") long id
    ) {
        Irror i = this.service.findById(id, Irror._USER);
        if (i == null) {
            return TtHttpErrorCode___.NotFound_404.___getPanelDisModel();
        }
        i.addVisitCount();
        i.setIsVisited(true);
        this.service.update(i);
        model.addAttribute("irror", i);
        return TtTile___.p_e_show.___getDisModel().addObject("pageTitle", "irror.c.pErrorShow");
    }

    //////////////////////////////////
    @TaskAccessLevel(TtTaskAccessLevel.Free4Gusts)
    @PersianName("آزمایش صفحه خطا")
    @RequestMapping(value = _FRONT_URL + "/{code}")
    public ModelAndView fServletErrorHandler(@PathVariable("code") String code
    ) {
        TtHttpErrorCode___ eCode = TtHttpErrorCode___.getByKey(code);
        if (eCode == null) {
            return TtHttpErrorCode___.NotFound_404.___getFrontDisModel()
                    .addObject("pageTitle", "irror.c.fServletErrorHandler");
        }
        return eCode.___getFrontDisModel().addObject("pageTitle", "irror.c.fServletErrorHandler");
    }

}
