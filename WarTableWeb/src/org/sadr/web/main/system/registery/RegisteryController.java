package org.sadr.web.main.system.registery;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@RestController
@PersianName("مدیریت اطلاعات پایه (رجیستری)")
public class RegisteryController extends GenericControllerImpl<Registery, RegisteryService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/registery";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;

    @TaskAccessLevel
    @PersianName("لیست مقادیر ")
    @RequestMapping(value = "/purejv-x/{oid}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> pFetchPureJsonValuesX(HttpSession session,
                                                 @PathVariable("oid") int oid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(this.service.getPureJsonValueBracket(TtRegisteryKey.getByOrdinal(oid)), headers, HttpStatus.OK);
    }

    @PersianName("لیست اطلاعات پایه")
    @MenuIdentity(TtTile___.p_sys_registery_list)
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pRegisteryList(Model model,
                                       final RedirectAttributes redirectAttributes) {

        model.addAttribute("rlist", this.service.findAll());
        return TtTile___.p_sys_registery_list.___getDisModel();

    }

    @PersianName("ویرایش اطلاعات پایه")
    @MenuIdentity(TtTile___.p_sys_registery_edit)
    @RequestMapping(value = _PANEL_URL + "/edit/{id}")
    public ModelAndView pRegisteryEdit(Model model,
                                       @PathVariable("id") int id,
                                       final RedirectAttributes redirectAttributes) {
        Registery reg = this.service.findById(id);
        if (reg == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.registery.not.found", JsonBuilder.toJson("regId", id + ""), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/registery/list");
        }
        model.addAttribute("registery", reg);
        model.addAttribute("rlist", reg.getPureJsonValueBracket());
        return TtTile___.p_sys_registery_edit.___getDisModel();

    }

    @RequestMapping(value = _PANEL_URL + "/edit", method = RequestMethod.POST)
    public ModelAndView pRegisteryEdit(Model model,
                                       @ModelAttribute("registery") Registery formObj,
                                       final RedirectAttributes redirectAttributes
    ) {
        Registery dbObj = this.service.findById(formObj.getId());
        if (dbObj == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.registery.not.found", formObj.getSecretNote(), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/registery/list");
        }
        if (dbObj.getIsNormalValue()) {
            dbObj.setValue(formObj.getValue());
        } else {
            dbObj.setValueJson("");
            dbObj.addPureJsonValues(formObj.getValueJson());
        }
        this.service.update(dbObj);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.registery.edit.success", formObj.getSecretNote(), TtNotice.Success)));
        return new ModelAndView("redirect:/panel/registery/edit/" + formObj.getId());
    }

    ////////////////////
}
