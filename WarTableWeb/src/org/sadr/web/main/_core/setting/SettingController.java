package org.sadr.web.main._core.setting;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtFileType;
import org.sadr._core._type.TtProjectPath;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.Digester;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin._type.TtGender;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.file.FileService;
import org.sadr.web.main.system.log.daily.DailyLogService;
import org.sadr.web.main.system.model.ModelService;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.registery.RegisteryService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("تنظیمات سامانه")
@RequestMapping(value = "/panel/setting")
public class SettingController {

    ///=//////////////////////////////////////////////////////////////
    public SettingController() {
    }

    private ModuleService moduleService;
    private DirectoryService directoryService;
    private UserService userService;

    @Autowired
    private ListableBeanFactory beanFactory;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Autowired
    public void setDirectoryService(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }
    ///=//////////////////////////////////////////////////////////////

    public void initCorePrime(boolean force) {
        if (!force && moduleService.count() > 0) {
            OutLog.pl();
            return;
        }
//        this.modelService.adaptAndRebuild(Module.class.getName());
//        this.modelService.adaptAndRebuild(Directory.class.getName());
//        this.modelService.adaptAndRebuild(File.class.getName());
//        this.modelService.adaptAndRebuild(User.class.getName());

        moduleService.init();
        directoryService.init();

        initAdminUser();
        initLogManagerUser();
    }

    private void initAdminUser() {
        User user = this.userService.findBy(
                Restrictions.eq(User.IS_SUPER_ADMIN, true)
        );
        if (user == null) {
            user = new User();
            user.setFirstName("مدیر");
            user.setLastName("سامانه");
            user.setUsername("admin");
            user.setPassword(Digester.digestSHA1("admin"));
        }
        user.setLevel(TtUserLevel.Administrator);
        user.setGender(TtGender.Male);
        user.setStatus(TtUserStatus.Active);
        user.setIsBlocked(false);
        user.setIsSuperAdmin(true);
        user.setIsLogManager(true);
        if (user.getIdi() == 0) {
            this.userService.save(user);
        } else {
            this.userService.update(user);
        }
        OutLog.pl("Admin User Initialized.");
    }

    private void initLogManagerUser() {
        User user = this.userService.findBy(
                Restrictions.and(
                        Restrictions.eq(User.IS_LOG_MANAGER, true),
                        Restrictions.eq(User.IS_SUPER_ADMIN, false)
                )
        );
        if (user == null) {
            user = new User();
            user.setPassword(Digester.digestSHA1("security"));
        }
        user.setLevel(TtUserLevel.Master);
        user.setGender(TtGender.Male);
        user.setFirstName("مدیر");
        user.setLastName("رویدادنگار");
        user.setUsername("security");
        user.setStatus(TtUserStatus.Active);
        user.setIsBlocked(false);
        user.setIsLogManager(true);
        user.setIsSuperAdmin(false);
        if (user.getIdi() == 0) {
            this.userService.save(user);
        } else {
            this.userService.update(user);
        }
        OutLog.pl("LogManager User Initialized.");

    }

    ///=////////////////////////////////////////////////////////////// SETTING
    @PersianName("پیشخوان تنظیمات")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pSetting(final RedirectAttributes redirectAttributes) {
//        return new ModelAndView("p.setting");
        return TtTile___.p_setting_index.___getDisModel();
    }

    ///=////////////////////////////////////////////////////////////// INITIALIZERS
    private List<String[]> getInitMethods(Method[] ms) {
        RequestMapping an;
        PersianName pn;
        List<String[]> ls = new ArrayList<>();
        for (Method m : ms) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                an = m.getAnnotation(RequestMapping.class);
                if ((an.method().length == 0 || an.method()[0] == null || an.method()[0] == RequestMethod.GET)
                        && an.value() != null && an.value().length > 0 && !an.value()[0].isEmpty()
                        && an.value()[0].contains("/init")) {
                    if (m.isAnnotationPresent(PersianName.class)) {
                        pn = m.getAnnotation(PersianName.class);
                        ls.add(new String[]{pn.value(), an.value()[0]});
                    }
                }
            }
        }
        ls.sort((String[] o1, String[] o2) -> {
            return (o1[0]).compareTo(o2[0]);
        });
        return ls;
    }

    @SuperAdminTask
    @PersianName("پیشخوان راه انداز")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView pInit(Model model, final RedirectAttributes redirectAttributes) {
        model.addAttribute("list", getInitMethods(SettingController.class.getMethods()));
        return TtTile___.p_setting_init.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("راه اندازی اولیه")
    @RequestMapping(value = "/init/prime", method = RequestMethod.GET)
    public ModelAndView pInitPrime(final RedirectAttributes redirectAttributes) throws InvocationTargetException, ClassNotFoundException, SQLException {
        initCorePrime(true);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/setting/init");
    }

    @SuperAdminTask
    @PersianName("پاکسازی اولیه")
    @RequestMapping(value = "/init/clean", method = RequestMethod.GET)
    public ModelAndView pInitClean(final RedirectAttributes redirectAttributes) throws InvocationTargetException, ClassNotFoundException, SQLException {
        if (moduleService.clean()) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.clean.success", TtNotice.Success)));
        } else {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.clean.failed", TtNotice.Danger)));
        }
        return new ModelAndView("redirect:/panel/setting/init");
    }


}
