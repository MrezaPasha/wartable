package org.sadr.web.main.note.note;

import org.hibernate.annotations.Type;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.note._type.TtNoteImportance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("یادداشت")
@Entity
@Table(name = "Web.Note")
public class Note extends GenericDataModel<Note> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String TITLE = "title";public static final String MESSAGE = "message";public static final String DATE_TIME_G = "dateTimeG";public static final String DATE_TIME = "dateTime";public static final String IS_VISITED = "isVisited";public static final String IS_NOTIFIED = "isNotified";public static final String IMPORTANCE = "importance";public static final String _USER = "user";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @Size(max = 250, min = 1)
    @PersianName("عنوان")
    private String title;

    @Type(type = "text")
    @PersianName("پیام")
    private String message;

    @PersianName("زمان سررسید")
    private long dateTimeG;

    @Size(max = 30)
    @PersianName("زمان سررسید")
    private String dateTime;

    @PersianName("مشاهده شده")
    private Boolean isVisited;

    @PersianName("اطلاع رسانی شده")
    private Boolean isNotified;

    @PersianName("اهمیت")
    private TtNoteImportance importance;
    ///############################## RELATIONS
    @PersianName("کاربر")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {

        return "{\"noteId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateTimeG() {
        return dateTimeG;
    }

    public void setDateTimeG(long dateTimeG) {
        this.dateTimeG = dateTimeG;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean visited) {
        isVisited = visited;
    }

    public TtNoteImportance getImportance() {
        return importance;
    }

    public void setImportance(TtNoteImportance importance) {
        this.importance = importance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(Boolean notified) {
        isNotified = notified;
    }
}
