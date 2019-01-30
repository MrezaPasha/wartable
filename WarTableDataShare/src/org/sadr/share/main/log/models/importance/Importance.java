package org.sadr.share.main.log.models.importance;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.log._types.TtImportanceLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@PersianName("درجه اهمیت")
@Entity
@Table(name = "Service.Importance")
public class Importance extends GenericDataModel<Importance> implements Serializable {

    // static field start
    public static  final String IMPORTANCE_LEVEL = "ImportanceLevel";
    public static final String IMPORTANCE_VALUE = "importanceValue";
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

    // static field end

    @PersianName("سطح اهمیت")
    @Column(nullable = false)
    private TtImportanceLevel ImportanceLevel;


    @PersianName("مقدار اهمیت")
    @Column(nullable = false)
    private String importanceValue;


    // METHODS


    public TtImportanceLevel getImportanceLevel() {
        return ImportanceLevel;
    }

    public void setImportanceLevel(TtImportanceLevel importanceLevel) {
        ImportanceLevel = importanceLevel;
    }

    public String getImportanceValue() {
        return importanceValue;
    }

    public void setImportanceValue(String importanceValue) {
        this.importanceValue = importanceValue;
    }
}
