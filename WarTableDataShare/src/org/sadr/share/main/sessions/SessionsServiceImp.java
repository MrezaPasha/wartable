package org.sadr.share.main.sessions;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main._types.TtSessionsState;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SessionsServiceImp extends GenericServiceImpl<Sessions,SessionsDao> implements SessionsService {
    SessionsServiceImp sessionsServiceImp;
    RpcResponse rpcResponse = new RpcResponse();

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    public void insertOrUpdateSession(ServiceUser user, String sessionId)
    {
       /* Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER,user));
        if (sessions != null && sessions.getStatus() == TtSessionsState.Active)
        {
            sessions.setSessionId(sessionId);
            sessions.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessions.setPrivilageFlag(user.getPrivilageClass().getPrivilageFlag());
            sessionsServiceImp.update(sessions);
        }
        else if (sessions == null)
        {
            Sessions newSession = new Sessions();
            newSession.setSessionId(sessionId);
            newSession.setPrivilageFlag(user.getPrivilageClass().getPrivilageFlag());
            newSession.setServiceUser(user);
            newSession.setStatus(TtSessionsState.Active);
            newSession.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            newSession.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessionsServiceImp.save(newSession);

        }*/
    }
    public void updateSession(RpcRequest rpcRequest)
    {
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId));
            if (sessions != null)
            {
                sessions.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
                sessionsServiceImp.update(sessions);

            }
            LoggerBL.log(TtRpcFunction.UpdateUserSession.name(),sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser().getUserName(),sessionId,TtActionType.Report,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.Report,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
        }
        catch (Exception e)
        {
            LoggerBL.log(TtRpcFunction.UpdateUserSession.name(),rpcRequest.getParams().get("Username").toString(),rpcRequest.getParams().get("SessionId").toString(),Utils.ObjToJson(rpcRequest),TtErrors.SessionUpdateFailed.getErrorValueService());

        }
    }
    public void insertNewSession(RpcRequest rpcRequest, ServiceUser user , String sessionId)
    {
        try
        {
            Sessions sessions = new Sessions();
            sessions.setServiceUser(user);
            sessions.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessions.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessions.setSessionId(sessionId);
            sessions.setPrivilageFlag(null);
            sessions.setStatus(TtSessionsState.Active);
            sessionsServiceImp.save(sessions);
            LoggerBL.log(TtRpcFunction.InsertNewSession.name(),sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER).getServiceUser().getUserName(),sessionId,TtActionType.Report,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.Report,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
        }
        catch (Exception e)
        {
            LoggerBL.log(rpcRequest.getMethod().toString(),user.getUserName(),sessionId,Utils.ObjToJson(rpcRequest),TtErrors.NewSessionInsertError.geterrorValue());

        }

    }
}
