package org.sadr.web.main.system.log.general;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.sadr._core._type.TtEntityState;
import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main._core.utils._type.TtCookierVariable;
import org.sadr.web.main._core.propertor.PropertorInLog;
import org.sadr.web.main._core.propertor._type.TtPropertorInLogList;
import org.sadr.web.main._core.utils.Cookier;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.task.Task;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
@PersianName("رویدادنگاری")
@Entity
@Table(name = "Web.System.Log")
public class Log extends GenericDataModel<Log> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String SERVER_ID = "serverId";public static final String IMPORTANCE_LEVEL = "importanceLevel";public static final String SENSITIVITY = "sensitivity";public static final String ACTION_TYPE = "actionType";public static final String ACTION_SUB_TYPE = "actionSubType";public static final String ACTION_STATUS = "actionStatus";public static final String USER_ID = "userId";public static final String USERNAME = "username";public static final String USER_CODE = "userCode";public static final String USER_LEVEL = "userLevel";public static final String USER_GROUP_ID = "userGroupId";public static final String DATE_TIME_G = "dateTimeG";public static final String TASK_NAME = "taskName";public static final String TASK_TITLE = "taskTitle";public static final String IS_TASK_TWO_LEVEL_CONFIRM = "isTaskTwoLevelConfirm";public static final String MESSAGE = "message";public static final String SESSION_ID = "sessionId";public static final String COMPUTER_SIGNATURE = "computerSignature";public static final String AGENT_SIGNATURE = "agentSignature";public static final String PORTER_UUID = "porterUuid";public static final String CLIENT_PORT_NUMBER = "clientPortNumber";public static final String CLIENT_IP_ADDRESS = "clientIpAddress";public static final String CLIENT_NAME = "clientName";public static final String URL = "url";public static final String REQUEST_METHOD = "requestMethod";public static final String HTTP_CODE = "httpCode";public static final String HOST_IP_ADDRESS = "hostIpAddress";public static final String HOST_PORT_NUMBER = "hostPortNumber";public static final String SEND_DATE_TIME_G = "sendDateTimeG";public static final String SEND_STATUS = "sendStatus";public static final String ONLINE_LOGGING_STRATEGY = "onlineLoggingStrategy";public static final String $IS_TASK_TWO_LEVEL_CONFIRM_Y = "isTaskTwoLevelConfirmY";public static final String $SEND_DATE_TIME = "sendDateTime";public static final String $ACT_COLUMNS = "actColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $REL_COLUMNS = "relColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    private static final int MESSAGE_LEN = 2048;

    public Log() {
    }

    public Log(ModelAndView andView,
               HttpServletRequest request, String message,
               TtLogHandler handler,
               Task task,
               User user) throws UnknownHostException {

        this.serverId = PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemName)
                + "|" + PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemHostName)
                + "|" + PropertorInLog.getInstance().getProperty(TtPropertorInLogList.SystemVersion)
                + "|" + request.getLocalAddr()
        ;

        String json = "{\"handler\":\"" + handler
                + "\",\"msg\":\"" + message
                + "\",\"uri\":\"" + request.getRequestURI() + "\"";
        if (request.getQueryString() != null) {
            json += ",\"QueryStr\":\"" + request.getQueryString() + "\"";
        }

        if (andView != null && andView.getModel() != null) {
            Object obj;
            obj = andView.getModel().get("noticeList");
            if (obj != null) {
                andView.getModel().remove("noticeList");
            }
            obj = andView.getModelMap().get("actionSubType");
            if (obj != null) {
                this.actionSubType = TtTaskActionSubType.getValue(obj.toString());
                andView.getModel().remove("actionSubType");
            }
            obj = andView.getModel().get("actionStatus");
            if (obj != null) {
                this.actionStatus = TtTaskActionStatus.getValue(obj.toString());
                andView.getModel().remove("actionStatus");
            }
            obj = andView.getModel().get("secretNote");
            if (obj != null) {
                andView.getModel().remove("secretNote");
            }
        }

        json += "}";

//        this.logLevel = logLevel;
//        this.threatLevel = threatLevel;
        this.url = request.getRequestURL().toString();
        if (this.url.contains("/er/")) {
            this.httpCode = this.url.substring(this.url.indexOf("/er/") + 4);
        }
        this.hostPortNumber = request.getLocalPort();
        this.hostIpAddress = request.getLocalAddr();
        this.clientIpAddress = request.getRemoteAddr();
        this.clientPortNumber = request.getRemotePort();
        if (user != null) {
            this.userLevel = user.getLevel();
            this.userId = user.getId();
            this.username = user.getUsername();
            this.userCode = user.getUserCode();
        }


        this.message = json.length() < MESSAGE_LEN ? json : json.substring(0, MESSAGE_LEN);
        if (task != null) {
            this.actionType = task.getActionType();
            this.taskName = task.getSignature();
            this.taskTitle = task.getTitle();
            this.sensitivity = task.getSensitivity();
            this.importanceLevel = task.getImportanceLevel();
            this.isTaskTwoLevelConfirm = task.getIsTwoLevelConfirm();
            //----------------- send info
            this.onlineLoggingStrategy = task.getOnlineLoggingStrategy();
            if (this.onlineLoggingStrategy != null) {
                switch (this.onlineLoggingStrategy) {
                    case WithDelay:
                        this.sendDateTimeG = new Date().getTime() + (task.getOnlineLoggingDelay() * 60000);
                        break;
                    case Scheduling:
                        String t = task.getOnlineSchedulingTime();
                        int h, m, s;
                        h = m = s = 0;
                        if (t != null) {
                            String[] st = t.split(":");
                            if (st.length > 0) {
                                try {
                                    h = Integer.parseInt(st[0]);
                                } catch (Exception e) {
                                }
                            }
                            if (st.length > 1) {
                                try {
                                    m = Integer.parseInt(st[1]);
                                } catch (Exception e) {
                                }
                            }
                            if (st.length > 2) {
                                try {
                                    s = Integer.parseInt(st[2]);
                                } catch (Exception e) {
                                }
                            }
                        }
                        Date date = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, h);
                        cal.set(Calendar.MINUTE, m);
                        cal.set(Calendar.SECOND, s);

                        long timeInMillis = cal.getTimeInMillis();
                        if (timeInMillis < date.getTime()) {
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        this.sendDateTimeG = cal.getTimeInMillis();
                        break;
                    default:
                        this.sendDateTimeG = new Date().getTime();
                        break;
                }
            }
        }

        this.setEntityState(TtEntityState.Exist);
        this.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
        this.dateTimeG = new Date().getTime();

        this.agentSignature = request.getHeader("User-Agent");
        this.computerSignature = InetAddress.getLocalHost().getHostAddress();
        this.porterUuid = Cookier.getValue(request, TtCookierVariable.UserPorterUUID.getKey());
        this.sessionId = request.getSession().getId();
        this.requestMethod = request.getMethod();

        this.sendStatus = TtLogOnlineSendStatus.NotSend;
    }

    public Log(ModelAndView andView, HttpServletRequest request, String message, TtLogHandler handler,
               Task task,
               User user, UserGroup ug) throws UnknownHostException {
        this(andView, request, message, handler, task, user);
        this.userGroupId = ug != null ? ug.getId() : 0;

    }

    /////////////////////////////////////

    @Size(max = 250)
    @PersianName("شناسه سامانه")
    private String serverId;

    @PersianName("سطح اهمیت")
    private TtTaskImportanceLevel importanceLevel;

    @PersianName("حساسیت")
    private TtTaskSensitivity sensitivity;

    @PersianName("نوع فعالیت")
    private TtTaskActionType actionType;

    @PersianName("نوع زیر فعالیت")
    private TtTaskActionSubType actionSubType;

    @PersianName("پرچم امنیتی")
    private TtTaskActionStatus actionStatus;

    //////////////////////////////////////

    @PersianName("شناسه کاربری")
    private long userId;

    @Size(max = 200)
    @PersianName("نام کاربری")
    private String username;

    @Size(max = 200)
    @PersianName("کد یکتای کاربری")
    private String userCode;

    @PersianName("سطح دسترسی کاربر")
    private TtUserLevel userLevel;

    @PersianName("شناسه گروه کاربران")
    private long userGroupId;

    //////////////////////////////////////

    @PersianName("زمان")
    private long dateTimeG;

    @Size(max = 255)
    @PersianName("نام عملیات")
    private String taskName;

    @Size(max = 255)
    @PersianName("عنوان عملیات")
    private String taskTitle;

    @PersianName("عملیات دو سطحی")
    private Boolean isTaskTwoLevelConfirm;

    @Size(max = MESSAGE_LEN)
    @PersianName("پیغام")
    private String message;

    //////////////////////////////////////

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

    @PersianName("شماره پورت کاربر")
    private Integer clientPortNumber;

    @Size(max = 100)
    @PersianName("آدرس Ip کاربر")
    private String clientIpAddress;

    @Size(max = 255)
    @PersianName("نام کاربر")
    private String clientName;

    @Size(max = 512)
    @PersianName("آدرس URL")
    private String url;

    @PersianName("روش درخواست")
    private String requestMethod;

    @Size(max = 100)
    @PersianName("کد HTTP")
    private String httpCode;

    ///====================================  HOST > CURRENT SERVER

    @Size(max = 100)
    @PersianName("آدرس Ip هاست")
    private String hostIpAddress;

    @PersianName("شماره پورت هاست")
    private Integer hostPortNumber;


    ///====================================  SEND LOG INFO

    @PersianName("زمان ارسال")
    private long sendDateTimeG;

    @PersianName("وضعیت ارسال")
    private TtLogOnlineSendStatus sendStatus;

    @PersianName("روش ارسال")
    private TtTaskOnlineLoggingStrategy onlineLoggingStrategy;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    @PersianName("زمان ارسال")
    public String getSendDateTime() {
        return ParsCalendar.getInstance().getShortDateTime(sendDateTimeG);
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=   METHODS

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public long getDateTimeG() {
        return dateTimeG;
    }

    public void setDateTimeG(long dateTimeG) {
        this.dateTimeG = dateTimeG;
    }

//    public UserGroup getUserGroup() {
//        return userGroup;
//    }
//
//    public void setUserGroup(UserGroup userGroup) {
//        this.userGroup = userGroup;
//    }

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

    public Integer getClientPortNumber() {
        return clientPortNumber;
    }

    public void setClientPortNumber(Integer clientPortNumber) {
        this.clientPortNumber = clientPortNumber;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getHostIpAddress() {
        return hostIpAddress;
    }

    public void setHostIpAddress(String hostIpAddress) {
        this.hostIpAddress = hostIpAddress;
    }

    public Integer getHostPortNumber() {
        return hostPortNumber;
    }

    public void setHostPortNumber(Integer hostPortNumber) {
        this.hostPortNumber = hostPortNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public TtSecurityThreatLevel getThreatLevel() {
//        return threatLevel;
//    }
//
//    public void setThreatLevel(TtSecurityThreatLevel threatLevel) {
//        this.threatLevel = threatLevel;
//    }
//
//    public TtLogLevel getLogLevel() {
//        return logLevel;
//    }
//
//    public void setLogLevel(TtLogLevel logLevel) {
//        this.logLevel = logLevel;
//    }

    public TtUserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(TtUserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public TtTaskImportanceLevel getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(TtTaskImportanceLevel importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public TtTaskSensitivity getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(TtTaskSensitivity sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Boolean getIsTaskTwoLevelConfirm() {
        return isTaskTwoLevelConfirm;
    }

    public void setIsTaskTwoLevelConfirm(Boolean taskTwoLevelConfirm) {
        isTaskTwoLevelConfirm = taskTwoLevelConfirm;
    }

    public TtYesNo getIsTaskTwoLevelConfirmY() {
        return TtYesNo.getValueByBool(isTaskTwoLevelConfirm);
    }

    public void setIsTaskTwoLevelConfirmY(TtYesNo taskTwoLevelConfirm) {
        isTaskTwoLevelConfirm = taskTwoLevelConfirm.getBoolean();
    }

    public long getSendDateTimeG() {
        return sendDateTimeG;
    }

    public void setSendDateTimeG(long sendDateTimeG) {
        this.sendDateTimeG = sendDateTimeG;
    }

    public TtLogOnlineSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(TtLogOnlineSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public TtTaskOnlineLoggingStrategy getOnlineLoggingStrategy() {
        return onlineLoggingStrategy;
    }

    public void setOnlineLoggingStrategy(TtTaskOnlineLoggingStrategy onlineLoggingStrategy) {
        this.onlineLoggingStrategy = onlineLoggingStrategy;
    }

    public TtTaskActionType getActionType() {
        return actionType;
    }

    public void setActionType(TtTaskActionType actionType) {
        this.actionType = actionType;
    }

    public TtTaskActionSubType getActionSubType() {
        return actionSubType;
    }

    public void setActionSubType(TtTaskActionSubType actionSubType) {
        this.actionSubType = actionSubType;
    }

    public TtTaskActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(TtTaskActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
