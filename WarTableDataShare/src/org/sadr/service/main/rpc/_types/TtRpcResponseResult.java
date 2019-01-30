package org.sadr.service.main.rpc._types;

public enum TtRpcResponseResult {
    SessionId("SessionId"),
    PrivilegeFlags("PrivilegeFlags"),
    LoginCounter("LoginCounter"),
    CurrentDateTime("CurrentDateTime"),
    LastVersion("LastVersion"),
    LastVersionSetupSize("LastVersionSetupSize"),
    LastRoomID("LastRoomID"),
    Success("Success");

    private String name ;

    TtRpcResponseResult(String name) {
        this.name = name;
    }

    TtRpcResponseResult() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TtRpcResponseResult getByOrdinal(int o){
        for (TtRpcResponseResult f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
