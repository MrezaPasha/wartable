/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.admin.user.uuid;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author dev1
 */
@PersianName("UUID کاربری")
@Entity
@Table(name = "Web.Admin.User.Uuid")
public class UserUuid extends GenericDataModel<UserUuid> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String UUID = "uuid";public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String AGENT_SIGNATURE = "agentSignature";public static final String SINGIN_DATE_TIME_G = "singinDateTimeG";public static final String EXPIRE_DATE_TIME_G = "expireDateTimeG";public static final String _USER = "user";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @Size(max = 100)
    @PersianName("UUID")
    private String uuid;

    @Size(max = 250)
    @PersianName("امضای کامپیوتر")
    private String computerSignature;

    @Size(max = 250)
    @PersianName("امضای عامل")
    private String agentSignature;

    @PersianName("زمان ورود")
    private long singinDateTimeG;

    @PersianName("زمان انقضاء")
    private long expireDateTimeG;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getComputerSignature() {
        return computerSignature;
    }

    public void setComputerSignature(String computerSignature) {
        this.computerSignature = computerSignature;
    }

    public long getSinginDateTimeG() {
        return singinDateTimeG;
    }

    public void setSinginDateTimeG(long singinDateTimeG) {
        this.singinDateTimeG = singinDateTimeG;
    }

    public long getExpireDateTimeG() {
        return expireDateTimeG;
    }

    public void setExpireDateTimeG(long expireDateTimeG) {
        this.expireDateTimeG = expireDateTimeG;
    }

    public String getAgentSignature() {
        return agentSignature;
    }

    public void setAgentSignature(String agentSignature) {
        this.agentSignature = agentSignature;
    }

    public String generateSecureUUID() {
        uuid = java.util.UUID.randomUUID().toString() + "_"
            + (agentSignature == null ? "" : agentSignature.trim().replace(" ", "ـ").toLowerCase().codePoints().sum());
//                + (computerSignature == null ? "" : computerSignature.trim().replace(".", "ـ").codePoints().sum());
        return uuid;
    }

    public boolean isPassSecureCheck(HttpServletRequest request) {
        return request.getHeader("User-Agent").equals(this.agentSignature);//&& request.getRemoteAddr().equals(this.computerSignature);
    }

}
