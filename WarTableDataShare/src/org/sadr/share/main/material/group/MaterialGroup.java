package org.sadr.share.main.material.group;

import org.sadr.share.main.material.material.Material;
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
@Table(name = "Service.Material.Group")
public class MaterialGroup extends GenericDataModel<MaterialGroup> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MATERIALS = "materials";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Service.Material.Material_Group",
        joinColumns = {
            @JoinColumn(name = "group_id")},
        inverseJoinColumns = {
            @JoinColumn(name = "material_id")})
    @PersianName("اشیاء")
    private Set<Material> materials;
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"materialGroupId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
