package org.sadr.share.main._types;

public enum TtRpcFunction {
    IsTerminalsPirs("Is_Terminals_pairs"),
    SaveDelivery("Save_Delivery"),
    PersistNews("Persist_News"),
    Login("Login_Account"),
    Logout("Logout Account"),
    ChangePassword("change password of user"),
    ListRooms("list the current rooms"),
    JoinRoom("join member to room"),
    LeaveRoom("disconnect user from online room"),
    ListRoomUsers("lisyt all online and ofline users"),
    ListMapSessions("list all meetings"),
    NewMapSession("create new map session"),
    GetCurrentRoomMaps("list all the dedicated map for room"),
    LoadMapSession("go user to meeting"),
    GetMapSessionInfo("return th e details of the map"),
    GetMapTile("download specific tile of the layer of map"),
    GetMapSessionTime("check is meeting is running or not"),
    ListObjects("get the all objects in rhe table"),
    GetMapWeatherList("get the all weather that assign to the specific meeting"),
    ListMapObjectChanges("get the list of the object on the current map of the meeting"),
    GetObject("get the file of the object"),
    AddMapObject("add the object to the specific location of the map of the meeting"),
    DeleteMapObjects("delete list of the objects from map of the specific meeting"),
    ModifyMapObjects("modify list of the object on map of meeting"),
    GetWeathers("get the list of the weathers in the map of the meeting"),
    SendWeather("insert weather to map of the meeting"),
    UpdateWeather("change the weather of the map"),
    DeleteWeather("delete weather from map"),
    ListMapEntities("list the entity map of the meeting"),
    AddMapEntity("insert entity to map of the meeting"),
    ModifyMapEntity("modify the json string of the specific entity"),
    DeleteMapEntities("delete entity from map of the meeting"),
    AddMapPositions("add positions to map"),
    DeleteMapPositions("delete positions to map"),
    UpdateMapPosition("update position to map"),
    ListMapPositions("list positions to map"),
    AddRoomRole("add role to user of the room"),
    DeleteRoomRole("delete role to user of the room"),
    SetRoomUserStatus("active or deactive the user of the room"),
    KickUser("kick out the user from room of meeting"),






    // this is Service for log
    InsertNewSession("insert login user session"),
    UpdateUserSession("update exist session");



    private  final String name;

    TtRpcFunction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TtRpcFunction getByOrdinal(int o){
        for (TtRpcFunction f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
