package org.sadr.web.main.archive.directory;

import org.sadr._core.meta.generic.GenericDao;
import org.sadr.web.main.archive._type.TtRepoDirectory;

/**
 * @author masoud
 */
public interface DirectoryDao extends GenericDao<Directory> {

    public Directory getDirectory(TtRepoDirectory repoDirectory);

    public Directory getDirectory(String path);

}
