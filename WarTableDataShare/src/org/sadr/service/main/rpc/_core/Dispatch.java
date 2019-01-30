package org.sadr.service.main.rpc._core;

import com.google.gson.Gson;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.service.main.seed.Seed;
import org.sadr.share.main.item.entity.EntityServiceImp;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.item.position.PositionServiceImp;
import org.sadr.share.main.item.weather.WeatherServiceImp;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;


public class Dispatch {
    public static PositionServiceImp positionServiceImp = (PositionServiceImp) IOCContainer.GetBeans(PositionServiceImp.class);
    public static Room_ServiceUserServiceImp roomServiceUserServiceImp = (Room_ServiceUserServiceImp) IOCContainer.GetBeans(Room_ServiceUserServiceImp.class);


    public static String dispatchFunction(RpcRequest protocol)
    {
        Gson gson = new Gson();
        String finalResponse="";


        try
        {
            switch (protocol.getMethod()) {
                case Login:
                    //  ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp)IOCContainer.GetBeans(ServiceUserServiceImp.class);
                    Seed.initBaseConfigs();
                    Seed.initBaseErrors();

                    ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
                    //ServiceUserServiceImp serviceUserServiceImp = new ServiceUserServiceImp();
                    //RpcResponse rpcResponse = new RpcHandler().login(protocol);
                    RpcResponse rpcResponse = serviceUserServiceImp.loginServiceUser(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case ChangePassword:
                    serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
                    rpcResponse = serviceUserServiceImp.changePasswordUser(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListRooms:
                    RoomServiceImp roomServiceImp = (RoomServiceImp) IOCContainer.GetBeans(RoomServiceImp.class);
                    rpcResponse = roomServiceImp.listRooms(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case JoinRoom:
                    /*Room_ServiceUserServiceImp roomServiceUserServiceImp = (Room_ServiceUserServiceImp) IOCContainer.GetBeans(Room_ServiceUserServiceImp.class);*/
                    rpcResponse = roomServiceUserServiceImp.joinRoom(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case LeaveRoom:
                    serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
                    rpcResponse = serviceUserServiceImp.leaveRoom(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListRoomUsers:
                    /*roomServiceUserServiceImp = (Room_ServiceUserServiceImp)IOCContainer.GetBeans(Room_ServiceUserServiceImp.class);*/
                    rpcResponse = roomServiceUserServiceImp.listRoomUsers(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case Logout:

                    serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
                    rpcResponse = serviceUserServiceImp.logout(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListMapSessions:

                    MeetingServiceImp meetingServiceImp = (MeetingServiceImp) IOCContainer.GetBeans(MeetingServiceImp.class);
                    rpcResponse = meetingServiceImp.listMeetings(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case NewMapSession:
                    MeetingServiceImp meetingServiceImp1 = (MeetingServiceImp) IOCContainer.GetBeans(MeetingServiceImp.class);
                    rpcResponse = meetingServiceImp1.newMeeting(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case GetCurrentRoomMaps:
                    MapServiceImp mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
                    rpcResponse = mapServiceImp.getCurrentRoomMaps(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case LoadMapSession:
                    meetingServiceImp1 = (MeetingServiceImp) IOCContainer.GetBeans(MeetingServiceImp.class);
                    rpcResponse = meetingServiceImp1.loadMeeting(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case GetMapSessionInfo:
                    mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
                    rpcResponse = mapServiceImp.getMapSessionInfo(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case GetMapTile:
                    /*mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
                    rpcResponse = mapServiceImp.getMapTile(protocol);
                    if (rpcResponse != null)
                    {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;*/
                case GetMapSessionTime:
                    meetingServiceImp1 = (MeetingServiceImp) IOCContainer.GetBeans(MeetingServiceImp.class);
                    rpcResponse = meetingServiceImp1.getSessionTimeStatus(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListObjects:
                    ObjectServiceImp objectServiceImp = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
                    rpcResponse = objectServiceImp.ListObjects(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListMapObjectChanges:
                    ObjectServiceImp objectServiceImp1 = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
                    rpcResponse = objectServiceImp1.listMapObjectChanges(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case AddMapObject:
                    ObjectServiceImp objectServiceImp2 = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
                    rpcResponse = objectServiceImp2.addMapObject(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case DeleteMapObjects:
                    ObjectServiceImp objectServiceImp3 = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
                    rpcResponse = objectServiceImp3.deleteMapObjects(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ModifyMapObjects:
                    ObjectServiceImp objectServiceImp4 = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
                    rpcResponse = objectServiceImp4.modifyMapObjects(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case SendWeather:
                    WeatherServiceImp weatherServiceImp1 = (WeatherServiceImp) IOCContainer.GetBeans(WeatherServiceImp.class);
                    rpcResponse = weatherServiceImp1.sendWeather(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case DeleteWeather:
                    WeatherServiceImp weatherServiceImp2 = (WeatherServiceImp) IOCContainer.GetBeans(WeatherServiceImp.class);
                    rpcResponse = weatherServiceImp2.deleteWeather(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case UpdateWeather:
                    WeatherServiceImp weatherServiceImp3 = (WeatherServiceImp) IOCContainer.GetBeans(WeatherServiceImp.class);
                    rpcResponse = weatherServiceImp3.updateWeather(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case GetWeathers:
                    WeatherServiceImp weatherServiceImp4 = (WeatherServiceImp) IOCContainer.GetBeans(WeatherServiceImp.class);
                    rpcResponse = weatherServiceImp4.getWeathers(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListMapEntities:
                    EntityServiceImp entityServiceImp = (EntityServiceImp) IOCContainer.GetBeans(EntityServiceImp.class);
                    rpcResponse = entityServiceImp.listMapEntities(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case DeleteMapEntities:
                    EntityServiceImp entityServiceImp1 = (EntityServiceImp) IOCContainer.GetBeans(EntityServiceImp.class);
                    rpcResponse = entityServiceImp1.deleteEntities(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ModifyMapEntity:
                    EntityServiceImp entityServiceImp2 = (EntityServiceImp) IOCContainer.GetBeans(EntityServiceImp.class);
                    rpcResponse = entityServiceImp2.modifyEntities(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case AddMapEntity:
                    EntityServiceImp entityServiceImp3 = (EntityServiceImp) IOCContainer.GetBeans(EntityServiceImp.class);
                    rpcResponse = entityServiceImp3.addMapEntity(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case ListMapPositions:
                    rpcResponse = positionServiceImp.listMapPositions(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case AddMapPositions:
                    rpcResponse = positionServiceImp.addMapPositions(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case DeleteMapPositions:
                    rpcResponse = positionServiceImp.deleteMapPositions(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
                case UpdateMapPosition:
                    rpcResponse = positionServiceImp.updateMapPosition(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case AddRoomRole:
                    rpcResponse = roomServiceUserServiceImp.addRoomRole(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case DeleteRoomRole:
                    rpcResponse = roomServiceUserServiceImp.deleteRoomRole(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case SetRoomUserStatus:
                    rpcResponse = roomServiceUserServiceImp.setRoomUserStatus(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;

                case KickUser:
                    rpcResponse = roomServiceUserServiceImp.kickUser(protocol);
                    if (rpcResponse != null) {
                        finalResponse = Utils.RpcResponseObjToJson(rpcResponse);
                    }
                    break;
















            }
            return finalResponse;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;

       }


    }
}
