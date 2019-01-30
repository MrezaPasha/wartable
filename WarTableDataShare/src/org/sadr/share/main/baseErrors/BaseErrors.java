package org.sadr.share.main.baseErrors;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.service.main.rpc._types.TtErrors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PersianName("تنظیمات خطای سامانه")
@Entity
@Table(name = "Service.BaseErrors")

public class BaseErrors extends GenericDataModel<BaseErrors> implements Serializable {


    public static final String ERROR_ID = "errorId";
    public static final String ERROR_DESCRIPTION = "errorDescription";
    public static final String ERROR_VALUE = "errorValue";
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

    @PersianName("شناسه فیلد خطا")
    private TtErrors errorId;

    @PersianName("توضیحات فیلد خطا")
    @Column(nullable = false)
    @Size(max = 100)
    private String errorDescription;


    @PersianName("مقدار فیلد خطا")
    @Column()
    @Size(max = 2000)
    private String errorValue;


    // METHODS


    public TtErrors getErrorId() {
        return errorId;
    }

    public void setErrorId(TtErrors errorId) {
        this.errorId = errorId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }
}


