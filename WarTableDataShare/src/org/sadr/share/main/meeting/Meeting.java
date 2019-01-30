package org.sadr.share.main.meeting;


import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.invite.Invite;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meeting._types.TtMeetingStatus;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingLog.MeetingLog;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.room.Room;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@PersianName("جلسات")
@Entity
@Table(name = "Service.Meeting")
public class Meeting extends GenericDataModel<Meeting> implements Serializable {

    public static final String _ROOM = "room";
    public static final String _CURRENT_ROOM_MAP = "currentRoomMap";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String GOAL = "goal";
    public static final String RESULT = "result";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String _MRMLS = "mrmls";
    public static final String _MEETING_ITEMS = "meetingItems";
    public static final String _MEETING_LOGS = "meetingLogs";
    public static final String _MEETING_SETTING = "meetingSettings";
    public static final String _INVITES = "invites";
    public static final String _PRIVATE_TALKS = "privateTalks";
    public static final String BOARD_TEXT = "boardText";
    public static final String DELETED = "deleted";
    public static final String STATUS = "status";
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

    // static fields end


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اتاق")
    private Room room;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("نقشه جاری جلسه")
    private Room_Map currentRoomMap;


    @PersianName("نام جلسه")
    @Size(max = 65)
    private String name;

    @PersianName("توضیحات جلسه")
    @Size(max = 65)
    private String description;

    @PersianName("هدف جلسه")
    @Size(max = 65)
    private String goal;

    @PersianName("نتیجه جلسه")
    @Size(max = 65)
    private String result;


    @PersianName("تاریخ شروع جلسه")
    private String creationDateTime;

    @OneToMany(mappedBy = "meeting")
    @PersianName("جلسه نقشه اتاق لایه")
    private Set<Mrml> mrmls;

    @OneToMany(mappedBy = "meeting")
    @PersianName("اشیای جلسه")
    private Set<MeetingItem> meetingItems;

    @OneToMany(mappedBy = "meeting")
    @PersianName("لاگ های مرتبط")
    private Set<MeetingLog> meetingLogs;


    @OneToMany(mappedBy = "meeting")
    @PersianName("تنظیمات جلسه")
    private Set<MeetingSetting> meetingSettings;


    @OneToMany(mappedBy = "meeting")
    @PersianName("دعوتنامه")
    private Set<Invite> invites;


    @OneToMany(mappedBy = "meeting")
    @PersianName("تماس های خصوصی")
    private Set<PrivateTalk> privateTalks;


    @PersianName("عنوان تابلو")
    private String boardText;

    @PersianName("وضعیت حضور جلسه")
    private TtMeetingDeleted deleted;

    @PersianName("وضعیت جلسه")
    private TtMeetingStatus status;


    // Methods

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

    public Room_Map getCurrentRoomMap() {
        return currentRoomMap;
    }

    public void setCurrentRoomMap(Room_Map currentRoomMap) {
        this.currentRoomMap = currentRoomMap;
    }

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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Set<Mrml> getMrmls() {
        return mrmls;
    }

    public void setMrmls(Set<Mrml> mrmls) {
        this.mrmls = mrmls;
    }

    public Set<MeetingItem> getMeetingItems() {
        return meetingItems;
    }

    public void setMeetingItems(Set<MeetingItem> meetingItems) {
        this.meetingItems = meetingItems;
    }

    public Set<MeetingLog> getMeetingLogs() {
        return meetingLogs;
    }

    public void setMeetingLogs(Set<MeetingLog> meetingLogs) {
        this.meetingLogs = meetingLogs;
    }

    public Set<MeetingSetting> getMeetingSettings() {
        return meetingSettings;
    }

    public void setMeetingSettings(Set<MeetingSetting> meetingSettings) {
        this.meetingSettings = meetingSettings;
    }

    public Set<Invite> getInvites() {
        return invites;
    }

    public void setInvites(Set<Invite> invites) {
        this.invites = invites;
    }

    public Set<PrivateTalk> getPrivateTalks() {
        return privateTalks;
    }

    public void setPrivateTalks(Set<PrivateTalk> privateTalks) {
        this.privateTalks = privateTalks;
    }

    public String getBoardText() {
        return boardText;
    }

    public void setBoardText(String boardText) {
        this.boardText = boardText;
    }

    public TtMeetingDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(TtMeetingDeleted deleted) {
        this.deleted = deleted;
    }

    public TtMeetingStatus getStatus() {
        return status;
    }

    public void setStatus(TtMeetingStatus status) {
        this.status = status;
    }
}
