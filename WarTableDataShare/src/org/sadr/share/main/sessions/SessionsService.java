package org.sadr.share.main.sessions;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.share.main.serviceUser.ServiceUser;

public interface SessionsService extends GenericService<Sessions> {
    public void insertOrUpdateSession(ServiceUser user,String sessionId);
    public void updateSession(RpcRequest rpcRequest);
}
