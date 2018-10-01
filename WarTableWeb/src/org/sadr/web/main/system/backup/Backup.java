package org.sadr.web.main.system.backup;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.archive.file.file.File;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("پشتیبان گیری")
@Entity
@Table(name = "Web.System.Backup")
public class Backup extends GenericDataModel<Backup> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String BACKUP_DATE_TIME = "backupDateTime";public static final String _FILE = "file";public static final String $TITLE = "title";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @Size(max = 30)
    @PersianName("زمان پشتیبان گیری")
    private String backupDateTime;

    @PersianName("فایل")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;


    ///############################## RELATIONS

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"backupId\":" + getId() + "}";
    }

    @PersianName("عنوان")
    public String getTitle() {
        return file != null ? file.getTitle() : "";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public String getBackupDateTime() {
        return backupDateTime;
    }

    public void setBackupDateTime(String backupDateTime) {
        this.backupDateTime = backupDateTime;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
