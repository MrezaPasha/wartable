package org.sadr.web.main.system.task;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main._core._type.TtRequestMethod;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtTaskActionType;
import org.sadr.web.main.system._type.TtTaskImportanceLevel;
import org.sadr.web.main.system._type.TtTaskOnlineLoggingStrategy;
import org.sadr.web.main.system._type.TtTaskSensitivity;
import org.sadr.web.main.system.module.Module;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName("کار")
@Entity
@Table(name = "Web.System.Task")
public class Task extends GenericDataModel<Task> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _MODULE = "module";public static final String URL = "url";public static final String PARENT_URL = "parentUrl";public static final String METHOD = "method";public static final String MENU_MESSAGE_CODE = "menuMessageCode";public static final String MESSAGE_CODE = "messageCode";public static final String ACCESS_LEVEL = "accessLevel";public static final String SIGNATURE = "signature";public static final String MENU_IDENTITY = "menuIdentity";public static final String IS_PANEL_TASK = "isPanelTask";public static final String IS_AJAX = "isAjax";public static final String IS_REFRESHED = "isRefreshed";public static final String IS_ACTIVE = "isActive";public static final String IS_TWO_LEVEL_CONFIRM = "isTwoLevelConfirm";public static final String IS_SUPER_ADMIN = "isSuperAdmin";public static final String IS_LOG_MANAGER = "isLogManager";public static final String IMPORTANCE_LEVEL = "importanceLevel";public static final String SENSITIVITY = "sensitivity";public static final String IS_ONLINE_LOGGING = "isOnlineLogging";public static final String IS_ACTIVE_LOGGING = "isActiveLogging";public static final String ONLINE_LOGGING_STRATEGY = "onlineLoggingStrategy";public static final String ACTION_TYPE = "actionType";public static final String ONLINE_LOGGING_DELAY = "onlineLoggingDelay";public static final String ONLINE_SCHEDULING_TIME = "onlineSchedulingTime";public static final String _USERS = "users";public static final String _USER_GROUPS = "userGroups";public static final String $TITLE = "title";public static final String $ONLINE_LOGGING_DELAY_TITLE = "onlineLoggingDelayTitle";public static final String $ONLINE_SCHEDULING_TIME_TITLE = "onlineSchedulingTimeTitle";public static final String $IS_ONLINE_LOGGING_Y = "isOnlineLoggingY";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $FULL_TITLE = "fullTitle";public static final String $REL_COLUMNS = "relColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //


    public Task() {
        isActive = true;
        isActiveLogging
                = isAjax
                = isOnlineLogging
                = isTwoLevelConfirm
                = isRefreshed
                = isSuperAdmin
                = isPanelTask = false;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    Module module;
    @PersianName("آدرس")
    private String url;
    @PersianName("آدرس پنل")
    private String parentUrl;
    @PersianName("متد")
    private TtRequestMethod method;
    @PersianName("کد متن منو")
    private String menuMessageCode;
    @PersianName("کد متن")
    private String messageCode;
    @PersianName("سطح دسترسی")
    private TtTaskAccessLevel accessLevel;
    @PersianName("امضاء")
    private String signature;
    @PersianName("شناسه منو")
    private String menuIdentity;
    @PersianName("عملیات سمت پنل")
    private Boolean isPanelTask;
    @PersianName("عملیات اجکسی")
    private Boolean isAjax;
    @PersianName("بروزرسانی")
    private Boolean isRefreshed;
    @PersianName("فعال سازی")
    private Boolean isActive;
    @PersianName("تایید دوسطحی")
    private Boolean isTwoLevelConfirm;
    @PersianName("عملیات مدیر ارشد")
    private Boolean isSuperAdmin;
    @PersianName("عملیات مدیر رویدادنگاری")
    private Boolean isLogManager;


    //==================== Log

    @PersianName("سطح اهمیت")
    private TtTaskImportanceLevel importanceLevel;

    @PersianName("حساسیت")
    private TtTaskSensitivity sensitivity;

    @PersianName("ارسال رویدادنگاری")
    private Boolean isOnlineLogging;

    @PersianName("فعالسازی رویدادنگاری")
    private Boolean isActiveLogging;

    @PersianName("روش ارسال رویداد")
    private TtTaskOnlineLoggingStrategy onlineLoggingStrategy;

    @PersianName("نوع فعالیت")
    private TtTaskActionType actionType;

    @PersianName("تاخیر در ارسال رویداد (دقیقه)")
    private int onlineLoggingDelay;

    @Size(max = 15)
    @PersianName("ساعت ارسال (09:29:00)")
    private String onlineSchedulingTime;

    //==================== Relation
    @ManyToMany
    @JoinTable(name = "Web.System.Task_User",
            joinColumns = {
                    @JoinColumn(name = "task_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")})
    @PersianName("کاربران")
    private Set<User> users;


    @ManyToMany
    @JoinTable(name = "Web.System.Task_UserGroup",
            joinColumns = {
                    @JoinColumn(name = "task_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "userGroup_id")})
    @PersianName("گروه کاربران")
    private Set<UserGroup> userGroups;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    public Object getSecretNote() {
        return "{\"taskId\":" + getId() + "}";
    }

    public String getTitle() {
        return SpringMessager.get(messageCode);
    }

    public String getFullTitle() {
        return SpringMessager.get(messageCode) + (isSuperAdmin != null && isSuperAdmin ? "*" : "");
    }

    public String getOnlineLoggingDelayTitle() {
        return onlineLoggingDelay + " دقیقه";
    }

    public String getOnlineSchedulingTimeTitle() {
        return "زمان: " + onlineSchedulingTime;
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Boolean getIsPanelTask() {
        return isPanelTask;
    }

    public void setIsPanelTask(Boolean isPanelTask) {
        this.isPanelTask = isPanelTask;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuMessageCode() {
        return menuMessageCode;
    }

    public void setMenuMessageCode(String menuMessageCode) {
        this.menuMessageCode = menuMessageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public TtTaskAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(TtTaskAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Boolean getIsRefreshed() {
        return isRefreshed;
    }

    public void setIsRefreshed(Boolean isRefreshed) {
        this.isRefreshed = isRefreshed;
    }

    public TtRequestMethod getMethod() {
        return method;
    }

    public void setMethod(TtRequestMethod method) {
        this.method = method;
    }

    public String getMenuIdentity() {
        return menuIdentity;
    }

    public void setMenuIdentity(String menuIdentity) {
        this.menuIdentity = menuIdentity;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }


    public Boolean getIsSuperAdmin() {
        return isSuperAdmin == null ? false : isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Boolean getIsTwoLevelConfirm() {
        return isTwoLevelConfirm;
    }

    public void setIsTwoLevelConfirm(Boolean twoLevelConfirm) {
        isTwoLevelConfirm = twoLevelConfirm;
    }

    public Boolean getIsAjax() {
        return isAjax;
    }

    public void setIsAjax(Boolean ajax) {
        isAjax = ajax;
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

    public Boolean getIsOnlineLogging() {
        return isOnlineLogging;
    }

    public void setIsOnlineLogging(Boolean onlineLogging) {
        isOnlineLogging = onlineLogging;
    }

    public TtYesNo getIsOnlineLoggingY() {
        return TtYesNo.getValueByBool(isOnlineLogging);
    }

    public void setIsOnlineLoggingY(TtYesNo onlineLogging) {
        isOnlineLogging = onlineLogging.getBoolean();
    }

    public Boolean getIsActiveLogging() {
        return isActiveLogging;
    }

    public void setIsActiveLogging(Boolean activeLogging) {
        isActiveLogging = activeLogging;
    }

    public TtTaskOnlineLoggingStrategy getOnlineLoggingStrategy() {
        return onlineLoggingStrategy;
    }

    public void setOnlineLoggingStrategy(TtTaskOnlineLoggingStrategy onlineLoggingStrategy) {
        this.onlineLoggingStrategy = onlineLoggingStrategy;
    }

    public String getOnlineSchedulingTime() {
        return onlineSchedulingTime;
    }

    public void setOnlineSchedulingTime(String onlineSchedulingTime) {
        this.onlineSchedulingTime = onlineSchedulingTime;
    }

    public int getOnlineLoggingDelay() {
        return onlineLoggingDelay;
    }

    public void setOnlineLoggingDelay(int onlineLoggingDelay) {
        this.onlineLoggingDelay = onlineLoggingDelay;
    }

    public Boolean getIsLogManager() {
        return isLogManager;
    }

    public void setIsLogManager(Boolean logManager) {
        isLogManager = logManager;
    }

    public TtTaskActionType getActionType() {
        return actionType;
    }

    public void setActionType(TtTaskActionType actionType) {
        this.actionType = actionType;
    }
}
