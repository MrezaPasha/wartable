package org.sadr.web.main.system.module;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface ModuleService extends GenericService<Module> {

    public Module findByClassName(String className);

    public Module findByPackageName(String packageName);

    public boolean clean();
}
