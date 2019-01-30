package org.sadr.share.main.log._types;

public enum  TtActionType {
    Login("Login"),
    Logout("Logout"),
    Add("Add"),
    Edit("Edit"),
    Report("Report"),
    Error("Error"),
    Delete("Delete"),
    UserManagement("User Management");


    private String actionName;

    TtActionType(String actionName) {
        this.actionName = actionName;
    }

    TtActionType() {
    }

    // getter & setter

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }


    // End Gettter and setter

    public static TtActionType getByOrdinal(int o) {
        for (TtActionType f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
