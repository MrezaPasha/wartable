package org.sadr.web.main.admin.user.confirm;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system.task.Task;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("تایید دوسطحی کاربر")
@Entity
@Table(name = "Web.Admin.UserConfirm")
public class UserConfirm extends GenericDataModel<UserConfirm> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _USER = "user";public static final String _TASK = "task";public static final String TASK_SIGNATURE = "taskSignature";public static final String CONFIRM_DATE_TIME = "confirmDateTime";public static final String CONFIRM_DATE_TIME_G = "confirmDateTimeG";public static final String CALL_COUNT = "callCount";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //


    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("عملیات")
    private Task task;

    @Size(max = 250)
    @PersianName("امضای عملیات")
    private String taskSignature;


    @Size(max = 30)
    @PersianName("زمان تایید")
    private String confirmDateTime;

    @PersianName("زمان تایید (میلادی)")
    private long confirmDateTimeG;

    @PersianName("تعداد فراخوانی")
    private int callCount;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"userConfirmId\":" + getId() + "}";
    }

    public void addCallCount() {
        callCount++;
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskSignature() {
        return taskSignature;
    }

    public void setTaskSignature(String taskSignature) {
        this.taskSignature = taskSignature;
    }

    public String getConfirmDateTime() {
        return confirmDateTime;
    }

    public void setConfirmDateTime(String confirmDateTime) {
        this.confirmDateTime = confirmDateTime;
    }

    public long getConfirmDateTimeG() {
        return confirmDateTimeG;
    }

    public void setConfirmDateTimeG(long confirmDateTimeG) {
        this.confirmDateTimeG = confirmDateTimeG;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }
}
