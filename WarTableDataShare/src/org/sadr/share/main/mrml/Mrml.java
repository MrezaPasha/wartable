package org.sadr.share.main.mrml;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingSnapshot.MeetingSnapshot;
import org.sadr.share.main.mrml._types.TtMrmlDeleted;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@PersianName("تنظیمات جلسه و میز و لایه ها")
@Entity
@Table(name = "Service.Mrml")
public class Mrml extends GenericDataModel<Mrml> implements Serializable {


    public static final String _ROOM_MAP = "roomMap";
    public static final String _MEETING = "meeting";
/*
    public static final String _MEETING_ITEM = "meetingItem";
*/
    public static final String _LAYER = "layer";
    public static final String ORDER = "order";
    public static final String OPACITY= "opacity";
    public static final String DELETED = "deleted";
    public static final String _MEETING_SNAPSHOT = "meetingSnapshots";

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
    @PersianName("نقشه و اتاق")
    private Room_Map roomMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;


    /*@ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اشیای اختصاص داده شده به لایه")
    private MeetingItem meetingItem;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("لایه")
    private Layer layer;

    @PersianName("ترتیب")
    private long order;

    @PersianName("شفافیت")
    private double opacity;

    @PersianName("حذف")
    private TtMrmlDeleted deleted;


    @ManyToMany
    @JoinTable(name = "Service.MeetingSnapshot_Mrml",
        joinColumns = {
            @JoinColumn(name = "mrml_id", nullable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "meetingSnapshot_id")})
    @PersianName("نقطه بازگشت")
    private Set<MeetingSnapshot> meetingSnapshots;

    // METHODS


    public Room_Map getRoomMap() {
        return roomMap;
    }

    public void setRoomMap(Room_Map roomMap) {
        this.roomMap = roomMap;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    /*public MeetingItem getMeetingItem() {
        return meetingItem;
    }

    public void setMeetingItem(MeetingItem meetingItem) {
        this.meetingItem = meetingItem;
    }*/

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public TtMrmlDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(TtMrmlDeleted deleted) {
        this.deleted = deleted;
    }

    public Set<MeetingSnapshot> getMeetingSnapshots() {
        return meetingSnapshots;
    }

    public void setMeetingSnapshots(Set<MeetingSnapshot> meetingSnapshots) {
        this.meetingSnapshots = meetingSnapshots;
    }
}
