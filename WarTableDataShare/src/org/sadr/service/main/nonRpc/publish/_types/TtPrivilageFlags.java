package org.sadr.service.main.nonRpc.publish._types;

public enum  TtPrivilageFlags {
    PERMISSION_LOGIN(1,"PERMISSION_LOGIN",0),
    PERMISSION_ADDROOM(1,"PERMISSION_ADDROOM",1),
    PERMISSION_DELROOM(0,"PERMISSION_DELROOM",2),
    PERMISSION_ADDUSER(0,"PERMISSION_ADDUSER",3),
    PERMISSION_DELUSER(0,"PERMISSION_DELUSER",4),
    PERMISSION_BANUSER(0,"PERMISSION_BANUSER",5),
    PERMISSION_ADDPOLL(0,"PERMISSION_ADDPOLL",6),
    PERMISSION_RENROOM(0,"PERMISSION_RENROOM",7),
    PERMISSION_ADDUSERTOROOM(0,"PERMISSION_ADDUSERTOROOM",8),
    PERMISSION_MAP_OPERATIONS(0,"PERMISSION_MAP_OPERATIONS",9),
    PERMISSION_MODIFYROLE(0,"PERMISSION_MODIFYROLE",10),
    PERMISSION_CLOSEPOLL(0,"PERMISSION_CLOSEPOLL",11),
    PERMISSION_CANCELPOLL(0,"PERMISSION_CANCELPOLL",12),
    PERMISSION_MODIFY_PRIVILEGE(0,"PERMISSION_MODIFY_PRIVILEGE",13),
    PERMISSION_LIST_ALLUSERS(0,"PERMISSION_LIST_ALLUSERS",14),
    PERMISSION_CHANGE_PASSWORD(0,"PERMISSION_CHANGE_PASSWORD",15),
    PERMISSION_ADD_ROOM_MEMBER(0,"PERMISSION_ADD_ROOM_MEMBER",16),
    PERMISSION_ADD_ROOMROLE_PERM(0,"PERMISSION_ADD_ROOMROLE_PERM",17),
    PERMISSION_UPLOAD_OBJECT(0,"PERMISSION_UPLOAD_OBJECT",18),
    PERMISSION_UPLOAD_MAP(0,"PERMISSION_UPLOAD_MAP",19),
    PERMISSION_MODIFY_SESSION(0,"PERMISSION_MODIFY_SESSION",20);

    private int defaultValue;
    private String name;
    private int position;

    TtPrivilageFlags(int defaultValue, String name, int position) {
        this.defaultValue = defaultValue;
        this.name = name;
        this.position = position;
    }

    TtPrivilageFlags() {
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static TtPrivilageFlags getByOrdinal(int o){
        for (TtPrivilageFlags f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
