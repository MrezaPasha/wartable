package org.sadr.web.main._core.propertor._type;

import org.sadr._core.utils.SpringMessager;

/**
 * @author masoud
 */
public enum TtPropertorInWebSection {
    Startup("N.propertor.generic.startup", TtPropertorInWebTab.Generic),
    UserAttempt("N.propertor.user.userAttempt", TtPropertorInWebTab.User),
    UserPasswordPolicy("N.propertor.user.passwordPolicy", TtPropertorInWebTab.User),
    UserDeactivate("N.propertor.user.deactivate", TtPropertorInWebTab.User),
    UserSession("N.propertor.user.session", TtPropertorInWebTab.User),
    LoadConcurrentUser("N.propertor.load.concurrent.user", TtPropertorInWebTab.LoadThreshold),
    LoadUpload("N.propertor.load.upload", TtPropertorInWebTab.LoadThreshold),
    ServiceUpload("N.propertor.service.upload", TtPropertorInWebTab.Service),
    ServiceUser("N.propertor.service.user", TtPropertorInWebTab.Service),
//    UserCreateDefault("N.propertor.user.create.default", TtPropertorInWebTab.User),

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
