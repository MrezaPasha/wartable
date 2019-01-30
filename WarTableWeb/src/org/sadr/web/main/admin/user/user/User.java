package org.sadr.web.main.admin.user.user;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin._type.TtGender;
import org.sadr.web.main.admin._type.TtUserIpRangeType;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.system.task.Task;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

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
@PersianName("کاربر")
@Entity
@Table(name = "Web.Admin.User")
public class User extends GenericDataModel<User> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String USERNAME = "username";public static final String USER_CODE = "userCode";public static final String PASSWORD = "password";public static final String IS_NEED_TO_CHANGE_PASSWORD = "isNeedToChangePassword";public static final String PASSWORD_HISTORY = "passwordHistory";public static final String PASSWORD_DATE_TIME = "passwordDateTime";public static final String LAST_SIGNIN_DATE_TIME = "lastSigninDateTime";public static final String COMMENT = "comment";public static final String IP_RANGE_TYPE = "ipRangeType";public static final String IP_ADDRESS = "ipAddress";public static final String IP_ADDRESS_FIRST_SIGNIN = "ipAddressFirstSignin";public static final String IP_ADDRESS_START = "ipAddressStart";public static final String IP_ADDRESS_END = "ipAddressEnd";public static final String GENDER = "gender";public static final String FIRST_NAME = "firstName";public static final String LAST_NAME = "lastName";public static final String ACCESS_LIMIT_YEARLY_START = "accessLimitYearlyStart";public static final String ACCESS_LIMIT_YEARLY_END = "accessLimitYearlyEnd";public static final String ACCESS_LIMIT_MONTHLY_START = "accessLimitMonthlyStart";public static final String ACCESS_LIMIT_MONTHLY_END = "accessLimitMonthlyEnd";public static final String ACCESS_LIMIT_DAILY_START = "accessLimitDailyStart";public static final String ACCESS_LIMIT_DAILY_END = "accessLimitDailyEnd";public static final String ACCESS_LIMIT_TIMELY_START = "accessLimitTimelyStart";public static final String ACCESS_LIMIT_TIMELY_END = "accessLimitTimelyEnd";public static final String _LOGO = "logo";public static final String STATUS = "status";public static final String LEVEL = "level";public static final String IS_BLOCKED = "isBlocked";public static final String IS_SUPER_ADMIN = "isSuperAdmin";public static final String IS_LOG_MANAGER = "isLogManager";public static final String PORTER_UUID = "porterUuid";public static final String _USER_UUIDS = "userUuids";public static final String _TASKS = "tasks";public static final String _USER_GROUPS = "userGroups";public static final String $FULL_NAME = "fullName";public static final String $AUTHORIZED_MENU = "authorizedMenu";public static final String $IS_ACTIVE = "isActive";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $IS_MASTER = "isMaster";public static final String $VIR_COLUMNS = "virColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $EXPORT_TITLE = "exportTitle";public static final String $IS_ADMIN = "isAdmin";public static final String $IS_CLIENT = "isClient";public static final String $IS_BLOCKED_Y = "isBlockedY";public static final String $FULL_NAME_ID = "fullNameId";public static final String $IS_NOT_MASTER = "isNotMaster";public static final String $IS_NOT_CLIENT = "isNotClient";public static final String $IS_NOT_ADMIN = "isNotAdmin";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///////////////////////
    public User() {
        this.isBlocked = false;
        this.isSuperAdmin = false;
        this.isLogManager = false;
        this.isNeedToChangePassword = true;
        this.level = TtUserLevel.Client;
        this.status = TtUserStatus.Active;
    }

    ///############################## PUBLIC
    @SafeHtml
    @NotEmpty
    @PersianName("نام کاربری")
    private String username;

    @SafeHtml
    @Type(type = "encryptedString")
    @PersianName("کد کاربری")
    private String userCode;

    @SafeHtml
    @Column(nullable = false)
    @Size(max = 255)
    @PersianName("رمز")
    private String password;

    @PersianName("نیاز به تغییر رمز")
    private Boolean isNeedToChangePassword;

    @Type(type = "text")
    @PersianName("تاریخچه رمز")
    private String passwordHistory;

    @SafeHtml
    @Size(max = 30)
    @PersianName("زمان تغییر رمز")
    private String passwordDateTime;

    @SafeHtml
    @Size(max = 30)
    @PersianName("آخرین زمان ورود")
    private String lastSigninDateTime;
    @SafeHtml
    @Size(max = 255)
    @PersianName("توضیحات")
    private String comment;

    @NotNull
    @PersianName("سیاست آدرس IP مجاز")
    private TtUserIpRangeType ipRangeType;

    @SafeHtml
    @Size(max = 50)
    @PersianName("آدرس Ip")
    private String ipAddress;

    @SafeHtml
    @Size(max = 50)
    @PersianName("آدرس IP اولین ورود")
    private String ipAddressFirstSignin;

    @SafeHtml
    @Size(max = 50)
    @PersianName("شروع بازه آدرس IP")
    private String ipAddressStart;

    @SafeHtml
    @Size(max = 50)
    @PersianName("پایان بازه آدرس IP")
    private String ipAddressEnd;


    ///############################## HUMAN
    @NotNull
    @PersianName("جنسیت")
    private TtGender gender;

    @SafeHtml
    @Size(min = 2, max = 50)
    @PersianName("نام")
    private String firstName;
    @SafeHtml
    @Size(min = 2, max = 50)
    @PersianName("نام خانوادگی")
    private String lastName;
    ///##############################  Access Limit in Time
    @SafeHtml
    @Size(max = 30)
    @PersianName("محدودیت دسترسی سالیانه (شروع)")
    private String accessLimitYearlyStart;

    @SafeHtml
    @Size(max = 30)
    @PersianName("محدودیت دسترسی سالیانه (پایان)")
    private String accessLimitYearlyEnd;

    @Min(0)
    @Max(12)
    @PersianName("محدودیت دسترسی ماهیانه (شروع)")
    private int accessLimitMonthlyStart;

    @Min(0)
    @Max(12)
    @PersianName("محدودیت دسترسی ماهیانه (پایان)")
    private int accessLimitMonthlyEnd;

    @Min(0)
    @Max(31)
    @PersianName("محدودیت دسترسی روزانه (شروع)")
    private int accessLimitDailyStart;

    @Min(0)
    @Max(31)
    @PersianName("محدودیت دسترسی روزانه (پایان)")
    private int accessLimitDailyEnd;

    @SafeHtml
    @Size(max = 15)
    @PersianName("محدودیت دسترسی ساعتی (شروع)")
    private String accessLimitTimelyStart;

    @SafeHtml
    @Size(max = 15)
    @PersianName("محدودیت دسترسی ساعتی (پایان)")
    private String accessLimitTimelyEnd;

    ///##############################
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("عکس")
    private File logo;//#
    ///##############################  STATUS
    @NotNull
    @PersianName("وضعیت")
    private TtUserStatus status;

    @NotNull
    @PersianName("سطح دسترسی")
    private TtUserLevel level;

    @PersianName("مسدود")
    private Boolean isBlocked;
    //////
    @PersianName("مدیر ارشد")
    private Boolean isSuperAdmin;

    @PersianName("مدیر رویدادنگار")
    private Boolean isLogManager;

    ///##############################  LOGGING
    @SafeHtml
    @Size(max = 1000)
    @PersianName("UUIDهای حامل")
    private String porterUuid;
    ///##############################  SYSTEM
    @OrderBy(Task.MENU_IDENTITY)
    @ManyToMany
    @JoinTable(name = "Web.System.Task_User",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "task_id")})
    @PersianName("سطح دسترسی")
    private Set<Task> tasks;
    ///##############################  COMMON RELATION
    @ManyToMany
    @JoinTable(name = "Web.Admin.User.User_UserGroup",
            joinColumns = {
                    @JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "userGroup_id")})
    @PersianName("گروه کاربران")
    private Set<UserGroup> userGroups;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS

    public void addPasswordToHistory() {
        if (password == null || password.isEmpty()) {
            return;
        }
        if (passwordHistory == null || passwordHistory.isEmpty()) {
            passwordHistory = "|" + password + "|";
        } else {
            passwordHistory = "|" + password + "|," + passwordHistory;
        }
        if (passwordHistory.length() > 102) {
            passwordHistory = passwordHistory.substring(0, 100);
            passwordHistory = passwordHistory.substring(0, passwordHistory.lastIndexOf(",|"));
        }
    }

    public boolean isPasswordInHistory(String password, int count) {
        if (passwordHistory == null || passwordHistory.isEmpty() || password == null) {
            return false;
        }
        String[] split = passwordHistory.split(",");
        password = "|" + password + "|";
        for (int i = 0; i < split.length && i < count; i++) {
            if (password.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    public Boolean isPorterUuidExist(String uuid) {
        return porterUuid != null && porterUuid.contains("|" + uuid + "|");
    }

    public Boolean removePorterUuid(String uuid) {
        if (porterUuid != null) {
            if (porterUuid.startsWith("|" + uuid + "|")) {
                porterUuid = porterUuid.replace("|" + uuid + "|", "");
                return true;
            } else if (porterUuid.contains(",|" + uuid + "|")) {
                porterUuid = porterUuid.replace(",|" + uuid + "|", "");
                return true;
            }
        }
        return false;
    }

    public void addPorterUuid(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return;
        }
        if (porterUuid != null && porterUuid.contains("|" + uuid + "|")) {
            return;
        }
        if (porterUuid == null || porterUuid.isEmpty()) {
            porterUuid = "|" + uuid + "|";
        } else {
            String[] split = porterUuid.split(",");
            if (split.length > 5) {
                porterUuid = porterUuid.substring(porterUuid.indexOf(",") + 1);
            }
            porterUuid += "," + "|" + uuid + "|";
        }
    }

    public String getAuthorizedMenu() {
        String am = "";
        for (Task t : tasks) {
            am += t.getMenuIdentity() + ",";
        }
        return am;
    }

    public boolean getIsActive() {
        return status == TtUserStatus.Active;
    }


    public boolean getIsAdmin() {
        return level == TtUserLevel.Administrator;
    }

    public boolean getIsMaster() {
        return level == TtUserLevel.Master;
    }

    public boolean getIsClient() {
        return level == TtUserLevel.Client;
    }

    public boolean getIsNotAdmin() {
        return level != TtUserLevel.Administrator;
    }

    public boolean getIsNotMaster() {
        return level != TtUserLevel.Master;
    }

    public boolean getIsNotClient() {
        return level != TtUserLevel.Client;
    }

    public Object getSecretNote() {
        return "{\"userId\":" + getId() + " , \"level\":\"" + level + "\"}";
    }

    @PersianName("نام")
    public String getFullNameId() {
        try {
            return getId() + " | " + gender.getTitle() + " " + firstName + " " + lastName + ((getEntityState() != null && getEntityState().isTrashOrRemove()) ? " <حذف شده>" : "");
        } catch (Exception e) {
            return getId() + " | " + firstName + " " + lastName + ((getEntityState() != null && getEntityState().isTrashOrRemove()) ? " <حذف شده>" : "");
        }
    }

    @PersianName("نام")
    public String getFullName(int limit) {
        String fullN = getFullName();
        if (fullN.length() > limit) {
            return fullN.substring(0, fullN.substring(0, limit).lastIndexOf(" "));
        }
        return fullN;
    }

    @PersianName("نام")
    public String getFullName() {
        return (gender == null ? "" : gender.getTitle() + " ") + (firstName == null ? "" : firstName + " ") + (lastName == null ? "" : lastName) + ((getEntityState() != null && getEntityState().isTrashOrRemove()) ? " <حذف شده>" : "");
    }

    @Override
    public String getExportTitle() {
        return getFullName();
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastSigninDateTime() {
        return lastSigninDateTime;
    }

    public void setLastSigninDateTime(String lastSigninDateTime) {
        this.lastSigninDateTime = lastSigninDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TtGender getGender() {
        return gender;
    }

    public void setGender(TtGender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }

    public TtUserStatus getStatus() {
        return status;
    }

    public void setStatus(TtUserStatus status) {
        this.status = status;
    }

    public TtUserLevel getLevel() {
        return level;
    }

    public void setLevel(TtUserLevel level) {
        this.level = level;
    }


    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean superAdmin) {
        this.isSuperAdmin = superAdmin;
    }

    public void setSuperAdminLogingIn(Boolean superAdminLogingIn) {
        superAdminLogingIn = superAdminLogingIn;
    }

    public String getPorterUuid() {
        return porterUuid;
    }

    public void setPorterUuid(String porterUuid) {
        this.porterUuid = porterUuid;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        this.isBlocked = blocked;
    }

    public TtYesNo getIsBlockedY() {
        return TtYesNo.getValueByBool(isBlocked);
    }

    public void setIsBlockedY(TtYesNo blocked) {
        this.isBlocked = blocked.getBoolean();
    }

    public String getPasswordHistory() {
        return passwordHistory;
    }

    public void setPasswordHistory(String passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    public String getPasswordDateTime() {
        return passwordDateTime;
    }

    public void setPasswordDateTime(String passwordDateTime) {
        this.passwordDateTime = passwordDateTime;
    }

    public Boolean getIsNeedToChangePassword() {
        return isNeedToChangePassword;
    }

    public void setIsNeedToChangePassword(Boolean isNeedToChangePassword) {
        this.isNeedToChangePassword = isNeedToChangePassword;
    }

    public TtUserIpRangeType getIpRangeType() {
        return ipRangeType;
    }

    public void setIpRangeType(TtUserIpRangeType ipRangeType) {
        this.ipRangeType = ipRangeType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddressStart() {
        return ipAddressStart;
    }

    public void setIpAddressStart(String ipAddressStart) {
        this.ipAddressStart = ipAddressStart;
    }

    public String getIpAddressEnd() {
        return ipAddressEnd;
    }

    public void setIpAddressEnd(String ipAddressEnd) {
        this.ipAddressEnd = ipAddressEnd;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getIpAddressFirstSignin() {
        return ipAddressFirstSignin;
    }

    public void setIpAddressFirstSignin(String ipAddressFirstSignin) {
        this.ipAddressFirstSignin = ipAddressFirstSignin;
    }

    public String getAccessLimitYearlyStart() {
        return accessLimitYearlyStart;
    }

    public void setAccessLimitYearlyStart(String accessLimitYearlyStart) {
        this.accessLimitYearlyStart = accessLimitYearlyStart;
    }

    public String getAccessLimitYearlyEnd() {
        return accessLimitYearlyEnd;
    }

    public void setAccessLimitYearlyEnd(String accessLimitYearlyEnd) {
        this.accessLimitYearlyEnd = accessLimitYearlyEnd;
    }

    public int getAccessLimitMonthlyStart() {
        return accessLimitMonthlyStart;
    }

    public void setAccessLimitMonthlyStart(int accessLimitMonthlyStart) {
        this.accessLimitMonthlyStart = accessLimitMonthlyStart;
    }

    public int getAccessLimitMonthlyEnd() {
        return accessLimitMonthlyEnd;
    }

    public void setAccessLimitMonthlyEnd(int accessLimitMonthlyEnd) {
        this.accessLimitMonthlyEnd = accessLimitMonthlyEnd;
    }

    public int getAccessLimitDailyStart() {
        return accessLimitDailyStart;
    }

    public void setAccessLimitDailyStart(int accessLimitDailyStart) {
        this.accessLimitDailyStart = accessLimitDailyStart;
    }

    public int getAccessLimitDailyEnd() {
        return accessLimitDailyEnd;
    }

    public void setAccessLimitDailyEnd(int accessLimitDailyEnd) {
        this.accessLimitDailyEnd = accessLimitDailyEnd;
    }

    public String getAccessLimitTimelyStart() {
        return accessLimitTimelyStart;
    }

    public void setAccessLimitTimelyStart(String accessLimitTimelyStart) {
        this.accessLimitTimelyStart = accessLimitTimelyStart;
    }

    public String getAccessLimitTimelyEnd() {
        return accessLimitTimelyEnd;
    }

    public void setAccessLimitTimelyEnd(String accessLimitTimelyEnd) {
        this.accessLimitTimelyEnd = accessLimitTimelyEnd;
    }

    public Boolean getIsLogManager() {
        return isLogManager;
    }

    public void setIsLogManager(Boolean logManager) {
        isLogManager = logManager;
    }
}
