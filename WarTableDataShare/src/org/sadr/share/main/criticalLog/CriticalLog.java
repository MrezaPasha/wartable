package org.sadr.share.main.criticalLog;


import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@PersianName("لاگ سامانه")
@Entity
@Table(name = "Service.CriticalLog")
public class CriticalLog extends GenericDataModel<CriticalLog> {



    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String LOG_DETAIL_POSITION = "logDetailPosition";
    public static final String LOG_DETAIL_MESSAGE = "logDetailMessage";
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


    @PersianName("تاریخ")
    @Column(nullable = false)
    @Size(max = 30)
    private String creationDateTime;


    @PersianName("محل خطا")
    @Size(max = 2000)
    private String logDetailPosition;

    @PersianName("متن خطا")
    @Size(max = 3000)
    private String logDetailMessage;


    // METHODS


    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLogDetailPosition() {
        return logDetailPosition;
    }

    public void setLogDetailPosition(String logDetailPosition) {
        this.logDetailPosition = logDetailPosition;
    }

    public String getLogDetailMessage() {
        return logDetailMessage;
    }

    public void setLogDetailMessage(String logDetailMessage) {
        this.logDetailMessage = logDetailMessage;
    }
}
