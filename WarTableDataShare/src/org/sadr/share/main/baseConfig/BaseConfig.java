package org.sadr.share.main.baseConfig;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.service.main.rpc._types.TtConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;


@PersianName("تنظیمات ابتدایی سامانه")
@Entity
@Table(name = "Service.BaseConfig")
public class BaseConfig extends GenericDataModel<BaseConfig> implements Serializable {

    public static final String CONFIG_ID = "configId";
    public static final String CONFIG_NAME = "configName";
    public static final String CONFIG_VALUE = "configValue";
    public static final String $CONFIG_ID_TITLE = "configIdTitle";
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

    // static fields end


    @PersianName("شناسه فیلد تنظیمات")
    private TtConfig configId;

    @PersianName("نام فیلد تنظیمات")
    @Column(nullable = false)
    @Size(max = 100)
    private String configName;


    @PersianName("مقدار فیلد تنظیمات")
    @Column(nullable = false)
    @Size(max = 2000)
    private String configValue;


    // METHODS

    @PersianName("شناسه فیلد تنظیمات")
    public String getConfigIdTitle() {
        return this.configId != null ? configId.getConfigName() : "";
    }

    public TtConfig getConfigId() {
        return configId;
    }

    public void setConfigId(TtConfig configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
