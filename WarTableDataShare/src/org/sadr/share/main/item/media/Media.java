package org.sadr.share.main.item.media;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("مدیای روی میز")
@Entity
@Table(name = "Service.Media")
public class Media extends GenericDataModel<Media> implements Serializable {

    public static final String FILE_NAME = "fileName";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description" ;
    public static final String SIZE = "size";
    public static final String _UPLOADER_USER = "uploaderUser" ;
    public static final String UPLOAD_DATE_TIME = "uploadDateTime";

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

    @PersianName("توضیحات شی")
    private String description;

    @PersianName("اندازه فایل شی")
    private double size;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر بارگذار کننده شی")
    private ServiceUser uploaderUser;

    @PersianName("زمان بارگذاری شی")
    private String uploadDateTime;


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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public ServiceUser getUploaderUse() {
        return uploaderUser;
    }

    public void setUploaderUse(ServiceUser uploaderUse) {
        this.uploaderUser = uploaderUse;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
}
