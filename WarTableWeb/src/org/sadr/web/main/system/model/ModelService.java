package org.sadr.web.main.system.model;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.system._type.TtModelAdaptResult;
import org.sadr.web.main.system._type.TtModelRebuildResult;

import java.util.List;

/**
 * @author masoud
 */
public interface ModelService extends GenericService<Model> {

    public Model findByClassName(String className);

    public Model findByPackageName(String packageName);

    public TtModelAdaptResult adapt(int id);

    public TtModelAdaptResult adapt(String className);

    public TtModelAdaptResult adapt(List<Model> models);

    public TtModelRebuildResult rebuild(int id);

    public TtModelRebuildResult rebuild(String className);

    public TtModelRebuildResult rebuild(List<Model> models);

    public TtModelRebuildResult adaptAndRebuild(String className);

}
