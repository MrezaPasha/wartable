package org.sadr.share.main.orgPosition;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@PersianName("جایگاه سازمانی")
@Entity
@Table(name = "Service.OrgPosition")
public class OrgPosition extends GenericDataModel<OrgPosition> {


    // static fields start
    public static  final String CODE = "code";
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

    @PersianName("کد جایگاه سازمانی")
    @Column(nullable = false)
    private int code;

    @PersianName("نام جایگاه سازمانی")
    @Column(nullable = false)
    @Size(max = 100)
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
