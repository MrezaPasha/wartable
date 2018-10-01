package org.sadr.share.main.admin.account.model;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main.admin.account.account.Account;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Service.Admin.Account.Model")
public class AccountModel extends GenericDataModel<AccountModel> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String _ACCOUNTS = "accounts";public static final String _FILE = "file";public static final String $ACT_COLUMNS = "actColumns";public static final String $SECRET_NOTE = "secretNote";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    ///############################## RELATIONS
    @OneToMany(mappedBy = "accountModel")
    @PersianName("کاربران")
    private Set<Account> accounts;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @PersianName("فایل")
//    private File file;
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"accountModelId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
}
