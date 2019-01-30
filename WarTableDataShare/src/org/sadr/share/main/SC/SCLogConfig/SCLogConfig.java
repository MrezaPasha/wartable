package org.sadr.share.main.SC.SCLogConfig;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtSendStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@PersianName("تنظیمات لاگ سامانه کنترل امنیت")
@Entity
@Table(name = "Service.SCLogConfig")
public class SCLogConfig extends GenericDataModel<SCLogConfig> implements Serializable {

    public static final String ACTION_TYPE = "actionType";
    public static final String SEND_STATE = "sendState";
    public static final String IMPORTANCE = "importance";
    public static final String SEND_STATUS = "sendStatus";
    public static final String SEND_TIME = "sendTime";
    public static final String SEND_DELAY_MINUTE_TIME = "sendDelayMinuteTime";

    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public static void setAvrColumns(String acts, String virts, String rels) {
        if (acts != null) {
            actColumns = acts.split(",");
        }
        if (virts != null) {
            virColumns = virts.split(",");
        }
        if (rels != null) {
            relColumns = rels.split(",");
        }
    }

    public static String[] getActColumns() {
        return actColumns;
    }

    public static String[] getVirColumns() {
        return virColumns;
    }

    public static String[] getRelColumns() {
        return relColumns;
    }

    // static fields end


    @PersianName("عملیات کلی")
    @Column(nullable = false)
    private TtActionType actionType;

    @PersianName("ارسال یا عدم ارسال")
    @Column(nullable = false)
    private boolean sendState;

    @PersianName("درجه اهمیت لاگ")
    @Column(nullable = false)
    private TtImportance importance;

    @PersianName("وضعیت ارسال لاگ")
    @Column(nullable = false)
    private TtSendStatus sendStatus;

    @PersianName("زمان ارسال لاگ")
    @Column()
    private String sendTime;

    @PersianName("مقدار تاخیر در ارسال لاگ")
    @Column()
    private int sendDelayMinuteTime;


    // METHODS


    public TtActionType getActionType() {
        return actionType;
    }

    public void setActionType(TtActionType actionType) {
        this.actionType = actionType;
    }

    public boolean getSendState() {
        return sendState;
    }

    public void setSendState(boolean sendState) {
        this.sendState = sendState;
    }

    public TtImportance getImportance() {
        return importance;
    }

    public void setImportance(TtImportance importance) {
        this.importance = importance;
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

    public int getSendDelayMinuteTime() {
        return sendDelayMinuteTime;
    }

    public void setSendDelayMinuteTime(int sendDelayMinuteTime) {
        this.sendDelayMinuteTime = sendDelayMinuteTime;
    }
}
