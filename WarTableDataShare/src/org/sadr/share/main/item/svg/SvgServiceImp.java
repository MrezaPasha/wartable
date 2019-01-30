package org.sadr.share.main.item.svg;

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
public class SvgServiceImp extends GenericServiceImpl<Svg,SvgDao> implements SvgService {

    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;


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

    public RpcResponse generateListSvgResponse(List<MeetingItem> meetingItems , TtErrors error)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListSvg.ordinal());
        Map result = new HashMap();
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError)
        {
            for (MeetingItem meetingItem:meetingItems)
            {
                ids.add(meetingItem.getSvg().getId());
                names.add(meetingItem.getSvg().getName());
                fileNames.add(meetingItem.getSvg().getFileName());
                
            }
            result.put("Ids",ids);
            result.put("Names",names);
            result.put("Filenames",fileNames);
            rpcResponse.setResult(result);


        }
        else
        {
            rpcResponse.setResult(result);
        }
        return rpcResponse;
    }

    public RpcResponse listSvg(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(MeetingItem.ITEM_TYPE,TtItemType.SVG)),MeetingItem._SVG);
                    if (!meetingItems.isEmpty())
                    {
                        rpcResponse = generateListSvgResponse(meetingItems,TtErrors.NoError);
                    }
                    else
                    {
                        rpcResponse = generateListSvgResponse(null,TtErrors.NoSvgFoundInThisMeeting);
                    }
                }
            }
            else {
                rpcResponse = generateListSvgResponse(null,TtErrors.Meeting_MeetingIsNull);
            }
        }
        catch (Exception e)
        {
            rpcResponse = generateListSvgResponse(null,TtErrors.OperationalErrorOccured);
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

    public RpcResponse deleteSvg(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        String broadcastMessage;
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();

                Meeting meeting = meetingServiceImp.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer svgId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("SVGId").toString())).intValue();
                    Svg svg = findBy(Restrictions.eq(Svg.ID,svgId));
                    if (svg != null) {
                        delete(svg);
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteSvg,TtErrors.NoError);
                        // broadcast message
                        List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_VECTORCHANGE);
                        List<MeetingItem> meetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING,meeting),Restrictions.eq(Meeting.DELETED,TtMeetingDeleted.Undeleted),Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.SVG)),MeetingItem._SVG);
                        broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.DeleteVector,TtErrors.NoError,broadcastFlags,generateListSvgResponse(meetingItems,TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage,meeting.getCurrentRoomMap().getRoom().getName());
                    }
                    else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteSvg,TtErrors.SvgNotExist);
                    }
                }
                else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteSvg,TtErrors.Meeting_MeetingIsNull);
                }
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.DeleteSvg,TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }


    public File getMapSvg(RpcRequest rpcRequest)
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
                    Integer svgId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("SVGId").toString())).intValue();
                    Svg svg = findBy(Restrictions.eq(Svg.ID, svgId));
                    if (svg != null) {
                        File file = new File("./ftp/svg/" + svg.getFileName());
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
