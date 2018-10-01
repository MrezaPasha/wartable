package org.sadr.share.main.material.material;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.material._map.Material_Map;
import org.sadr.share.main.material.area.MaterialArea;
import org.sadr.share.main.material.group.MaterialGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Material")
public class Material extends GenericDataModel<Material> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MATERIAL_MAPS = "material_maps";public static final String _MATERIAL_AREAS = "materialAreas";public static final String _FILE = "file";public static final String _MATERIAL_GROUPS = "materialGroups";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @OneToMany(mappedBy = "material")
    @PersianName("نقشه ها")
    private Set<Material_Map> material_maps;

        @OneToMany(mappedBy = "material")
    @PersianName("نواحی")
    private Set<MaterialArea> materialAreas;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @PersianName("فایل")
//    private File file;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Service.Material.Material_Group",
        joinColumns = {
            @JoinColumn(name = "material_id")},
        inverseJoinColumns = {
            @JoinColumn(name = "group_id")})
    @PersianName("گروه ها")
    private Set<MaterialGroup> materialGroups;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"materialId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
