package org.sadr.web.main.system.registery;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.sadr.web.main.system._type.TtRegisteryValueType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masoud
 */
@Service
//@Component
public class RegisteryServiceImp extends GenericServiceImpl<Registery, RegisteryDao> implements RegisteryService {

    @Override
    public String getPureJsonValueBracket(TtRegisteryKey trt) {
        if (trt == null) {
            return null;
        }
        Registery reg = findBy(
            Restrictions.and(
                Restrictions.eq(Registery.KEY, trt.getKey()),
                Restrictions.eq(Registery.VALUE_TYPE, TtRegisteryValueType.PureJson)));
        if (reg == null) {
            reg = new Registery();
            reg.setKey(trt.getKey());
            reg.setTitle(trt.getTitle());
            reg.setValueType(TtRegisteryValueType.PureJson);
            save(reg);
        }
        return reg.getPureJsonValueBracket();
    }

    @Override
    public String getPureJsonValueBracket(String registeryKey) {
        if (registeryKey == null || registeryKey.isEmpty()) {
            return null;
        }
        Registery reg = findBy(
            Restrictions.and(
                Restrictions.eq(Registery.KEY, registeryKey),
                Restrictions.eq(Registery.VALUE_TYPE, TtRegisteryValueType.PureJson)));
        if (reg == null) {
            reg = new Registery();
            reg.setKey(registeryKey);
            reg.setTitle(registeryKey);
            reg.setValueType(TtRegisteryValueType.PureJson);
            save(reg);
        }
        return reg.getPureJsonValueBracket();
    }

    @Override
    public void addPureJsonValues(TtRegisteryKey trt, String values) {
        Registery reg = findBy(Restrictions.eq(Registery.KEY, trt.getKey()));
        if (reg == null) {
            reg = new Registery();
            reg.setKey(trt.getKey());
            reg.setTitle(trt.getTitle());
            reg.setValueType(TtRegisteryValueType.PureJson);
            reg.addPureJsonValues(values);
            save(reg);
        } else if (reg.addPureJsonValues(values)) {
            update(reg);
        }
    }

    @Override
    public void addPureJsonValues(String rk, String values) {
        Registery reg = findBy(Restrictions.eq(Registery.KEY, rk));
        if (reg == null) {
            reg = new Registery();
            reg.setKey(rk);
            reg.setTitle(rk);
            reg.setValueType(TtRegisteryValueType.PureJson);
            reg.addPureJsonValues(values);
            save(reg);
        } else if (reg.addPureJsonValues(values)) {
            update(reg);
        }
    }

    @Override
    public Registery findByKey(String key) {
        return dao.findByKey(key);
    }

    @Override
    public Registery findByKey(TtRegisteryKey key) {
        return dao.findByKey(key.getKey());
    }

    @Override
    public Registery findByKeyAndType(String key, String type) {
        return dao.findByKeyAndType(key, type);
    }

    @Override
    public List<Registery> findAllByType(String type) {
        return findAllBy(Restrictions.eq(Registery.TYPE, type));
    }

    @Override
    public void clearByKey(String key) {
        OutLog.p("");
        List<Registery> list = findAllBy(Restrictions.eq(Registery.KEY, key));
        if (list != null) {
            OutLog.pl("");
            for (Registery reg : list) {
                OutLog.p(reg.getId());
                delete(reg);
            }
        }
    }

    @Override
    public void clearByType(String type) {
        OutLog.p("");
        List<Registery> list = findAllBy(Restrictions.eq(Registery.TYPE, type));
        if (list != null) {
            OutLog.pl("");
            for (Registery reg : list) {
                OutLog.p(reg.getId());
                delete(reg);
            }
        }
    }

}
