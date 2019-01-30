package org.sadr.service.main.rpc.rpcRequest;

import org.sadr.share.main._types.TtRpcFunction;

import java.util.Map;

public class RpcRequest {

    String jsonrpc ;
    TtRpcFunction method;
    Map params;
    int id;
    //TtProtocolType ttProtocolType;


    public RpcRequest() {
    }

    public RpcRequest(String jsonrpc, TtRpcFunction method, Map params, int id) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public TtRpcFunction getMethod() {
        return method;
    }

    public void setMethod(TtRpcFunction method) {
        this.method = method;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
