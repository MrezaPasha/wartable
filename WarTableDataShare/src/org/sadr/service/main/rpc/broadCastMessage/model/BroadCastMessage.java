package org.sadr.service.main.rpc.broadCastMessage.model;

import java.io.Serializable;
import java.util.List;

public class BroadCastMessage implements Serializable {
    private List<Integer> updateFlags;
    List<String> broadCastFields;

    // Constructors


    public BroadCastMessage() {
    }

    public BroadCastMessage(List<Integer> updateFlags, List<String> broadCastFields) {
        this.updateFlags = updateFlags;
        this.broadCastFields = broadCastFields;
    }

    // Getters and Setters


    public List<Integer> getUpdateFlags() {
        return updateFlags;
    }

    public void setUpdateFlags(List<Integer> updateFlags) {
        this.updateFlags = updateFlags;
    }

    public List<String> getBroadCastFields() {
        return broadCastFields;
    }

    public void setBroadCastFields(List<String> broadCastFields) {
        this.broadCastFields = broadCastFields;
    }
}
