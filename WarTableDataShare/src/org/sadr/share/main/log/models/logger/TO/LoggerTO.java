package org.sadr.share.main.log.models.logger.TO;

import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtSendStatus;

import java.io.Serializable;

public class LoggerTO implements Serializable {

    private String log;
    private TtImportance importance;
    private TtActionType actionType;
    private boolean sendstate;
    private TtSendStatus sendStatus;
    private String sendTime;
    private String deliverDateTime;


    // Constructors


    public LoggerTO() {
    }

    public LoggerTO(String log, TtImportance importance, TtActionType actionType, boolean sendstate, TtSendStatus sendStatus, String sendTime, String deliverDateTime) {
        this.log = log;
        this.importance = importance;
        this.actionType = actionType;
        this.sendstate = sendstate;
        this.sendStatus = sendStatus;
        this.sendTime = sendTime;
        this.deliverDateTime = deliverDateTime;
    }

// Getters & Setters


    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public TtImportance getImportance() {
        return importance;
    }

    public void setImportance(TtImportance importance) {
        this.importance = importance;
    }

    public TtActionType getActionType() {
        return actionType;
    }

    public void setActionType(TtActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isSendstate() {
        return sendstate;
    }

    public void setSendstate(boolean sendstate) {
        this.sendstate = sendstate;
    }

    public TtSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(TtSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(String deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }
}
