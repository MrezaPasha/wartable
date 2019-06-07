package org.sadr.web.main._core.propertor._type;


import org.sadr._core.utils.SpringMessager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author masoud
 */
public enum TtPropertorInWebList {

    ///==================== sys config
    SigninNotice("N.propertor.startup.signin.notice", "خوش آمدید", TtPropertorType.StringBig, TtPropertorInWebSection.Startup, true), ///

    ///==================== user deactivate config
    UserDeactivateOn("N.propertor.userAttempt.deactivate.on", true, TtPropertorType.OnOff, TtPropertorInWebSection.UserDeactivate, false), ///
    UserDeactivateTimeout("N.propertor.userAttempt.deactivate.timeout", 10, TtPropertorType.Integer, TtPropertorInWebSection.UserDeactivate, false), ///
    ///==================== user session
    UserSessionTimeOut("N.propertor.userAttempt.session.timeout", 1800, TtPropertorType.Integer, TtPropertorInWebSection.UserSession, false), ///
    ///==================== userAttempt config
    UserAttemptOn("N.propertor.userAttempt.state.on", true, TtPropertorType.OnOff, TtPropertorInWebSection.UserAttempt, false), ///
    UserAttemptSigninAttemptMax("N.propertor.userAttempt.signin.attempt.max", 2, TtPropertorType.Integer, TtPropertorInWebSection.UserAttempt, false), ///
    UserAttemptSigninRageTime("N.propertor.userAttempt.signin.rangeTime", 60, TtPropertorType.Integer, TtPropertorInWebSection.UserAttempt, false), ///
    UserAttemptSigninBlockMax("N.propertor.userAttempt.signin.block.max", 5, TtPropertorType.Integer, TtPropertorInWebSection.UserAttempt, false), ///
    UserAttemptSigninBlockTime("N.propertor.userAttempt.signin.blockTime", 180, TtPropertorType.Integer, TtPropertorInWebSection.UserAttempt, false), ///
    UserAttemptSigninBlockDuring("N.propertor.userAttempt.signin.block.during", 120, TtPropertorType.Integer, TtPropertorInWebSection.UserAttempt, false), ///
    ///==================== user password policy
    UserPasswordPeriodToChange("N.propertor.user.password.period.to.change", 90, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordLastPassNotToBeSelected("N.propertor.user.password.last.pass.not.to.be.selected", 2, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordMinLength("N.propertor.user.password.min.length", 4, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordMaxLength("N.propertor.user.password.max.length", 60, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordAtLeastBigCharacter("N.propertor.user.password.at.least.big.char", false, TtPropertorType.OnOff, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordCountBigCharacter("N.propertor.user.password.count.big.char", 1, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordAtLeastSmallCharacter("N.propertor.user.password.at.least.small.char", true, TtPropertorType.OnOff, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordCountSmallCharacter("N.propertor.user.password.count.small.char", 1, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordAtLeastNumber("N.propertor.user.password.at.least.number", false, TtPropertorType.OnOff, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordCountNumber("N.propertor.user.password.count.number", 1, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordAtLeastSpecific("N.propertor.user.password.at.least.specific", false, TtPropertorType.OnOff, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    UserPasswordCountSpecific("N.propertor.user.password.count.specific", 1, TtPropertorType.Integer, TtPropertorInWebSection.UserPasswordPolicy, false), ///
    ///==================== load threshold
    LoadThresholdMaxConcurrentUser("N.propertor.load.user.max.concurrent.user", 100, TtPropertorType.Integer, TtPropertorInWebSection.LoadConcurrentUser, false), ///
    LoadThresholdMaxUploadSize("N.propertor.load.upload.max.size", 10, TtPropertorType.Integer, TtPropertorInWebSection.LoadUpload, false), ///
    ///==================== service
    IrrorAlertPublicOn("N.propertor.irror.public.on", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertPublic, false), ///
    //
    IrrorAlertEmailOn("N.propertor.irror.email.on", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailTrace("N.propertor.irror.email.trace", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailDebug("N.propertor.irror.email.debug", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailInfo("N.propertor.irror.email.info", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailWarn("N.propertor.irror.email.warn", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailError("N.propertor.irror.email.error", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    IrrorAlertEmailFatal("N.propertor.irror.email.fatal", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmail, false), ///
    //
    IrrorAlertNotifyOn("N.propertor.irror.notify.on", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyTrace("N.propertor.irror.notify.trace", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyDebug("N.propertor.irror.notify.debug", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyInfo("N.propertor.irror.notify.info", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyWarn("N.propertor.irror.notify.warn", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyError("N.propertor.irror.notify.error", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    IrrorAlertNotifyFatal("N.propertor.irror.notify.fatal", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertNotify, false), ///
    //
    IrrorAlertEmailBoxAddress("N.propertor.irror.email.box.address", "support@sadr.com", TtPropertorType.String, TtPropertorInWebSection.IrrorAlertEmailBox, false), ///
    IrrorAlertEmailBoxPassword("N.propertor.irror.email.box.password", "password", TtPropertorType.Password, TtPropertorInWebSection.IrrorAlertEmailBox, false), ///
    IrrorAlertEmailBoxHost("N.propertor.irror.email.box.host", "host", TtPropertorType.String, TtPropertorInWebSection.IrrorAlertEmailBox, false), ///
    IrrorAlertEmailBoxPort("N.propertor.irror.email.box.port", 25, TtPropertorType.Integer, TtPropertorInWebSection.IrrorAlertEmailBox, false), ///
    IrrorAlertEmailBoxTlsOn("N.propertor.irror.email.box.tls.on", false, TtPropertorType.OnOff, TtPropertorInWebSection.IrrorAlertEmailBox, false), ///
    //
//    IrrorAlertEmailReceiverAddresses("N.propertor.irror.email.receiver.addresses", "reciever01@sadr.com,reciever02@sadr.com", TtPropertorType.String, TtPropertorInWebSection.IrrorAlertEmailReceiver, false), ///

    ///==================== service
    ServiceUploadPath_Base("N.propertor.service.upload.path.base", "D:/warTable/ftp", TtPropertorType.String, TtPropertorInWebSection.ServiceUpload, false), ///
    ServiceUploadPath_Object("N.propertor.service.upload.path.object", "D:/warTable/ftp/objects", TtPropertorType.String, TtPropertorInWebSection.ServiceUpload, false), ///
    ServiceUploadPath_Map("N.propertor.service.upload.path.map", "D:/warTable/ftp/map", TtPropertorType.String, TtPropertorInWebSection.ServiceUpload, false), ///
    ServiceUploadPath_Vector("N.propertor.service.upload.path.vector", "D:/warTable/ftp/vector", TtPropertorType.String, TtPropertorInWebSection.ServiceUpload, false), ///
    ServiceUserDefaultModelId("N.propertor.service.user.default.model.id", 1, TtPropertorType.Integer, TtPropertorInWebSection.ServiceUser, false), ///

    ;

    public static TtPropertorInWebList getByOrdinal(int ordinal) {
        for (TtPropertorInWebList value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }

    private final String spMessage;
    private final Object defaultValue;
    private final TtPropertorType type;
    private final TtPropertorInWebSection section;
    private final boolean isNeedRedeploy;

    TtPropertorInWebList(String m, Object dv, TtPropertorType tpt, TtPropertorInWebSection tps, boolean inr) {
        spMessage = m;
        defaultValue = dv;
        type = tpt;
        section = tps;
        isNeedRedeploy = inr;
    }


    public Object getDefaultValue() {
        return defaultValue;
    }


    public Object getDefaultValueInForm() {
        if (defaultValue.getClass().getSimpleName().startsWith("Tt")) {
            Method method;
            try {
                method = defaultValue.getClass().getMethod("ordinal");
                return method.invoke(defaultValue);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public String getDefaultValueStr() {
        if (defaultValue.getClass().getModifiers() == 16401) {
            Method method;
            try {
                method = defaultValue.getClass().getMethod("ordinal");
                return String.valueOf(method.invoke(defaultValue));

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(defaultValue);
    }

    public TtPropertorType getType() {
        return type;
    }

    public TtPropertorInWebSection getSection() {
        return section;
    }

    public int getId() {
        return ordinal();
    }

    public HashMap<String, String> getTtValues() {
        if (type == TtPropertorType.TtVariable && defaultValue != null && defaultValue.getClass().getSimpleName().startsWith("Tt")) {
            Method method;
            HashMap<String, String> map = new HashMap<>();
            String id;
            String title;
            try {
                method = defaultValue.getClass().getMethod("values");
                Object[] invoke = (Object[]) method.invoke(defaultValue);
                for (Object o : invoke) {
                    method = defaultValue.getClass().getMethod("ordinal");
                    id = String.valueOf(method.invoke(o));
                    method = defaultValue.getClass().getMethod("getTitle");
                    title = (String) method.invoke(o);
                    map.put(id, title);
                }
                return map;
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public String getKey() {
        return spMessage;
    }

    public boolean isIsNeedRedeploy() {
        return isNeedRedeploy;
    }
}
