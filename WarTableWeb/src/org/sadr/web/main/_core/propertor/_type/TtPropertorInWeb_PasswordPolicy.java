package org.sadr.web.main._core.propertor._type;

/**
 * @author masoud
 */
public enum TtPropertorInWeb_PasswordPolicy {

    AtLeast1Char("localTest"), // start on ide (netbeans)
    ServerReal("serverReal"), // start on onlin resl server
    ;

    private final String title;

    TtPropertorInWeb_PasswordPolicy(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }
}
