package org.sadr.share.main.privateTalk;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._utils.ShareUtils;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.privateTalk._types.TtPrivateTalkStatus;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("مکالمات خصوصی")
@Entity
@Table(name = "Service.PrivateTalk")
public class PrivateTalk extends GenericDataModel<PrivateTalk> implements Serializable {


    public static final String _SERVICE_USERS = "serviceUsers";
    public static final String _MEETING = "meeting";
    public static final String START_DATE_TIME = "startDateTime";
    public static final String END_DATE_TIME = "endDateTime";
    public static final String STATUS = "status";
    public static final String FILE_NAME = "fileName";
    public static final String SIZE = "size";
    public static final String _REQUEST_USER = "requestUser";
    public static final String _JOINED_USERS = "joinedServiceUsers";
/*
    public static final String CALL_NUMBER = "callNumber";
*/


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




    /*@OneToMany(mappedBy = "privateTalk")
    @PersianName("کاربران")
    private Set<PrivateTalk_ServiceUser> privateTalkServiceUsers;*/

    @ManyToMany
    @JoinTable(name = "Service.PrivateTalk_ServiceUser",
            joinColumns = {
                    @JoinColumn(name = "privateTalk_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "serviceUser_id")})
    @PersianName("کاربران")
    private Set<ServiceUser> serviceUsers;

    @ManyToMany
    @JoinTable(name = "Service.PrivateTalk_JoinedServiceUser",
            joinColumns = {
                    @JoinColumn(name = "privateTalk_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "serviceUser_id")})

    @PersianName("کاربران آنلاین")
    private Set<ServiceUser> joinedServiceUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر درخواست دهنده")
    private ServiceUser requestUser;


    @PersianName("زمان شروع تماس خصوصی")
    private String startDateTime;

    @PersianName("زمان پایان تماس خصوصی")
    private String endDateTime;


    @PersianName("وضعیت تماس خصوصی")
    private TtPrivateTalkStatus status;

    @PersianName("نام فایل")
    private String fileName;


    @PersianName("سایز فایل")
    private double size;

    /*@PersianName("شماره جلسه خصوصی")
    private String callNumber;*/


    // METHODS
    public String getSoundPath() {
        return ShareUtils.getTalkFileAddress(fileName);
    }


    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public TtPrivateTalkStatus getStatus() {
        return status;
    }

    public void setStatus(TtPrivateTalkStatus status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Set<ServiceUser> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(Set<ServiceUser> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    public ServiceUser getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(ServiceUser requestUser) {
        this.requestUser = requestUser;
    }

    public Set<ServiceUser> getJoinedServiceUsers() {
        return joinedServiceUsers;
    }

    public void setJoinedServiceUsers(Set<ServiceUser> joinedServiceUSer) {
        this.joinedServiceUsers = joinedServiceUSer;
    }

    /*public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }*/
}
