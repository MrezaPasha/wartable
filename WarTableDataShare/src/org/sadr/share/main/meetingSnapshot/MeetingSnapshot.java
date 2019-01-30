package org.sadr.share.main.meetingSnapshot;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("نقاط بازگشت")
@Entity
@Table(name = "Service.MeetingSnapshot")
public class MeetingSnapshot extends GenericDataModel<MeetingSnapshot> implements Serializable {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String _CREATOR_USER = "creatorUser";
    public static final String _MEETINg = "meeting";
    public static final String _MRMLS = "mrmls";

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






    @PersianName("نام")
    private String name;

    @PersianName("توضیحات")
    private String description;

    @PersianName("زمان ایجاد نقطه بازگشت")
    private String creationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر ایجاد کنندهنقطه بازگشت")
    private ServiceUser creatorUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;

    @ManyToMany
    @JoinTable(name = "Service.MeetingSnapshot_Mrml",
        joinColumns = {
            @JoinColumn(name = "meetingSnapshot_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "mrml_id")})
    @PersianName("تنظیمات لایه ها")
    private Set<Mrml> mrmls;


    // METHODS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ServiceUser getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(ServiceUser creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Set<Mrml> getMrmls() {
        return mrmls;
    }

    public void setMrmls(Set<Mrml> mrmls) {
        this.mrmls = mrmls;
    }
}
