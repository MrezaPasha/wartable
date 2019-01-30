package org.sadr.share.main.servicePolicy;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.groupPolicy._types.TtGroupPolicyCategory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@PersianName("سیاست های سرویس")
@Entity
@Table(name = "Service.ServicePolicy")
public class ServicePolicy extends GenericDataModel<ServicePolicy> implements Serializable {


    public static final String CATEGOURY = "category";
    public static final String FUNCTIONS = "functions";


    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public static void setAvrColumns(String acts, String virts, String rels) {
        if (acts != null) {
            actColumns = acts.split(",");
        }
        if (virts != null) {
            virColumns = virts.split(",");
        }
        if (rels != null) {
            relColumns = rels.split(",");
        }
    }

    // static fields end

    @PersianName("نام دسته بندی گروه")
    private TtGroupPolicyCategory category;


    @ElementCollection()
    @PersianName("سرویس ها")
    private List<TtRpcFunction> functions;


    // METHODS


    public TtGroupPolicyCategory getCategory() {
        return category;
    }

    public void setCategory(TtGroupPolicyCategory category) {
        this.category = category;
    }

    public List<TtRpcFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<TtRpcFunction> functions) {
        this.functions = functions;
    }
}
