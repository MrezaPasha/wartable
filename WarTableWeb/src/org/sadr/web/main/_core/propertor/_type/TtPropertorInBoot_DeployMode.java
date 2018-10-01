package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInBoot_DeployMode {

    LocalTest("localTest"), // start on ide (netbeans)
    ServerReal("serverReal"), // start on onlin resl server
    ;

    private final String title;

    TtPropertorInBoot_DeployMode(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }
}
