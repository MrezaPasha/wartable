package org.sadr.service.main.http.controller;

import com.google.gson.Gson;
import org.sadr._core.utils.OutLog;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._core.Dispatch;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeService;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.map.MapServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class TomcatController {

    GradeService gradeService;

    @Autowired
    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = {"application/x-www-form-urlencoded; charset=UTF-8"},
            produces = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Consumes("application/json-rpc")
//    @Produces()
    //@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_OCTET_STREAM})
    public Response post(@RequestParam(value = "param", required = false) String request) {
        RpcRequest protocol = new Gson().fromJson(request, RpcRequest.class);


        if (protocol.getMethod() == TtRpcFunction.GetMapTile) {
            MapServiceImp mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
            java.io.File file = mapServiceImp.getMapTile(protocol);
            if (file != null) {
                Response.ResponseBuilder response = Response.ok((Object) file);
                return response.type(MediaType.APPLICATION_OCTET_STREAM).build();
            } else {
                RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(protocol);
                return Response.status(200).entity(new Gson().toJson(rpcResponse)).type("application/json-rpc").build();

            }

        } else if (protocol.getMethod() == TtRpcFunction.GetObject) {
            ObjectServiceImp objectServiceImp = (ObjectServiceImp) IOCContainer.GetBeans(ObjectServiceImp.class);
            java.io.File file = objectServiceImp.getObject(protocol);
            if (file != null) {
                Response.ResponseBuilder response = Response.ok((Object) file);
                return response.type(MediaType.APPLICATION_OCTET_STREAM).build();
            } else {
                RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(protocol);
                return Response.status(200).entity(new Gson().toJson(rpcResponse)).type("application/json-rpc").build();
            }

        } else {
            String response = Dispatch.dispatchFunction(protocol);
            RpcResponse rpcResponse = new Gson().fromJson(response, RpcResponse.class);
            System.out.println(response);
            return Response.status(200).entity(response).type("application/json-rpc").build();

        }
    }


    @RequestMapping(value = "/x", method = RequestMethod.GET)
    public String get() {
        OutLog.pl("1212");

      /*  ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
        List<ServiceUser> all = serviceUserServiceImp.findAll();
        for (ServiceUser map : all) {

            OutLog.p(map.getName());
        }*/


        List<Grade> all = gradeService.findAll();

        for (Grade g : all) {
            OutLog.p(g.getValue());
        }

        OutLog.pl();

        return "";
    }


}
