package org.sadr.share.main.item.position;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.item.position._types.TtPositionType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
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
public class PositionServiceImp extends GenericServiceImpl<Position,PositionDao> implements PositionService {

    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;



    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }


    @Autowired
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    public RpcResponse generatelistPositionsResponse(List<MeetingItem> meetingItems, TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListMapPositions.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> types = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<String> fonts = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        List<String> colors = new ArrayList<>();



        try
        {
            if (error == TtErrors.NoError)
            {
                if (meetingItems == null)
                {
                    rpcResponse.setResult(result);
                    return rpcResponse;
                }
                for (MeetingItem meetingItem: meetingItems)
                {
                    ids.add(meetingItem.getPosition().getId());
                    names.add(meetingItem.getPosition().getName());
                    types.add(meetingItem.getPosition().getPositionType().ordinal());
                    descriptions.add(meetingItem.getPosition().getDescription());
                    fonts.add(meetingItem.getPosition().getFont());
                    positions.add(meetingItem.getPosition().getPosition());
                    colors.add(meetingItem.getPosition().getColor());
                }
                result.put("Ids",ids);
                result.put("Names",names);
                result.put("Types",types);
                result.put("Descriptions",descriptions);
                result.put("Fonts",fonts);
                result.put("Positions",positions);
                result.put("Colors",colors);
                rpcResponse.setResult(result);

            }
            else
            {
                rpcResponse.setResult(result);
            }

        }
        catch (Exception e)
        {
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);
            rpcResponse.setResult(null);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        return rpcResponse;
    }

    public RpcResponse generateAddMapPositionResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.AddMapPositions.ordinal());
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

    public RpcResponse listMapPositions(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            if (sessions != null)
            {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.ID,meetingId),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
                if (true)
                {
                    List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.POSITION),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted)),MeetingItem._POSITION);
                    if (!meetingItems.isEmpty())
                    {
                        rpcResponse = generatelistPositionsResponse(meetingItems,TtErrors.NoError);
                    }
                    else
                    {
                        rpcResponse = generatelistPositionsResponse(null,TtErrors.NoError);
                    }
                }
                else
                {
                    rpcResponse = generatelistPositionsResponse(null,TtErrors.PrivilageClassNotFound);
                }
            }
            else
            {
                rpcResponse = generatelistPositionsResponse(null,TtErrors.SessionIsNull);
            }

        }
        catch (Exception e)
        {
            rpcResponse = generatelistPositionsResponse(null,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse generateDeleteMapPositionsResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.DeleteMapPositions.ordinal());
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

    public RpcResponse generateUpdateMapPositionResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.UpdateMapPosition.ordinal());
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

    public RpcResponse addMapPositions(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        String DEFAULT_COMMENT = "default comment";
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            if (sessions != null)
            {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.ID,meetingId),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
                if (true)
                {

                    List<String> names = (List<String>) rpcRequest.getParams().get("Names");
                    List<Double> types = (List<Double>) rpcRequest.getParams().get("Types");
                    List<String> descriptions = (List<String>) rpcRequest.getParams().get("Descriptions");
                    List<String> fonts = (List<String>) rpcRequest.getParams().get("Fonts");
                    List<String> positions = (List<String>) rpcRequest.getParams().get("Positions");
                    List<String> colors = (List<String>) rpcRequest.getParams().get("Colors");
                    for (int INDEX = 0; INDEX < names.size() ; INDEX++)
                    {
                        Position position = new Position();
                        position.setName(names.get(INDEX));
                        position.setDescription(descriptions.get(INDEX));
                        position.setColor(colors.get(INDEX));
                        position.setFont(fonts.get(INDEX));
                        position.setPositionType(TtPositionType.getByOrdinal(types.get(INDEX).intValue()));
                        position.setPosition(positions.get(INDEX));
                        position.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                        save(position);
                        // insert meetingitem
                        MeetingItem meetingItem = new MeetingItem();
                        meetingItem.setDeleted(TtMeetingItemDeleted.Undeleted);
                        meetingItem.setComment(DEFAULT_COMMENT);
                        meetingItem.setItemType(TtItemType.POSITION);
                        meetingItem.setInserterUser(sessions.getServiceUser());
                        meetingItem.setMeeting(meeting);
                        meetingItem.setPosition(position);
                        meetingItem.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                        meetingItemServiceImp.save(meetingItem);

                    }
                    rpcResponse = generateAddMapPositionResponse(TtErrors.NoError);
                    // broadcast message
                    List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.POSITION)),MeetingItem._POSITION);
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_POSITIONCHANGE);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generatelistPositionsResponse(meetingItems,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());

                }
                else if (meeting == null)
                {
                    rpcResponse = generateAddMapPositionResponse(TtErrors.Meeting_MeetingIsNull);
                }
            }
            else
            {
                rpcResponse = generateAddMapPositionResponse(TtErrors.SessionIsNull);
            }
        }
        catch (Exception e)
        {
            rpcResponse = generateAddMapPositionResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
    public RpcResponse deleteMapPositions(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            if (sessions != null)
            {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(Meeting.ID,meetingId)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
                if (true)
                {
                    List<Double> positionIds = (List<Double>) rpcRequest.getParams().get("PositionIds");
                    List<MeetingItem> meetingItems = new ArrayList<>();
                    for (Double positionId:positionIds)
                    {
                        Position position = findBy(Restrictions.eq(Position.ID,positionId.intValue()));
                        if (position == null)
                        {
                            rpcResponse = generateDeleteMapPositionsResponse(TtErrors.MeetingItemIdNotExist);
                            return rpcResponse;
                        }
                        MeetingItem meetingItem = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem._POSITION,position),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted)),MeetingItem._POSITION);
                        if (meetingItem != null)
                        {
                            meetingItems.add(meetingItem);
                        }
                        else
                        {
                            rpcResponse = generateDeleteMapPositionsResponse(TtErrors.MeetingItemIdNotExist);
                            return rpcResponse;
                        }

                    }
                    for (MeetingItem meetingItem: meetingItems)
                    {
                        meetingItem.setDeleted(TtMeetingItemDeleted.Deleted);
                        meetingItemServiceImp.update(meetingItem);
                    }
                    rpcResponse = generateDeleteMapPositionsResponse(TtErrors.NoError);
                    List<MeetingItem> changedMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.POSITION)),MeetingItem._POSITION);
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_POSITIONCHANGE);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generatelistPositionsResponse(changedMeetingItems,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());

                }
                else
                {
                    rpcResponse = generateDeleteMapPositionsResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            }
            else
            {
                rpcResponse = generateDeleteMapPositionsResponse(TtErrors.SessionIsNull);
            }
        }
        catch (Exception e)
        {
            rpcResponse = generateDeleteMapPositionsResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse updateMapPosition(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");

            List<MeetingItem> meetingItems = new ArrayList<>();

            Integer positionId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("PositionId").toString())).intValue();
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
            Position position = findBy(Restrictions.eq(Position.ID,positionId));
            if (sessions != null && meeting != null)
            {
                if (roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER,sessions.getServiceUser()),Restrictions.eq(Room_ServiceUser._ROOM,meeting.getCurrentRoomMap().getRoom())))))
                {

                    String posision = (String) rpcRequest.getParams().get("Position");
                    String color = (String) rpcRequest.getParams().get("Color");
                    String fonts = (String) rpcRequest.getParams().get("Fonts");



                    //
                    position.setPosition(posision);
                    position.setColor(color);
                    position.setFont(fonts);
                    update(position);

                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_POSITIONCHANGE);
                    List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.POSITION),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted)),MeetingItem._INSERTER_USER);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generatelistPositionsResponse(changeMeetingItems,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());
                    rpcResponse = generateUpdateMapPositionResponse(TtErrors.NoError);
                }
                else
                {
                    rpcResponse = generateUpdateMapPositionResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            }
            else if (sessions == null || meeting == null)
            {
                rpcResponse = generateUpdateMapPositionResponse(TtErrors.SessionIsNull);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rpcResponse = generateUpdateMapPositionResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
}
