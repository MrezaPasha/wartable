package org.sadr.share.main.meeting;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc._types.TtRpcResponseResult;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.Room_Map.Room_MapServiceImp;
import org.sadr.share.main._types.TtUserRoleFlags;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meeting._types.TtMeetingFields;
import org.sadr.share.main.meeting._types.TtMeetingStatus;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.meetingLog.MeetingLogServiceImp;
import org.sadr.share.main.meetingSetting.MeetingSettingServiceImp;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.mrml._types.TtMrmlDeleted;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserPresence;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MeetingServiceImp extends GenericServiceImpl<Meeting,MeetingDao> implements MeetingService{


    private SessionsServiceImp sessionsServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    private MapServiceImp mapServiceImp;
    private Room_MapServiceImp roomMapServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MrmlServiceImp mrmlServiceImp;
    private MeetingSettingServiceImp  meetingSettingServiceImp;
    private MeetingLogServiceImp meetingLogServiceImp;
    private RoomServiceImp roomServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;



    @Autowired
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }

    @Autowired
    public void setMeetingLogServiceImp(MeetingLogServiceImp meetingLogServiceImp) {
        this.meetingLogServiceImp = meetingLogServiceImp;
    }

    @Autowired
    public void setMeetingSettingServiceImp(MeetingSettingServiceImp meetingSettingServiceImp) {
        this.meetingSettingServiceImp = meetingSettingServiceImp;
    }

    @Autowired
    public void setMrmlServiceImp(MrmlServiceImp mrmlServiceImp) {
        this.mrmlServiceImp = mrmlServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }


    @Autowired
    public void setRoomMapServiceImp(Room_MapServiceImp roomMapServiceImp) {
        this.roomMapServiceImp = roomMapServiceImp;
    }

    @Autowired
    public void setMapServiceImp(MapServiceImp mapServiceImp) {
        this.mapServiceImp = mapServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }
    public RpcResponse generateGenericSuccessResponse(TtGlobalId globalId, TtErrors error)
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
    public RpcResponse generateGetSessionTimeStatus(Meeting meeting , TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetMapSessionTime.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        java.util.Map result = new HashMap();
        if (errors == TtErrors.NoError)
        {
            result.put("Status", meeting.getStatus().ordinal());
            rpcResponse.setResult(result);
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        }
        else
        {
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        }

    }

    public RpcResponse generateBooleanResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();

        try
        {
            rpcResponse.setId(TtGlobalId.LoadMapSession.ordinal());
            java.util.Map result = new HashMap();
            if (error == TtErrors.NoError)
            {
                result.put(TtRpcResponseResult.Success.name(),1);
                rpcResponse.setResult(result);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);
            }
            else
            {
                result.put(TtRpcResponseResult.Success.name(),0);
                rpcResponse.setResult(result);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);

            }

            return rpcResponse;

        }
        catch (Exception e)
        {
            rpcResponse.setResult(null);
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        return rpcResponse;
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
    public Room getRoomFromSessionId(String sessionId)
    {
        try
        {
            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();
            if (serviceUser != null)
            {
                Room room = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser),Restrictions.eq(Room_ServiceUser.PRESENCE , TtRoomServiceUserPresence.Online)),Room_ServiceUser._ROOM).getRoom();
                return room;
            }
            else
            {
                return null;
            }

        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return null;

        }
    }
    public  boolean isUserHaveAdminPrivilages(Sessions sessions)
    {
        try
        {
            ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessions.getSessionId()),Sessions._SERVICE_USER).getServiceUser();
            if (serviceUser != null)
            {
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser),Restrictions.eq(Room_ServiceUser.PRESENCE,TtRoomServiceUserPresence.Online)));
                if (isUserHaveAdminPrivilages(roomServiceUser))
                {
                    return true;
                }
                else
                {
                    return false;
                }
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
    public boolean isMapForRoom(Map map, Room room)
    {
        try
        {
            Room_Map roomMap = roomMapServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_Map._MAP,map),Restrictions.eq(Room_Map._ROOM,room)));
            if (roomMap != null)
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
    public Meeting insertNewMeeting(String name , String description , String goal , Room_Map currentRoomMap)
    {

        try
        {
            final String DEFAULT_RESULT = "default result";
            final String DEFAULT_BOARD_TEXT = "in the name of god";
            /*MeetingSetting meetingSetting = new MeetingSetting();
            meetingSetting.setDescription(description);*/
            Meeting meeting = new Meeting();
            meeting.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            meeting.setName(name);
            meeting.setResult(DEFAULT_RESULT);
            meeting.setStatus(TtMeetingStatus.Active);
            meeting.setCurrentRoomMap(currentRoomMap);
            meeting.setRoom(currentRoomMap.getRoom());
            meeting.setBoardText(DEFAULT_BOARD_TEXT);
            meeting.setDescription(description);
            meeting.setDeleted(TtMeetingDeleted.Undeleted);
            meeting.setDescription(description);
            meeting.setGoal(goal);
            meetingServiceImp.save(meeting);
            return meeting;
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return null;
        }
    }
    public void insertNewLayer(Layer layer , Room_Map roomMap , Meeting meeting , long order)
    {
        try
        {
            Mrml mrml = new Mrml();
            mrml.setDeleted(TtMrmlDeleted.Undeleted);
            mrml.setLayer(layer);
            mrml.setRoomMap(roomMap);
            mrml.setMeeting(meeting);
            mrml.setDeleted(TtMrmlDeleted.Undeleted);
            mrml.setOrder(layer.getId());
            mrmlServiceImp.save(mrml);

        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
    }
    public Meeting isRoomActive(Room room)
    {
        try
        {
          Meeting meeting =   meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.STATUS,TtMeetingStatus.Active)));
          if (meeting != null)
          {
              return meeting;
          }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return null;
        }
        return null;
    }
    public boolean deactiveMeeting(Meeting meeting)
    {
        try
        {
            meeting.setStatus(TtMeetingStatus.Deactive);
            meetingServiceImp.update(meeting);
            return true;

        }
        catch (Exception e )
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return false;

        }
    }
    public RpcResponse generateNewMeetingResponse(RpcRequest rpcRequest, Meeting meeting , TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(rpcRequest.getId());
        java.util.Map result = new HashMap();
        if (errors == TtErrors.NoError)
        {
            result.put("MapSessionId",meeting.getId());
        }
        else
        {
            result.put("MapSessionId",null);
        }
        rpcResponse.setResult(result);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errors.ordinal());
        errorResponse.setDescription(errors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }
    public boolean isDuplicateMeeting(String name , String goal , String description)
    {
        try
        {
            Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.NAME,name),Restrictions.eq(Meeting.GOAL,goal),Restrictions.eq(Meeting.DESCRIPTION,description),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted)));
            if (meeting != null)
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
            return true;

        }
    }




    public RpcResponse generateListMeetings( List<Meeting> meetings, TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListMapSessions.ordinal());
        List<Long> meetingIds = new ArrayList<>();
        List<String> meetingName = new ArrayList<String>();
        List<String> meetingDescription = new ArrayList<String>();
        List<String> meetingGoal = new ArrayList<String>();
        List<String> meetingResult = new ArrayList<String>();
        List<String> mapName = new ArrayList<String>();
        List<String> meetingCreationTime = new ArrayList<String>();
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        try
        {
            if (!meetings.isEmpty())
            {
                for (Meeting meeting:meetings)
                {
                    meetingIds.add(meeting.getId());
                    meetingName.add(meeting.getName());
                    meetingDescription.add(meeting.getDescription());
                    meetingGoal.add(meeting.getGoal());
                    meetingResult.add(meeting.getResult());
                    mapName.add(meeting.getCurrentRoomMap().getMap().getName());
                    meetingCreationTime.add(meeting.getCreationDateTime());

                }
                result.put(TtMeetingFields.MeetingIds.getResponseFieldName(),meetingIds);
                result.put(TtMeetingFields.MeetingNames.getResponseFieldName(),meetingName);
                result.put(TtMeetingFields.MeetingDescriptions.getResponseFieldName(),meetingDescription);
                result.put(TtMeetingFields.MeetingGoals.getResponseFieldName(),meetingGoal);
                result.put(TtMeetingFields.MeetingResults.getResponseFieldName(),meetingResult);
                result.put(TtMeetingFields.MeetingMapNames.getResponseFieldName(),mapName);
                result.put(TtMeetingFields.MeetingCreationTimes.getResponseFieldName(),meetingCreationTime);
                rpcResponse.setResult(result);
                errorResponse.setCode(errors.ordinal());
                errorResponse.setDescription(errors.getErrorValueService());
                rpcResponse.setError(errorResponse);
            }
            else if (meetings.isEmpty())
            {
                rpcResponse.setResult(null);
                errorResponse.setCode(errors.ordinal());
                errorResponse.setDescription(errors.getErrorValueService());
            }


        }
        catch (Exception e)
        {
            rpcResponse.setResult(null);
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        return rpcResponse;
    }

   public RpcResponse listMeetings(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       String sessionId = rpcRequest.getParams().get("SessionId").toString();
       try
       {
           if (sessionId != null)
           {
               ServiceUser serviceUser = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser();
               if (serviceUser != null)
               {

                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser),Room_ServiceUser._ROOM);
                   if ( roomServiceUser.getRoom() != null && isUserHaveAdminPrivilages(roomServiceUser))
                   {
                       List<Meeting> meetings = findAllBy(Restrictions.eq(Meeting._ROOM,roomServiceUser.getRoom()),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                       rpcResponse = generateListMeetings(meetings,TtErrors.NoError);

                   }
                   else if (roomServiceUser.getRoom() == null)
                   {
                       rpcResponse = generateListMeetings(null,TtErrors.UserDoesntexist);

                   }

               }
               else if (serviceUser == null)
               {
                   rpcResponse = generateListMeetings(null,TtErrors.UserDoesntexist);

               }

           }
           else if (sessionId == null)
           {
               rpcResponse = generateListMeetings(null,TtErrors.SessionIsNull);
           }
       }
       catch (Exception e)
       {
           rpcResponse = generateListMeetings(null,TtErrors.OperationalErrorOccured);
           LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
       }
       return rpcResponse;
   }
   public RpcResponse newMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMapId = Double.parseDouble((String) rpcRequest.getParams().get("MapId").toString());
           Double d = new Double(doubleMapId);
           Integer mapId = d.intValue();
           String name = (String) rpcRequest.getParams().get("Name");
           String description = (String) rpcRequest.getParams().get("Description");
           String goal = (String) rpcRequest.getParams().get("Goal");
           Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
           Map map = mapServiceImp.findBy(Restrictions.eq(Map.ID,mapId));
           if ( sessions != null)
           {
               Room room = getRoomFromSessionId(sessionId);
               if (room != null && map != null)
               {
                   if (isUserHaveAdminPrivilages(sessions))
                   {
                       if (isMapForRoom(map,room))
                       {
                           Room_Map roomMap = roomMapServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_Map._ROOM,room),Restrictions.eq(Room_Map._MAP,map)),Room_Map._ROOM);
                           Meeting meeting = new Meeting();
                           if (!isDuplicateMeeting(name,goal,description)) {
                                meeting = insertNewMeeting(name, description, goal, roomMap);
                           }
                           else
                           {
                               rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.Meeting_DuplicateMeeting);
                               return rpcResponse;
                           }

                           Set<Layer> layers = roomMapServiceImp.findBy(Restrictions.eq(Room_Map._MAP,map),RePa.p__(Room_Map._MAP, Map._LAYERS)).getMap().getLayers();
                           if (!layers.isEmpty())
                           {
                               Mrml mrml = new Mrml();
                               for (Layer layer:layers)
                               {
                                   insertNewLayer(layer,roomMap,meeting,layer.getId());
                               }
                               List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_SESSIONLIST);
                               List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted)));
                               String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.JoinRoom,TtErrors.NoError,broadcastFlags,generateListMeetings(meetings,TtErrors.NoError));

                               rpcResponse = generateNewMeetingResponse(rpcRequest,meeting,TtErrors.NoError);
                               BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                               return rpcResponse;

                              /* Meeting activeMeeting = isRoomActive(room);
                               if (activeMeeting != null)
                               {
                                   deactiveMeeting(activeMeeting);
                                   List<Integer> broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_SESSIONLIST);
                                   //BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                   // here we must set log
                                   rpcResponse = generateNewMeetingResponse(rpcRequest,meeting,TtErrors.NoError);
                                   return rpcResponse;
                               }*/
                           }
                           else
                           {
                               rpcResponse = generateNewMeetingResponse(rpcRequest,meeting,TtErrors.MapDoesntHaveAnyLayer);
                               return rpcResponse;
                           }
                       }
                       else
                       {
                           rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.MapDoesntForRoom);
                           return rpcResponse;
                       }
                   }
                   else
                   {
                       rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.UserDoesntHaveEnoughPermission);
                       return rpcResponse;
                   }
               }
               else if (room == null || map == null)
               {
                   rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.RoomDoesntExist);
                   return rpcResponse;
               }
           }
           else
           {
               rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.SessionIsNull);
               return rpcResponse;
           }
       }
       catch (Exception e)
       {
           rpcResponse = generateNewMeetingResponse(rpcRequest,null,TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;
   }

   public RpcResponse loadMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           Sessions session =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
           if (session != null)
           {
               ServiceUser serviceUser = session.getServiceUser();
               Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._ROOM);
               if (meeting != null)
               {
                   Room room = meeting.getRoom();
                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)));
                   System.out.println(room.getName());
                   if (roomServiceUser != null)
                   {
                       if (isUserHaveAdminPrivilages(roomServiceUser))
                       {
                           room.setCurrentMeeting(meeting);
                           roomServiceImp.update(room);
                           List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_NEWMAPSESSION);
                           String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,new RpcResponse()
                           );
                           BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                           rpcResponse = generateBooleanResponse(TtErrors.NoError);
                           return rpcResponse;
                       }
                       else
                       {
                           rpcResponse = generateBooleanResponse(TtErrors.UserDoesntHaveEnoughPermission);
                           return rpcResponse;

                       }

                   }
                   else if (roomServiceUser == null)
                   {
                       rpcResponse = generateBooleanResponse(TtErrors.RoomOrUserDoesntExist);
                       return rpcResponse;

                   }
               }
               else if (meeting == null)
               {
                   rpcResponse = generateBooleanResponse(TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }

           }
           else if(session == null)
           {
               rpcResponse = generateBooleanResponse(TtErrors.SessionIsNull);
               return rpcResponse;

           }

       }
       catch (Exception e)
       {
           rpcResponse = generateBooleanResponse(TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;
   }
   public RpcResponse getSessionTimeStatus(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
           if (sessions != null)
           {
               Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.ID,meetingId),Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted)));
               if (meeting != null)
               {
                   rpcResponse = generateGetSessionTimeStatus(meeting,TtErrors.NoError);
                   return rpcResponse;


               }
               else if (meeting == null)
               {
                   rpcResponse = generateGetSessionTimeStatus(null,TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }
           }
           else if (sessions == null)
           {
               rpcResponse = generateGetSessionTimeStatus(null,TtErrors.SessionIsNull);
               return rpcResponse;

           }


       }
       catch (Exception e)
       {
           rpcResponse= generateGetSessionTimeStatus(null, TtErrors.OperationalErrorOccured);
           return rpcResponse;

       }
       return rpcResponse;
   }

   public RpcResponse deleteMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           Sessions session =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),/*Sessions._SERVICE_USER*/RePa.p__(Sessions._SERVICE_USER,ServiceUser._LAST_ROOM,Room._CURRENT_MEETING));
           if (session != null)
           {
               ServiceUser serviceUser = session.getServiceUser();
               Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._ROOM);
               if (meeting != null)
               {
                   Room room = meeting.getRoom();
                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)));
                   //System.out.println(room.getName());
                   if (roomServiceUser != null)
                   {
                       if (isUserHaveAdminPrivilages(roomServiceUser))
                       {
                           if (meeting == session.getServiceUser().getLastRoom().getCurrentMeeting())
                           {
                               rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting,TtErrors.DeleteMeetingErrorForAdminReason);
                           }
                           else
                           {
                               // here we set deleted for meeting
                               meeting.setDeleted(TtMeetingDeleted.Deleted);
                               update(meeting);
                               // here we delete items
                               List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.eq(MeetingItem._MEETING,meeting));
                               if (meetingItems.isEmpty())
                               {
                                   for (MeetingItem meetingItem: meetingItems)
                                   {
                                       meetingItem.setDeleted(TtMeetingItemDeleted.Deleted);
                                       meetingItemServiceImp.update(meetingItem);
                                   }
                               }

                               rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting,TtErrors.NoError);
                           }
                           // broadcast flag for user of the room
                           List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_SESSIONLIST);
                           List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED , TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                           String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,meetingServiceImp.generateListMeetings(meetings,TtErrors.NoError));
                           BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
                       }
                       else
                       {
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting,TtErrors.UserDoesntHaveEnoughPermission);
                           return rpcResponse;

                       }

                   }
                   else if (roomServiceUser == null)
                   {
                       rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting ,TtErrors.RoomOrUserDoesntExist);
                       return rpcResponse;

                   }
               }
               else if (meeting == null)
               {
                   rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting,TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }

           }
           else if(session == null)
           {
               rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting,TtErrors.SessionIsNull);
               return rpcResponse;

           }

       }
       catch (Exception e)
       {
           rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteMeeting , TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;

   }

   public RpcResponse editMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           String name = (String) rpcRequest.getParams().get("Name");
           String description = (String) rpcRequest.getParams().get("Description");
           Sessions session =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
           if (session != null)
           {
               ServiceUser serviceUser = session.getServiceUser();
               Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._ROOM);
               if (meeting != null)
               {
                   Room room = meeting.getRoom();
                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)));
                   //System.out.println(room.getName());
                   if (roomServiceUser != null)
                   {
                       if (isUserHaveAdminPrivilages(roomServiceUser))
                       {
                           meeting.setName(name);
                           meeting.setDescription(description);
                           meetingServiceImp.update(meeting);
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting,TtErrors.NoError);
                           // broadcast message
                           List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED , TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                           List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_SESSIONLIST);
                           String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListMeetings(meetings,TtErrors.NoError)
                           );
                           BrokerUtils.broadcastMessage(broadcastMessage,room.getName());

                       }
                       else
                       {
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting,TtErrors.UserDoesntHaveEnoughPermission);
                           return rpcResponse;

                       }

                   }
                   else if (roomServiceUser == null)
                   {
                       rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting ,TtErrors.RoomOrUserDoesntExist);
                       return rpcResponse;

                   }
               }
               else if (meeting == null)
               {
                   rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting,TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }

           }
           else if(session == null)
           {
               rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting,TtErrors.SessionIsNull);
               return rpcResponse;

           }

       }
       catch (Exception e)
       {
           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditMeeting , TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;

   }
   public RpcResponse endMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           Sessions session =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),/*Sessions._SERVICE_USER*/RePa.p__(Sessions._SERVICE_USER,ServiceUser._LAST_ROOM,Room._CURRENT_MEETING));
           if (session != null)
           {
               ServiceUser serviceUser = session.getServiceUser();
               Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._ROOM);
               if (meeting != null)
               {
                   Room room = meeting.getRoom();
                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)),Room_ServiceUser._ROOM,Room._CURRENT_MEETING);
                   //System.out.println(room.getName());
                   if (roomServiceUser != null)
                   {
                       if (isUserHaveAdminPrivilages(roomServiceUser))
                       {
                           Room editedRoom = roomServiceUser.getRoom();
                           editedRoom.setCurrentMeeting(null);
                           roomServiceImp.update(editedRoom);
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting,TtErrors.NoError);
                           // broadcast message
                           List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED , TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                           List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ENDMAPSESSION);
                           String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListMeetings(meetings,TtErrors.NoError)
                           );
                           BrokerUtils.broadcastMessage(broadcastMessage,room.getName());

                       }
                       else
                       {
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting,TtErrors.UserDoesntHaveEnoughPermission);
                           return rpcResponse;

                       }

                   }
                   else if (roomServiceUser == null)
                   {
                       rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting ,TtErrors.RoomOrUserDoesntExist);
                       return rpcResponse;

                   }
               }
               else if (meeting == null)
               {
                   rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting,TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }

           }
           else if(session == null)
           {
               rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting,TtErrors.SessionIsNull);
               return rpcResponse;

           }

       }
       catch (Exception e)
       {
           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EndMeeting , TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;


   }
   public RpcResponse editGoalResultMeeting(RpcRequest rpcRequest)
   {
       RpcResponse rpcResponse = new RpcResponse();
       try
       {
           String sessionId = (String) rpcRequest.getParams().get("SessionId");
           double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
           Double d = new Double(doubleMeetingId);
           Integer meetingId = d.intValue();
           String goal = (String) rpcRequest.getParams().get("Goal");
           String result = (String) rpcRequest.getParams().get("Result");
           Sessions session =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
           if (session != null)
           {
               ServiceUser serviceUser = session.getServiceUser();
               Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._ROOM);
               if (meeting != null)
               {
                   Room room = meeting.getRoom();
                   Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM,room),Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser)));
                   //System.out.println(room.getName());
                   if (roomServiceUser != null)
                   {
                       if (isUserHaveAdminPrivilages(roomServiceUser))
                       {
                           if (goal.isEmpty() || result.isEmpty())
                           {
                               rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting,TtErrors.InputValuesAreNull);
                               return rpcResponse;

                           }
                           meeting.setGoal(goal);
                           meeting.setDescription(result);
                           meetingServiceImp.update(meeting);
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting,TtErrors.NoError);
                           // broadcast message
                           List<Meeting> meetings = meetingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Meeting._ROOM,room),Restrictions.eq(Meeting.DELETED , TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
                           List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_SESSIONLIST);
                           String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateListMeetings(meetings,TtErrors.NoError)
                           );
                           BrokerUtils.broadcastMessage(broadcastMessage,room.getName());

                       }
                       else
                       {
                           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting,TtErrors.UserDoesntHaveEnoughPermission);
                           return rpcResponse;

                       }

                   }
                   else if (roomServiceUser == null)
                   {
                       rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting ,TtErrors.RoomOrUserDoesntExist);
                       return rpcResponse;

                   }
               }
               else if (meeting == null)
               {
                   rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting,TtErrors.Meeting_MeetingIsNull);
                   return rpcResponse;

               }

           }
           else if(session == null)
           {
               rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting,TtErrors.SessionIsNull);
               return rpcResponse;

           }

       }
       catch (Exception e)
       {
           rpcResponse = generateGenericSuccessResponse(TtGlobalId.EditGoalResultMeeting , TtErrors.OperationalErrorOccured);
           return rpcResponse;
       }
       return rpcResponse;

   }

}
