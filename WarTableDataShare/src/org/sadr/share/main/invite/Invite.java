package org.sadr.share.main.invite;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.invite._types.TtInviteDeliver;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@PersianName("دعوت ها")
@Entity
@Table(name = "Service.Invite")

public class Invite extends GenericDataModel<Invite> implements Serializable {

    public static final String _MEETING = "meeting";
    public static final String _USER_INVITE = "userInvites";
    public static final String _INVITER_USER = "inviterUSer";
    public static final String DELIVERD = "deliverd" ;
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String SEND_INVITE_DATE_TIME = "sendInviteDateTime";
    public static final String _ROOM_SERVICE_USER = "roomServiceUsers";

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



    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;

   /* @OneToMany(mappedBy = "invite")
    @PersianName("کاربران دعوت شده")
    private Set<RoomServiceUserInvite> userInvites;*/

    @ManyToMany
    @JoinTable(name = "Service.RoomServiceUser_Invite",
        joinColumns = {
            @JoinColumn(name = "invite_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "roomServiceUser_id")})
    @PersianName("کاربران اتاق")
    private Set<Room_ServiceUser> roomServiceUsers;



    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر دعوت کننده")
    private Room_ServiceUser inviterUSer;

    @PersianName("وضعیت ارسال")
    private TtInviteDeliver deliverd;

    @PersianName("عنوان دعوتنامه")
    private String title;

    @PersianName("متن دعوتنامه")
    private String content;

    @PersianName("زمان ارسال دعوتنامه")
    private String sendInviteDateTime;



    // METHODS


    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }


    public Room_ServiceUser getInviterUSer() {
        return inviterUSer;
    }

    public void setInviterUSer(Room_ServiceUser inviterUSer) {
        this.inviterUSer = inviterUSer;
    }

    public TtInviteDeliver getDeliverd() {
        return deliverd;
    }

    public void setDeliverd(TtInviteDeliver deliverd) {
        this.deliverd = deliverd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendInviteDateTime() {
        return sendInviteDateTime;
    }

    public void setSendInviteDateTime(String sendInviteDateTime) {
        this.sendInviteDateTime = sendInviteDateTime;
    }

    public Set<Room_ServiceUser> getRoomServiceUsers() {
        return roomServiceUsers;
    }

    public void setRoomServiceUsers(Set<Room_ServiceUser> roomServiceUsers) {
        this.roomServiceUsers = roomServiceUsers;
    }
}
