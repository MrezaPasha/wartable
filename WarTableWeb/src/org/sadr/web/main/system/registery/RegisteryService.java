package org.sadr.web.main.system.registery;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.system._type.TtRegisteryKey;

import java.util.List;

/**
 * @author masoud
 */
public interface RegisteryService extends GenericService<Registery> {

    public Registery findByKey(String key);

    public Registery findByKey(TtRegisteryKey key);

    public Registery findByKeyAndType(String key, String type);

    public List<Registery> findAllByType(String type);

    public String getPureJsonValueBracket(TtRegisteryKey trt);

    public String getPureJsonValueBracket(String registeryKey);

    public void addPureJsonValues(TtRegisteryKey trt, String values);

    public void addPureJsonValues(String registeryKey, String values);

    public void clearByKey(String registeryKey);

    public void clearByType(String registeryKey);

}
