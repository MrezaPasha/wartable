package org.sadr.web.main.system.module;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface ModuleDao extends GenericDao<Module> {

    public Module findByClassName(String className);

    public Module findByPackageName(String packageName);
}
