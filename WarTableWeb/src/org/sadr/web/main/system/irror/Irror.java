package org.sadr.web.main.system.irror;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.utils.Cookier;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtHttpErrorStatus;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;

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
@TypeDef(
        name = "encryptedString",
        typeClass = EncryptedStringType.class,
        parameters = {
                @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor")
        }
)
@PersianName(value = "خطا")
@Entity
@Table(name = "Web.System.Irror")
public class Irror extends GenericDataModel<Irror> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String MESSAGE = "message";public static final String CAUSE = "cause";public static final String SESSION_ID = "sessionId";public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String AGENT_SIGNATURE = "agentSignature";public static final String PORTER_UUID = "porterUuid";public static final String TASK_NAME = "taskName";public static final String HTTP_ERROR_CODE = "httpErrorCode";public static final String IS_VISITED = "isVisited";public static final String VISIT_COUNT = "visitCount";public static final String STATUS = "status";public static final String LEVEL = "level";public static final String PLACE = "place";public static final String _USER = "user";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $USER_FULL_NAME = "userFullName";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    public Irror() {
    }

    public Irror(String message, String cause, String taskName, TtHttpErrorCode___ httpErrorCode, TtIrrorLevel level,TtIrrorPlace place, HttpServletRequest request) {
        this.message = message;
        this.cause = cause;
        this.taskName = taskName;
        this.httpErrorCode = httpErrorCode;

        if (request != null) {
            this.sessionId = request.getSession().getId();
            this.computerSignature = request.getRemoteAddr();
            this.agentSignature = request.getHeader("User-Agent");
            this.porterUuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID);
            this.user = (User) request.getSession().getAttribute("sUser");
        }
        this.status = TtHttpErrorStatus.New;
        this.isVisited = false;
        this.level = level;
        this.place =place;
    }

    public Irror(String message, String cause, String taskName, TtHttpErrorCode___ httpErrorCode, TtIrrorLevel level, User user) {
        this.message = message;
        this.cause = cause;
        this.taskName = taskName;
        this.httpErrorCode = httpErrorCode;
        this.user = user;

        this.status = TtHttpErrorStatus.New;
        this.isVisited = false;
        this.level = level;
    }

    @Type(type = "text")
    @PersianName("متن خطا")
    private String message;

    @Size(max = 255)
    @PersianName("علت")
    private String cause;

    @Type(type = "encryptedString")
    @Size(max = 255)
    @PersianName("شناسه نشست")
    private String sessionId;

    @Size(max = 255)
    @PersianName("امضای کامپیوتر")
    private String computerSignature;

    @Size(max = 255)
    @PersianName("امضای عامل")
    private String agentSignature;

    @Size(max = 255)
    @PersianName("UUID حامل")
    private String porterUuid;

    @Size(max = 255)
    @PersianName("نام عملیات")
    private String taskName;

    @PersianName("کد خطا")
    private TtHttpErrorCode___ httpErrorCode;

    @PersianName("مشاهده شده")
    private Boolean isVisited;

    @Min(0)
    @PersianName("تعداد بازدید")
    private int visitCount;

    @PersianName("وضعیت")
    private TtHttpErrorStatus status;

    @PersianName("سطح خطا")
    private TtIrrorLevel level;

    @PersianName("محل رخداد")
    private TtIrrorPlace place;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    @PersianName("کاربر")
    public String getUserFullName(){
        return user!=null? user.getFullName():"";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getPorterUuid() {
        return porterUuid;
    }

    public void setPorterUuid(String porterUuid) {
        this.porterUuid = porterUuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TtHttpErrorCode___ getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(TtHttpErrorCode___ httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean isVisited) {
        this.isVisited = isVisited;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void addVisitCount() {
        this.visitCount += 1;
    }

    public TtHttpErrorStatus getStatus() {
        return status;
    }

    public void setStatus(TtHttpErrorStatus status) {
        this.status = status;
    }

    public TtIrrorLevel getLevel() {
        return level;
    }

    public void setLevel(TtIrrorLevel level) {
        this.level = level;
    }

    public TtIrrorPlace getPlace() {
        return place;
    }

    public void setPlace(TtIrrorPlace place) {
        this.place = place;
    }
}
