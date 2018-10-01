package org.sadr._core.meta.generic;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.sadr._core._type.TtInitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @param <T>
 * @param <D>
 * @author masoud
 */
@Transactional
public abstract class GenericServiceImpl<T, D extends GenericDao<T>> implements GenericService<T> {

    @Autowired
    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @Override
    public TtInitResult init() {
        return TtInitResult.NotDefined;
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @Override
    public T find(GB gb) {
        //  OutLog.pl("");
        return dao.find(gb);
    }

    @Override
    public T find(long id) {
        return dao.find(id);
    }

    @Override
    public T find(long id, GB gb) {
        //  OutLog.pl("");
        return dao.find(id, gb);
    }

    //---------------------------
    @Override
    public List<T> findAll(GB gb) {
        //  OutLog.pl("");
        return dao.findAll(gb);
    }


    @Override
    public String findAllJson(GB gb, JB jb) {
        //  OutLog.pl("");
        return dao.findAllJson(gb, jb);
    }

    //---------------------------
    @Override
    public int count(GB gb) {
        return dao.count(gb);
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @Override
    public T findBy(Criterion c) {
        return dao.findBy(c);
    }

    @Override
    public T findById(long id) {
        return dao.findById(id);

    }

    @Override
    public T findById(long id, String... relatedClasses) {
        return dao.findById(id, relatedClasses);

    }

    @Override
    public T findBy(Criterion c, String... relatedClasses) {
        return dao.findBy(c, relatedClasses);
    }

    //---------------------------
    //---------------------------
    @Override
    public List<T> findAll() {
        //  OutLog.pl("");
        return dao.findAll();
    }

    @Override
    public List<T> findAll(int limit) {
        //  OutLog.pl("");
        return dao.findAll(limit);
    }

    @Override
    public List<T> findAll(Order o) {
        //  OutLog.pl("");
        return dao.findAll(o);
    }

    @Override
    public List<T> findAll(int limit, Order o) {
        //  OutLog.pl("");
        return dao.findAll(limit, o);
    }

    @Override
    public List<T> findAllBy(Criterion c) {
        //  OutLog.pl("");
        return dao.findAllBy(c);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit);
    }

    @Override
    public List<T> findAllBy(Criterion c, Order o) {
        //  OutLog.pl("");
        return dao.findAllBy(c, o);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, Order o) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, o);

    }

    @Override
    public List<T> findAll(String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAll(relatedClasses);
    }

    @Override
    public List<T> findAll(int limit, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAll(limit, relatedClasses);
    }

    @Override
    public List<T> findAll(Order o, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAll(o, relatedClasses);
    }

    @Override
    public List<T> findAll(int limit, Order o, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAll(limit, o, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, Order o, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, o, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, Order o, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, o, relatedClasses);

    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, Order o, Order oo, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, o, oo, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, int offset, Order o, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, offset, o, relatedClasses);
    }

    @Override
    public List<T> findAllBy(Criterion c, int limit, int offset, Order o, Order oo, String... relatedClasses) {
        //  OutLog.pl("");
        return dao.findAllBy(c, limit, offset, o, oo, relatedClasses);
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public int count(Criterion c) {
        return dao.count(c);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public long saveId(T entity) {
        return dao.saveId(entity);
    }

    //    @Override
//    public void saveOrUpdate(T entity) {
//        dao.saveOrUpdate(entity);
//    }
    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void deleteAll(long id) {
        dao.deleteAll(id);
    }

    @Override
    public void deleteAllBy(long id, List<T> lists) {
        dao.deleteAllBy(id, lists);
    }

    @Override
    public void deleteAllBy(long id, Set<T> lists) {
        dao.deleteAllBy(id, lists);
    }

    @Override
    public void remove(long id) {
        dao.remove(id);
    }

    @Override
    public void trash(long id) {
        dao.trash(id);
    }

    @Override
    public void trash(long... id) {
        dao.trash(id[0]);
    }

    @Override
    public void trash(List<T> lists) {
        dao.trash(lists);
    }

    @Override
    public void exist(long id) {
        dao.exist(id);
    }

    //=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#
    @Override
    public boolean isExist(Criterion c) {
        return dao.isExist(c);
    }

    @Override
    public boolean isExist(long id) {
        return dao.isExist(id);
    }

    @Override
    public boolean isDuplicateWith(Criterion c, long ownId) {
        return dao.isDuplicateWith(c, ownId);
    }

}
