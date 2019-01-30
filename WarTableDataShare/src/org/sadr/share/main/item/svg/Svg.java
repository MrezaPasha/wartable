package org.sadr.share.main.item.svg;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@PersianName("SVG")
@Entity
@Table(name = "Service.Svg")
public class Svg extends GenericDataModel<Svg> implements Serializable {

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

    @PersianName("اسم فایل")
    private String fileName;

    @PersianName("نام ")
    private String name;

    @PersianName("توضیحات ")
    private String description;

    @PersianName("حجم فایل ")
    private double size;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر بارگذار کننده")
    private ServiceUser uploaderUser;

    @PersianName("زمان بارگذاری")
    private String uploadDateTime;


    // getter and setters


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
}
