package org.sadr.share.main.serviceUser;

import org.hibernate.validator.constraints.NotEmpty;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtServiceUserState;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.groupPolicy.GroupPolicy;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.personModel.PersonModel;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.startupNotice.item.StartupNoticeItem;
import org.sadr.share.main.textChat.TextChat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@PersianName("کاربران سرویس")
@Entity
@Table(name = "Service.ServiceUser")
public class ServiceUser extends GenericDataModel<ServiceUser> implements Serializable {

    // start static fields
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String FAMILY = "family";
    public static final String _GRADE = "grade";
    public static final String _ORG_POSITION = "orgPosition";
    public static final String DELETED = "deleted";
    public static final String BANNED = "banned";
    public static final String LAST_IP_ADDRESS = "lastIpAddress";
    public static final String LAST_SIGNIN_DATETIME = "lastSigninDateTime";
    public static final String LOGIN_COUNT = "loginCount";
    public static final String LAST_PASSWORD = "lastPassword";
    public static final String CREATION_TIME = "creationDateTime";
    public static final String FAILED_PASSWORD = "failedPasswordCount";
    public static final String FAILED_TIME = "failedDateTime";
    public static final String _LAST_ROOM = "lastRoom";
    public static final String _ROOM_SERVICE_USER = "room_serviceUsers";
    public static final String _USER_MODEL = "userModel";
    public static final String _GROUP_POLICY = "groupPolicy";
    public static final String _ONLINE_ROOM = "onlineRoom";
    public static final String _TEXT_CHATS = "textChats";
    public static final String _PRIVATE_TALKS = "privateTalks";
    public static final String $GRAGE_TITLE = "gradeTitle";
    public static final String $ORG_POSITION_TITLE= "orgPositionTitle";
    public static final String _STARTUP_NOTICE_ITEMS= "startupNoticeItems";

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


    @PersianName("نام کاربری")
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String userName;

    @PersianName("گذرواژه")
    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @PersianName("نام")
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @PersianName("نام خانوادگی")
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String family;


    @PersianName("درجه سازمانی")
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="grade_fk")
    private Grade grade;


    @PersianName("جایگاه سازمانی")
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="orgPosition_fk")
    private OrgPosition orgPosition;


    @PersianName("کاربر حذف شده")
    private TtServiceUserState deleted;

    @PersianName("کاربر غیر مجاز")
    private TtServiceUserState banned;

    @PersianName("تعداد دفعات ورود به سیستم")
    private int loginCount;


    @PersianName("گذرواژه قبلی")
    @Size(max = 100)
    private String lastPassword;


    @PersianName("تاریخ ثبت کاربر")
    @Size(max = 30)
    private String creationDateTime;


    @PersianName("تعداد دفعات ورود ناموفق")
    private int failedPasswordCount;

    @PersianName("زمان آخرین ورود ناموفق")
    @Size(max = 30)
    private String failedDateTime;

    @PersianName("آخرین زمان ورود")
    @Size(max = 30)
    private String lastSigninDateTime;

    @PersianName("آخرین آدرس آی پی ورود")
    @Size(max = 100)
    private String lastIpAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("آخرین اتاق وارد شده")
    private Room lastRoom;

    @OneToMany(mappedBy = "serviceUser")
    @PersianName("اتاق ها")
    private Set<Room_ServiceUser> room_serviceUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اتاق فعال")
    private Room_ServiceUser onlineRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مدل کاربر")
    private PersonModel userModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مجوز گروهی")
    private GroupPolicy groupPolicy;


    /////////////////////////
    /////////////////////////
    /////////////////////////
    /*@OneToMany(mappedBy = "serviceUser")
    @PersianName("تماس های خصوصی")
    private Set<PrivateTalk_ServiceUser> privateTalkServiceUsers;*/

    @OneToMany(mappedBy = "serviceUser")
    @PersianName("فراخوان ها")
    private Set<StartupNoticeItem> startupNoticeItems;

    @ManyToMany
    @JoinTable(name = "Service.TextChat_ServiceUser",
            joinColumns = {
                    @JoinColumn(name = "serviceUser_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "textChat_id")})

    @PersianName("پیام ها")
    private Set<TextChat> textChats;

    @ManyToMany
    @JoinTable(name = "Service.PrivateTalk_ServiceUser",
            joinColumns = {
                    @JoinColumn(name = "serviceUser_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "privateTalk_id")})

    @PersianName("تماس ها")
    private Set<PrivateTalk> privateTalks;


    // METHODS


    @PersianName("درجه سازمانی")
    public String getGradeTitle() {
        return grade != null ? grade.getValue() : "";
    }

    @PersianName("جایگاه سازمانی")
    public String getOrgPositionTitle() {
        return orgPosition != null ? orgPosition.getValue() : "";
    }


    public String getFullName() {
        return name + " " + family;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public OrgPosition getOrgPosition() {
        return orgPosition;
    }

    public void setOrgPosition(OrgPosition orgPosition) {
        this.orgPosition = orgPosition;
    }


    public TtServiceUserState getDeleted() {
        return deleted;
    }

    public void setDeleted(TtServiceUserState deleted) {
        this.deleted = deleted;
    }

    public TtServiceUserState getBanned() {
        return banned;
    }

    public void setBanned(TtServiceUserState banned) {
        this.banned = banned;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public int getFailedPasswordCount() {
        return failedPasswordCount;
    }

    public void setFailedPasswordCount(int failedPasswordCount) {
        this.failedPasswordCount = failedPasswordCount;
    }

    public String getFailedDateTime() {
        return failedDateTime;
    }

    public void setFailedDateTime(String failedDateTime) {
        this.failedDateTime = failedDateTime;
    }

    public Room getLastRoom() {
        return lastRoom;
    }

    public void setLastRoom(Room lastRoom) {
        this.lastRoom = lastRoom;
    }


    public Set<Room_ServiceUser> getRoom_serviceUsers() {
        return room_serviceUsers;
    }

    public void setRoom_serviceUsers(Set<Room_ServiceUser> room_serviceUsers) {
        this.room_serviceUsers = room_serviceUsers;
    }

    public PersonModel getUserModel() {
        return userModel;
    }

    public void setUserModel(PersonModel userModel) {
        this.userModel = userModel;
    }

    public GroupPolicy getGroupPolicy() {
        return groupPolicy;
    }

    public void setGroupPolicy(GroupPolicy groupPolicy) {
        this.groupPolicy = groupPolicy;
    }


    public Set<TextChat> getTextChats() {
        return textChats;
    }

    public void setTextChats(Set<TextChat> textChats) {
        this.textChats = textChats;
    }

    public Set<PrivateTalk> getPrivateTalks() {
        return privateTalks;
    }

    public void setPrivateTalks(Set<PrivateTalk> privateTalks) {
        this.privateTalks = privateTalks;
    }

    public String getLastIpAddress() {
        return lastIpAddress;
    }

    public void setLastIpAddress(String lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    public String getLastSigninDateTime() {
        return lastSigninDateTime;
    }

    public void setLastSigninDateTime(String lastSigninDateTime) {
        this.lastSigninDateTime = lastSigninDateTime;
    }

    public Room_ServiceUser getOnlineRoom() {
        return onlineRoom;
    }

    public void setOnlineRoom(Room_ServiceUser onlineRoom) {
        this.onlineRoom = onlineRoom;
    }

    public Set<StartupNoticeItem> getStartupNoticeItems() {
        return startupNoticeItems;
    }

    public void setStartupNoticeItems(Set<StartupNoticeItem> startupNoticeItems) {
        this.startupNoticeItems = startupNoticeItems;
    }


    // METHOD End


}
