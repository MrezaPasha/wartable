package org.sadr.service.main.nonRpc.publish._types;

public enum TtState {
    SESS_UPDATE_POLLS(0,0),
    SESS_UPDATE_ROOMS(1,0),
    SESS_UPDATE_ROOMUSERS(2,0),
    SESS_UPDATE_POLLRESULT(3,0),
    SESS_UPDATE_NEWMAPSESSION(4,0),
    SESS_UPDATE_NEWPRIVATEMSG(5,0),
    SESS_UPDATE_CAM(6,0),
    SESS_UPDATE_ROLLCHANGE(7,0),
    SESS_UPDATE_ROOMCHANGE(8,0),
    SESS_UPDATE_ENDMAPSESSION(9,0),
    SESS_UPDATE_MAPOBJECTS(10,0),
    SESS_UPDATE_POLL_CLOSED(11,0),
    SESS_UPDATE_OPACITYCHANGE(12,0),
    SESS_UPDATE_BOARD_TEXT(13,0),
    SESS_UPDATE_NEWPUBLICMSG(14,0),
    SESS_UPDATE_SNAPSHOTCHANGE(15,0),
    SESS_UPDATE_ROOMLOGCHANGE(16,0),
    SESS_UPDATE_VECTORCHANGE(17,0),
    SESS_UPDATE_SVGCHANGE(18,0),
    SESS_UPDATE_REQUESTPRIVATE(19,0),
    SESS_UPDATE_ACCEPTPRIVATE(20,0),
    SESS_UPDATE_REJECTPRIVATE(21,0),
    SESS_UPDATE_STOPPRIVATE(22,0),
    SESS__UPDATE_START_MAPSESSIONTIME(23,0),
    SESS__UPDATE_STOP_MAPSESSIONTIME(24,0),
    SESS_UPDATE_POSITIONCHANGE(25,0),
    SESS_UPDATE_UPDATEUSERTOOLS(26,0),
    SESS_UPDATE_UPDATENOTICES(27,0),
    SESS_UPDATE_NEWMAPINVITATION(28,0),
    SESS_UPDATE_GETUSERCAMERA(29,0),
    SESS_UPDATE_MAP_LIST(30,0),
    SESS_UPDATE_ARCHEIVE(31,0),
    SESS_UPDATE_LAYERCHANGE(32,0),
    SESS_UPDATE_MAP_ENTITY(33,0),
    SESS_UPDATE_WEATHER(34,0),
    SESS_UPDATE_SESSIONLIST(35,0),
    SESS_ERROR_KICK_USER(36,0),
    SESS_ERROR_DOUBLE_LOGIN(37,0),
    SESS_ERROR_SESSION_EXPIRED(38,0),
    SESS_UPDATE_OBJECT_LIST(39,0),
    SESS_UPDATE_PRIVATE_VOICECHAT_LIST(40,0);


    int index;
    int value;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    TtState(int i, int i1) {
    }

    public static TtState getByOrdinal(int o){
        for (TtState f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
