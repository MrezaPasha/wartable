package org.sadr.web.main._core.propertor;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.LogManagerTask;
import org.sadr.web.main._core.meta.annotation.MenuIdentity;
import org.sadr.web.main._core.meta.annotation.StandaloneController;
import org.sadr.web.main._core.propertor._type.TtPropertorInBackupList;
import org.sadr.web.main._core.propertor._type.TtPropertorInLogList;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.utils.Ison;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtIsonStatus;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;


/**
 * @author masoud
 */
@StandaloneController
@RestController
@PersianName("پیکربندی ها")
@RequestMapping(value = "/panel/propertor")
public class PropertorController {
    ///=//////////////////////////////////////////////////////////////
    public PropertorController() {
    }


    ///=////////////////////////////////////////////////////////////// WEB PROPERTOR
    @PersianName("وب: پیشخوان پیکربندی")
    @RequestMapping("/web")
    public ModelAndView pPropertorInWeb(Model model) {

        PropertorInWeb.getInstance().load();

        List<PropertorBag> glist = new ArrayList<>();
        List<PropertorBag> ulist = new ArrayList<>();
        List<PropertorBag> llist = new ArrayList<>();
        List<PropertorBag> slist = new ArrayList<>();

        String pn = "";
        for (TtPropertorInWebList value : TtPropertorInWebList.values()) {
            switch (value.getSection().getTab()) {
                case Generic:
                    glist.add(new PropertorBag(value, PropertorInWeb.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case User:
                    ulist.add(new PropertorBag(value, PropertorInWeb.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case LoadThreshold:
                    llist.add(new PropertorBag(value, PropertorInWeb.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case Service:
                    slist.add(new PropertorBag(value, PropertorInWeb.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
            }
            pn = value.getSection().toString();
        }
        model.addAttribute("glist", glist);
        model.addAttribute("ulist", ulist);
        model.addAttribute("llist", llist);
        model.addAttribute("slist", slist);

        return TtTile___.p_propertor_web.___getDisModel();
    }

    @PersianName("وب: بازنشانی به تنظیمات اولیه")
    @RequestMapping("/web/reset")
    public ModelAndView pPropertorInWebReset(final RedirectAttributes redirectAttributes) throws Exception {
        PropertorInWeb.getInstance().load();
        PropertorInWeb.getInstance().resetProperties();
        PropertorInWeb.getInstance().store();
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.corePropertor.reset.success", TtNotice.Success)));
        return Referer.redirect("/panel/propertor/web");

    }

    @PersianName("وب:  تنظیم پارامتر پیکربندی")
    @RequestMapping(value = "/web/set", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> pPropertorInWebSetValue(@FormParam("type") String type,
                                                   @FormParam("id") int id,
                                                   @FormParam("value") String value) {
        boolean res = false;
        switch (type) {
            case "OnOff":
                if ("true".equals(value)) {
                    res = PropertorInWeb.getInstance().setOn(TtPropertorInWebList.getByOrdinal(id));
                } else {
                    res = PropertorInWeb.getInstance().setOff(TtPropertorInWebList.getByOrdinal(id));
                }
                break;
            case "Variable":
            case "Integer":
            case "String":
            case "StringBig":
                res = PropertorInWeb.getInstance().setProperty(TtPropertorInWebList.getByOrdinal(id), value);
                break;
            case "TtVariable":
                res = PropertorInWeb.getInstance().setProperty(TtPropertorInWebList.getByOrdinal(id), value);
                break;
        }
        if (res) {
            PropertorInWeb.getInstance().store();
        }

        return Ison.init()
                .setStatus(TtIsonStatus.Ok)
                .setProperty("isOk", res)
                .toResponse();
    }

    ///=////////////////////////////////////////////////////////////// LOG PROPERTOR

    @LogManagerTask
    @MenuIdentity(TtTile___.p_propertor_log)
    @PersianName("کنترل امنیت: پیشخوان پیکربندی")
    @RequestMapping("/log")
    public ModelAndView pPropertorInLog(Model model) throws Exception {

        PropertorInLog.getInstance().load();

        List<PropertorBag> slist = new ArrayList<>();
        List<PropertorBag> clist = new ArrayList<>();

        String pn = "";
        for (TtPropertorInLogList value : TtPropertorInLogList.values()) {
            switch (value.getSection().getTab()) {
                case System:
                    slist.add(new PropertorBag(value, PropertorInLog.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case Connection:
                    clist.add(new PropertorBag(value, PropertorInLog.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;

            }
            pn = value.getSection().toString();
        }
        model.addAttribute("slist", slist);
        model.addAttribute("clist", clist);


        return TtTile___.p_propertor_log.___getDisModel();
    }

    @LogManagerTask
    @PersianName("کنترل امنیت: بازنشانی به تنظیمات اولیه")
    @RequestMapping("/log/reset")
    public ModelAndView pPropertorInLogReset(final RedirectAttributes redirectAttributes) throws Exception {
        PropertorInLog.getInstance().load();
        PropertorInLog.getInstance().resetProperties();
        PropertorInLog.getInstance().store();
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.corePropertor.reset.success", TtNotice.Success)));
        return Referer.redirect("/panel/propertor/log");

    }

    @LogManagerTask
    @PersianName("کنترل امنیت:  تنظیم پارامتر پیکربندی")
    @RequestMapping(value = "/log/set", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> pPropertorInLogSetValue(@FormParam("type") String type,
                                                   @FormParam("id") int id,
                                                   @FormParam("value") String value) {
        boolean res = false;
        switch (type) {
            case "OnOff":
                if ("true".equals(value)) {
                    res = PropertorInLog.getInstance().setOn(TtPropertorInLogList.getByOrdinal(id));
                } else {
                    res = PropertorInLog.getInstance().setOff(TtPropertorInLogList.getByOrdinal(id));
                }
                break;
            case "Variable":
            case "Integer":
            case "String":
            case "StringBig":
                res = PropertorInLog.getInstance().setProperty(TtPropertorInLogList.getByOrdinal(id), value);
                break;
            case "TtVariable":
                res = PropertorInLog.getInstance().setProperty(TtPropertorInLogList.getByOrdinal(id), value);
                break;
        }
        if (res) {
            PropertorInLog.getInstance().store();
        }

        return Ison.init()
                .setStatus(TtIsonStatus.Ok)
                .setProperty("isOk", res)
                .toResponse();
    }

    ///=////////////////////////////////////////////////////////////// BACKUP PROPERTOR

    @MenuIdentity(TtTile___.p_propertor_backup)
    @PersianName("پشتیبان گیری: پیشخوان پیکربندی")
    @RequestMapping("/backup")
    public ModelAndView pPropertorInBackup(Model model) {

        PropertorInBackup.getInstance().load();

        List<PropertorBag> blist = new ArrayList<>();
        List<PropertorBag> rlist = new ArrayList<>();
        List<PropertorBag> ulist = new ArrayList<>();

        String pn = "";
        for (TtPropertorInBackupList value : TtPropertorInBackupList.values()) {
            switch (value.getSection().getTab()) {
                case Backup:
                    blist.add(new PropertorBag(value, PropertorInBackup.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case Restore:
                    rlist.add(new PropertorBag(value, PropertorInBackup.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;
                case Upload:
                    ulist.add(new PropertorBag(value, PropertorInBackup.getInstance().getProperty(value), !value.getSection().toString().equals(pn)));
                    break;

            }
            pn = value.getSection().toString();
        }
        model.addAttribute("blist", blist);
        model.addAttribute("rlist", rlist);
        model.addAttribute("ulist", ulist);


        return TtTile___.p_propertor_backup.___getDisModel();
    }

    @PersianName("پشتیبان گیری: بازنشانی تنظیمات اولیه")
    @RequestMapping("/backup/reset")
    public ModelAndView pPropertorInBackupReset(final RedirectAttributes redirectAttributes) throws Exception {
        PropertorInBackup.getInstance().load();
        PropertorInBackup.getInstance().resetProperties();
        PropertorInBackup.getInstance().store();
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.corePropertor.reset.success", TtNotice.Success)));
        return Referer.redirect("/panel/propertor/backup");

    }

    @PersianName("پشتیبان گیری:  تنظیم پارامتر پیکربندی")
    @RequestMapping(value = "/backup/set", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> pPropertorInBackupSetValue(@FormParam("type") String type,
                                                      @FormParam("id") int id,
                                                      @FormParam("value") String value) {
        boolean res = false;
        switch (type) {
            case "OnOff":
                if ("true".equals(value)) {
                    res = PropertorInBackup.getInstance().setOn(TtPropertorInBackupList.getByOrdinal(id));
                } else {
                    res = PropertorInBackup.getInstance().setOff(TtPropertorInBackupList.getByOrdinal(id));
                }
                break;
            case "Variable":
            case "Integer":
            case "String":
            case "StringBig":
                res = PropertorInBackup.getInstance().setProperty(TtPropertorInBackupList.getByOrdinal(id), value);
                break;
            case "TtVariable":
                res = PropertorInBackup.getInstance().setProperty(TtPropertorInBackupList.getByOrdinal(id), value);
                break;
        }
        if (res) {
            PropertorInBackup.getInstance().store();
        }

        return Ison.init()
                .setStatus(TtIsonStatus.Ok)
                .setProperty("isOk", res)
                .toResponse();
    }

    ///=////////////////////////////////////////////////////////////// INIT PROPERTOR
}
