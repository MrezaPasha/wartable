package org.sadr.web.main.system.log.signin;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtSigninLogStatus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("ورودهای ناموفق")
@Entity
@Table(name = "Web.System.Log.SigninLog")
public class SigninLog extends GenericDataModel<SigninLog> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String AGENT_SIGNATURE = "agentSignature";public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String IP_ADDRESS = "ipAddress";public static final String DOMAIN_NAME = "domainName";public static final String UUID = "uuid";public static final String LAST_DATE_TIME_G = "lastDateTimeG";public static final String LAST_DATE_TIME = "lastDateTime";public static final String STATUS = "status";public static final String _USER = "user";public static final String $USER_NAME = "userName";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $LAST_DATE_TIME_S_M = "lastDateTimeSM";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @Size(max = 250)
    @PersianName("امضای عامل")
    private String agentSignature;

    @Size(max = 250)
    @PersianName("امضای کامپیوتر")
    private String computerSignature;

    @Size(max = 50)
    @PersianName("آدرس IP")
    private String ipAddress;

    @Size(max = 150)
    @PersianName("نام دامنه")
    private String domainName;

    @Size(max = 150)
    @PersianName("UUID")
    private String uuid;

    @PersianName("زمان آخرین ورود")
    private long lastDateTimeG;

    @Size(max = 30)
    @PersianName("زمان آخرین ورود")
    private String lastDateTime;

    @PersianName("وضعیت")
    private TtSigninLogStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    ///############################## RELATIONS

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"signinLogId\":" + getId() + "}";
    }

    @PersianName("نام کاربری")
    public String getUserName() {
        return user != null ? user.getUsername() : "";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public String getAgentSignature() {
        return agentSignature;
    }

    public void setAgentSignature(String agentSignature) {
        this.agentSignature = agentSignature;
    }

    public String getComputerSignature() {
        return computerSignature;
    }

    public void setComputerSignature(String computerSignature) {
        this.computerSignature = computerSignature;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getLastDateTimeG() {
        return lastDateTimeG;
    }

    public void setLastDateTimeG(long lastDateTimeG) {
        this.lastDateTimeG = lastDateTimeG;
    }

    public String getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(String lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public String getLastDateTimeSM() {
        return ParsCalendar.getInstance().getDateTimeInMonthString(lastDateTime);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TtSigninLogStatus getStatus() {
        return status;
    }

    public void setStatus(TtSigninLogStatus status) {
        this.status = status;
    }
}
