package org.sadr.share.main.room;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;

public interface RoomService extends GenericService<Room> {
    public RpcResponse listRooms(RpcRequest rpcRequest);

}
