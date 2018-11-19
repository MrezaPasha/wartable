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
