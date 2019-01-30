package org.sadr.share.main.sessions;


import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtSessionsState;
import org.sadr.share.main.serviceUser.ServiceUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PersianName("نشست های فعال")
@Entity
@Table(name = "Service.Sessions")
public class Sessions extends GenericDataModel<Sessions> implements Serializable {

    public static final String _SERVICE_USER = "serviceUser";
    public static final String PRIVILAGE_FLAG = "privilageFlag";
    public static final String SESSION_ID = "sessionId";
    public static final String STATUS = "status";
    public static final String CREATION_DATE_TIME = "creationDateTime";
    public static final String UPDATE_DATE_TIME = "updateDateTime";
    public static final String $SERVICE_USER_FULL_NAME = "serviceUserFullName";


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

    @OneToOne()
    @PersianName("کاربر")
    private ServiceUser serviceUser;

    @PersianName("سطح دسترسی موقتی کاربر")
    private String privilageFlag;

    @PersianName("شناسه نشست")
    @Column(nullable = false)
    @Size(max = 100)
    private String sessionId;

    @PersianName("وضعیت نشست کاربر")
    private TtSessionsState status;


    @PersianName("زمان ایجاد نشست")
    @Column(nullable = false)
    @Size(max = 30)
    private String creationDateTime;


    @PersianName("زمان بروز رسانی نشست")
    @Column(nullable = false)
    @Size(max = 30)
    private String updateDateTime;


    // getter and setters

    @PersianName("نام کاربر")
    public String getServiceUserFullName() {
        return serviceUser == null ? "" : serviceUser.getFullName();
    }


    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public String getPrivilageFlag() {
        return privilageFlag;
    }

    public void setPrivilageFlag(String privilageFlag) {
        this.privilageFlag = privilageFlag;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public TtSessionsState getStatus() {
        return status;
    }

    public void setStatus(TtSessionsState status) {
        this.status = status;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
