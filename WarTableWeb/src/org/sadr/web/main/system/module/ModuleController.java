package org.sadr.web.main.system.module;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main.system._type.TtTaskActionStatus;
import org.sadr.web.main.system._type.TtTaskActionSubType;
import org.sadr.web.main.system.task.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author masoud
 * @version 1.95.03.31
 */
@PersianName("مدیریت ماژول")
@RestController
public class ModuleController extends GenericControllerImpl<Module, ModuleService> {
    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/module";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;

    ////////////////////
    public ModuleController() {
    }

    @PersianName("لیست ماژول های تایید دو سطحی")
    @RequestMapping(value = _PANEL_URL + "/list/confirm")
    public ModelAndView pListForTwoLevelConfirm(Model model,
                                                final RedirectAttributes redirectAttributes
    ) {
        List<Module> modules = this.service.findAll(Module._TASKS);
        List<Module> newList = new ArrayList<>();
        boolean isExist;
        for (Module m : modules) {
            isExist = false;
            for (Task t : m.getTasks()) {
                if (t.getIsSuperAdmin() == false && t.getIsAjax() == false) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                newList.add(m);
            }
        }
        model.addAttribute("mlist", newList);
        return TtTile___.p_sys_module_listConfirm.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }


}
