package org.sadr.share.main.accessCategoury._types;


public enum TtAccessCategoryType {

    BTN_ROOMS(),
    BTN_USERS(),
    BTN_INFO(),
    BTN_LEAPMOTION(),
    BTN_COMBINATION(),
    BTN_SESSIONS(),
    BTN_LAYERS(),
    BTN_DRAWING(),
    BTN_OBJECTS(),
    BTN_SESSION_TIME(),
    BTN_LOGMANAGER(),
    BTN_SNAPSHOT(),
    BTN_POSITIONS(),
    BTN_IMAGEONMAP(),
    BTN_TEXTCHAT(),
    BTN_NOTICES(),
    BTN_IMP_EXP(),
    BTN_INVITE(),
    BTN_SETTINGS(),
    BTN_VKEYBOARD(),
    BTN_FLOATING_CAMERA(),
    BTN_SAVE_MAP(),
    BTN_DISTANCE(),
    BTN_MAP_REQUEST(),
    BTN_LOGOUTROOM(),
    BTN_SAVE_LAYER(),
    BTN_VR(),
    BTN_MEDIA();

    public static TtAccessCategoryType getByOrdinal(int o){
        for (TtAccessCategoryType f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
