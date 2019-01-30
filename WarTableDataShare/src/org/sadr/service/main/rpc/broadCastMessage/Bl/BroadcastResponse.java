package org.sadr.service.main.rpc.broadCastMessage.Bl;

import com.google.gson.Gson;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.model.BroadCast;
import org.sadr.service.main.rpc.broadCastMessage.model.BroadCastMessage;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BroadcastResponse implements Serializable {
    public static String generateBroadcastResponse(TtGlobalId globalId , TtErrors errors , List<Integer> updateFlags , RpcResponse... rpcResponses)
    {
        String broadCastString = new String();
        try
        {
            BroadCast broadCast = new BroadCast();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(errors.ordinal());
            errorResponse.setDescription(errors.getErrorValueService());
            broadCast.setId(globalId.ordinal());
            broadCast.setError(errorResponse);
            List<String> allRpcResponse = new ArrayList<String>();
            for (RpcResponse rpcResponse: rpcResponses)
            {
                if (rpcResponse.getId() == 0)
                {
                    allRpcResponse.add("");
                }
                else
                {
                    allRpcResponse.add(new Gson().toJson(rpcResponse));
                }
            }
            BroadCastMessage broadCastMessage = new BroadCastMessage();
            broadCastMessage.setUpdateFlags(updateFlags);
            broadCastMessage.setBroadCastFields(allRpcResponse);
            broadCast.setBroadCastMessage(broadCastMessage);
            broadCastString = new Gson().toJson(broadCast);
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
        return broadCastString;
    }
}
