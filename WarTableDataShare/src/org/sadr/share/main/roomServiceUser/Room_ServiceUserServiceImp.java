package org.sadr.share.main.roomServiceUser;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc._types.TtRpcResponseResult;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main._types.TtMemberStatus;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main._types.TtUserRoleFlags;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.groupPolicy.GroupPolicy;
import org.sadr.share.main.groupPolicy.GroupPolicyServiceImp;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.room._types.TtBusyType;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserPresence;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserResponse;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Room_ServiceUserServiceImp extends GenericServiceImpl<Room_ServiceUser,Room_ServiceUserDao> implements Room_ServiceUserService {

    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    //private RoomServiceIm roomServiceImp;
    private RoomServiceImp roomServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private GroupPolicyServiceImp groupPolicyServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private ServiceUserServiceImp  serviceUserServiceImp;
    private ObjectServiceImp objectServiceImp;
    private BaseConfigServiceImp baseConfigServiceImp;






    @Autowired
    public void setBaseConfigServiceImp(BaseConfigServiceImp baseConfigServiceImp) {
        this.baseConfigServiceImp = baseConfigServiceImp;
    }

    @Autowired
    public void setObjectServiceImp(ObjectServiceImp objectServiceImp) {
        this.objectServiceImp = objectServiceImp;
    }

    @Autowired
    public void setServiceUserServiceImp(ServiceUserServiceImp serviceUserServiceImp) {
        this.serviceUserServiceImp = serviceUserServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setGroupPolicyServiceImp(GroupPolicyServiceImp groupPolicyServiceImp) {
        this.groupPolicyServiceImp = groupPolicyServiceImp;
    }




    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }


    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }

    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    public  boolean isUserHaveAdminPrivilages(Room_ServiceUser roomServiceUser)
    {
        try
        {
            if (roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.Admin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdmin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdminViewSource || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.AdminViewSource)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return false;
        }
    }

    public RpcResponse generateJoinRoomResponse(Room room , TtErrors errors)
    {
        List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.JoinRoom.ordinal());
        Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        if (errors == TtErrors.NoError)
        {
            result.put("ConferencesNumber",room.getId());
            result.put("RoomName",room.getName());
            // new field
            result.put("VoipServerIP",baseConfigs.get(TtConfig.VoipServerIp.ordinal()).getConfigValue());
            //result.put("VoipPort",baseConfigs.get(TtConfig.VoipServerRestPort.ordinal()));
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);

        }
        else if (errors != TtErrors.NoError)
        {
            //result.put("ConferenceNumber",-1);
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);

        }
        return rpcResponse;


    }
    public static RpcResponse generateLeaveRoomBooleanResponse(RpcRequest rpcRequest, TtErrors ttErrors , boolean opResult)
    {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        Map result = new HashMap();
        if (opResult)
        {
            result.put(TtRpcResponseResult.Success.getName(),1);
        }
        else
        {
            result.put(TtRpcResponseResult.Success.getName(),0);
        }

        rpcResponse.setResult(result);
        errorResponse.setCode(ttErrors.ordinal());
        errorResponse.setDescription(ttErrors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }
    public boolean checkHaveAdminTempPrivilage(TtUserRoleFlags userRoleFlags) {
        if (userRoleFlags == TtUserRoleFlags.Admin || userRoleFlags == TtUserRoleFlags.TempAdmin || userRoleFlags == TtUserRoleFlags.TempAdminViewSource || userRoleFlags == TtUserRoleFlags.TempAdminViewSource) {
            return true;
        } else
        {
            return false;
        }
    }





    public RpcResponse generateGenericSuccessResponse(TtGlobalId globalId,TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(globalId.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        java.util.Map result = new HashMap();
        if (error == TtErrors.NoError)
        {
            result.put("Success",1);
        }
        else
        {
            result.put("Success",0);
        }
        rpcResponse.setError(errorResponse);
        rpcResponse.setResult(result);
        return rpcResponse;
    }




    public  RpcResponse generateListRoomUsers(List<Room_ServiceUser> roomServiceUsers, TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListRoomUsers.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        Map result = new HashMap();
        try
        {
            List<String> users = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<String> families = new ArrayList<String>();
            List<String> grades = new ArrayList<String>();
            List<String> orgPositions =  new ArrayList<String>();
            List<Long> ids = new ArrayList<>();
            List<Integer> roomRoleFlags = new ArrayList<Integer>();
            List<Integer> userStatus = new ArrayList<Integer>();
            List<Integer> onOff = new ArrayList<Integer>();
            //List<String> accessList = new ArrayList<String>();
            List<String> accessList = new ArrayList<String>();
            //List<Integer> byteAccessList = new ArrayList<>();
            List<Integer> defaultRecValues = new ArrayList<Integer>();
            List<Integer> moderatorRecAccess = new ArrayList<Integer>();
            List<Integer> accessToRequestPrivateTextChat = new ArrayList<>();
            List<Integer> accessToRequestPrivateVoiceChat = new ArrayList<>();
            String personalAccessList;

            if (!roomServiceUsers.isEmpty())
            {
                for (Room_ServiceUser roomServiceUser : roomServiceUsers)
                {
                    users.add(roomServiceUser.getServiceUser().getUserName());
                    names.add(roomServiceUser.getServiceUser().getName());
                    families.add(roomServiceUser.getServiceUser().getFamily());
                    grades.add(roomServiceUser.getServiceUser().getGrade().getValue());
                    orgPositions.add(roomServiceUser.getServiceUser().getOrgPosition().getValue());
                    ids.add(roomServiceUser.getServiceUser().getId());
                    roomRoleFlags.add(roomServiceUser.getTemporaryUserRoleFlag().ordinal());
                    userStatus.add(roomServiceUser.getUserStatus().ordinal());
                    onOff.add(roomServiceUser.getPresence().ordinal());
                    defaultRecValues.add(roomServiceUser.getRoom().getRecSetting().ordinal());
                    moderatorRecAccess.add(roomServiceUser.getRoom().getAccessSetting().ordinal());
                    accessToRequestPrivateTextChat.add(roomServiceUser.getAcceptPrivateChat().ordinal());
                    accessToRequestPrivateVoiceChat.add(roomServiceUser.getAcceptPrivateTalk().ordinal());

                    if (checkHaveAdminTempPrivilage(roomServiceUser.getTemporaryUserRoleFlag()))
                    {
                        personalAccessList = groupPolicyServiceImp.setAllStateBytes();
                        /*if (accessList.size() != 38)
                        {
                            System.out.println("error occured");
                        }*/
                    }
                    else
                    {
                        personalAccessList = groupPolicyServiceImp.setStateBytes(false,roomServiceUser.getServiceUser().getGroupPolicy().getAccessCategories());
                       /* accessList.add(Integer.parseInt(groupPolicyServiceImp.setStateBytes(true,roomServiceUser.getServiceUser().getGroupPolicy().getAccessCategories())));*/
                    }
                    accessList.add(personalAccessList);

                }

                    result.put(TtRoomServiceUserResponse.Users,users);
                    result.put(TtRoomServiceUserResponse.Names,names);
                    result.put(TtRoomServiceUserResponse.Families,families);
                    result.put(TtRoomServiceUserResponse.Grades,grades);
                    result.put(TtRoomServiceUserResponse.OrgPositions,orgPositions);
                    result.put(TtRoomServiceUserResponse.Ids,ids);
                    result.put(TtRoomServiceUserResponse.RoomRoleFlags,roomRoleFlags);
                    result.put(TtRoomServiceUserResponse.UserStatus,userStatus);
                    result.put(TtRoomServiceUserResponse.OnOff,onOff);
                    result.put(TtRoomServiceUserResponse.AccessList,accessList);
                    result.put(TtRoomServiceUserResponse.DefaultRecValues , defaultRecValues);
                    result.put(TtRoomServiceUserResponse.ModeratorRecAccess , moderatorRecAccess);
                    result.put("AccessToRequestPrivateTextChat",accessToRequestPrivateTextChat);
                    result.put("AccessToRequestPrivateVoiceChat",accessToRequestPrivateVoiceChat);

                rpcResponse.setResult(result);
                    errorResponse.setCode(errors.ordinal());
                    errorResponse.setDescription(errors.getErrorValueService());
                    rpcResponse.setError(errorResponse);
            }

            else if (roomServiceUsers.isEmpty() || roomServiceUsers.isEmpty())
            {
                rpcResponse.setResult(result);
                errorResponse.setCode(errors.ordinal());
                errorResponse.setDescription(errors.getErrorValueService());
                rpcResponse.setError(errorResponse);
            }

        }
        catch (Exception e)
        {
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);

        }
        return rpcResponse;

    }
    public RpcResponse leaveRoom(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();
            if (serviceUser != null)
            {
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser));
                if (roomServiceUser != null)
                {
                    roomServiceUserServiceImp.delete(roomServiceUser);
                    rpcResponse = generateLeaveRoomBooleanResponse(rpcRequest,TtErrors.NoError,true);
                    LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Logout,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserProfile,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                }
                else if (roomServiceUser == null)
                {
                    rpcResponse = generateLeaveRoomBooleanResponse(rpcRequest,TtErrors.UserDoesntexist,false);
                    LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Logout,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserProfile,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));


                }

            }
            else if (serviceUser == null)
            {
                rpcResponse = generateLeaveRoomBooleanResponse(rpcRequest,TtErrors.UserIsNotRoot,false);
                LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Logout,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserProfile,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));


            }
        }
        catch (Exception e)
        {
            rpcResponse = generateLeaveRoomBooleanResponse(rpcRequest,TtErrors.OperationalErrorOccured,false);
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();
            LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));


        }
        return rpcResponse;
    }


    public RpcResponse joinRoom(RpcRequest rpcRequest)
    {

         RpcResponse rpcResponse = new RpcResponse();
        Room_ServiceUser newUser = new Room_ServiceUser();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleRoomId = Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString());
            Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();

            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID,roomId),Room._MEETINGS);
            //System.out.println(room.getName());
            if (room.getBusyType() == TtBusyType.UnBusy)
            {
                if (roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.PRESENCE,TtRoomServiceUserPresence.Online))).isEmpty())
                {
                    Utils.createVoipConferenceBridgeByroom(room);
                    room.setBusyType(TtBusyType.Busy);
                    roomServiceImp.update(room);
                }
            }
            List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED , TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
            if (!meetings.isEmpty())
            {
                if (serviceUser != null && room != null)
                {
                    /*Room_Member roomMember = roomMemberServiceImp.findBy(Restrictions.eq(Room_Member._SERVICE_USER,serviceUser));*/
                    Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)),Room_ServiceUser._ROOM,RePa.p__(Room_ServiceUser._SERVICE_USER,ServiceUser._LAST_ROOM));
                    if (roomServiceUser != null)
                    {
                        if (roomServiceUser.getPermanentUserRoleFlag() == TtUserRoleFlags.User)
                        {
                            if (roomServiceUser.getUserStatus() == TtMemberStatus.Deactive)
                            {
                                rpcResponse = generateJoinRoomResponse(null,TtErrors.UserIsDeactive);
                                return rpcResponse;
                            }
                            List<Room_ServiceUser> countRoomServiceUser = roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.PRESENCE,TtRoomServiceUserPresence.Online)));
                            if (countRoomServiceUser.size() >= 1)
                            {
                                roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.User);
                                roomServiceUser.setPermanentUserRoleFlag(TtUserRoleFlags.User);
                                roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                                roomServiceUserServiceImp.update(roomServiceUser);
                                // update last room for serviceuser
                                ServiceUser changeUser = roomServiceUser.getServiceUser();
                                changeUser.setLastRoom(room);
                                serviceUserServiceImp.update(changeUser);
                                // add queue to exchange
                                BrokerUtils.addQueueToExchange(sessionId,room.getName());
                                // end add queue to exchange
                                rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                                //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                 List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                                List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                                //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                                String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                                System.out.println("#######################update###################");
                                System.out.println(broadcastMessage);
                                System.out.println("#######################update###################");

                                BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));

                                // update flag for user that request join room
                                List<Integer> unicastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_NEWMAPSESSION);
                                String unicastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,unicastFlags,new RpcResponse());
                                BrokerUtils.unicastMessage(unicastMessage,sessionId);



                            }
                            else
                            {
                                // اولین کاربر عادی که وارد اتاق می شود
                                //room.setBusyType(TtBusyType.Busy);
                                //roomServiceImp.update(room);
                                roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.TempAdmin);
                                roomServiceUser.setPermanentUserRoleFlag(TtUserRoleFlags.User);
                                roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                                roomServiceUserServiceImp.update(roomServiceUser);
                                // update last room for serviceuser
                                ServiceUser changeUser = roomServiceUser.getServiceUser();
                                changeUser.setLastRoom(room);
                                serviceUserServiceImp.update(changeUser);
                                // add queue to exchange
                                BrokerUtils.addQueueToExchange(sessionId,room.getName());
                                // end add queue to exchange
                                rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                                //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                                List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                                // List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                                //List<Meeting> meetings = meetingServiceImp.findAll(RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                                //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,roomServiceUser.getRoom()),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                                String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                                System.out.println("#######################update###################");
                                System.out.println(broadcastMessage);
                                System.out.println("#######################update###################");
                                BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));

                            }

                        }
                        else if (roomServiceUser.getPermanentUserRoleFlag() == TtUserRoleFlags.Admin)
                        {
                            Room_ServiceUser conditionUser =  roomServiceUserServiceImp.findBy(Restrictions.or(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG,TtUserRoleFlags.TempAdmin)),Restrictions.and(Restrictions.or(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG,TtUserRoleFlags.TempAdminViewSource))))));
                            if (conditionUser != null)
                            {
                                conditionUser.setTemporaryUserRoleFlag(TtUserRoleFlags.User);
                                roomServiceUserServiceImp.update(conditionUser);
                                roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.Admin);
                                roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                                roomServiceUserServiceImp.update(roomServiceUser);
                                // update last room for serviceuser
                                ServiceUser changeUser = roomServiceUser.getServiceUser();
                                changeUser.setLastRoom(room);
                                serviceUserServiceImp.update(changeUser);
                                // add queue to exchange
                                BrokerUtils.addQueueToExchange(sessionId,room.getName());
                                // end add queue to exchange
                                rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                                //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                                List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                                //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                                String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                                System.out.println("#######################update###################");
                                System.out.println(broadcastMessage);
                                System.out.println("#######################update###################");
                                BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                            }
                            else
                            {
                                // اولین کاربری که وارد اتاق می شود ادمین هست
                                //room.setBusyType(TtBusyType.Busy);
                                roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.Admin);
                                roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                                roomServiceUserServiceImp.update(roomServiceUser);
                                // update last room for serviceuser
                                ServiceUser changeUser = roomServiceUser.getServiceUser();
                                changeUser.setLastRoom(room);
                                serviceUserServiceImp.update(changeUser);
                                // add queue to exchange
                                BrokerUtils.addQueueToExchange(sessionId,room.getName());
                                // end add queue to exchange
                                rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                                //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                                List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                                //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                                String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                                System.out.println("#######################update###################");
                                System.out.println(broadcastMessage);
                                System.out.println("#######################update###################");
                                BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));



                            }


                        }

                    }
                    else if (roomServiceUser == null)
                    {
                        rpcResponse = generateJoinRoomResponse(room,TtErrors.UserDoesntexist);
                        LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));

                    }


                }
                else if (serviceUser == null || room == null)
                {
                    rpcResponse = generateJoinRoomResponse(room,TtErrors.SessionIsNull);
                    LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));

                }

            }
            else if (meetings.isEmpty())
            {
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)));
                if (isUserHaveAdminPrivilages(roomServiceUser))
                {
                    Room_ServiceUser conditionUser =  roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG,TtUserRoleFlags.TempAdmin),Restrictions.eq(Room_ServiceUser.TEMPORARY_USER_ROLE_FLAG,TtUserRoleFlags.TempAdminViewSource)));
                    if (conditionUser != null)
                    {
                        conditionUser.setTemporaryUserRoleFlag(TtUserRoleFlags.User);
                        roomServiceUserServiceImp.update(conditionUser);
                        roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.Admin);
                        roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                        roomServiceUserServiceImp.update(roomServiceUser);
                        // update last room for serviceuser
                        ServiceUser changeUser = roomServiceUser.getServiceUser();
                        changeUser.setLastRoom(room);
                        serviceUserServiceImp.update(changeUser);
                        // add queue to exchange
                        BrokerUtils.addQueueToExchange(sessionId,room.getName());
                        // end add queue to exchange
                        rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                        //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                        List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                        //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                        System.out.println("#######################update###################");
                        System.out.println(broadcastMessage);
                        System.out.println("#######################update###################");
                        BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                        LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    }
                    else
                    {
                        roomServiceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.Admin);
                        roomServiceUser.setPresence(TtRoomServiceUserPresence.Online);
                        roomServiceUserServiceImp.update(roomServiceUser);
                        // update last room for serviceuser
                        ServiceUser changeUser = roomServiceUser.getServiceUser();
                        changeUser.setLastRoom(room);
                        serviceUserServiceImp.update(changeUser);
                        // add queue to exchange
                        BrokerUtils.addQueueToExchange(sessionId,room.getName());
                        // end add queue to exchange
                        rpcResponse = generateJoinRoomResponse(room,TtErrors.NoError);
                        //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST,TtState.SESS_UPDATE_OBJECT_LIST);
                        List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM,room),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                        //List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.eq(Meeting._ROOM,room),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError),meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError),objectServiceImp.generateListObjcetsResponse(objectServiceImp.findAll(),TtErrors.NoError));
                        System.out.println("#######################update###################");
                        System.out.println(broadcastMessage);
                        System.out.println("#######################update###################");
                        BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                        LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    }
                }
                else if (!isUserHaveAdminPrivilages(roomServiceUser))
                {
                    rpcResponse = generateJoinRoomResponse(room,TtErrors.RoomServiceUser_RoomDoesntHaveAnyMeeting);
                    LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.Login,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                }
            }

        }
        catch (Exception e)
        {
            /*double doubleRoomId = Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString());
            Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID,roomId));
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();*/
            rpcResponse = generateJoinRoomResponse(null,TtErrors.OperationalErrorOccured);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            //LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
        }
        sessionsServiceImp.updateSession(rpcRequest);

        return rpcResponse;
    }

    public RpcResponse listRoomUsers(RpcRequest rpcRequest)
    {

        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            List<ServiceUser> serviceUsers = new ArrayList<ServiceUser>();
            List<ServiceUser> memberUsers = new ArrayList<ServiceUser>();
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            //String roomId = (String) rpcRequest.getParams().get("RoomId");
            double doubleRoomId = Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString());
            Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID,roomId));
            Sessions session = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
            if (session != null && room != null)
            {
                List<Room_ServiceUser> roomServiceUsers =  roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room)),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                if (!roomServiceUsers.isEmpty())
                {
                    rpcResponse = generateListRoomUsers(roomServiceUsers , TtErrors.NoError);
                }
                else
                {
                    rpcResponse = generateListRoomUsers(null , TtErrors.OperationalErrorOccured);

                }

            }
            else if (session == null || room != null)
            {
                rpcResponse = generateListRoomUsers(null , TtErrors.UserDoesntexist);
            }

        }
        catch (Exception e)
        {
            rpcResponse = generateListRoomUsers(null,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public  boolean isUserHavePermanentAdminPrivilages(Room_ServiceUser roomServiceUser)
    {
        try
        {
            if (roomServiceUser.getPermanentUserRoleFlag() == TtUserRoleFlags.Admin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdmin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdminViewSource || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.AdminViewSource)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return false;
        }
    }

    public RpcResponse addRoomRole(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {

            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            //String roomId = (String) rpcRequest.getParams().get("RoomId");
            Integer roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
            Integer userId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserId").toString())).intValue();
            ServiceUser changeUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,userId));
            Integer roomRoleFlagId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomRoleFlags").toString())).intValue();
            /*Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();*/

            Sessions session = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));

            TtUserRoleFlags roleFlag;
            if (session != null && room != null)
            {
                if (!roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,session.getServiceUser())))))
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.UserDoesntHaveEnoughPermission);
                    return rpcResponse;
                }
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,changeUser)));
                if (roomServiceUser != null)
                {
                    int currentRoomRoleFlagId = roomServiceUser.getTemporaryUserRoleFlag().ordinal();
                    int finalRoomRoleFlagId = currentRoomRoleFlagId + roomRoleFlagId;
                    roleFlag = TtUserRoleFlags.getByOrdinal(finalRoomRoleFlagId);
                    if (roleFlag == null)
                    {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.FinalRoomRoleFlagNotDefine);
                        return rpcResponse;
                    }
                    roomServiceUser.setTemporaryUserRoleFlag(roleFlag);
                    roomServiceUserServiceImp.update(roomServiceUser);
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.NoError);
                    // broadcast message
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROLLCHANGE);
                    List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.USER_STATUS,TtMemberStatus.Active)),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                }
                else
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.RoomOrUserDoesntExist);
                }
            }
            else
            {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.SessionIsNull);

            }
        }
        catch (Exception e)
        {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.AddRoomRole,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
    public RpcResponse setRoomUserStatus(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            //String roomId = (String) rpcRequest.getParams().get("RoomId");
            Integer roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
            Integer userId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserId").toString())).intValue();
            Integer userStatusId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserStatus").toString())).intValue();
            /*Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();*/

            Sessions session = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));

            TtMemberStatus memberStatus  = TtMemberStatus.getByOrdinal(userStatusId);
            if (session != null && room != null && memberStatus != null) {
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, room), Restrictions.eq(Room_ServiceUser._SERVICE_USER,session.getServiceUser())),Room_ServiceUser._ROOM);
                if (!roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUser)){
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.SetRoomUserStatus,TtErrors.UserDoesntHaveEnoughPermission);
                    return rpcResponse;
                }
                ServiceUser changeStatusUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,userId));
                if (changeStatusUser == null)
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.SetRoomUserStatus,TtErrors.UserDoesntexist);
                    return rpcResponse;
                }
                roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, room), Restrictions.eq(Room_ServiceUser._SERVICE_USER,changeStatusUser)),Room_ServiceUser._ROOM);
                roomServiceUser.setUserStatus(memberStatus);
                roomServiceUserServiceImp.update(roomServiceUser);
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.SetRoomUserStatus,TtErrors.NoError);
                // broadcast message
                List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROLLCHANGE);
                List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room)),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError));
                BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
            }
            else {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.SetRoomUserStatus,TtErrors.SessionIsNull);
            }

        }
        catch (Exception e)
        {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.SetRoomUserStatus,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }
    public RpcResponse deleteRoomRole(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {

            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            //String roomId = (String) rpcRequest.getParams().get("RoomId");
            Integer roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
            Integer userId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserId").toString())).intValue();
            ServiceUser changeUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,userId));
            Integer roomRoleFlagId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomRoleFlags").toString())).intValue();
            /*Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();*/

            Sessions session = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));

            TtUserRoleFlags roleFlag;
            if (session != null && room != null)
            {
                if (!roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,session.getServiceUser())))))
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.UserDoesntHaveEnoughPermission);
                    return rpcResponse;
                }
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,changeUser)));
                if (roomServiceUser != null)
                {
                    int currentRoomRoleFlagId = roomServiceUser.getTemporaryUserRoleFlag().ordinal();
                    int finalRoomRoleFlagId = currentRoomRoleFlagId - roomRoleFlagId;
                    roleFlag = TtUserRoleFlags.getByOrdinal(finalRoomRoleFlagId);
                    if (roleFlag == null)
                    {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.FinalRoomRoleFlagNotDefine);
                        return rpcResponse;

                    }
                    roomServiceUser.setTemporaryUserRoleFlag(roleFlag);
                    roomServiceUserServiceImp.update(roomServiceUser);
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.NoError);
                    // broadcast message
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROLLCHANGE);
                    List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.USER_STATUS,TtMemberStatus.Active)),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                }
                else
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.RoomOrUserDoesntExist);
                }
            }
            else
            {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.SessionIsNull);

            }
        }
        catch (Exception e)
        {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteRoomRole,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
    public RpcResponse kickUser(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {

            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Integer userId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserId").toString())).intValue();
            Integer roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);

            if (sessions != null)
            {
                ServiceUser kickUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,userId));
                if (kickUser != null)
                {
                    Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID,roomId));
                    if (room != null)
                    {
                        RpcResponse firstStepResponse = serviceUserServiceImp.leaveRoom(rpcRequest);
                        if (firstStepResponse.getError().getCode() != TtErrors.NoError.ordinal())
                        {
                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.OperationalErrorOccured);
                            return rpcResponse;

                        }
                        Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,kickUser)),Room_ServiceUser._SERVICE_USER);


                        /*roomServiceUser.setUserStatus(TtMemberStatus.Deactive);
                        roomServiceUserServiceImp.update(roomServiceUser);*/
                        roomServiceUserServiceImp.delete(roomServiceUser);
                        // change last room id to lobby
                        Room lobby = roomServiceImp.findBy(Restrictions.eq(Room.ID,0));
                        if (lobby == null)
                        {
                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.OperationalErrorOccured);
                        }
                        kickUser.setLastRoom(lobby);
                        serviceUserServiceImp.update(kickUser);
                        // end to set last room to lobby
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.NoError);
                        // broadcast message
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                        List<Room_ServiceUser> roomServiceUsers = roomServiceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser.USER_STATUS,TtMemberStatus.Active)),RePa.p__(Room_ServiceUser._ROOM, Room_ServiceUser._SERVICE_USER,ServiceUser._GRADE,ServiceUser._ORG_POSITION , ServiceUser._GROUP_POLICY , GroupPolicy._ACCESS_CATEGORIES));
                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListRoomUsers(roomServiceUsers,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,room.getName());

                    }
                    else
                    {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.RoomDoesntExist);
                    }
                }
                else
                {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.UserDoesntexist);
                }
            }
            else
            {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.SessionIsNull);
            }

        }
        catch (Exception e)
        {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.KickUser,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;


    }
}
