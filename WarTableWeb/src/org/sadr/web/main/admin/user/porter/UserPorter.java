package org.sadr.web.main.admin.user.porter;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin._type.TtUserPorterSignoutStatus;
import org.sadr.web.main.admin.user.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("حامل کاربری")
@Entity
@Table(name = "Web.Admin.User.Porter")
public class UserPorter extends GenericDataModel<UserPorter> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String AGENT_SIGNATURE = "agentSignature";public static final String UUID = "uuid";public static final String SINGIN_DATE_TIME_G = "singinDateTimeG";public static final String SINGOUT_DATE_TIME_G = "singoutDateTimeG";public static final String EXPIRE_DATE_TIME_G = "expireDateTimeG";public static final String CONFIRM_CODE = "confirmCode";public static final String CONFIRM_CODE_DATE_TIME_G = "confirmCodeDateTimeG";public static final String SIGNOUT_STATUS = "signoutStatus";public static final String IS_CONFIRMED = "isConfirmed";public static final String COUNT = "count";public static final String IS_ACTIVE_TWO_STEP_CONFIRM = "isActiveTwoStepConfirm";public static final String _USER = "user";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $IS_ACTIVE_TWO_STEP_CONFIRM_Y = "isActiveTwoStepConfirmY";public static final String $IS_CONFIRMED_Y = "isConfirmedY";public static final String $ACT_COLUMNS = "actColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    public static boolean isValidateUUID(String agentSignature, String computerSignature, String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return false;
        }
        String[] sp = uuid.split("_");
        if (sp.length < 2) {
            return false;
        }

        String s = "" + (agentSignature == null ? "" : agentSignature.trim().replace(" ", "").toUpperCase().codePoints().sum());
        return (s.equals(sp[1]));
    }

    public UserPorter() {
        this.count = 0;
    }

    @Size(max = 255)
    @PersianName("امضای کامپیوتر")
    private String computerSignature;
    @Size(max = 255)
    @PersianName("امضای عامل")
    private String agentSignature;
    @Size(max = 255)
    @PersianName("UUID")
    private String uuid;
    @PersianName("تاریخ ورود")
    private long singinDateTimeG;
    @PersianName("تاریخ خروج")
    private long singoutDateTimeG;
    @PersianName("تاریخ انقضاء")
    private long expireDateTimeG;
    @PersianName("کد تایید")
    private int confirmCode;
    @PersianName("تاریخ ارسال کد تایید")
    private long confirmCodeDateTimeG;
    @PersianName("وضعیت خروج")
    private TtUserPorterSignoutStatus signoutStatus;
    @PersianName("تایید")
    private Boolean isConfirmed;
    @Min(0)
    @PersianName("تعداد")
    private int count;

    @PersianName("فعال")
    private Boolean isActiveTwoStepConfirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    public long getConfirmCodeDateTimeG() {
        return confirmCodeDateTimeG;
    }

    public void setConfirmCodeDateTimeG(long confirmCodeDateTimeG) {
        this.confirmCodeDateTimeG = confirmCodeDateTimeG;
    }

    public int getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(int confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String getComputerSignature() {
        return computerSignature;
    }

    public void setComputerSignature(String computerSignature) {
        this.computerSignature = computerSignature;
    }

    public String getAgentSignature() {
        return agentSignature;
    }

    public void setAgentSignature(String agentSignature) {
        this.agentSignature = agentSignature;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getExpireDateTimeG() {
        return expireDateTimeG;
    }

    public void setExpireDateTimeG(long expireDateTimeG) {
        this.expireDateTimeG = expireDateTimeG;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public TtYesNo getIsConfirmedY() {
        return TtYesNo.getValueByBool(isConfirmed);
    }

    public void setIsConfirmedY(TtYesNo isConfirmed) {
        this.isConfirmed = isConfirmed.getBoolean();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getIsActiveTwoStepConfirm() {
        return isActiveTwoStepConfirm;
    }

    public void setIsActiveTwoStepConfirm(Boolean activeTwoStepConfirm) {
        isActiveTwoStepConfirm = activeTwoStepConfirm;
    }

    public TtYesNo getIsActiveTwoStepConfirmY() {
        return TtYesNo.getValueByBool(isActiveTwoStepConfirm);
    }

    public void setIsActiveTwoStepConfirmY(TtYesNo activeTwoStepConfirm) {
        isActiveTwoStepConfirm = activeTwoStepConfirm.getBoolean();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getSinginDateTimeG() {
        return singinDateTimeG;
    }

    public void setSinginDateTimeG(long singinDateTimeG) {
        this.singinDateTimeG = singinDateTimeG;
    }

    public long getSingoutDateTimeG() {
        return singoutDateTimeG;
    }

    public void setSingoutDateTimeG(long singoutDateTimeG) {
        this.singoutDateTimeG = singoutDateTimeG;
    }

    public TtUserPorterSignoutStatus getSignoutStatus() {
        return signoutStatus;
    }

    public void setSignoutStatus(TtUserPorterSignoutStatus signoutStatus) {
        this.signoutStatus = signoutStatus;
    }

    public String generateSecureUUID() {
        uuid = java.util.UUID.randomUUID().toString() + "_"
            + (agentSignature == null ? "" : agentSignature.trim().replace(" ", "").toUpperCase().codePoints().sum());
//                + (computerSignature == null ? "" : computerSignature.trim().replace(".", "").codePoints().sum());
        return uuid;
    }

    public boolean isPassSecureCheck(HttpServletRequest request) {
        return request.getHeader("User-Agent").equals(this.agentSignature);// && request.getRemoteAddr().equals(this.computerSignature);
    }

    public Object getSecretNote() {
        return "{\"porterId\":" + getId() + "}";
    }

}
