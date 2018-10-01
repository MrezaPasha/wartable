package org.sadr.share.main.meeting.meeting;

import org.sadr.share.main.map.map.Map;
import org.sadr.share.main.meeting._account.Meeting_Account;
import org.sadr.share.main.room.room.Room;
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
@Table(name = "Service.Meeting")
public class Meeting extends GenericDataModel<Meeting> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MAP = "map";public static final String _ROOM = "room";public static final String _MEETING_ACCOUNTS = "meeting_accounts";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("نقشه")
    private Map map;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("اتاق")
    private Room room;

    @OneToMany(mappedBy = "meeting")
    @PersianName("کاربران")
    private Set<Meeting_Account> meeting_accounts;


    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"meetingId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
