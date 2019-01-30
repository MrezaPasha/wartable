package org.sadr.share.main.log._types;

public enum TtSubType {

    SuccessLogin("Success Login "),
    UnsuccessLogin("Unsuccess Login "),
    SubsystemStart("Subsystem Start"),
    SubsystemStop("Subsystem Stop"),
    InvalidToken("Invalid Token "),
    BlockUser("Block User"),
    ChangePassword("| Change Password "),
    UserLoginlogout("User Login-logout"),
    Report("Report"),
    PrintReport("Print Report"),
    ExportFile("Export File"),
    DBConnectionError("DB Connection Error"),
    InputVariableError("Input Variable Error"),
    DbMatchError("DB Match Error"),
    TransactionCommitError("Transaction Commit Error"),
    AccessDeniedError("Access Denied Error"),
    SessionError("Session Error "),
    SystemError("System Error "),
    EncryptionModuleError("Encryption Module Error"),
    SSLError("SSL Error"),
    TLSError("TLS Error"),
    DeleteFromDB("Delete From DB"),
    DeleteFromFile("Delete From File"),
    DeleteAttachment("Delete Attachment"),
    AddNewUser("Add New User"),
    deleteUser("delete User"),
    ChangeUserProfile("Change User Profile"),
    ChangeUserAccess("Change User Access"),
    ChangeGroup("Change Group"),
    AddGroup("Add Group"),
    EditGroup("Edit Group "),
    DeleteGroup("Delete Group"),
    ChangeGroupAccess("Change Group Access"),
    AddToken("Add  Token"),
    DeleteToken("Delete Token"),
    TokenAssignment("Token Assignment");

    private String subTypeName;

    TtSubType(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    TtSubType() {
    }

    // getter and setter start

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }


    // getter and setter end

    public static TtSubType getByOrdinal(int o) {
        for (TtSubType f : values()) {
            if (f.ordinal() == o) {
                return f;
            }
        }
        return null;
    }
}
