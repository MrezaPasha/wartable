package org.sadr.service.main.rpc.rpcResponse;

import java.util.Map;

public class RpcResponse {
    int id;
    Map result;
    ErrorResponse error;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map getResult() {
        return result;
    }

    public void setResult(Map result) {
        this.result = result;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public RpcResponse(int id, Map result, ErrorResponse error) {
        this.id = id;
        this.result = result;
        this.error = error;
    }

    public RpcResponse() {
    }
}
