package org.sadr.web.main.system.model;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface ModelDao extends GenericDao<Model> {

    public Model findByClassName(String className);

    public Model findByPackageName(String packageName);
}
