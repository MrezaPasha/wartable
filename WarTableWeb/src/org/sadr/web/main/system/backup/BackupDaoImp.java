package org.sadr.web.main.system.backup;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class BackupDaoImp extends GenericDaoImpl<Backup> implements BackupDao {
}
