package org.sadr.web.main.archive.file.download;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive._type.TtFileDownloadStatus;
import org.sadr.web.main.archive.file.file.File;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("دانلود فایل")
@Entity
@Table(name = "Web.Archive.File.Download")
public class FileDownload extends GenericDataModel<FileDownload> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _USER = "user";public static final String STATUS = "status";public static final String _FILE = "file";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @ManyToOne
    @PersianName("کاربر")
    private User user;

    @PersianName("وضعیت دانلود")
    private TtFileDownloadStatus status;

    @ManyToOne
    @PersianName("فایل")
    private File file;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public TtFileDownloadStatus getStatus() {
        return status;
    }

    public void setStatus(TtFileDownloadStatus status) {
        this.status = status;
    }
}
