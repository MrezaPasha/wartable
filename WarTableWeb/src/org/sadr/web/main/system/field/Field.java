package org.sadr.web.main.system.field;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main.system._type.TtDataRelation;
import org.sadr.web.main.system._type.TtFieldStatus;
import org.sadr.web.main.system.model.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author masoud
 */
@PersianName("فیلد")
@Entity
@Table(name = "Web.System.Field")
public class Field extends GenericDataModel<Field> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String IS_BIDIRECTIONAL = "isBidirectional";public static final String IS_ENCRYPTED = "isEncrypted";public static final String STATUS = "status";public static final String MO_TITLE = "moTitle";public static final String MO_PREVIOUS_TITLE = "moPreviousTitle";public static final String IS_MO_NULLABLE = "isMoNullable";public static final String MO_DEFAULT_VALUE = "moDefaultValue";public static final String MO_EXTRA = "moExtra";public static final String MO_KEY = "moKey";public static final String MO_ANNOTATIONS = "moAnnotations";public static final String IS_MO_REFRESHED = "isMoRefreshed";public static final String MO_MODIFIER = "moModifier";public static final String MO_TYPE = "moType";public static final String MO_MIN_SIZE = "moMinSize";public static final String MO_MAX_SIZE = "moMaxSize";public static final String MO_DATA_RELATION = "moDataRelation";public static final String MO_DATA_RELATION_DES = "moDataRelationDes";public static final String DB_TITLE = "dbTitle";public static final String DB_TYPE = "dbType";public static final String DB_SIZE = "dbSize";public static final String IS_DB_NULLABLE = "isDbNullable";public static final String DB_DEFAULT_VALUE = "dbDefaultValue";public static final String DB_EXTRA = "dbExtra";public static final String DB_KEY = "dbKey";public static final String DB_INDEX = "dbIndex";public static final String DB_CONSTRAINT = "dbConstraint";public static final String DB_REF_TABLE = "dbRefTable";public static final String DB_REF_COLUMN = "dbRefColumn";public static final String IS_DB_REFRESHED = "isDbRefreshed";public static final String IS_DB_F_K = "isDbFK";public static final String IS_DB_PRIMARY = "isDbPrimary";public static final String _MODEL = "model";public static final String $IS_REMOVE_FROM_MODEL = "isRemoveFromModel";public static final String $IS_MO_REFRESHED_Y = "isMoRefreshedY";public static final String $IS_BIDIRECTIONAL_Y = "isBidirectionalY";public static final String $IS_DB_REFRESHED_Y = "isDbRefreshedY";public static final String $IS_REMOVE_FROM_MODEL_AND_D_B = "isRemoveFromModelAndDB";public static final String $IS_DB_NULLABLE_Y = "isDbNullableY";public static final String $IS_ENCRYPTED_Y = "isEncryptedY";public static final String $IS_DB_PRIMARY_Y = "isDbPrimaryY";public static final String $IS_DB_F_K_Y = "isDbFKY";public static final String $IS_MO_NULLABLE_Y = "isMoNullableY";public static final String $IS_NEW = "isNew";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $DB_TITLE_FROM_MO = "dbTitleFromMo";public static final String $MO_DATA_TYPE_TT = "moDataTypeTt";public static final String $IS_CHANGED = "isChanged";public static final String $DB_TYPE_FROM_MO = "dbTypeFromMo";public static final String $DB_NULL_FROM_MO = "dbNullFromMo";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @PersianName("دو طرفه")
    private Boolean isBidirectional;

    @PersianName("رمزنگاری")
    private Boolean isEncrypted;

    @PersianName("وضعیت")
    private TtFieldStatus status;
    //////////////////////////// MODEL
    @Size(max = 255)
    @PersianName("عنوان (مدل)")
    private String moTitle;

    @Size(max = 255)
    @PersianName("عنوان قبلی (مدل)")
    private String moPreviousTitle;

    @PersianName("تهی (مدل)")
    private Boolean isMoNullable;

    @Size(max = 255)
    @PersianName("پیش فرض (مدل)")
    private String moDefaultValue;

    @Size(max = 255)
    @PersianName("سایر (مدل)")
    private String moExtra;

    @Size(max = 255)
    @PersianName("کلید (مدل)")
    private String moKey;

    @Size(max = 255)
    @PersianName("اعلان ها (مدل)")
    private String moAnnotations;

    @PersianName("بروز رسانی (مدل)")
    private Boolean isMoRefreshed;

    @PersianName("شناسه نوع (مدل)")
    private int moModifier;

    @Size(max = 255)
    @PersianName("نوع (مدل)")
    private String moType;

    @PersianName("حداقل اندازه (مدل)")
    private int moMinSize;

    @PersianName("حداکثر اندازه (مدل)")
    private int moMaxSize;

    @PersianName("شرح رابطه (مدل)")
    private TtDataRelation moDataRelation;

    @Size(max = 255)
    @PersianName("شرح رابطه (مدل)")
    private String moDataRelationDes;

    //////////////////////////// DATABASE
    @Size(max = 255)
    @PersianName("عنوان (پایگاه)")
    private String dbTitle;

    @Size(max = 255)
    @PersianName("نوع (پایگاه)")
    private String dbType;

    @PersianName("اندازه (پایگاه)")
    private int dbSize;

    @PersianName("تهی (پایگاه)")
    private Boolean isDbNullable;

    @Size(max = 255)
    @PersianName("پیش فرض (پایگاه)")
    private String dbDefaultValue;

    @Size(max = 255)
    @PersianName("سایر (پایگاه)")
    private String dbExtra;

    @Size(max = 255)
    @PersianName("کلید (پایگاه)")
    private String dbKey;

    @Size(max = 255)
    @PersianName("اندیس (پایگاه)")
    private String dbIndex;

    @Size(max = 255)
    @PersianName("ویژگی کلید (پایگاه)")
    private String dbConstraint;

    @Size(max = 255)
    @PersianName("جدول مرجع (پایگاه)")
    private String dbRefTable;

    @Size(max = 255)
    @PersianName("ستون مرجع (پایگاه)")
    private String dbRefColumn;

    @PersianName("بروزرسانی (پایگا)")
    private Boolean isDbRefreshed;

    @PersianName("کلی خارجی (پایگاه)")
    private Boolean isDbFK;

    @PersianName("کلید اصلی (پایگاه)")
    private Boolean isDbPrimary;

    //////////////////////////// RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مدل")
    private Model model;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"fieldId\":" + getId() + "}";
    }

    public boolean isIndirectEqual(Field dbField) {
        OutLog.pl("isDbNullable: " + dbField.isDbNullable + "  " + isDbNullable + "   " + Objects.equals(dbField.isDbNullable, isMoNullable));
        OutLog.pl("dbType: " + dbField.getDbType() + "  " + dbType + "   " + TtDataType.isEqual(dbField.dbType, moType));
        OutLog.pl("dbKey: " + dbField.getDbKey() + "  " + dbKey + "   " + (dbField.getDbKey() == null ? dbKey == null : dbField.getDbKey().equals(dbKey)));
        OutLog.pl("dbExtra: " + dbField.getDbExtra() + "  " + dbExtra + "   " + (dbField.getDbExtra() == null ? dbExtra == null : dbField.getDbExtra().equals(dbExtra)));
        OutLog.pl("moDataRelation: " + dbField.getMoDataRelation() + "  " + moDataRelation + "   " + (dbField.getMoDataRelation() == moDataRelation));

        return Objects.equals(dbField.isDbNullable, isMoNullable)
                && TtDataType.isEqual(dbField.dbType, moType)
            && (dbField.getDbKey() == null ? dbKey == null : dbField.getDbKey().equals(dbKey))
            && (dbField.getDbExtra() == null ? dbExtra == null : dbField.getDbExtra().equals(dbExtra))
            && dbField.getMoDataRelation() == moDataRelation;
    }

    public boolean verifyStatus() {
        boolean isForignRel = (moDataRelation == TtDataRelation.ManyToOne
            || (moDataRelation == TtDataRelation.OneToOne && moDataRelationDes != null));

        TtDataType tdt = TtDataType.getByMoType(moType);
        OutLog.pl(moTitle + " ++++  tdt  " + tdt);
        OutLog.p(isBidirectional + "");
        OutLog.p(moTitle + "  " + dbTitle);
        OutLog.p(isDbNullable + "  " + isMoNullable);
        OutLog.p(dbType + "  " + moType);
        OutLog.p(moMaxSize + "  " + dbSize);
        OutLog.p(isBidirectional
            + "  " + ((moTitle != null && (isForignRel ? !(moTitle + "_id").equals(dbTitle) : !moTitle.equals(dbTitle))))
            + "  " + !Objects.equals(isDbNullable, isMoNullable)
            + "  " + !TtDataType.isEqual(dbType, moType)
            + "  " + (tdt == TtDataType.String && moMaxSize != dbSize)
            + "  ");

        boolean isChanged = false;
        if (isBidirectional != null && !isBidirectional) {
            status = TtFieldStatus.NoChange;
        } else if (isMoRefreshed && !isDbRefreshed) {
            status = TtFieldStatus.New;
            isChanged = true;
        } else if (!isMoRefreshed && isDbRefreshed) {
            status = TtFieldStatus.RemoveFromModel;
            isChanged = true;
        } else if (!isMoRefreshed && !isDbRefreshed) {
            status = TtFieldStatus.RemoveFromModelAndDB;
            isChanged = true;
        } else if ((moTitle != null && (isForignRel ? !(moTitle + "_id").equals(dbTitle) : !moTitle.equals(dbTitle)))
            || !Objects.equals(isDbNullable, isMoNullable)
            || !TtDataType.isEqual(dbType, moType)
            || (tdt == TtDataType.String && moMaxSize != dbSize)) {
            status = TtFieldStatus.Changed;
            isChanged = true;
        } else {
            status = TtFieldStatus.NoChange;
        }
        OutLog.p("status: " + status);
        return isChanged;
    }

    public boolean getIsNew() {
        return status == TtFieldStatus.New;
    }

    public boolean getIsRemoveFromModel() {
        return status == TtFieldStatus.RemoveFromModel;
    }

    public boolean getIsRemoveFromModelAndDB() {
        return status == TtFieldStatus.RemoveFromModelAndDB;
    }

    public boolean getIsChanged() {
        return status == TtFieldStatus.Changed;
    }

    public TtDataType getMoDataTypeTt() {
        return TtDataType.getByMoType(moType);
    }

    public String getDbTypeFromMo() {
        TtDataType tdt = TtDataType.getByMoType(moType);
        if (tdt == null) {
            return "";
        }
        if (tdt == TtDataType.String) {
            return tdt.getSqlType() + "(" + moMaxSize + ")";
        }
        return tdt.getSqlType();
    }

    public String getDbNullFromMo() {
        return (isMoNullable ? " NULL " : " NOT NULL");

    }

    public String getDbTitleFromMo() {
        return (moDataRelation == TtDataRelation.ManyToOne
            || (moDataRelation == TtDataRelation.OneToOne && moDataRelationDes != null)) ? moTitle + "_id" : moTitle;

    }

    @Override
    public String toString() {
        return "Field{" + "isBidirectional=" + isBidirectional + ", status=" + status + ", moTitle=" + moTitle + ", isMoNullable=" + isMoNullable + ", moDefaultValue=" + moDefaultValue + ", moExtra=" + moExtra + ", moKey=" + moKey + ", moAnnotations=" + moAnnotations + ", isMoRefreshed=" + isMoRefreshed + ", moModifier=" + moModifier + ", moType=" + moType + ", moMinSize=" + moMinSize + ", moMaxSize=" + moMaxSize + ", moDataRelation=" + moDataRelation + ", moDataRelationDes=" + moDataRelationDes + ", dbTitle=" + dbTitle + ", dbType=" + dbType + ", dbSize=" + dbSize + ", isDbNullable=" + isDbNullable + ", dbDefaultValue=" + dbDefaultValue + ", dbExtra=" + dbExtra + ", dbKey=" + dbKey + ", isDbRefreshed=" + isDbRefreshed + '}';
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public String getMoTitle() {
        return moTitle;
    }

    public void setMoTitle(String moTitle) {
        this.moTitle = moTitle;
    }

    public String getMoPreviousTitle() {
        return moPreviousTitle;
    }

    public void setMoPreviousTitle(String moPreviousTitle) {
        this.moPreviousTitle = moPreviousTitle;
    }

    public TtFieldStatus getStatus() {
        return status;
    }

    public TtYesNo getIsEncryptedY() {
        return TtYesNo.getValueByBool(isEncrypted);
    }

    public Boolean getIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(Boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public void setStatus(TtFieldStatus status) {
        this.status = status;
    }

    public TtYesNo getIsBidirectionalY() {
        return TtYesNo.getValueByBool(isBidirectional);
    }

    public Boolean getIsBidirectional() {
        return isBidirectional;
    }

    public String getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getDbConstraint() {
        return dbConstraint;
    }

    public void setDbConstraint(String dbConstraint) {
        this.dbConstraint = dbConstraint;
    }

    public String getDbRefTable() {
        return dbRefTable;
    }

    public void setDbRefTable(String dbRefTable) {
        this.dbRefTable = dbRefTable;
    }

    public String getDbRefColumn() {
        return dbRefColumn;
    }

    public void setDbRefColumn(String dbRefColumn) {
        this.dbRefColumn = dbRefColumn;
    }

    public TtYesNo getIsDbPrimaryY() {
        return TtYesNo.getValueByBool(isDbPrimary == null ? false : isDbPrimary);
    }

    public Boolean getIsDbPrimary() {
        return isDbPrimary == null ? false : isDbPrimary;
    }

    public void setIsDbPrimary(Boolean isDbPrimary) {
        this.isDbPrimary = isDbPrimary;
    }

    public TtYesNo getIsDbFKY() {
        return TtYesNo.getValueByBool(isDbFK == null ? false : isDbFK);
    }

    public Boolean getIsDbFK() {
        return isDbFK == null ? false : isDbFK;
    }

    public void setIsDbFK(Boolean isDbFK) {
        this.isDbFK = isDbFK;
    }

    public String getMoType() {
        return moType;
    }

    public void setMoType(String moType) {
        this.moType = moType;
    }

    public int getMoMinSize() {
        return moMinSize;
    }

    public void setMoMinSize(int moMinSize) {
        this.moMinSize = moMinSize;
    }

    public int getMoMaxSize() {
        return moMaxSize;
    }

    public void setMoMaxSize(int moMaxSize) {
        this.moMaxSize = moMaxSize;
    }

    public int getDbSize() {
        return dbSize;
    }

    public void setDbSize(int dbSize) {
        this.dbSize = dbSize;
    }

    public void setIsBidirectional(Boolean isBidirectional) {
        this.isBidirectional = isBidirectional;
    }

    public TtYesNo getIsDbRefreshedY() {
        return TtYesNo.getValueByBool(isDbRefreshed == null ? false : isDbRefreshed);
    }

    public Boolean getIsDbRefreshed() {
        return isDbRefreshed == null ? false : isDbRefreshed;
    }

    public void setIsDbRefreshed(Boolean isDbRefreshed) {
        this.isDbRefreshed = isDbRefreshed;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getMoModifier() {
        return moModifier;
    }

    public void setMoModifier(int moModifier) {
        this.moModifier = moModifier;
    }

    public TtDataRelation getMoDataRelation() {
        return moDataRelation;
    }

    public void setMoDataRelation(TtDataRelation moDataRelation) {
        this.moDataRelation = moDataRelation;
    }

    public Boolean getIsMoNullable() {
        return isMoNullable == null ? false : isMoNullable;
    }

    public TtYesNo getIsMoNullableY() {
        return TtYesNo.getValueByBool(isMoNullable);
    }

    public void setIsMoNullable(Boolean isMoNullable) {
        this.isMoNullable = isMoNullable;
    }

    public String getMoDataRelationDes() {
        return moDataRelationDes;
    }

    public void setMoDataRelationDes(String moDataRelationDes) {
        this.moDataRelationDes = moDataRelationDes;
    }

    public TtYesNo getIsMoRefreshedY() {
        return TtYesNo.getValueByBool(isMoRefreshed);
    }

    public Boolean getIsMoRefreshed() {
        return isMoRefreshed;
    }

    public void setIsMoRefreshed(Boolean isMoRefreshed) {
        this.isMoRefreshed = isMoRefreshed;
    }

    public String getMoDefaultValue() {
        return moDefaultValue;
    }

    public void setMoDefaultValue(String moDefaultValue) {
        this.moDefaultValue = moDefaultValue;
    }

    public String getMoExtra() {
        return moExtra;
    }

    public void setMoExtra(String moExtra) {
        this.moExtra = moExtra;
    }

    public String getMoKey() {
        return moKey;
    }

    public void setMoKey(String moKey) {
        this.moKey = moKey;
    }

    public String getMoAnnotations() {
        return moAnnotations;
    }

    public void setMoAnnotations(String moAnnotations) {
        this.moAnnotations = moAnnotations;
    }

    public String getDbTitle() {
        return dbTitle;
    }

    public void setDbTitle(String dbTitle) {
        this.dbTitle = dbTitle;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Boolean getIsDbNullable() {
        return isDbNullable;
    }

    public TtYesNo getIsDbNullableY() {
        return TtYesNo.getValueByBool(isDbNullable);
    }

    public void setIsDbNullable(Boolean isDbNullable) {
        this.isDbNullable = isDbNullable;
    }

    public String getDbDefaultValue() {
        return dbDefaultValue;
    }

    public void setDbDefaultValue(String dbDefaultValue) {
        this.dbDefaultValue = dbDefaultValue;
    }

    public String getDbExtra() {
        return dbExtra;
    }

    public void setDbExtra(String dbExtra) {
        this.dbExtra = dbExtra;
    }

    public String getDbKey() {
        return dbKey;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }

}
