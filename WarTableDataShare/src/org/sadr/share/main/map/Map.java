package org.sadr.share.main.map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main._types.TtMapCategory;
import org.sadr.share.main.layer.Layer;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@PersianName("نقشه")
@Entity
@Table(name = "Service.Map")
public class Map extends GenericDataModel<Map> implements Serializable {

    // static fields
    public static final String NAME = "name";
    public static final String DESCRIPTION ="descriptions";
    public static final String FILE_NAME = "fileName";
    public static final String FILE_SIZE = "fileSize";
    public static final String CREATION_TIME = "creationDateTime";
    public static final String UPDATE_TIME = "updateDateTime";
    public static final String CATEGORY = "category";
    public static final String _ASSIGN_ROOMS = "assignRooms";
    public static final String _LAYERS = "layers";



    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public Map() {
    }

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

    // static field end

    @Size(max = 100)
    @PersianName("نام نقشه")
    private String name;


    @Size(max = 1000)
    @PersianName("توضیحات نقشه")
    private String descriptions;


    @Size(max = 1000)
    @PersianName("آدرس نقشه")
    private String fileName;


    @PersianName("سایز فایل نقشه")
    private double fileSize;



    @Size(max = 100)
    @PersianName("زمان ساخت نقشه")
    private String creationDateTime;


    @Size(max = 100)
    @PersianName("زمان آخرین بروزرسانی")
    private String updateDateTime;


    @PersianName("دسته بندی")
    private TtMapCategory category;

    @OneToMany(mappedBy = "map")
    @PersianName("اتاق ها")
    private Set<Room_Map> assignRooms;

    @OneToMany(mappedBy = "map")
    @PersianName("لایه ها")
    private Set<Layer> layers;






    // METHODS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public TtMapCategory getCategory() {
        return category;
    }

    public void setCategory(TtMapCategory category) {
        this.category = category;
    }

    public Set<Room_Map> getAssignRooms() {
        return assignRooms;
    }

    public void setAssignRooms(Set<Room_Map> assignRooms) {
        this.assignRooms = assignRooms;
    }

    public Set<Layer> getLayers() {
        return layers;
    }

    public void setLayers(Set<Layer> layers) {
        this.layers = layers;
    }
}
