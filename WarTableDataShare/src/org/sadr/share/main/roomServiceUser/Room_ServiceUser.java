package org.sadr.share.main.roomServiceUser;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtMemberStatus;
import org.sadr.share.main._types.TtUserRoleFlags;
import org.sadr.share.main.invite.Invite;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserEnablePrivateCommunication;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserPresence;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@PersianName("تخصیص کاربر به اتاق")
@Entity
@Table(name = "Service.Room_ServiceUser")
public class Room_ServiceUser extends GenericDataModel<Room_ServiceUser> implements Serializable {

    public static final String _ROOM = "room";
    public static final String _SERVICE_USER = "serviceUser";
    public static final String JOIN_DATE_TIME = "joinDateTime";
    public static final String ACCEPT_PRIVATE_CHAT = "acceptPrivateChat";
    public static final String ACCEPT_PRIVATE_TALK = "acceptPrivateTalk";
    public static final String USER_STATUS = "userStatus";
    public static final String TEMPORARY_USER_ROLE_FLAG = "temporaryUserRoleFlag";
    public static final String PERMANENT_USER_ROLE_FLAG = "permanentUserRoleFlag";
    public static final String _INVITES = "invites";
    public static final String PRESENCE = "presence";
    public static final String $SERVICE_USER_FULL_NAME = "serviceUserFullName";
    public static final String $ROOM_TITLE = "roomTitle";
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

//#########******#######// StaticFields: End //


    @PersianName("اتاق")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @PersianName("کاربر")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceUser serviceUser;


    @Column(nullable = false)
    @Size(min = 2,max = 30)
    @PersianName("زمان تخصیص اتاق به کاربر")
    private String joinDateTime;

    @NotNull
    @PersianName("مجوز ارتباط متنی خصوصی ")
    private TtRoomServiceUserEnablePrivateCommunication acceptPrivateChat;

    @NotNull
    @PersianName("مجوز ارتباط صوتی خصوصی ")
    private TtRoomServiceUserEnablePrivateCommunication acceptPrivateTalk;


    @NotNull
    @PersianName("نقش کاربر")
    private TtMemberStatus userStatus;


    ////////////////////////////

    @PersianName("سطح دسترسی موقت")
    private TtUserRoleFlags temporaryUserRoleFlag;

    @NotNull
    @PersianName("سطح دسترسی دائمی")
    private TtUserRoleFlags permanentUserRoleFlag;


    @OneToMany(mappedBy = "onlineRoom")
    @PersianName("کاربران آنلاین اتاق")
    private Set<ServiceUser> serviceUsers;

    @ManyToMany
    @JoinTable(name = "Service.RoomServiceUser_Invite",
        joinColumns = {
            @JoinColumn(name = "roomServiceUser_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = " invite_id")})
    @PersianName("دعوتنامه ها")
    private Set<Invite> invites;

    @NotNull
    @PersianName("وضعیت حضور کاربر")
    private TtRoomServiceUserPresence presence;


    // METHODS

    @PersianName("نام کاربر")
    public String getServiceUserFullName() {
        return serviceUser == null ? "" : serviceUser.getFullName();
    }

    @PersianName("نام اتاق")
    public String getRoomTitle() {
        return room == null ? "" : room.getName();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }


    public String getJoinDateTime() {
        return joinDateTime;
    }

    public void setJoinDateTime(String joinDateTime) {
        this.joinDateTime = joinDateTime;
    }

    public TtRoomServiceUserEnablePrivateCommunication getAcceptPrivateChat() {
        return acceptPrivateChat;
    }

    public void setAcceptPrivateChat(TtRoomServiceUserEnablePrivateCommunication acceptPrivateChat) {
        this.acceptPrivateChat = acceptPrivateChat;
    }

    public TtRoomServiceUserEnablePrivateCommunication getAcceptPrivateTalk() {
        return acceptPrivateTalk;
    }

    public void setAcceptPrivateTalk(TtRoomServiceUserEnablePrivateCommunication acceptPrivateTalk) {
        this.acceptPrivateTalk = acceptPrivateTalk;
    }

    public TtMemberStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(TtMemberStatus userStatus) {
        this.userStatus = userStatus;
    }

    public TtUserRoleFlags getTemporaryUserRoleFlag() {
        return temporaryUserRoleFlag;
    }

    public void setTemporaryUserRoleFlag(TtUserRoleFlags temporaryUserRoleFlag) {
        this.temporaryUserRoleFlag = temporaryUserRoleFlag;
    }

    public TtUserRoleFlags getPermanentUserRoleFlag() {
        return permanentUserRoleFlag;
    }

    public void setPermanentUserRoleFlag(TtUserRoleFlags permanentUserRoleFlag) {
        this.permanentUserRoleFlag = permanentUserRoleFlag;
    }

    public Set<Invite> getInvites() {
        return invites;
    }

    public void setInvites(Set<Invite> invites) {
        this.invites = invites;
    }

    public TtRoomServiceUserPresence getPresence() {
        return presence;
    }

    public void setPresence(TtRoomServiceUserPresence presence) {
        this.presence = presence;
    }

    public Set<ServiceUser> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(Set<ServiceUser> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }
}
