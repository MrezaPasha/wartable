package org.sadr.web.main.system.irror.notify;

import org.hibernate.validator.constraints.SafeHtml;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtIrrorNotifyStatus;
import org.sadr.web.main.system.irror.irror.Irror;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author masoud
 */
@PersianName("اطلاع رسانی خطا")
@Entity
@Table(name = "Web.System.Irror.Notify")
public class IrrorNotify extends GenericDataModel<IrrorNotify> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String STATUS = "status";public static final String VISIT_DATE_TIME = "visitDateTime";public static final String _IRROR = "irror";public static final String _USER = "user";public static final String $ACT_COLUMNS = "actColumns";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $SECRET_NOTE = "secretNote";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @PersianName("وضعیت")
    private TtIrrorNotifyStatus status;

    @SafeHtml
    @Size(max = 30)
    @PersianName("زمان مشاهده")
    private String visitDateTime;

    ///############################## RELATIONS

    @PersianName("خطا")
    @ManyToOne(fetch = FetchType.LAZY)
    private Irror irror;

    @PersianName("کاربر")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"irrorNotifyId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public TtIrrorNotifyStatus getStatus() {
        return status;
    }

    public void setStatus(TtIrrorNotifyStatus status) {
        this.status = status;
    }

    public Irror getIrror() {
        return irror;
    }

    public void setIrror(Irror irror) {
        this.irror = irror;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(String visitDateTime) {
        this.visitDateTime = visitDateTime;
    }
}
