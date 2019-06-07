package org.sadr.web.main._core.setting;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtFileType;
import org.sadr._core._type.TtProjectPath;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.utils.*;
import org.sadr._core.utils._type.TtPasswordType;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareService;
import org.sadr.share.main.serviceUser.ServiceUserShareConfig;
import org.sadr.share.main.serviceUser.ServiceUserShareService;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.StandaloneController;
import org.sadr.web.main._core.meta.annotation.SuperAdminTask;
import org.sadr.web.main._core.tools.listener.SessionListener;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin._type.TtGender;
import org.sadr.web.main.admin._type.TtUserIpRangeType;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.sadr.web.main.system._type.TtRegisteryValueType;
import org.sadr.web.main.system.module.ModuleService;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryService;
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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author masoud
 */
@StandaloneController
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
    private RegisteryService registeryService;

    @Autowired
    public void setRegisteryService(RegisteryService registeryService) {
        this.registeryService = registeryService;
    }

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
        moduleService.init();
        directoryService.init();

        initAdminUser();
        initLogManagerUser();
        initEncryptionKey();
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
            user.setIpRangeType(TtUserIpRangeType.All);
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
            user.setIpRangeType(TtUserIpRangeType.All);
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

    private void initEncryptionKey() {
        Registery byKey = registeryService.findByKey(TtRegisteryKey.IxportCryptKey);
        if (byKey == null) {
            byKey = new Registery();
            byKey.setKey(TtRegisteryKey.IxportCryptKey.getKey());
            byKey.setTitle(TtRegisteryKey.IxportCryptKey.getTitle());
            byKey.setValueType(TtRegisteryValueType.Normal);
            byKey.setValue(Cryptor.encrypt(CodeGenerator.password(TtPasswordType.Mix, 32)));
            this.registeryService.save(byKey);
        }

        //================================ public private for backup
        try {
            KeyPairGenerator keyPairGenerator;
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            byKey = registeryService.findByKey(TtRegisteryKey.IxportPrivateKey);
            if (byKey == null) {
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
                byKey = new Registery();
                byKey.setKey(TtRegisteryKey.IxportPrivateKey.getKey());
                byKey.setTitle(TtRegisteryKey.IxportPrivateKey.getTitle());
                byKey.setValueType(TtRegisteryValueType.Byte);
                byKey.setValueByte(pkcs8EncodedKeySpec.getEncoded());
                this.registeryService.save(byKey);
            }

            byKey = registeryService.findByKey(TtRegisteryKey.IxportPublicKey);
            if (byKey == null) {
                X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
                byKey = new Registery();
                byKey.setKey(TtRegisteryKey.IxportPublicKey.getKey());
                byKey.setTitle(TtRegisteryKey.IxportPublicKey.getTitle());
                byKey.setValueType(TtRegisteryValueType.Byte);
                byKey.setValueJson(getHexString(x509EncodedKeySpec2.getEncoded()));
                byKey.setValueByte(x509EncodedKeySpec2.getEncoded());
                this.registeryService.save(byKey);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //###########################################==========================

        byKey = registeryService.findByKey(TtRegisteryKey.BackupCryptKey);
        if (byKey == null) {
            byKey = new Registery();
            byKey.setKey(TtRegisteryKey.BackupCryptKey.getKey());
            byKey.setTitle(TtRegisteryKey.BackupCryptKey.getTitle());
            byKey.setValueType(TtRegisteryValueType.Normal);
            byKey.setValue(Cryptor.encrypt(CodeGenerator.password(TtPasswordType.Mix, 32)));
            this.registeryService.save(byKey);
        }

        //================================ public private for backup
        try {
            KeyPairGenerator keyPairGenerator;
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            byKey = registeryService.findByKey(TtRegisteryKey.BackupPrivateKey);
            if (byKey == null) {
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
                byKey = new Registery();
                byKey.setKey(TtRegisteryKey.BackupPrivateKey.getKey());
                byKey.setTitle(TtRegisteryKey.BackupPrivateKey.getTitle());
                byKey.setValueType(TtRegisteryValueType.Byte);
                byKey.setValueByte(pkcs8EncodedKeySpec.getEncoded());
                this.registeryService.save(byKey);
            }

            byKey = registeryService.findByKey(TtRegisteryKey.BackupPublicKey);
            if (byKey == null) {
                X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
                byKey = new Registery();
                byKey.setKey(TtRegisteryKey.BackupPublicKey.getKey());
                byKey.setTitle(TtRegisteryKey.BackupPublicKey.getTitle());
                byKey.setValueType(TtRegisteryValueType.Byte);
                byKey.setValueJson(getHexString(x509EncodedKeySpec2.getEncoded()));
                byKey.setValueByte(x509EncodedKeySpec2.getEncoded());
                this.registeryService.save(byKey);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    ///=////////////////////////////////////////////////////////////// SETTING
    @PersianName("پیشخوان تنظیمات")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pSetting() {
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
        ls.sort(Comparator.comparing(o -> (o[0])));
        return ls;
    }

    @SuperAdminTask
    @PersianName("پیشخوان راه انداز")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView pInit(Model model) {
        model.addAttribute("list", getInitMethods(SettingController.class.getMethods()));
        return TtTile___.p_setting_init.___getDisModel();
    }

    @SuperAdminTask
    @PersianName("راه اندازی اولیه")
    @RequestMapping(value = "/init/prime", method = RequestMethod.GET)
    public ModelAndView pInitPrime(final RedirectAttributes redirectAttributes) {
        initCorePrime(true);
        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/setting/init");
    }

    @SuperAdminTask
    @PersianName("پاکسازی اولیه")
    @RequestMapping(value = "/init/clean", method = RequestMethod.GET)
    public ModelAndView pInitClean(final RedirectAttributes redirectAttributes) {
        if (moduleService.clean()) {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.clean.success", TtNotice.Success)));
        } else {
            Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.clean.failed", TtNotice.Danger)));
        }
        return new ModelAndView("redirect:/panel/setting/init");
    }

    private String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    @SuperAdminTask
    @PersianName("ایجاد کلیدهای رمزنگاری")
    @RequestMapping(value = "/init/crypt/keys", method = RequestMethod.GET)
    public ModelAndView pInitEncryptionKey(final RedirectAttributes redirectAttributes) {

        initEncryptionKey();

        Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.init.crypt.success", TtNotice.Success)));
        return new ModelAndView("redirect:/panel/setting/init");
    }


}
