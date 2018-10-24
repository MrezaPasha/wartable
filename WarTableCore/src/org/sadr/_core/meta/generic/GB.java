/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.meta.generic;

import com.google.gson.Gson;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtGbColumnFetch;
import org.sadr._core.utils.SpringMessager;
import org.springframework.ui.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author dev1
 */
public class GB {

    public static final String SEPERATOR = ";";

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public static GB init(Class clazz) {
        return new GB(clazz);
    }

    public static GB init(Class clazz, String nameInParent) {
        return new GB(clazz, nameInParent);
    }

    public static String path(String... items) {
        if (items == null || items.length == 0) {
            return "";
        }
        String s = items[0];
        for (int i = 1; i < items.length - 1; i++) {
            s += "_" + items[i];
        }
        if (items.length > 1) {
            return s + "." + items[items.length - 1];
        }
        return s;
    }

    public static String unRePath(String repath) {
        String rs = "";
        for (String a : repath.split("")) {
            rs = a + rs;
        }
        return rs;
    }

    public static String rePath(String... items) {
        if (items == null || items.length == 0) {
            return "";
        }
        String s = items[0];
        for (int i = 1; i < items.length - 1; i++) {
            s += "_" + items[i];
        }
        String rs = "";

        if (items.length > 1) {
            s += "." + items[items.length - 1];
            for (String a : s.split("")) {
                rs = a + rs;
            }
            return rs;
        }
        for (String a : s.split("")) {
            rs = a + rs;
        }
//        OutLog.pl(rs);
        return rs;
    }

    public static String[] col(String cName) {
        return new String[]{cName, rePath(cName)};
    }

    public static String[] col(String cName, String repaName) {
        return new String[]{cName, rePath(repaName)};
    }

    public static void searchTableColumns(Model model, Class clazz, String[]... columns) {
        HashMap<String, String[]> map = new LinkedHashMap<>();
        if (columns != null) {
            String clazzName = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
//            OutLog.pl(clazzName);
            for (int i = 0; i < columns.length; i++) {
                if (columns[i][0].equals("id") ||
                    columns[i][0].equals("createDateTime") ||
                    columns[i][0].equals("modifyDateTime")) {
                    map.put(columns[i][0], new String[]{columns[i][1], SpringMessager.get("model." + columns[i][0])});
                } else {
//                    columns[i][2] = SpringMessager.get(clazzName + "." + columns[i][0]);
                    map.put(columns[i][0], new String[]{columns[i][1], SpringMessager.get(clazzName + "." + columns[i][0])});
                }
            }
        }
        model.addAttribute("cols", map);
    }

    //-------------
    private GB() {
        gson = new Gson();
        isWithFinalAccolade = true;
    }

    public static void searchTableColumns(Model model, String modelName, String[]... columns) {
        HashMap<String, String[]> h = new HashMap();
        h.put("id", new String[]{rePath("id"), "model.id"});
        if (columns != null) {
            for (String[] c : columns) {
                if (c[1].indexOf(".") == -1) {
                    h.put(c[0], new String[]{rePath(c[0]), modelName + "." + c[1]});
                } else {
                    h.put(c[0], new String[]{rePath(c[0]), c[1]});
                }
            }
        }
        model.addAttribute("cols", h);
    }

    private GB(Class clazz) {
        gson = new Gson();
        isWithFinalAccolade = true;
        this.clazz = clazz;
    }

    private GB(Class clazz, String nameInParent) {
        gson = new Gson();
        isWithFinalAccolade = true;
        this.clazz = clazz;
        this.name = nameInParent;
    }

    Gson gson;
    private Class clazz;
    private String name;
    private GB parent;
    private Criterion restrictions;
    private String[] columns;
    private GB[] gbs;
    private Order[] orders;
    private int limit;
    private int offset;
    private TtGbColumnFetch columnFetch;
    private GbPaging paging;
    private boolean isWithFinalAccolade;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public GB getByClassAndName(Class clazz, String name) {
        if (gbs != null) {
            for (GB g : gbs) {
//                OutLog.p(clazz.getName(), name, g.getClazz().getName(), g.getName());
                if (g.getClazz() == clazz && name.equals(g.getName())) {
                    return g;
                }
            }
        }
        return null;
    }

    public String[] getColumnsAndGbs() {
        if (gbs == null || gbs.length == 0) {
            return columns;
        } else if (columns == null || columns.length == 0) {
            String[] sx = new String[gbs.length];
            for (int i = 0; i < gbs.length; i++) {
                sx[i] = gbs[i].getName();
            }
            return sx;
        } else {
            int ii = gbs.length + columns.length;
            String[] sx = new String[ii];
            int i = 0, j = 0;
            for (; i < columns.length; i++) {
                sx[i] = columns[i];
            }
            for (; i < ii; j++, i++) {
                sx[i] = gbs[j].getName();
            }
            return sx;
        }
    }

    public GB setSearchParams(String ajaxParam) {
        return setSearchParams(ajaxParam, null);
    }

    public GB setSearchParams(String ajaxParam, Order defaultOrder) {

        GbParamList gbParamList = gson.fromJson(ajaxParam, GbParamList.class
        );
        if (gbParamList != null) {
            gbParamList.update(clazz);
            restrictions = gbParamList.getRestrictions();
            setPaging(
                GbPaging.init()
                    .setIndex(gbParamList.getPageIndex())
                    .setSize(gbParamList.getPageSize())
                    .setPageRage(gbParamList.getPageRange())
            );
        } else {
            restrictions = null;
        }


        if (gbParamList
            != null && gbParamList.getOrders()
            != null && gbParamList.getOrders().length > 0) {
//            OutLog.pl(Arrays.deepToString(gbParamList.getOrders()));
            orders = new Order[gbParamList.getOrders().length];
            for (int i = 0; i < orders.length; i++) {
//                OutLog.pl("i " + i);
//                OutLog.pl(Arrays.toString(gbParamList.getOrders()[i]));
                if ("-1".equals(gbParamList.getOrders()[i][1])) {
                    orders[i] = Order.desc(gbParamList.getOrders()[i][0]);
                } else {
                    orders[i] = Order.asc(gbParamList.getOrders()[i][0]);
                }
            }
        } else {
            setOrder(defaultOrder == null ? Order.desc("id") : defaultOrder);
        }

        return this;
    }

    public int getColumnsCountInDepth() {
        int cnt = 0;
        if (gbs != null) {
            for (GB j : gbs) {
                cnt += j.getColumnsCountInDepth();
            }
        }
        return cnt + (columns != null ? columns.length : 0);
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public GbPaging getPaging() {
        return paging;
    }

    public GB setPaging(GbPaging paging) {
        this.paging = paging;
        return this;
    }

    public String getPath() {
        return parent != null && parent.getPath() != null ? parent.getPath() + "." + name : name;
    }

    public String getPathUL() {

        return parent != null && parent.getPathUL() != null ? parent.getPathUL() + "_" + name : name;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public String[] getColumns() {
        return columns;
    }

    public String[] getColumnsWithCheck() {
        if (columns == null && columnFetch == TtGbColumnFetch.All) {
            try {
                columns = (String[]) clazz.getMethod("getActColumns").invoke(clazz.newInstance());
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
        return columns;
    }


    public GB set(String... columns) {
        this.columns = columns;
        return this;
    }

    public GB set(String column) {
        this.columns = new String[]{column};
        return this;
    }

    public GB[] getGbs() {
        return gbs;
    }

    public GB setGbs(GB... gbs) {
        for (GB gb : gbs) {
            gb.setParent(this);
        }
        this.gbs = gbs;
        return this;
    }

    public Order[] getOrders() {
        return orders;
    }

    public GB setOrder(Order... orders) {
        this.orders = orders;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public GB setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public GB setIsAccolade(boolean b) {
        this.isWithFinalAccolade = b;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public GB setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public Criterion getRestrictions() {
        return restrictions;
    }

    public GB setRest(Criterion restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public GB set(TtGbColumnFetch columnFetch) {
        this.columnFetch = columnFetch;
        return this;
    }

    public TtGbColumnFetch getColumnFetch() {
        return columnFetch;
    }

    public GB getParent() {
        return this.parent;
    }

    public void setParent(GB parent) {
        this.parent = parent;
    }

    public GB addRestrictionsAnd(Criterion restrictions) {
        if (this.restrictions != null) {
            this.restrictions = Restrictions.and(this.restrictions, restrictions);
        } else {
            this.restrictions = restrictions;
        }
        return this;
    }

    public GB addRestrictionsOr(Criterion restrictions) {
        if (this.restrictions != null) {
            this.restrictions = Restrictions.or(this.restrictions, restrictions);
        } else {
            this.restrictions = restrictions;
        }
        return this;
    }

    public boolean getIsWithFinalAccolade() {
        return isWithFinalAccolade;
    }

}
