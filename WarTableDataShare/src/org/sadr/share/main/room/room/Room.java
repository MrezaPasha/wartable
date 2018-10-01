package org.sadr.share.main.room.room;

import org.sadr.share.main.admin.account.account.Account;
import org.sadr.share.main.map._room.Map_Room;
import org.sadr.share.main.meeting.meeting.Meeting;
import org.sadr.share.main.room._account.Room_Account;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Room")
public class Room extends GenericDataModel<Room> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MAP = "map";public static final String _ROOM = "room";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر مدیر")
    private Account adminAccount;

    @OneToMany(mappedBy = "room")
    @PersianName("کاربران")
    private Set<Room_Account> room_accounts;

    @OneToMany(mappedBy = "room")
    @PersianName("نقشه ها")
    private Set<Map_Room> map_rooms;

    @OneToMany(mappedBy = "room")
    @PersianName("جلسات")
    private Set<Meeting> meetings;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه فعال")
    private Meeting activeMeeting;



    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"roomId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
