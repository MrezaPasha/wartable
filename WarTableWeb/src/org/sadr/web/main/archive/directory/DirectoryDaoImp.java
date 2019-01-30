package org.sadr.web.main.archive.directory;

import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class DirectoryDaoImp extends GenericDaoImpl<Directory> implements DirectoryDao {

    @Override
    public Directory getDirectory(TtRepoDirectory repoDirectory) {
        return (Directory) getCurrentSession()
                .createQuery("FROM " + Directory.class.getName() + " WHERE entityState= :es AND repoDirectory= :rd")
                .setInteger("es", TtEntityState.Exist.ordinal())
                .setInteger("rd", repoDirectory.ordinal()).uniqueResult();
    }

    @Override
    public Directory getDirectory(String path) {
        return (Directory) getCurrentSession()
                .createQuery("FROM " + Directory.class.getName() + " WHERE entityState= :es AND path= :rd")
                .setInteger("es", TtEntityState.Exist.ordinal())
                .setString("rd", path).uniqueResult();
    }
}
