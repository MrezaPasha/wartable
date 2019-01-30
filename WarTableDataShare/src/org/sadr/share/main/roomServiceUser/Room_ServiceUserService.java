package org.sadr.share.main.roomServiceUser;

import org.sadr._core.meta.generic.GenericService;

public interface Room_ServiceUserService extends GenericService<Room_ServiceUser> {
    //public RpcResponse listRoomUsers(RpcRequest rpcRequest);
    //public RpcResponse joinRoom(RpcRequest rpcRequest);
    public  boolean isUserHaveAdminPrivilages(Room_ServiceUser roomServiceUser);
}
