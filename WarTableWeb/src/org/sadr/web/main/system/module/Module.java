package org.sadr.web.main.system.module;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.SpringMessager;
import org.sadr.web.main.system.task.Task;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName(value = "ماژول")
@Entity
@Table(name = "Web.System.Module")
public class Module extends GenericDataModel<Module> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String URL = "url";public static final String MENU_MESSAGE_CODE = "menuMessageCode";public static final String MESSAGE_CODE = "messageCode";public static final String PACKAGE_NAME = "packageName";public static final String CLASS_NAME = "className";public static final String IS_PROTECTED = "isProtected";public static final String IS_REFRESHED = "isRefreshed";public static final String _TASKS = "tasks";public static final String $METHODS = "methods";public static final String $TITLE = "title";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $FULL_TITLE = "fullTitle";public static final String $REL_COLUMNS = "relColumns";public static final String $IS_PROTECTED_Y = "isProtectedY";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @PersianName("آدرس")
    private String url;

    @PersianName("کد متن منو")
    private String menuMessageCode;

    @PersianName("کد متن")
    private String messageCode;

    @PersianName("نام پکیج")
    private String packageName;

    @PersianName("نام کلاس")
    private String className;

    @PersianName("محافظت شده")
    private Boolean isProtected;

    @PersianName("بروزرسانی")
    private Boolean isRefreshed;

    @OrderBy(Task.MENU_IDENTITY)
    @OneToMany(mappedBy = "module")
    @PersianName("عملیات ها")
    private Set<Task> tasks;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"moduleId\":" + getId() + "}";
    }

    public String getTitle() {
        return SpringMessager.get(messageCode);
    }

    public String getFullTitle() {
        return
                (packageName.contains(".sadr.share.") ? SpringMessager.get("module.service") : SpringMessager.get("module.web")) +
                        SpringMessager.get(messageCode);
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public TtYesNo getIsProtectedY() {
        return TtYesNo.getValueByBool(isProtected);
    }

    public void setIsProtectedY(TtYesNo protectedModule) {
        this.isProtected = protectedModule.getBoolean();
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Task> getMethods() {
        return tasks;

    }

    public void setMethods(Set<Task> tasks) {
        this.tasks = tasks;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getIsRefreshed() {
        return isRefreshed;
    }

    public void setIsRefreshed(Boolean isRefreshed) {
        this.isRefreshed = isRefreshed;
    }

    public Boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

}
