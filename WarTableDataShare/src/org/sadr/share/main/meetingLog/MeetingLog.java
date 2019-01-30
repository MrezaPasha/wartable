package org.sadr.share.main.meetingLog;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@PersianName("لاگ های جلسه")
@Entity
@Table(name = "Service.MeetingLog")
public class MeetingLog extends GenericDataModel<MeetingLog> implements Serializable {



    public static final String REQUEST = "request";
    public static final String _MEETING = "meeting";
    public static final String _USER = "user";
    public static final String DESCRIPTION = "description";
    public static final String RESPONSE = "response";
    public static final String METHOD = "method";
    public static final String CREATION_DATE_TIME = "creationDateTime";


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

    // static fields end





    @PersianName("درخواست")
    private String request;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private ServiceUser user;

    @PersianName("توضیحات")
    private String description;

    @PersianName("پاسخ")
    private String response;

    @PersianName("نام سرویس")
    private TtRpcFunction method;

    @PersianName("تاریخ درج لاگ")
    private String creationDateTime;


    // METHODS


    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public ServiceUser getUser() {
        return user;
    }

    public void setUser(ServiceUser user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public TtRpcFunction getMethod() {
        return method;
    }

    public void setMethod(TtRpcFunction method) {
        this.method = method;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
