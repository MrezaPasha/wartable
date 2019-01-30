package org.sadr.share.main.room;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
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
public class RoomServiceImp extends GenericServiceImpl<Room,RoomDao> implements RoomService {
    private RpcResponse rpcResponse = new RpcResponse();
    private RoomServiceImp roomServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private RpcResponse
    generateListRoomsResponse(RpcRequest rpcRequest, List<Room> rooms , TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(rpcRequest.getId());
        List<Long> roomIds = new ArrayList<>();
        List<String> roomName = new ArrayList<String>();
        Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        if (!rooms.isEmpty())
        {
            for (Room room:rooms)
            {
                System.out.println(room.getId());
                System.out.println(room.getName());
                roomIds.add(room.getId());
                roomName.add(room.getName());
            }
            result.put("RoomIds",roomIds);
            result.put("Rooms",roomName);
            rpcResponse.setResult(result);
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setError(errorResponse);
        }
        else if (rooms.isEmpty())
        {
            rpcResponse.setResult(result);
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        }
        return rpcResponse;
    }



    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }

    public RpcResponse listRooms(RpcRequest rpcRequest)
    {
        try
        {

             String sessionId = (String) rpcRequest.getParams().get("SessionId");
            sessionsServiceImp.updateSession(rpcRequest);
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            if (sessions != null)
            {
                List<Room> rooms = roomServiceImp.findAll();
                rpcResponse = generateListRoomsResponse(rpcRequest,rooms,TtErrors.NoError);
                LoggerBL.log(TtRpcFunction.ListRooms.name(),sessions.getServiceUser().getUserName(),sessions.getSessionId(),TtActionType.Report,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.Report,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                //return rpcResponse;

            }
            else if (sessions == null)
            {
                rpcResponse = generateListRoomsResponse(rpcRequest,null,TtErrors.ListRoomIsNull);
                LoggerBL.log(TtRpcFunction.ListRooms.name(),sessions.getServiceUser().getUserName(),sessions.getSessionId(),TtActionType.Report,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.Report,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                //return rpcResponse;

            }

        }
        catch (Exception e)
        {
            System.out.println(e);
            rpcResponse = generateListRoomsResponse(rpcRequest,null,TtErrors.SessionIsNull);
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            String username = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser().getUserName();
            LoggerBL.log(TtRpcFunction.ListRooms.name(),username,sessionId,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));

        }
        return rpcResponse;
    }


}
