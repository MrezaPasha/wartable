package org.sadr.share.main.map.map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.layer._map.Layer_Map;
import org.sadr.share.main.map._room.Map_Room;
import org.sadr.share.main.map.region.MapRegion;
import org.sadr.share.main.material._map.Material_Map;
import org.sadr.share.main.meeting.meeting.Meeting;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Map")
public class Map extends GenericDataModel<Map> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MAP = "map";public static final String _LAYER = "layer";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;}
//#########******#######// StaticFields: End //

    ///############################## RELATIONS

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("ناحیه جغرافیایی")
    private MapRegion mapRegion;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @PersianName("فایل")
//    private File file;

    @OneToMany(mappedBy = "map")
    @PersianName("اشیاء")
    private Set<Material_Map> material_maps;

    @OneToMany(mappedBy = "map")
    @PersianName("لایه ها")
    private Set<Layer_Map> layer_maps;

    @OneToMany(mappedBy = "map")
    @PersianName("جلسات")
    private Set<Meeting> meetings;

    @OneToMany(mappedBy = "map")
    @PersianName("اتاق ها")
    private Set<Map_Room> map_rooms;



    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"mapId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
