package org.sadr.share.main.item.vector;

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
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class VectorServiceImp extends GenericServiceImpl<Vector,VectorDao> implements VectorService {

    private MeetingItemServiceImp meetingItemServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;


    @Autowired
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }


    public RpcResponse generateListVectorResponse(List<MeetingItem> meetingItems , TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListVector.ordinal());
        Map result = new HashMap();
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        List<String> types = new ArrayList<>();
        List<Double> opacities =  new ArrayList<>();
        List<String> colors = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError)
        {
            for (MeetingItem meetingItem:meetingItems)
            {
                ids.add(meetingItem.getVector().getId());
                names.add(meetingItem.getVector().getName());
                fileNames.add(meetingItem.getVector().getFileName());
                types.add(meetingItem.getVector().getVectorType());
                opacities.add(meetingItem.getVector().getOpacity());
                colors.add(meetingItem.getVector().getColor());
            }
            result.put("Ids",ids);
            result.put("Names",names);
            result.put("Filenames",fileNames);
            result.put("Opacities",opacities);
            result.put("Colors",colors);
            rpcResponse.setResult(result);


        }
        else
        {
            rpcResponse.setResult(result);
        }
        return rpcResponse;
    }

    public RpcResponse generateGenericSuccessResponse(TtGlobalId globalId, TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(globalId.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        Map result = new HashMap();
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



    public RpcResponse listvector(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.VECTOR)),MeetingItem._VECTOR);
                    if (!meetingItems.isEmpty())
                    {
                        rpcResponse = generateListVectorResponse(meetingItems,TtErrors.NoError);
                    }
                    else
                    {
                        rpcResponse = generateListVectorResponse(null,TtErrors.NoSvgFoundInThisMeeting);
                    }
                }
            }
            else {
                rpcResponse = generateListVectorResponse(null,TtErrors.Meeting_MeetingIsNull);
            }
        }
        catch (Exception e)
        {
            rpcResponse = generateListVectorResponse(null,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse updateVectorOpacity(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer vectorId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("VectorId").toString())).intValue();
                    Vector vector = findBy(Restrictions.eq(Vector.ID,vectorId));
                    if (vector != null) {
                        Double opacity = new Double(Double.parseDouble((String) rpcRequest.getParams().get("VectorId").toString()));
                        vector.setOpacity(opacity);
                        update(vector);
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.NoError);
                        // broadcast message
                        List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_VECTORCHANGE);
                        List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.VECTOR)),MeetingItem._VECTOR);
                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.NoError,broadcastFlags,generateListVectorResponse(meetingItems,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());


                    }
                    else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.VectorNotExist);
                    }
                }
                else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.Meeting_MeetingIsNull);
                }
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse updateVectorColor(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer vectorId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("VectorId").toString())).intValue();
                    Vector vector = findBy(Restrictions.eq(Vector.ID,vectorId));
                    if (vector != null) {
                        String color = (String) rpcRequest.getParams().get("VectorId").toString();
                        vector.setColor(color);
                        update(vector);
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorColor,TtErrors.NoError);
                        // broadcast message
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_VECTORCHANGE);
                        List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.VECTOR)),MeetingItem._VECTOR);
                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.UpdateVectorOpacity,TtErrors.NoError,broadcastFlags,generateListVectorResponse(meetingItems,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());


                    }
                    else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorColor,TtErrors.VectorNotExist);
                    }
                }
                else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorColor,TtErrors.Meeting_MeetingIsNull);
                }
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.UpdateVectorColor,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse deleteVector(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        String broadcastMessage;
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer vectorId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("VectorId").toString())).intValue();
                    Vector vector = findBy(Restrictions.eq(Vector.ID,vectorId));
                    if (vector != null) {
                        delete(vector);
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteVector,TtErrors.NoError);
                        // broadcast message
                        List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_VECTORCHANGE);
                        List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.VECTOR)),MeetingItem._VECTOR);
                        broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.DeleteVector,TtErrors.NoError,broadcastFlags,generateListVectorResponse(meetingItems,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());
                    }
                    else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteVector,TtErrors.VectorNotExist);
                    }
                }
                else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteVector,TtErrors.Meeting_MeetingIsNull);
                }
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteVector,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }


    public File getMapVector(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        String broadcastMessage;
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer vectorId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("VectorId").toString())).intValue();
                    Vector vector = findBy(Restrictions.eq(Vector.ID, vectorId));
                    if (vector != null) {
                        File file = new File("./ftp/vector/" + vector.getFileName());
                        if (file.exists()) {
                            return file;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        return null;


    }
}
