package org.sadr.web.main.system.registery;

import org.hibernate.annotations.Type;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main.system._type.TtRegisteryValueType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("رجیستری")
@Entity
@Table(name = "Web.System.Registery")
public class Registery extends GenericDataModel<Registery> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String TITLE = "title";public static final String KEY = "key";public static final String TYPE = "type";public static final String VALUE = "value";public static final String VALUE_JSON = "valueJson";public static final String VALUE_TYPE = "valueType";public static final String $SECRET_NOTE = "secretNote";public static final String $IS_NORMAL_VALUE = "isNormalValue";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $PURE_JSON_VALUE_NO_COUT = "pureJsonValueNoCout";public static final String $PURE_JSON_VALUE_ARRAY = "pureJsonValueArray";public static final String $ACT_COLUMNS = "actColumns";public static final String $PURE_JSON_VALUE_BRACKET = "pureJsonValueBracket";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //
    @Size(max = 255)
    @PersianName("عنوان")
    private String title;
    @Size(max = 255)
    @PersianName("کلید")
    private String key;
    @Size(max = 255)
    @PersianName("نوع")
    private String type;
    @Size(max = 255)
    @PersianName("مقدار")
    private String value; //    @Text
    @Type(type = "text")
    @PersianName("مقدار")
    private String valueJson;
    @PersianName("نوع رجیستری")
    private TtRegisteryValueType valueType;


    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"registeryId\":" + getId() + "}";
    }

    public Boolean getIsNormalValue() {
        return valueType == TtRegisteryValueType.Normal;
    }

    public boolean isContainPureJsonValue(String val) {
        if (valueType == TtRegisteryValueType.PureJson || valueType == TtRegisteryValueType.Json) {
            return valueJson != null && valueJson.contains("\"" + val + "\"");
        }
        return value != null && value.contains("\"" + val + "\"");
    }

    public boolean addPureJsonValues(String... values) {
        if (valueType != TtRegisteryValueType.PureJson || values == null) {
            return false;
        }
        boolean isadd = false;
        String[] v;
        for (String vs : values) {
            OutLog.pl(vs);
            if (vs == null || vs.isEmpty()) {
                continue;
            }
            v = vs.split(",");
            for (String v1 : v) {
                if (valueJson == null || valueJson.isEmpty()) {
                    valueJson = "\"" + v1 + "\"";
                    isadd = true;
                } else if (!valueJson.contains("\"" + v1 + "\"")) {
                    valueJson += ",\"" + v1 + "\"";
                    isadd = true;
                }
            }

        }
        OutLog.pl("AA " + valueJson);
        return isadd;
    }

    public String getPureJsonValueNoCout() {
        return valueType != TtRegisteryValueType.PureJson || valueJson == null ? "" : valueJson.replaceAll("\"", "");
    }

    public String getPureJsonValueBracket() {
//        OutLog.pl(value);
        return valueType != TtRegisteryValueType.PureJson || valueJson == null ? "[]" : "[" + valueJson + "]";
    }

    public String[] getPureJsonValueArray() {
        return valueType != TtRegisteryValueType.PureJson || valueJson == null ? null : valueJson.replaceAll("\"", "").split(",");
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=   METHODS
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TtRegisteryValueType getValueType() {
        return valueType;
    }

    public void setValueType(TtRegisteryValueType valueType) {
        this.valueType = valueType;
    }

    public String getValueJson() {
        return valueJson;
    }

    public void setValueJson(String valueJson) {
        this.valueJson = valueJson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
