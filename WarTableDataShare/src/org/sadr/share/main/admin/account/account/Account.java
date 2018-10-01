package org.sadr.share.main.admin.account.account;

import org.sadr.share.main.admin.account.group.AccountGroup;
import org.sadr.share.main.admin.account.model.AccountModel;
import org.sadr.share.main.admin.notification._account.Notification_Account;
import org.sadr.share.main.meeting._account.Meeting_Account;
import org.sadr.share.main.room._account.Room_Account;
import org.sadr.share.main.room.room.Room;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Admin.Account")
public class Account extends GenericDataModel<Account> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _NOTIFICATION_ACCOUNTS = "notificationAccounts";public static final String _ROOM_ACCOUNTS = "room_accounts";public static final String _ROOMS = "rooms";public static final String _MEETING_ACCOUNTS = "meeting_accounts";public static final String _ACCOUNT_MODEL = "accountModel";public static final String _ACCOUNT_GROUP = "accountGroup";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @OneToMany(mappedBy = "account")
    @PersianName("اعلان ها")
    private Set<Notification_Account> notificationAccounts;

    @OneToMany(mappedBy = "account")
    @PersianName("اتاق های عضو")
    private Set<Room_Account> room_accounts;

    @OneToMany(mappedBy = "adminAccount")
    @PersianName("اتاق های تحت مدیریت")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "account")
    @PersianName("جلسات")
    private Set<Meeting_Account> meeting_accounts;


    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مدل")
    private AccountModel accountModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("گروه")
    private AccountGroup accountGroup;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"accountId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
