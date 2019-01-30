package org.sadr.share.main.item.entity;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;

import javax.persistence.Table;
import java.io.Serializable;


@PersianName("موجودیت روی میز")
@javax.persistence.Entity
@Table(name = "Service.Entity")
public class Entity extends GenericDataModel<Entity> implements Serializable {


    public static final String JSON_STRING = "jsonString";
    public static final String TYPE ="type";
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

    @PersianName("ویژگی ها")
    private String jsonString;

    @PersianName("نوع")
    private Integer type;




    // METHODS


    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
