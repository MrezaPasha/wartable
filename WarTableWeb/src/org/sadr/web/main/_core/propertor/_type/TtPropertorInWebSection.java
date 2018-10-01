package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInWebSection {
    Startup("N.propertor.generic.startup", TtPropertorInWebTab.Generic),
    UserAttempt("N.propertor.User.userAttempt", TtPropertorInWebTab.User),
    UserPasswordPolicy("N.propertor.User.passwordPolicy", TtPropertorInWebTab.User),
    UserDeactivate("N.propertor.User.deactivate", TtPropertorInWebTab.User),
    UserSession("N.propertor.User.session", TtPropertorInWebTab.User),
//    UserCreateDefault("N.propertor.User.create.default", TtPropertorInWebTab.User),

    ;

    private final String spMessage;
    private final TtPropertorInWebTab tab;

    TtPropertorInWebSection(String m, TtPropertorInWebTab p) {
        spMessage = m;
        tab = p;
    }

    public String getMessage() {
        return SpringMessager.get(spMessage);
    }

    public TtPropertorInWebTab getTab() {
        return tab;
    }


}
