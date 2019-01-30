package org.sadr.service.main.http.controller;

import com.google.gson.Gson;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._core.Dispatch;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.map.MapServiceImp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Controller {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }

    @POST
    @Path("/")
    @Consumes("application/json-rpc")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_OCTET_STREAM})
    public Response post(String request) {
        RpcRequest protocol = new Gson().fromJson(request,RpcRequest.class);


         if (protocol.getMethod() == TtRpcFunction.GetMapTile )
        {
            MapServiceImp mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
             java.io.File file =  mapServiceImp.getMapTile(protocol);
             if (file != null) {
                 Response.ResponseBuilder response = Response.ok((Object) file);
                 return response.type(MediaType.APPLICATION_OCTET_STREAM).build();
             }
             else
             {
                 RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(protocol);
                 return Response.status(200).entity(new Gson().toJson(rpcResponse)).type("application/json-rpc").build();

             }

        }
        else if (protocol.getMethod() == TtRpcFunction.GetObject)
         {
             ObjectServiceImp objectServiceImp = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
             java.io.File file =  objectServiceImp.getObject(protocol);
             if (file != null) {
                 Response.ResponseBuilder response = Response.ok((Object) file);
                 return response.type(MediaType.APPLICATION_OCTET_STREAM).build();
             }
             else
             {
                 RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(protocol);
                 return Response.status(200).entity(new Gson().toJson(rpcResponse)).type("application/json-rpc").build();
             }

         }
        else {
            String response = Dispatch.dispatchFunction(protocol);
            RpcResponse rpcResponse = new Gson().fromJson(response,RpcResponse.class);
            System.out.println(response);
            return Response.status(200).entity(response).type("application/json-rpc").build();

        }
    }
}
