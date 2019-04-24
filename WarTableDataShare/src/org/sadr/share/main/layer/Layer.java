package org.sadr.share.main.layer;

import org.hibernate.validator.constraints.NotEmpty;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.layer._type.TtLayerStatus;
import org.sadr.share.main.layer._type.TtLayerType;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@PersianName("لایه ها")
@Entity
@Table(name = "Service.Layer")
public class Layer extends GenericDataModel<Layer> {
    public static final String ORDER = "order";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FILE_NAME = "fileName";
    public static final String UPLOAD_DATE_TIME = "uploadDateTime";
    public static final String _UPLOAD_USER = "uploaderUser";
    public static final String LAYER_STATUS = "layerStatus";



    public static final String TYPE = "type";
    public static final String SAMPLE_WIDTH = "samplesWidth";
    public static final String SAMPLE_HEIGHT = "samplesHeight";
    public static final String TILE_COUNT_HORZ = "tileCountHorz";
    public static final String TILE_COUNT_VERT = "tileCountVert";
    public static final String BOUND = "bounds";
    public static final String MIN_HEIGHT = "minHeight";
    public static final String MAX_HEIGHT = "maxHeight";
    public static final String  SPACING_HORZ = "spacingHorz";
    public static final String SPACING_VERT = "spacingVert";
    public static final String SPACING_HEIGHT = "spacingHeight";
    public static final String _MRMLS = "mrmls";
    public static final String _MAP = "map";
    public static final String LAYER_TYPE = "layerType";


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

    // static field end

    @Column(nullable = false)
    @PersianName("ترتیب نقشه")
    private int order;

    @Size(min = 2, max = 100)
    @PersianName("نام لایه")
    private String name;

    @Size(min = 2,max = 1000)
    @PersianName("توضیحات لایه")
    private String description;



    @PersianName("نام فایل لایه")
    private String fileName;

    @PersianName("زمان بارگذاری")
    private String uploadDateTime;

    @PersianName("کاربر بارگزار کننده")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceUser uploaderUser;

    @PersianName("وضعیت لایه")
    private TtLayerStatus layerStatus;


    @NotEmpty
    @PersianName("نوع لایه")
    private String type;


    @PersianName("عرض لایه")
    private int samplesWidth;

    @PersianName("ارتفاع لایه")
    private int samplesHeight;

    @PersianName("عرض لایه")
    private int tileCountHorz;

    @PersianName("عرض لایه")
    private int tileCountVert;

    @PersianName("عرض لایه")
    private String bounds;

    @PersianName("عرض لایه")
    private int minHeight;

    @PersianName("عرض لایه")
    private int maxHeight;

    @PersianName("عرض لایه")
    private double spacingHorz;

    @PersianName("عرض لایه")
    private double spacingVert;

    @PersianName("عرض لایه")
    private int spacingHeight;


    @PersianName("نقشه")
    @ManyToOne(fetch = FetchType.LAZY)
    private Map map;


    @PersianName("فرمت لایه")
    private TtLayerType layerType;

    @OneToMany(mappedBy = "meeting")
    @PersianName("جلسه نقشه اتاق لایه")
    private Set<Mrml> mrmls;


    // METHODS


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public ServiceUser getUploaderUser() {
        return uploaderUser;
    }

    public void setUploaderUser(ServiceUser uploaderUser) {
        this.uploaderUser = uploaderUser;
    }

    public TtLayerStatus getLayerStatus() {
        return layerStatus;
    }

    public void setLayerStatus(TtLayerStatus layerStatus) {
        this.layerStatus = layerStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSamplesWidth() {
        return samplesWidth;
    }

    public void setSamplesWidth(int samplesWidth) {
        this.samplesWidth = samplesWidth;
    }

    public int getSamplesHeight() {
        return samplesHeight;
    }

    public void setSamplesHeight(int samplesHeight) {
        this.samplesHeight = samplesHeight;
    }

    public int getTileCountHorz() {
        return tileCountHorz;
    }

    public void setTileCountHorz(int tileCountHorz) {
        this.tileCountHorz = tileCountHorz;
    }

    public int getTileCountVert() {
        return tileCountVert;
    }

    public void setTileCountVert(int tileCountVert) {
        this.tileCountVert = tileCountVert;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getSpacingHorz() {
        return spacingHorz;
    }

    public void setSpacingHorz(double spacingHorz) {
        this.spacingHorz = spacingHorz;
    }

    public double getSpacingVert() {
        return spacingVert;
    }

    public void setSpacingVert(double spacingVert) {
        this.spacingVert = spacingVert;
    }

    public int getSpacingHeight() {
        return spacingHeight;
    }

    public void setSpacingHeight(int spacingHeight) {
        this.spacingHeight = spacingHeight;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public TtLayerType getLayerType() {
        return layerType;
    }

    public void setLayerType(TtLayerType layerType) {
        this.layerType = layerType;
    }

    public Set<Mrml> getMrmls() {
        return mrmls;
    }

    public void setMrmls(Set<Mrml> mrmls) {
        this.mrmls = mrmls;
    }
}
