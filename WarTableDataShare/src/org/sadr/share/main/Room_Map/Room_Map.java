package org.sadr.share.main.Room_Map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.Room_Map._types.TtRoomMapDeleted;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@PersianName("تخصیص نقشه به اتاق")
@Entity
@Table(name = "Service.Room_Map")
public class Room_Map extends GenericDataModel<Room_Map> implements Serializable {
    public static final String _MAP = "map";
    public static final String _ROOM = "room";
    public static final String _SERVICE_USER = "serviceUser";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String _MRML = "mrmls";
    public static final String $ROOM_TITLE = "roomTitle";
    public static final String $MAP_TITLE = "mapTitle";
    public static final String DELETED = "deleted";
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


    @PersianName("نقشه")
    @ManyToOne(fetch = FetchType.LAZY)
    private Map map;


    @PersianName("اتاق")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @PersianName("بارگذار نقشه به اتاق")
    @OneToOne
    private ServiceUser serviceUser;

    @Column(nullable = false)
    @PersianName("زمان ایجاد رکورد")
    String creationDateTime;


    @OneToMany(mappedBy = "roomMap")
    @PersianName("جلسه نقشه اتاق لایه")
    private Set<Mrml> mrmls;

    @PersianName("وضعیت حذف")
    private TtRoomMapDeleted deleted;


    // METHODS
    @PersianName("نام نقشه")
    public String getMapTitle() {
        return map == null ? "" : map.getName();
    }

    @PersianName("نام اتاق")
    public String getRoomTitle() {
        return room == null ? "" : room.getName();
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
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

    public TtRoomMapDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(TtRoomMapDeleted deleted) {
        this.deleted = deleted;
    }
}
