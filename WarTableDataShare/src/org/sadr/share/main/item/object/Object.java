package org.sadr.share.main.item.object;

import org.hibernate.validator.constraints.NotEmpty;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.item.object._types.TtObjectPrivacy;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@PersianName("المان های روی میز")
@Entity
@Table(name = "Service.Object")

public class Object extends GenericDataModel<Object> implements Serializable {

    public static final String FILE_NAME = "fileName";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String AREA = "area";
    public static final String SIZE = "size";
    public static final String _UPLOADER_USER = "uploaderUser";
    public static final String UPLOAD_DATE_TIME = "uploadDateTime";
    public static final String _ROOMS = "rooms";
    public static final String PRIVACY = "privacy";

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

    // static fields end

    @PersianName("مسیر مدل")
    private String modelPath;

    @PersianName("اسم شی")
    private String fileName;

    @PersianName("شناسه فایل")
    private long fileId;

    @NotEmpty
    @PersianName("نام ")
    private String name;

    @NotEmpty
    @PersianName("توضیحات شی")
    private String description;

    @NotEmpty
    @PersianName("دسته بندی شی")
    private String category;

    @NotNull
    @PersianName("موقعیت شی")
    private TtObjectArea area;


    @PersianName("اندازه فایل شی")
    private double size;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر بارگذار کننده شی")
    private ServiceUser uploaderUser;

    @PersianName("زمان بارگذاری شی")
    private String uploadDateTime;


    @ManyToMany
    @JoinTable(name = "Service.Object_Room",
            joinColumns = {
                    @JoinColumn(name = "object_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "room_id")})
    @PersianName("دسترسی اتاق ها")
    private Set<Room> rooms;


    @PersianName("سطح دسترسی")
    private TtObjectPrivacy privacy;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TtObjectArea getArea() {
        return area;
    }

    public void setArea(TtObjectArea area) {
        this.area = area;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public ServiceUser getUploaderUser() {
        return uploaderUser;
    }

    public void setUploaderUser(ServiceUser uploaderUser) {
        this.uploaderUser = uploaderUser;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public TtObjectPrivacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(TtObjectPrivacy privacy) {
        this.privacy = privacy;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
