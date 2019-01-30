package org.sadr.service.main.rpc.rpcResponse;

public class ErrorResponse {
    int code;
    String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ErrorResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public ErrorResponse() {
    }
}
