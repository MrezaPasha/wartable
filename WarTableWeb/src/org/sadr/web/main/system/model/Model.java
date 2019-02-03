package org.sadr.web.main.system.model;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.system._type.TtModelStatus;
import org.sadr.web.main.system.field.Field;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName(value = "مدل")
@Entity
@Table(name = "Web.System.Model")
public class Model extends GenericDataModel<Model> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String TABLE_NAME = "tableName";public static final String MESSAGE_CODE = "messageCode";public static final String PACKAGE_NAME = "packageName";public static final String CLASS_NAME = "className";public static final String IS_REFRESHED = "isRefreshed";public static final String STATUS = "status";public static final String _FIELDS = "fields";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $IS_REMOVE_FROM_MODEL = "isRemoveFromModel";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $IS_CHANGED = "isChanged";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @PersianName("جدول")
    private String tableName;

    @PersianName("کد پیام")
    private String messageCode;

    @PersianName("نام پکیج")
    private String packageName;

    @PersianName("نام کلاس")
    private String className;

    @PersianName("بروزرسانی")
    private Boolean isRefreshed;

    @PersianName("وضعیت")
    private TtModelStatus status;

    @OrderBy(Field.MO_TITLE)
    @OneToMany(mappedBy = "model")
    @PersianName("فیلدها")
    private Set<Field> fields;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"modelId\":" + getId() + "}";
    }

    public void verifyStatus(boolean isFieldChanged) {
        if (!isRefreshed) {
            status = TtModelStatus.RemoveFromModel;
        } else if (isFieldChanged) {
            status = TtModelStatus.Changed;
        } else {
            status = TtModelStatus.NoChange;
        }
    }

    public boolean getIsRemoveFromModel() {
        return status == TtModelStatus.RemoveFromModel;
    }

    public boolean getIsChanged() {
        return status == TtModelStatus.Changed;
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TtModelStatus getStatus() {
        return status;
    }

    public void setStatus(TtModelStatus status) {
        this.status = status;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getIsRefreshed() {
        return isRefreshed;
    }

    public void setIsRefreshed(Boolean isRefreshed) {
        this.isRefreshed = isRefreshed;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

}
