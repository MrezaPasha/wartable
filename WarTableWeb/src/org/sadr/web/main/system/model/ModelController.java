package org.sadr.web.main.system.model;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.system._type.TtModelAdaptResult;
import org.sadr.web.main.system._type.TtModelRebuildResult;
import org.sadr.web.main.system._type.TtModelStatus;
import org.sadr.web.main.system.field.Field;
import org.sadr.web.main.system.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import java.sql.SQLException;
import java.util.List;

/**
 * @author masoud
 * @version 1.96.01.02
 */
@PersianName("مدیریت مدل")
@RestController
public class ModelController extends GenericControllerImpl<Model, ModelService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/model";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////

    private FieldService fieldService;

    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public ModelController() {
    }

    @SuperAdminTask
    @PersianName("تطبیق کل مدل ها")
    @RequestMapping(_PANEL_URL + "/adapt")
    public ModelAndView pAdaptAllInfo(org.springframework.ui.Model model, final RedirectAttributes redirectAttributes) throws ClassNotFoundException, SQLException {
        this.service.adapt(this.service.findAll());
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.adapt.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/model/list");
    }

    @SuperAdminTask
    @PersianName("تطبیق یک مدل")
    @RequestMapping(_PANEL_URL + "/adapt/{id}")
    public ModelAndView pAdaptInfo(org.springframework.ui.Model model,
                                   @PathVariable("sx") String sx,
                                   final RedirectAttributes redirectAttributes,
                                   @PathVariable("id") int id) throws ClassNotFoundException, SQLException {

        TtModelAdaptResult adapt = this.service.adapt(id);
        switch (adapt) {
            case Success:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.model.adapt.success", TtNotice.Success, "" + id)));
                break;
            case ModelDeleted:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.deleted", JsonBuilder.toJson("modelId", "" + id), TtNotice.Warning)));
                break;
            case ModelNotFound:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.not.found", JsonBuilder.toJson("modelId", "" + id), TtNotice.Warning)));
                break;
        }
        if ("sx".equals(sx)) {
            return new ModelAndView("redirect:/panel/model/fields/" + id);
        }
        return new ModelAndView("redirect:/panel/model/list");
    }

    @SuperAdminTask
    @PersianName("بازسازی کل مدل ها")
    @RequestMapping(_PANEL_URL + "/rebuild")
    public ModelAndView pRebuildAllInfo(org.springframework.ui.Model model, final RedirectAttributes redirectAttributes) throws ClassNotFoundException, SQLException {
        List<Model> models = this.service.findAllBy(Restrictions.eq("status", TtModelStatus.Changed));
        TtModelRebuildResult rebuild = this.service.rebuild(models);
        switch (rebuild) {
            case Success:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.rebuild.success", TtNotice.Success)));
                break;
            case NoThingToRebuild:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.rebuild.built", TtNotice.Info)));
                break;
        }
        return new ModelAndView("redirect:/panel/model/list");
    }

    @SuperAdminTask
    @PersianName("بازسازی یک مدل")
    @RequestMapping(_PANEL_URL + "/rebuild/{id}")
    public ModelAndView pRebuildInfo(org.springframework.ui.Model model,
                                     @PathVariable("id") int id,
                                     @PathParam("sx") String sx,
                                     final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        TtModelRebuildResult rebuild = this.service.rebuild(id);
        switch (rebuild) {
            case Success:
                this.service.adapt(id);
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.model.rebuild.success", TtNotice.Success, "" + id)));
                break;
            case NoThingToRebuild:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.rebuild.built", TtNotice.Info)));
                break;
            case ModelDeleted:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.deleted", JsonBuilder.toJson("modelId", "" + id), TtNotice.Warning)));
                break;
            case ModelNotFound:
                Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.not.found", JsonBuilder.toJson("modelId", "" + id), TtNotice.Warning)));
                break;
        }
        if ("sx".equals(sx)) {
            return new ModelAndView("redirect:/panel/model/fields/" + id);
        }
        return new ModelAndView("redirect:/panel/model/list");
    }

    @SuperAdminTask
    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(org.springframework.ui.Model model) {
        List<Model> list = this.service.findAll();
        model.addAttribute("mlist", list);

        return TtTile___.p_sys_model_list.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("جزئیات مدل")
    @RequestMapping(value = _PANEL_URL + "/fields/{id}")
    public ModelAndView pFields(org.springframework.ui.Model model, @PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        Model m = this.service.findById(id);
        if (m == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.model.not.found", JsonBuilder.toJson("modelId", "" + id), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/model/list");
        }
        model.addAttribute("model", m);
        model.addAttribute("flist", this.fieldService.findAllBy(Restrictions.eq("model", m)));
        return TtTile___.p_sys_model_field_list.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("جزئیات فیلد")
    @RequestMapping(value = _PANEL_URL + "/details/{id}")
    public ModelAndView pDetails(org.springframework.ui.Model model, @PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        Field f = this.fieldService.findById(id, Field._MODEL);
        if (f == null) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.field.not.found", JsonBuilder.toJson("fieldId", "" + id), TtNotice.Warning)));
            return new ModelAndView("redirect:/panel/model/list");
        }
        model.addAttribute("field", f);
        return TtTile___.p_sys_model_field_details.___getDisModel();
    }

}
