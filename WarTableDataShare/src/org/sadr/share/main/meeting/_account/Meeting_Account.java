package org.sadr.share.main.meeting._account;

import org.sadr.share.main.admin.account.account.Account;
import org.sadr.share.main.meeting.meeting.Meeting;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Meeting_Account")
public class Meeting_Account extends GenericDataModel<Meeting_Account> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _ACCOUNT = "account";public static final String _MEETING = "meeting";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("جلسه")
    private Meeting meeting;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"meeting_AccountId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
