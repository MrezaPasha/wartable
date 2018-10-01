package org.sadr._core._type;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GbParam;
import org.sadr._core.utils.OutLog;

import java.util.Collection;

/**
 * @author masoud
 */
public enum TtRestrictionOperator {

    Equal("تساوی", "=", "eq"),
    NotEqual("عدم تساوی", "<>", "ne"),
    GreatThan("بزرگتر", ">", "gt"),
    GreaterEqual("بزرگتر-مساوی", ">=", "ge"),
    LessThan("کوچکتر", "<", "lt"),
    LessEqual("کوچکتر-مساوی", "<=", "le"),
    IsNull("تهی", "NULL", "isNull"),
    IsNotNull("ناتهی", "Not NULL", "isNotNull"),
    In("ناتهی", "In", "in"),
    ILike("شبیه غیر حساس", "ilike", "ilk"),
    ILike_ANY("شبیه غیر حساس", "ilike", "ilka"),
    ILike_Start("شبیه غیر حساس", "ilike", "ilks"),
    ILike_End("شبیه غیر حساس", "ilike", "ilke"),
    ILike_Exact("شبیه غیر حساس", "ilike", "ilkx"),
    Like("شبیه غیر حساس", "like", "lk"),
    Like_ANY("شبیه غیر حساس", "like", "lka"),
    Like_Start("شبیه غیر حساس", "like", "lks"),
    Like_End("شبیه غیر حساس", "like", "lke"),
    Like_Exact("شبیه غیر حساس", "like", "lkx"),;

    //                Restrictions.allEq(null)
//    Restrictions.and(predicates)
//    Restrictions.between(predicates)
//    Restrictions.conjunction(predicates)
//    Restrictions.eq(predicates)
//    Restrictions.eqOrIsNull(predicates)
//    Restrictions.eqProperty(null, null)
//    Restrictions.ge(null, AllEq)
//    Restrictions.gtProperty(null, null)
//    Restrictions.idEq(AllEq)
//    Restrictions.ilike(null, AllEq)
//    Restrictions.in(null, null)
//    Restrictions.isEmpty(null)
//    Restrictions.isNotEmpty(null)
//    Restrictions.isNull(null)
//    Restrictions.isNotNull(null)
//    Restrictions.le(null, AllEq)
//    Restrictions.leProperty(null, null)
//    Restrictions.like(null, AllEq)
//    Restrictions.lt(null, AllEq)
//    Restrictions.ltProperty(null, null)
//    Restrictions.ne(null, AllEq)
//    Restrictions.not(null)
//    Restrictions.
//    Restrictions.
//    Restrictions.
//    Restrictions.
    private final String key;
    private final String title;
    private final String token;

    private TtRestrictionOperator(String t, String k, String tk) {
        key = k;
        title = t;
        token = tk;

    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getToken() {
        return token;
    }

    public static Criterion getRestrictionsByToken(GbParam gbParam) {
        try {
            if (gbParam == null) {
                OutLog.pl("");
                return null;
            }

            if (gbParam.getName().contains("#")) {
                String[] names = gbParam.getName().split("#");
                Disjunction or = Restrictions.or();

                String[] split;
                for (String nm : names) {
                    if (gbParam.getValue() != null) {
                        try {
                            split = gbParam.getValue().toString().split(" ");
                            for (String s : split) {
                                or.add(getRes(gbParam.getOperator(), nm, s));
                            }
                        } catch (Exception e) {
                            or.add(getRes(gbParam.getOperator(), nm, gbParam.getValue().toString()));
                        }
                    }
                }

                OutLog.pl(or.toString());
                return or;
            }
            return getRes(gbParam.getOperator(), gbParam.getName(), gbParam.getValue());

        } catch (Exception c) {
            return null;
        }
    }

    private static Criterion getRes(String op, String name, Object value) {
        switch (op) {
            case "eq":
                return Restrictions.eq(name, value);
            case "ne":
                return Restrictions.ne(name, value);
            case "eqOrIsNull":
                return Restrictions.eqOrIsNull(name, value);
            case "lt":
                return Restrictions.lt(name, value);
            case "le":
                return Restrictions.le(name, value);
            case "ge":
                return Restrictions.ge(name, value);
            case "gt":
                return Restrictions.gt(name, value);
            case "lk":
                return Restrictions.like(name, value);
            case "lka":
                if (value != null) {
                    return Restrictions.like(name, value.toString().trim(), MatchMode.ANYWHERE);
                }
                return Restrictions.like(name, value);
            case "lks":
                if (value != null) {
                    return Restrictions.like(name, value.toString().trim(), MatchMode.START);
                }
                return Restrictions.like(name, value);
            case "lke":
                if (value != null) {
                    return Restrictions.like(name, value.toString().trim(), MatchMode.END);
                }
                return Restrictions.like(name, value);
            case "lkx":
                if (value != null) {
                    return Restrictions.like(name, value.toString().trim(), MatchMode.EXACT);
                }
                return Restrictions.like(name, value);
            case "ilk":
                return Restrictions.ilike(name, value);
            case "ilka":
                if (value != null) {
                    return Restrictions.ilike(name, value.toString().trim(), MatchMode.ANYWHERE);
                }
                return Restrictions.ilike(name, value);
            case "ilks":
                if (value != null) {
                    return Restrictions.ilike(name, value.toString().trim(), MatchMode.START);
                }
                return Restrictions.ilike(name, value);
            case "ilke":
                if (value != null) {
                    return Restrictions.ilike(name, value.toString().trim(), MatchMode.END);
                }
                return Restrictions.ilike(name, value);
            case "ilkx":
                if (value != null) {
                    return Restrictions.ilike(name, value.toString().trim(), MatchMode.EXACT);
                }
                return Restrictions.ilike(name, value);
            case "in":
                return Restrictions.in(name, (Collection) value);
            case "isEmpty":
                return Restrictions.isEmpty(name);
            case "isNotEmpty":
                return Restrictions.isNotEmpty(name);
            case "isNull":
                return Restrictions.isNull(name);
            case "isNotNull":
                return Restrictions.isNotNull(name);
        }
        return null;
    }

    public static TtRestrictionOperator getByToken(String tk) {
        for (TtRestrictionOperator value : values()) {
            if (value.token.equals(tk)) {
                return value;
            }
        }
        return null;
    }
}
