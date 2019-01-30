package org.sadr.service.main.rpc.broadCastMessage.model;

import com.google.gson.Gson;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;

import java.io.Serializable;

public class BroadCast implements Serializable {
    private int id;
    private  BroadCastMessage broadCastMessage;
    private ErrorResponse error;


    // Constructors


    public BroadCast() {
    }

    public BroadCast(int id, BroadCastMessage broadCastMessage, ErrorResponse error) {
        this.id = id;
        this.broadCastMessage = broadCastMessage;
        this.error = error;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BroadCastMessage getBroadCastMessage() {
        return broadCastMessage;
    }

    public void setBroadCastMessage(BroadCastMessage broadCastMessage) {
        this.broadCastMessage = broadCastMessage;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public String convertToJson()
    {
        try
        {
            String json = new Gson().toJson(this);
            return json;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
