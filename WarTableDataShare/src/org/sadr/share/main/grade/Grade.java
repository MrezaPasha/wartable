package org.sadr.share.main.grade;

import org.hibernate.validator.constraints.NotEmpty;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@PersianName("درجه سازمانی")
@Entity
@Table(name = "Service.Grade")
public class Grade extends GenericDataModel<Grade> {

    // static fields start

    public static final String CODE = "code";
    public static final String VALUE = "value";
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

    @PersianName("کد سازمانی")
    @Column(nullable = false)
    private int code;

    @PersianName("نام سازمانی")
    @Column(nullable = false)
    @Size(min = 2,max = 100)
    private String value;

    // METHODS


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
