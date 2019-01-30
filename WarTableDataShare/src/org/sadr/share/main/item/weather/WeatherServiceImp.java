package org.sadr.share.main.item.weather;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
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
import org.sadr.share.main.item.weather._types.TtWeatherType;
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
public class WeatherServiceImp extends GenericServiceImpl<Weather,WeatherDao> implements WeatherService {
    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;



    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    @Autowired
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    public RpcResponse generateAddMapWeatherResponse(MeetingItem meetingItem , TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.InsertWeather.ordinal());
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError) {
            result.put("ObjectInstanceId", meetingItem.getId());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        } else {
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        }
        return rpcResponse;
    }

    public RpcResponse generateGetMapWeatherList(List<MeetingItem> meetingItems , TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetWeathers.ordinal());
        Map result = new HashMap();
        ErrorResponse errorResponse =  new ErrorResponse();
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<Double> scales = new ArrayList<>();
        List<Double> rotations = new ArrayList<>();
        List<Double> posxs = new ArrayList<>();
        List<Double> posys = new ArrayList<>();
        List<Double> poszs = new ArrayList<>();
        List<Integer> types = new ArrayList<>();

        try
        {
            if (error == TtErrors.NoError)
            {
                if (meetingItems == null)
                {
                    errorResponse.setCode(error.ordinal());
                    errorResponse.setDescription(error.getErrorValueService());
                    rpcResponse.setResult(null);
                    rpcResponse.setError(errorResponse);
                    return rpcResponse;
                }
                for (MeetingItem meetingItem: meetingItems)
                {
                    ids.add(meetingItem.getId());
                    names.add(meetingItem.getWeather().getName());
                    descriptions.add(meetingItem.getWeather().getDescription());
                    scales.add(meetingItem.getScale());
                    rotations.add(meetingItem.getWeather().getRotation());
                    posxs.add(meetingItem.getPosX());
                    posys.add(meetingItem.getPosY());
                    poszs.add(meetingItem.getPosZ());
                    types.add(meetingItem.getItemType().ordinal());
                }
                result.put("Ids",ids);
                result.put("Descriptions", descriptions);
                result.put("Names",names);
                result.put("Scales",scales);
                result.put("Rotations", rotations);
                result.put("PosXs",posxs);
                result.put("PosYs", posys);
                result.put("PosZs",poszs);
                result.put("Types",types);
                rpcResponse.setResult(result);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);

            }
            else
            {
                rpcResponse.setResult(null);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);

            }

        }
        catch (Exception e)
        {
            rpcResponse.setResult(null);
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);

        }
        return rpcResponse;
    }
    public RpcResponse generateUpdateMapWeatherResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.UpdateWeather.ordinal());
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

    public RpcResponse generateDeleteMapWeatherResponse(TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.DeleteWeather.ordinal());
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

    public RpcResponse getWeathers(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
            Double a = new Double(doubleMeetingId);
            Integer meetingId = a.intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
            if (sessions != null)
            {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),Meeting._MEETING_ITEMS);
                if (meeting != null)
                {
                    List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.Weather)),MeetingItem._WEATHER);
                    if (!meetingItems.isEmpty())
                    {
                        rpcResponse = generateGetMapWeatherList(meetingItems,TtErrors.NoError);


                    }
                    else
                    {
                        rpcResponse = generateGetMapWeatherList(null,TtErrors.NoError);

                    }

                }
                else if (meeting == null)
                {
                    rpcResponse = generateGetMapWeatherList(null,TtErrors.Meeting_MeetingIsNull);

                }


            }
            else
            {
                rpcResponse = generateGetMapWeatherList(null,TtErrors.SessionIsNull);

            }


        }
        catch (Exception e)
        {
            rpcResponse = generateGetMapWeatherList(null,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }

    public RpcResponse sendWeather(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        String DEFAULT_COMMENT = "no comment";

        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            if (sessions != null)
            {
                double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
                Double a = new Double(doubleMeetingId);
                Integer meetingId = a.intValue();
                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(Meeting.ID,meetingId)),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
                if (meeting != null)
                {
                    String name = (String) rpcRequest.getParams().get("Name");
                    String description = (String) rpcRequest.getParams().get("Description");
                    double posx = Double.parseDouble((String) rpcRequest.getParams().get("PosX").toString());
                    double posy = Double.parseDouble((String) rpcRequest.getParams().get("PosY").toString());
                    double posz = Double.parseDouble((String) rpcRequest.getParams().get("PosZ").toString());
                    double scale = Double.parseDouble((String) rpcRequest.getParams().get("Scale").toString());
                    double rotation = Double.parseDouble((String) rpcRequest.getParams().get("Rotation").toString());
                    Integer type = new Double(Double.parseDouble((String) rpcRequest.getParams().get("Type").toString())).intValue();
                    Weather weather = new Weather();
                    weather.setWeatherType(TtWeatherType.getByOrdinal(type));
                    weather.setName(name);
                    weather.setDescription(description);
                    weather.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                    weather.setRotation(rotation);
                    save(weather);

                    if (weather != null)
                    {
                        MeetingItem meetingItem = new MeetingItem();
                        meetingItem.setPosX(posx);
                        meetingItem.setPosY(posy);
                        meetingItem.setPosZ(posz);
                        meetingItem.setScale(scale);
                        meetingItem.setComment(DEFAULT_COMMENT);
                        meetingItem.setInserterUser(sessions.getServiceUser());
                        meetingItem.setMeeting(meeting);
                        meetingItem.setItemType(TtItemType.Weather);
                        meetingItem.setWeather(weather);
                        meetingItem.setDeleted(TtMeetingItemDeleted.Undeleted);
                        meetingItem.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                        meetingItemServiceImp.save(meetingItem);

                       /* List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.Object)),MeetingItem._WEATHER,RePa.p__(MeetingItem._MRML,Mrml._LAYER),MeetingItem._INSERTER_USER);*/
                        List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.Weather)),MeetingItem._WEATHER);
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_WEATHER);
                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateGetMapWeatherList(meetingItems,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());
                        rpcResponse = generateAddMapWeatherResponse(meetingItem,TtErrors.NoError);
                    }
                    else if (weather == null)
                    {
                        rpcResponse = generateAddMapWeatherResponse(null,TtErrors.WeatherNotExist);
                    }

                }
                else if (meeting == null)
                {
                    rpcResponse = generateAddMapWeatherResponse(null,TtErrors.Meeting_MeetingIsNull);
                }
            }
            else
            {
                rpcResponse = generateAddMapWeatherResponse(null,TtErrors.SessionIsNull);
            }
        }
        catch (Exception e)
        {
            rpcResponse = generateAddMapWeatherResponse(null,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse updateWeather(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");

            List<MeetingItem> meetingItems = new ArrayList<>();

            Integer meetingItemId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("Id").toString())).intValue();

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            MeetingItem meetingItem = meetingItemServiceImp.findBy(Restrictions.eq(MeetingItem.ID, meetingItemId),MeetingItem._WEATHER,RePa.p__(MeetingItem._MEETING,Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));
            if (sessions != null && meetingItem != null)
            {
                if (true)
                {
                    Integer type = new Double(Double.parseDouble((String) rpcRequest.getParams().get("Type").toString())).intValue();
                    String name = (String) rpcRequest.getParams().get("Name");
                    String description = (String) rpcRequest.getParams().get("Description");
                    double posx = Double.parseDouble((String) rpcRequest.getParams().get("PosX").toString());
                    double posy = Double.parseDouble((String) rpcRequest.getParams().get("PosY").toString());
                    double posz = Double.parseDouble((String) rpcRequest.getParams().get("PosZ").toString());
                    double rotation = Double.parseDouble((String) rpcRequest.getParams().get("Rotation").toString());
                    double scale = Double.parseDouble((String) rpcRequest.getParams().get("Scale").toString());
                    //
                    meetingItem.setPosX(posx);
                    meetingItem.setPosY(posy);
                    meetingItem.setPosZ(posz);
                    meetingItem.setScale(scale);
                    Weather updateWeather = meetingItem.getWeather();
                    updateWeather.setRotation(rotation);
                    updateWeather.setName(name);
                    updateWeather.setDescription(description);
                    update(updateWeather);
                    /*meetingItem.getWeather().setRotation(rotation);
                    meetingItem.getWeather().setName(name);
                    meetingItem.getWeather().setDescription(description);*/
                    meetingItem.setWeather(updateWeather);
                    meetingItemServiceImp.update(meetingItem);
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_WEATHER);
                    List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meetingItem.getMeeting()),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.Weather),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted)),MeetingItem._INSERTER_USER);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateGetMapWeatherList(changeMeetingItems,TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage,meetingItem.getMeeting().getCurrentRoomMap().getRoom().getName());
                    rpcResponse = generateUpdateMapWeatherResponse(TtErrors.NoError);
                }
                else
                {
                    rpcResponse = generateUpdateMapWeatherResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            }
            else if (sessions == null || meetingItem == null)
            {
                rpcResponse = generateUpdateMapWeatherResponse(TtErrors.SessionIsNull);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rpcResponse = generateUpdateMapWeatherResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse deleteWeather(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
/*
            String test = ((String) rpcRequest.getParams().get("ObjectInstanceIds").toString());
*/
            List<Double> weatherInstanceIds =  new ArrayList<Double>();
            weatherInstanceIds = (List<Double>) rpcRequest.getParams().get("Ids");
            /*Double[] doubleObjectInstanceId = Double[].parseDouble((String) rpcRequest.getParams().get("ObjectInstanceIds").toString());
            Double[] objectInstanceId = doubleObjectInstanceId;*/
            /*Double[] a = new Double[](doubleObjectInstanceId);
            Integer objectInstanceId = a.intValue();*/


            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
            if (sessions != null)
            {
                Meeting meeting = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ID,weatherInstanceIds.get(0).intValue())),RePa.p__(MeetingItem._MEETING,Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM)).getMeeting();
                List<MeetingItem> meetingItems = new ArrayList<>();

                for (Double weatherInstanceId:weatherInstanceIds)
                {
                    MeetingItem meetingItem = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted),Restrictions.eq(MeetingItem.ID,weatherInstanceId.intValue())),RePa.p__(MeetingItem._MEETING,Meeting._CURRENT_ROOM_MAP,Room_Map._ROOM));

                    if (meetingItem != null)
                    {
                        meetingItems.add(meetingItem);

                    }
                    else if (meetingItem == null)
                    {
                        rpcResponse = generateDeleteMapWeatherResponse(TtErrors.Meeting_MeetingIsNull);

                    }

                }
                for (MeetingItem meetingItem: meetingItems)
                {
                    meetingItem.setDeleted(TtMeetingItemDeleted.Deleted);
                    meetingItemServiceImp.update(meetingItem);
                }
                rpcResponse.setId(TtGlobalId.Update.ordinal());
                List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_WEATHER);
                List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.Weather),Restrictions.eq(MeetingItem.DELETED,TtMeetingItemDeleted.Undeleted)),MeetingItem._WEATHER,MeetingItem._INSERTER_USER);
                String broadcastMessage;
                if (!changeMeetingItems.isEmpty())
                {
                    broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,generateGetMapWeatherList(changeMeetingItems,TtErrors.NoError));
                }
                else
                {
                    rpcResponse.setId(TtGlobalId.Update.ordinal());
                    broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags,rpcResponse);

                }
                BrokerUtils.broadcastMessage(broadcastMessage,meetingItems.get(0).getMeeting().getCurrentRoomMap().getRoom().getName());
                rpcResponse = generateDeleteMapWeatherResponse(TtErrors.NoError);

            }
            else
            {
                rpcResponse = generateDeleteMapWeatherResponse(TtErrors.SessionIsNull);
            }

        }
        catch (Exception e)
        {
            rpcResponse = generateDeleteMapWeatherResponse(TtErrors.OperationalErrorOccured);

        }
        return rpcResponse;
    }
}
