package org.sadr.web.main.system.registery;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface RegisteryDao extends GenericDao<Registery> {

    public Registery findByKey(String key);

    public Registery findByKeyAndType(String key, String type);
}
