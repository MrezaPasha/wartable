package org.sadr.share.main.roomModel;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@PersianName("مدل های اتاق")
@Entity
@Table(name = "Service.RoomModel")

public class RoomModel extends GenericDataModel<RoomModel> implements Serializable {


    public static final String FILENAME  = "fileName";
    public static final String NAME  = "name";
    public static final String SCALE = "scale";
    public static final String SIZE  = "size";
    public static final String _UPLOAD_USER  = "uploaderUser";
    public static final String UPLOAD_DATE_TIME  = "uploadDateTime";
    public static final String _ASSIGN_ROOMS = "assignRooms";


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


    @PersianName("اسم شی")
    private String fileName;

    @PersianName("نام ")
    private String name;

    @PersianName("ضریب اندازه مدل")
    private double scale;

    @PersianName("اندازه فایل شی")
    private double size;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر بارگذار کننده شی")
    private ServiceUser uploaderUse;

    @PersianName("زمان بارگذاری شی")
    private String uploadDateTime;


    @OneToMany(mappedBy = "roomModel")
    @PersianName("اتاق ها")
    private Set<Room> assignRooms;


    // METHODS


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public ServiceUser getUploaderUse() {
        return uploaderUse;
    }

    public void setUploaderUse(ServiceUser uploaderUse) {
        this.uploaderUse = uploaderUse;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public Set<Room> getAssignRooms() {
        return assignRooms;
    }

    public void setAssignRooms(Set<Room> assignRooms) {
        this.assignRooms = assignRooms;
    }
}
