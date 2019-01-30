package org.sadr.share.main.serviceUser;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;

public interface ServiceUserService extends GenericService<ServiceUser>{
    public RpcResponse loginServiceUser(RpcRequest rpcRequest);
}
