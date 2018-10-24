package org.sadr.web.main.system._type;

/**
 * @author masoud
 */
public enum TtTaskActionSubType {

    Success_Login,
    Unsuccess_Login,
    Subsystem_Start,
    Subsystem_Stop,
    Invalid_Token,
    Block_User,
    Change_Password,
    User_LoginLogout, New_Data,
    Edit_Data,
    Change_SystemConfig,
    Change_SecurityConfig,
    Take_Report,
    Print_Report,
    Export_File,
    DB_Connection_Error,
    Input_Variable_Error,
    DB_Match_Error,
    Transaction_Commit_Error,
    Access_Denied_Error,
    Session_Error,
    System_Error,
    Encryption_Module_Error,
    SSL_Error,
    TSL_Error,
    Delete_From_DB,
    Delete_From_File,
    Delete_Attachment,
    Add_New_User,
    delete_User,
    Change_User_Profile,
    Change_User_Access,
    Change_Group,
    Add_Group,
    Edit_Group,
    Delete_Group,
    Change_Group_Access,
    Add_Token,
    Delete_Token,
    Token_Assignment;


    public static TtTaskActionSubType getValue(String stringValue) {
        if (stringValue != null) {
            for (TtTaskActionSubType v : values()) {
                if (stringValue.equals(v.toString())) {
                    return v;
                }
            }
        }
        return null;
    }

}
