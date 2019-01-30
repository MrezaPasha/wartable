package org.sadr._core.meta.generic;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.sadr._core._type.TtEntityState;
import org.sadr._core._type.TtGbColumnFetch;
import org.sadr._core._type.TtTransformerType;
import org.sadr._core.tools.transformer.CustomTransformer;
import org.sadr._core.utils.ParsCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.persistence.OneToOne;
import java.io.Serializable;
import java.lang.InstantiationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author masoud
 * @version 950402
 */
@Scope("prototype")
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    private final Class<T> clazz;
    ParsCalendar ipc;

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# VARIABLES
    private String getDateTime() {
        ipc = ParsCalendar.getInstance();
        return ipc.getShortDateTime();
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Autowired
    private SessionFactory sessionFactoryRest;

    @Autowired
    private SessionFactory sessionFactoryLog;

    @Override
    public final Session getCurrentSession() {
        if (clazz.getName().endsWith(".RemoteLog")) {
            return sessionFactoryLog.getCurrentSession();
        }
        return sessionFactoryRest.getCurrentSession();
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# PRIVATE METHODS
    private Criteria attachRelation(Criteria criteria, String[] relatedClass) {
        if (relatedClass == null || relatedClass.length == 0) {
//            OutLog.pl("");
            return criteria;
        }
        String sa = ",", b;
        int ix;
        for (String r : relatedClass) {
            ix = r.indexOf(".");
            while (ix != -1) {
                b = r.substring(0, ix);
//                OutLog.p(b);
                if (!sa.contains("," + b + ",")) {
                    sa += b + ",";
                }
                ix = r.indexOf(".", ix + 1);

            }
            if (!sa.contains("," + r + ",")) {
                sa += r + ",";
            }
        }
//        OutLog.p(sa);
//        OutLog.p(sa.substring(1, sa.length() - 1));
        for (String r : sa.substring(1, sa.length() - 1).split(",")) {
            criteria.setFetchMode(r, FetchMode.JOIN);
//            OutLog.pl("_ " + r);
            if (r.contains(".")) {
                continue;
            }
            try {
                if (clazz.getDeclaredField(r).isAnnotationPresent(OneToOne.class) && clazz.getDeclaredField(r).getAnnotation(OneToOne.class).mappedBy().isEmpty()) {
//                    OutLog.pl(r + "." + clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1));
                    criteria.setFetchMode(r + "." + clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1), FetchMode.JOIN);
                }
            } catch (NoSuchFieldException | SecurityException ex) {
                ex.printStackTrace();
            }
        }
//        OutLog.pl(criteria.toString());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        return criteria;
    }

    private Criteria initCrt(Criteria c, GB[] gbs, ProjectionList pl) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        for (GB gb : gbs) {
            String aliName = gb.getPath(),
                aliNameUL = gb.getPathUL();
            c.createAlias(aliName, aliNameUL, JoinType.LEFT_OUTER_JOIN);
            if (gb.getRestrictions() != null) {
                c.add(gb.getRestrictions());

            }
            if (gb.getColumnFetch() == TtGbColumnFetch.All) {
                String[] invoke = (String[]) gb.getClazz().getMethod("getActColumns").invoke(gb.getClazz().newInstance());
                for (String in : invoke) {
                    pl.add(Projections.property(aliNameUL + "." + in).as(aliNameUL + "." + in));
//                    OutLog.p(aliName + "." + in + "     " + aliNameUL + "." + in);
                }
            } else {
                pl.add(Projections.property(aliNameUL + ".id").as(aliNameUL + ".id"))
                    .add(Projections.property(aliNameUL + ".entityState").as(aliNameUL + ".entityState"));

                if (gb.getColumns() != null) {
                    for (String l : gb.getColumns()) {
//                        OutLog.p(aliName + "." + l + "     " + aliNameUL + "." + l);
                        pl.add(Projections.property(aliNameUL + "." + l).as(aliNameUL + "." + l));

                    }
                }

            }

            if (gb.getGbs() != null && gb.getGbs().length > 0) {
                c = initCrt(c, gb.getGbs(), pl);
            }
        }
        return c;
    }

    private Criteria initCriteria(TtTransformerType transformerType, GB gb, JB jb) {
        try {
            ProjectionList pl = Projections.projectionList();
            Criteria c;
            if (gb.getGbs() != null && gb.getGbs().length > 0) {
                c = initCrt(
                    getCurrentSession().createCriteria(clazz, gb.getName())
                        .add(Restrictions.eq("entityState", TtEntityState.Exist)),
                    gb.getGbs(), pl
                );
            } else {
                c = getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist));
            }
            if (gb.getRestrictions() != null) {
                c.add(gb.getRestrictions());
            }
            if (gb.getColumnFetch() == TtGbColumnFetch.All) {
                @SuppressWarnings("unchecked")
                String[] invoke = (String[]) gb.getClazz().getMethod("getActColumns").invoke(gb.getClazz().newInstance());
                for (String in : invoke) {
                    if ("id".equals(in)) {
                        pl.add(Projections.id().as("id"));
                    } else {
                        pl.add(Projections.property(in).as(in));
                    }
                }
            } else {
                pl.add(Projections.id().as("id"))
                    .add(Projections.property("entityState").as("entityState"));
                if (gb.getColumns() != null) {
                    for (String l : gb.getColumns()) {
                        pl.add(Projections.property(l).as(l));
                    }
                }
            }

            if (pl.getLength() > 0) {
                c.setProjection(pl);
            }

            // orders
            Order[] orders = gb.getOrders();
            if (orders != null && orders.length > 0) {
                for (Order o : orders) {
                    c.addOrder(o);
                }
            }
            if (gb.getLimit() > 0) {
                c.setMaxResults(gb.getLimit());
            }

//            if (transformerType == TtTransformerType.Json) {
//                c.setResultTransformer(new CustomTransformerJson(clazz));
//            } else {
            if (jb != null) {
                c.setResultTransformer(new CustomTransformer<>(clazz, transformerType, jb));
            } else {
                c.setResultTransformer(new CustomTransformer(clazz, transformerType, gb));
            }
//            }
//            c.setResultTransformer(new FluentHibernateResultTransformer(clazz));
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# CONSTRUCTOR
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# METHODS
    @SuppressWarnings("unchecked")
    @Override
    public T find(GB gb) {
        Criteria c = initCriteria(TtTransformerType.Object, gb, null);
        if (c == null) {
            return null;
        }
        return (T) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(long id) {
        Criteria c = initCriteria(TtTransformerType.Object, GB.init(clazz), null);
        if (c == null) {
            return null;
        }
//        OutLog.pl(c.toString());
        c.add(Restrictions.eq("id", id));
        return (T) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(long id, GB gb) {
        Criteria c = initCriteria(TtTransformerType.Object, gb, null);
        if (c == null) {
            return null;
        }
//        OutLog.pl(c.toString());
        c.add(Restrictions.eq("id", id));
        return (T) c.uniqueResult();
    }

    //----------------------------------------
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(GB gb) {
        Criteria c = initCriteria(TtTransformerType.Object, gb, null);
        if (c == null) {
            return null;
        }
        if (gb.getPaging() != null) {
            Criteria cc = initCriteria(TtTransformerType.Object, gb, null);
            cc.setProjection(Projections.projectionList()
                .add(Projections.rowCount())
                .add(Projections.groupProperty("id"))
            );
            List o = cc.list();
            gb.getPaging().setSearchCount(o == null ? 0 : o.size());
            gb.getPaging().calc();

            c.setMaxResults(gb.getPaging().getSize())
                .setFirstResult(gb.getPaging().getFirstResult());
            List list = c.list();
//            gb.getPaging().setSize(list == null ? 0 : list.size());
            return list;
        } else {
            return c.list();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String findAllJson(GB gb, JB jb) {
        Criteria c = initCriteria(TtTransformerType.Json, gb, jb);
        if (c == null) {
            return null;
        }
        if (gb.getPaging() != null) {
            Criteria cc = initCriteria(TtTransformerType.Json, gb, jb);
            cc.setProjection(Projections.projectionList()
                .add(Projections.rowCount())
                .add(Projections.groupProperty("id"))
            );
            List o = cc.list();
            gb.getPaging().setSearchCount(o == null ? 0 : o.size());
            gb.getPaging().calc();
//            OutLog.pl(gb.getPaging().getSize());
            if (gb.getPaging().getSize() > 0) {
                c.setMaxResults(gb.getPaging().getSize());
            } else {
                gb.getPaging().setSize(gb.getPaging().getSearchCount());
            }
            c.setFirstResult(gb.getPaging().getFirstResult());
//            OutLog.pl(c.toString());
            List list = c.list();

//            gb.getPaging().setSize(list == null ? 0 : list.size());
            String json = "{\"paging\":"
                + gb.getPaging().toJson()
                + ",\"list\":[";

            if (list != null && !list.isEmpty()) {
                for (Object l : list) {
//                    OutLog.pl(l.toString());
                    json += l.toString() + ",";

                }
                json = json.substring(0, json.length() - 1);
            }

            json += "]}";
//            OutLog.pl(json);
            return json;
        } else {
            List<String> list;

//            OutLog.pl(c.toString());
            list = c.list();

            String json = (gb.getIsWithFinalAccolade() ? "{\"list\":[" : "\"list\":[");

//            OutLog.pl("jljlj");
            if (list != null && !list.isEmpty()) {
                for (Object l : list) {
//                    OutLog.pl(l.toString());
                    json += l.toString() + ",";

                }
                json = json.substring(0, json.length() - 1);
            }

            json += (gb.getIsWithFinalAccolade() ? "]}" : "]");
            return json;
        }
    }

    //---------------------------
    @SuppressWarnings("unchecked")
    @Override
    public int count(GB gb) {
        Criteria c = initCriteria(TtTransformerType.Object, gb, null);
        if (c == null) {
            return 0;
        }
        try {
            return (int) (long) c.setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            return 0;
        }
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @SuppressWarnings("unchecked")
    @Override
    public T findBy(Criterion c, String... relatedClass) {
        // OutLog.pl("" + c.toString());
        Criteria crt = getCurrentSession().createCriteria(clazz)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .add(c);
        try {

            List list = attachRelation(crt, relatedClass).list();

            return list.isEmpty() ? null : (T) list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(long id, String... relatedClass) {
        try {
            // OutLog.pl("");
            Criteria crt = getCurrentSession().createCriteria(clazz)
                .add(Restrictions.eq("entityState", TtEntityState.Exist))
                .add(Restrictions.eq("id", id));
            return (T) attachRelation(crt, relatedClass).uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# METHODS
    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# METHODS
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist)),
                relatedClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(int limit, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist)),
                relatedClass).setMaxResults(limit)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(int limit, Order o, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist)),
                relatedClass).addOrder(o).setMaxResults(limit)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Order o, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist)),
                relatedClass).addOrder(o)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass)
                .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, int limit, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).setMaxResults(limit)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, Order o, String... relatedClass) {
        try {
            List list = attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).addOrder(o)
                .list();
            return list;

        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, int limit, Order o, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).addOrder(o).setMaxResults(limit)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, int limit, Order o, Order oo, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).addOrder(o).setMaxResults(limit)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, int limit, int offset, Order o, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).addOrder(o).setMaxResults(limit).setFirstResult(offset)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(Criterion c, int limit, int offset, Order o, Order oo, String... relatedClass) {
        try {
            return attachRelation(
                getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq("entityState", TtEntityState.Exist))
                    .add(c),
                relatedClass).addOrder(o).addOrder(oo).setMaxResults(limit).setFirstResult(offset)
                .list();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public long count() {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .setProjection(Projections.rowCount());
        return (long) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int count(Criterion c) {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .add(c)
            .setProjection(Projections.rowCount());
        return (int) (long) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isExist(Criterion c) {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .add(c);
        List<Object> obj = criteria.list();
        return !(obj == null || obj.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isExist(long id) {
        Query query = getCurrentSession().createQuery("FROM " + clazz.getName()
            + " WHERE entityState= :es AND id= :id");
        query.setInteger("es", TtEntityState.Exist.ordinal());
        query.setLong("id", id);

        List<Object> obj = query.list();

        return !(obj == null || obj.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isDuplicateWith(Criterion c, long ownId) {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
            .add(Restrictions.eq("entityState", TtEntityState.Exist))
            .add(c)
            .add(Restrictions.ne("id", ownId));

        List<Object> obj = criteria.list();
        return !(obj == null || obj.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(T entity) {
        try {
            Method m_set = clazz.getMethod("setModifyDateTime", String.class
            );
            m_set.invoke(entity, getDateTime());
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        getCurrentSession().update(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(T entity) {
        try {
            Method m_set = clazz.getMethod("setCreateDateTime", String.class
            );
            m_set.invoke(entity, getDateTime());
            m_set = clazz.getMethod("setEntityState", TtEntityState.class);

            m_set.invoke(entity, TtEntityState.Exist);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GenericDaoImpl.class
                .getName()).log(Level.SEVERE, null, ex);
        }
        getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public long saveId(T entity) {
        try {
            Method m_set = clazz.getMethod("setCreateDateTime", String.class
            );
            m_set.invoke(entity, getDateTime());
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GenericDaoImpl.class
                .getName()).log(Level.SEVERE, null, ex);
        }
        return (long) getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(long id) {
        Query query = getCurrentSession().createQuery("DELETE FROM " + clazz.getName()
            + " WHERE id= :eid");
        query.setLong("eid", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteAll(long id) {
        if (id != 0163) {
            return;
        }
        Query query = getCurrentSession().createQuery("DELETE FROM " + clazz.getName());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteAllBy(long id, List<T> lists) {
        if (id != 0163 || lists == null || lists.isEmpty()) {
            return;
        }
        Method m_set;
        Long[] idsa = new Long[lists.size()];
        int i = 0;
        for (T list : lists) {
            try {
                m_set = clazz.getMethod("getId");
                idsa[i++] = (Long) m_set.invoke(list);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }

        Query query = getCurrentSession().createQuery("DELETE FROM " + clazz.getName()
            + " WHERE id IN (:eid)");
        query.setParameterList("eid", idsa);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteAllBy(long id, Set<T> lists) {
        if (id != 0163 || lists == null || lists.isEmpty()) {
            return;
        }
        Method m_set;
        Long[] idsa = new Long[lists.size()];
        int i = 0;
        for (T list : lists) {
            try {
                m_set = clazz.getMethod("getId");
                idsa[i++] = (Long) m_set.invoke(list);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Query query = getCurrentSession().createQuery("DELETE FROM " + clazz.getName()
            + " WHERE id IN (:eid)");
        query.setParameterList("eid", idsa);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void remove(long id) {
        try {
            Query query = getCurrentSession().createQuery("UPDATE " + clazz.getName()
                + " SET entityState= :es" + " WHERE id= :eid");
            query.setLong("es", TtEntityState.Remove.ordinal());
            query.setLong("eid", id);
            query.executeUpdate();
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void trash(long id) {
        try {
            Query query = getCurrentSession().createQuery("UPDATE " + clazz.getName()
                + " SET entityState= :es, modifyDateTime= :mdt" + " WHERE id= :eid");
            query.setInteger("es", TtEntityState.Trash.ordinal());
            query.setString("mdt", getDateTime());
            query.setLong("eid", id);
            query.executeUpdate();
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void trash(List<T> lists) {
        Method m_set;
        String idsa = "";
        for (T list : lists) {
//            OutLog.pl("");
            try {
                m_set = clazz.getMethod("getId");
                idsa += (Long) m_set.invoke(list) + ",";
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!idsa.isEmpty()) {
            idsa = idsa.substring(0, idsa.length() - 1);
        }
//        OutLog.pl(idsa);
//        try {
        Query query = getCurrentSession().createQuery("UPDATE " + clazz.getName()
            + " SET entityState= :es, modifyDateTime= :mdt" + " WHERE id in (:eid)");
        query.setInteger("es", TtEntityState.Trash.ordinal());
        query.setString("mdt", getDateTime());
        query.setString("eid", idsa);
//        OutLog.pl(query.toString());
        query.executeUpdate();
//        } catch (Exception e) {
//        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void exist(long id) {
        try {
            Query query = getCurrentSession().createQuery("UPDATE " + clazz.getName()
                + " SET entityState= :es, modifyDateTime= :mdt" + " WHERE id= :eid");
            query.setInteger("es", TtEntityState.Exist.ordinal());
            query.setLong("eid", id);
            query.executeUpdate();
        } catch (Exception e) {
        }
    }

}
