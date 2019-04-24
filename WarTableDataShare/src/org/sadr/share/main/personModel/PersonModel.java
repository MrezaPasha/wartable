package org.sadr.share.main.personModel;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@PersianName("مدل های انسانی")
@Entity
@Table(name = "Service.PersonalModel")
public class PersonModel extends GenericDataModel<PersonModel> implements Serializable {

    public static final String FILENAME  = "fileName";
    public static final String NAME  = "name";
    public static final String SCALE = "scale";
    public static final String SIZE  = "size";
    public static final String _UPLOAD_USER  = "uploaderUser";
    public static final String UPLOAD_DATE_TIME  = "uploadDateTime";
    public static final String _ASSIGN_USERS  = "assignUSers";

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







    @PersianName("اسم شی")
    private String fileName;

    @PersianName("نام ")
    private String name;

    @PersianName("ضریب اندازه مدل")
    private double scale;

    @PersianName("اندازه فایل شی")
    private double size;

    @PersianName("شناسه فایل")
    private long fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر بارگذار کننده شی")
    private ServiceUser uploaderUser;

    @PersianName("زمان بارگذاری شی")
    private String uploadDateTime;

    @OneToMany(mappedBy = "userModel")
    @PersianName("کاربران")
    private Set<ServiceUser> assignUSers;


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

    public Set<ServiceUser> getAssignUSers() {
        return assignUSers;
    }

    public void setAssignUSers(Set<ServiceUser> assignUSers) {
        this.assignUSers = assignUSers;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }
}
