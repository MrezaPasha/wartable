/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.system.log.attempt;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin._type.TtUserAttemptType;
import org.sadr.web.main.admin.user.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dev1
 */
@PersianName("تلاش کاربر")
@Entity
@Table(name = "Web.System.Log.User")
public class UserAttempt extends GenericDataModel<UserAttempt> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String AGENT_SIGNATURE = "agentSignature";public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String UUID = "uuid";public static final String DATE_TIME_G = "dateTimeG";public static final String LAST_DATE_TIME_G = "lastDateTimeG";public static final String IS_SUCCESS = "isSuccess";public static final String COUNT = "count";public static final String ATTEMPT_TYPE = "attemptType";public static final String _USER = "user";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $AGENT_HASH_CODE = "agentHashCode";public static final String $ACT_COLUMNS = "actColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    public UserAttempt() {
        this.count = 1;
    }

    @Size(max = 250)
    @PersianName("امضای عامل")
    private String agentSignature;

    @Size(max = 250)
    @PersianName("امضای کامپیوتر")
    private String computerSignature;

    @Size(max = 150)
    @PersianName("UUID")
    private String uuid;

    @PersianName("زمان")
    private long dateTimeG;

    @PersianName("زمان آخرین تلاش")
    private long lastDateTimeG;

    @PersianName("موفق")
    private Boolean isSuccess;

    @Min(0)
    @PersianName("تعداد")
    private int count;

    @PersianName("نوع تلاش")
    private TtUserAttemptType attemptType;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    public int getAgentHashCode() {
        return agentSignature.hashCode();
    }

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

    public long getDateTimeG() {
        return dateTimeG;
    }

    public void setDateTimeG(long dateTimeG) {
        this.dateTimeG = dateTimeG;
    }

    public long getLastDateTimeG() {
        return lastDateTimeG;
    }

    public void setLastDateTimeG(long lastDateTimeG) {
        this.lastDateTimeG = lastDateTimeG;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TtUserAttemptType getAttemptType() {
        return attemptType;
    }

    public void setAttemptType(TtUserAttemptType attemptType) {
        this.attemptType = attemptType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInAttemptRange() {
        return this.dateTimeG > (new Date().getTime() - (attemptType.getRangeMiliSec()));
    }

    public boolean isBlocked() {
        return this.dateTimeG > (new Date().getTime() - (attemptType.getBlockMiliSec()))
            && this.count > attemptType.getBlockMaxCount();
    }

    public boolean needVerification() {
        return this.dateTimeG > (new Date().getTime() - (attemptType.getRangeMiliSec()))
            && this.count > attemptType.getAttemptMaxCount();
    }

    public boolean isInAttemptCount() {
        return this.count <= attemptType.getAttemptMaxCount();
    }

    public void addCount() {
        this.count += 1;
    }

    public void refreshDateTime() {
        this.dateTimeG = new Date().getTime();
    }

    @Override
    public String toString() {
        return "id: " + getId()
            + "\n Count: " + count;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String generateSecureUUID() {
        uuid = java.util.UUID.randomUUID().toString() + "_"
            + (agentSignature == null ? "" : agentSignature.trim().codePoints().sum());
//                + (computerSignature == null ? "" : computerSignature.trim().replace(".", "-").toUpperCase().codePoints().sum());
        return uuid;
    }

    public static boolean isValidateUUID(String agentSignature, String computerSignature, String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return false;
        }
        String[] sp = uuid.split("_");
        if (sp.length < 2) {
            return false;
        }

        String s = "" + (agentSignature == null ? "" : agentSignature.trim().codePoints().sum());
//                + (computerSignature == null ? "" : computerSignature.trim().replace(".", "-").toUpperCase().codePoints().sum());

        return (s.equals(sp[1]));
    }

    public boolean isPassSecureCheck(HttpServletRequest request) {
        return request.getHeader("User-Agent").equals(this.agentSignature);// && request.getRemoteAddr().equals(this.computerSignature);
    }
}
