package org.sadr.share.main.room;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main._types.TtRoomType;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.room._types.TtBusyType;
import org.sadr.share.main.room._types.TtRoomAccessSetting;
import org.sadr.share.main.room._types.TtRoomRecSetting;
import org.sadr.share.main.roomModel.RoomModel;
import org.sadr.share.main.roomPolls.RoomPolls;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.textChat.TextChat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@PersianName("اتاق")
@Entity
@Table(name = "Service.Room")
public class Room extends GenericDataModel<Room> implements Serializable {

    // static fields
    public static final String ROOM_TYPE = "roomType";
    public static final String ROOM_NAME = "name";
    public static final String DESCRIPTIONS = "descriptions";
    public static final String TABLE_LENGHT = "tableLenght";
    public static final String TABLE_WIDTH = "tableWidth";
    public static final String _CURRENT_MEETING = "currentMeeting";
    public static final String _ROOM_MAPS = "roomMaps";
    public static final String _ROOM_SERVICEUSERS = "room_serviceUsers" ;
    public static final String _ROOM_MAP = "room_maps";
    public static final String _MEETINGS = "meetings";
    public static final String _ROOM_MODELS = "roomModel";
    public static final String _POLLS = "polls";
    public static final String _TEXT_CHATS= "textChats";
    public static final String REC_SETTING = "recSetting";
    public static final String ACCESS_SETTING = "recSetting";
    public static final String BUSY_TYPE = "busyType";
    public static final String $CURRENT_MEETING_NAME = "currentMeetingName";
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




    @Column(nullable = false)
    @NotNull
    @PersianName("نوع اتاق")
    private TtRoomType roomType;


    @Column(nullable = false )
    @Size(min = 2, max = 100)
    @PersianName("نام اتاق")
    private String name;


    @Column(nullable = false)
    @Size(min = 2, max = 1000)
    @PersianName("توضیحات اتاق")
    private String descriptions;

    @Column(nullable = false)
    @NotNull
    @PersianName("طول میز اتاق")
    private double tableLenght;

    @Column(nullable = false)
    @NotNull
    @PersianName("عرض میز اتاق")
    private double tableWidth;

    @PersianName("شماره جلسه جاری")
    @OneToOne()
    private Meeting currentMeeting;


    @OneToMany(mappedBy = "room")
    @PersianName("کاربران اتاق")
    private Set<Room_ServiceUser> room_serviceUsers ;


    @OneToMany(mappedBy = "room")
    @PersianName("نقشه ها")
    private Set<Room_Map> room_maps ;

    @OneToMany(mappedBy = "room")
    @PersianName("جلسه ها")
    private Set<Meeting> meetings  ;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مدل اتاق")
    private RoomModel roomModel;

    @OneToMany(mappedBy = "room")
    @PersianName("نظرسنجی های اتاق")
    private Set<RoomPolls> polls;

    @PersianName("تنظیمات ضبط اتاق")
    private TtRoomRecSetting recSetting;

    @PersianName("دسترسی برای تغییرات تنظیمات ضبط")
    private TtRoomAccessSetting accessSetting;

    @PersianName("وضعیت دسترسی")
    private TtBusyType busyType;



    @PersianName("جلسه فعال")
    public String getCurrentMeetingName()
    {
        return currentMeeting != null ? currentMeeting.getName() : "";
    }


    @OneToMany(mappedBy = "room")
    @PersianName("چت ها")
    private Set<TextChat> textChats;

    // getter and setter


    public TtRoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(TtRoomType roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getTableLenght() {
        return tableLenght;
    }

    public void setTableLenght(double tableLenght) {
        this.tableLenght = tableLenght;
    }

    public double getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(double tableWidth) {
        this.tableWidth = tableWidth;
    }



    public Set<Room_ServiceUser> getRoom_serviceUsers() {
        return room_serviceUsers;
    }

    public void setRoom_serviceUsers(Set<Room_ServiceUser> room_serviceUsers) {
        this.room_serviceUsers = room_serviceUsers;
    }

    public Set<Room_Map> getRoom_maps() {
        return room_maps;
    }

    public void setRoom_maps(Set<Room_Map> room_maps) {
        this.room_maps = room_maps;
    }

    public Meeting getCurrentMeeting() {
        return currentMeeting;
    }

    public void setCurrentMeeting(Meeting currentMeeting) {
        this.currentMeeting = currentMeeting;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public Set<RoomPolls> getPolls() {
        return polls;
    }

    public void setPolls(Set<RoomPolls> polls) {
        this.polls = polls;
    }

    public TtRoomRecSetting getRecSetting() {
        return recSetting;
    }

    public void setRecSetting(TtRoomRecSetting recSetting) {
        this.recSetting = recSetting;
    }

    public TtRoomAccessSetting getAccessSetting() {
        return accessSetting;
    }

    public void setAccessSetting(TtRoomAccessSetting accessSetting) {
        this.accessSetting = accessSetting;
    }

    public TtBusyType getBusyType() {
        return busyType;
    }

    public void setBusyType(TtBusyType busyType) {
        this.busyType = busyType;
    }


    public Set<TextChat> getTextChats() {
        return textChats;
    }

    public void setTextChats(Set<TextChat> textChats) {
        this.textChats = textChats;
    }
}
