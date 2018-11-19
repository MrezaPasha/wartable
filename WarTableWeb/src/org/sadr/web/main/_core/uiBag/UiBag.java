package org.sadr.web.main._core.uiBag;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.web.main._core._type.TtUiFonts;
import org.sadr.web.main._core._type.TtUiStyle;
import org.sadr.web.main.admin.user.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("***")
@Entity
@Table(name = "Web.Main._core.UiBag")
public class UiBag extends GenericDataModel<UiBag> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String FONT = "font";public static final String STYLE = "style";public static final String _USER = "user";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $REL_COLUMNS = "relColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @PersianName("فونت")
    private TtUiFonts font;

    @PersianName("رنگ بندی")
    private TtUiStyle style;

    ///############################## RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("کاربر")
    private User user;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public Object getSecretNote() {
        return "{\"uiBagId\":" + getId() + "}";
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS


    public TtUiFonts getFont() {
        return font;
    }

    public void setFont(TtUiFonts font) {
        this.font = font;
    }

    public TtUiStyle getStyle() {
        return style;
    }

    public void setStyle(TtUiStyle style) {
        this.style = style;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
