package org.sadr.share.main.layer._map;

import org.sadr.share.main.layer.layer.Layer;
import org.sadr.share.main.map.map.Map;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Layer_Map")
public class Layer_Map extends GenericDataModel<Layer_Map> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MAP = "map";public static final String _LAYER = "layer";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("نقشه")
    private Map map;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("لایه")
    private Layer layer;


    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"layer_MapId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
