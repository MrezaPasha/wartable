package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PersianName("رویدادهای سرویس")
@Entity
@Table(name = "Service.Log")
public class ServiceLog extends GenericDataModel<ServiceLog> implements Serializable {

    // static fields start
    public static final String TIME = "creationDateTime";
    public static final String SOFTWARE_ID = "softwareID";
    public static final String SOFTWARE_NAME = "softwareName";
    public static final String SOFTWARE_VERSION = "softwareVersion";
    public static final String SERVER_HOSTNAME = "serverHostname";
    public static final String SERVER_IP = "serverIP";
    public static final String PORT_NUMBER = "portNumber";
    public static final String CLIENT_HOSTNAME = "clientHostname";
    public static final String CLIENT_IP = "clientIP";
    public static final String PAGE_TITLE = "pageTitle";
    public static final String URLL = "url";
    public static final String USERNAME = "username";
    public static final String USER_UNIQUE_ID = "userUniqueID";
    public static final String ACTION_TYPE = "actionType";
    public static final String SENSITIVITY = "sensitivity";
    public static final String IMPORTANCE = "importance";
    public static final String FLAG = "flag";
    public static final String SUB_TYPE = "subType";
    public static final String SUB_TYPE_DESCRIPTION = "subTypeDescription";
    public static final String DESCRIPTION = "description";

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
    @Column()
    @PersianName("زمان رویداد")
    @Size(max = 30)
    private String creationDateTime;

    @Column()
    @PersianName("شناسه نرم افزار")
    //@Max(30)
    private String softwareID;

    @Column()
    @PersianName("نام نرم افزار")
    private String softwareName;

    @Column()
    @PersianName("نسخه نرم افزار")
    private String softwareVersion;

    @Column()
    @PersianName("آدرس دامین سرور")
    private String serverHostname;

    @Column()
    @PersianName("آی پی سرور")
    private String serverIP;

    @Column()
    @PersianName("شماره پورت سرور")
    private String portNumber;

    @Column()
    @PersianName("نام کلاینت")
    private String clientHostname;

    @Column()
    @PersianName("ای پی کلاینت")
    @Size(max = 50)
    private String clientIP;

    @Column()
    @PersianName("عنوان صفحه")
    private String pageTitle;

    @Column()
    @PersianName("آدرس صفحه درخواستی")
    private String url;

    @Column()
    @PersianName("شناسه کاربر درخواست کننده")
    private String username;

    @Column()
    @PersianName("شناسه واحد کاربری")
    private String userUniqueID;

    @Column()
    @PersianName("عملیات انجام شده")
    private TtActionType actionType;

    @Column()
    @PersianName("درجه حساسیت")
    private TtSensitivity sensitivity;


    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    @PersianName("درجه اهمیت")
    private TtImportance importance;

    @Column()
    @PersianName("نتیجه عملیات")
    private TtFlag flag;

    @Column()
    @PersianName("زیر رویداد")
    private TtSubType subType;

    @Column()
    @Size(max = 1000)
    @PersianName("توضیحات زیر رویداد")
    private String subTypeDescription;


    @Column()
    @Size(max = 1000)
    @PersianName("توضیحات اضافی")
    private String description;


    // METHODS

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getSoftwareID() {
        return softwareID;
    }

    public void setSoftwareID(String softwareID) {
        this.softwareID = softwareID;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getServerHostname() {
        return serverHostname;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getClientHostname() {
        return clientHostname;
    }

    public void setClientHostname(String clientHostname) {
        this.clientHostname = clientHostname;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUniqueID() {
        return userUniqueID;
    }

    public void setUserUniqueID(String userUniqueID) {
        this.userUniqueID = userUniqueID;
    }

    public TtActionType getActionType() {
        return actionType;
    }

    public void setActionType(TtActionType actionType) {
        this.actionType = actionType;
    }

    public TtSensitivity getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(TtSensitivity sensitivity) {
        this.sensitivity = sensitivity;
    }

    public TtImportance getImportance() {
        return importance;
    }

    public void setImportance(TtImportance importance) {
        this.importance = importance;
    }

    public TtFlag getFlag() {
        return flag;
    }

    public void setFlag(TtFlag flag) {
        this.flag = flag;
    }

    public TtSubType getSubType() {
        return subType;
    }

    public void setSubType(TtSubType subType) {
        this.subType = subType;
    }

    public String getSubTypeDescription() {
        return subTypeDescription;
    }

    public void setSubTypeDescription(String subTypeDescription) {
        this.subTypeDescription = subTypeDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // constructor


   /* public ServiceLog(Timestamp time, String softwareID, String softwareName, String softwareVersion, String serverHostname, String serverIP, String portNumber, String clientHostname, String clientIP, String pageTitle, String URL, String username, String userUniqueID, TtActionType actionType, TtSensitivity sensitivity, TtImportance importance, TtFlag flag, TtSubType subType, String subTypeDescription, String description) {
        Time = time;
        SoftwareID = softwareID;
        SoftwareName = softwareName;
        SoftwareVersion = softwareVersion;
        this.serverHostname = serverHostname;
        ServerIP = serverIP;
        PortNumber = portNumber;
        this.clientHostname = clientHostname;
        ClientIP = clientIP;
        PageTitle = pageTitle;
        this.URL = URL;
        Username = username;
        UserUniqueID = userUniqueID;
        ActionType = actionType;
        Sensitivity = sensitivity;
        Importance = importance;
        Flag = flag;
        SubType = subType;
        SubTypeDescription = subTypeDescription;
        Description = description;
    } */
}
