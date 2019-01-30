package org.sadr.share.main.roomPolls;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.pollVotes.PollVotes;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.roomPolls._types.TtRoomPollResult;
import org.sadr.share.main.roomPolls._types.TtRoomPollStatus;
import org.sadr.share.main.roomPolls._types.TtRoomPollType;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@PersianName("نظرسنجی ها")
@Entity
@Table(name = "Service.RoomPolls")

public class RoomPolls extends GenericDataModel<RoomPolls> implements Serializable {

    public static final String _ROOM = "room";
    public static final String POLL_TYPE = "pollType";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String END_TIME = "endDateTime";
    public static final String STATUS = "status";
    public static final String DESCRIPTION = "description";
    public static final String _POLL_CREATOR_USER = "pollCreatorUser";
    public static final String _POLL_TARGET_USER = "pollTargetUser";
    public static final String RESULT = "result";
    public static final String _VOTES = "votes";


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

    // sttaic fields end








    @PersianName("اتاق")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @PersianName("نوع نظرسنجی")
    private TtRoomPollType pollType;

    @PersianName("زمان ایجاد نظرسنجی")
    private String creationDateTime;

    @PersianName("زمان پایان نظرسنجی")
    private String endDateTime;

    @PersianName("وضعیت نظرسنجی")
    private TtRoomPollStatus status;

    @PersianName("توضیحات نظرسنجی")
    private String description;

    @PersianName("کاربر ایجاد کننده")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceUser pollCreatorUser;

    @PersianName("کاربر هدف")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceUser pollTargetUser;

    @PersianName("نتیجه نظرسنجی")
    private TtRoomPollResult result;

    @PersianName("رای ها")
    @OneToMany(mappedBy = "poll")
    private Set<PollVotes> votes;


    // METHODS


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TtRoomPollType getPollType() {
        return pollType;
    }

    public void setPollType(TtRoomPollType pollType) {
        this.pollType = pollType;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public TtRoomPollStatus getStatus() {
        return status;
    }

    public void setStatus(TtRoomPollStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceUser getPollCreatorUser() {
        return pollCreatorUser;
    }

    public void setPollCreatorUser(ServiceUser pollCreatorUser) {
        this.pollCreatorUser = pollCreatorUser;
    }

    public ServiceUser getPollTargetUser() {
        return pollTargetUser;
    }

    public void setPollTargetUser(ServiceUser pollTargetUser) {
        this.pollTargetUser = pollTargetUser;
    }

    public TtRoomPollResult getResult() {
        return result;
    }

    public void setResult(TtRoomPollResult result) {
        this.result = result;
    }

    public Set<PollVotes> getVotes() {
        return votes;
    }

    public void setVotes(Set<PollVotes> votes) {
        this.votes = votes;
    }
}
