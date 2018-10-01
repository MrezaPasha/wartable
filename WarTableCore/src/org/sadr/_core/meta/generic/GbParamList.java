package org.sadr._core.meta.generic;

import com.google.gson.annotations.SerializedName;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;

import java.util.List;

/**
 * @author masoud
 */
@PersianName("پارامترها")
public class GbParamList {

    private Class<?> clazz;
    @SerializedName("c")
    private String condition;
    @SerializedName("o")
    private String[][] orders;
    @SerializedName("ps")
    private int pageSize;
    @SerializedName("pi")
    private int pageIndex;
    @SerializedName("pr")
    private int pageRange;
    @SerializedName("df") // if query is empty, show list or do not?
    private boolean defaultFill;
    @SerializedName("list")
    private List<GbParam> list;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public void update(Class<?> c) {
        clazz = c;
        Object val;
        if (list != null) {
            for (GbParam gb : list) {
                gb.setName(GB.unRePath(gb.getName()));

                val = gb.getValue();

                val = TtDataType.convertValueByBrifKey(gb.getType(), val, gb.getName(), clazz);

                if (gb.getNoSpace() && val instanceof String) {
                    val = val.toString().replaceAll(" ", "");
                }
                gb.setValue(val);
                if (gb.getOperator() == null || gb.getOperator().isEmpty()) {
                    if (val instanceof String) {
                        gb.setOperator("ilike");
                    } else if (val instanceof java.util.ArrayList) {
                        gb.setOperator("in");
                    } else {
                        gb.setOperator("eq");
                    }
                }
            }
        }
        if (orders != null && orders.length > 0) {
            for (String[] order : orders) {
                order[0] = GB.unRePath(order[0]);
            }
        }
    }

    public Criterion getRestrictions() {
        Conjunction and = Restrictions.and();
        if (list != null && !list.isEmpty()) {
            if (condition == null || condition.isEmpty()) {
                list.stream().forEach((gbParam) -> {
                    and.add(TtRestrictionOperator.getRestrictionsByToken(gbParam));
                });
            } else {

            }
        } else if (!isDefaultFill()) {
            and.add(Restrictions.eq("id", -1));
        }
//        OutLog.pl(and.toString());
        return and;
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<GbParam> getList() {
        return list;
    }

    public void setList(List<GbParam> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String[][] getOrders() {
        return orders;
    }

    public void setOrders(String[][] orders) {
        this.orders = orders;
    }

    public int getPageRange() {
        return pageRange;
    }

    public void setPageRange(int pageRange) {
        this.pageRange = pageRange;
    }

    public boolean isDefaultFill() {
        return defaultFill;
    }

    public void setDefaultFill(boolean defaultFill) {
        this.defaultFill = defaultFill;
    }
}
