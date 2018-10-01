package org.sadr.web.main.archive.directory;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.archive._type.TtDirectoryOrigin;
import org.sadr.web.main.archive._type.TtRepoDirectory;

/**
 * @author masoud
 */
public interface DirectoryService extends GenericService<Directory> {

    public Directory getDirectory(TtRepoDirectory repoDirectory);

    public Directory getDirectory(String path);

    public Directory getDirectory(String path, TtDirectoryOrigin directoryOrigin);

    public Directory getDirectoryAndSubs(TtRepoDirectory repoDirectory);

    public Directory getDirectoryAndSubs(String path);

    public Directory getAutoSubDirectory(TtRepoDirectory repoDirectory);

    public Directory getAutoSubDirectory(Directory directory);
}
