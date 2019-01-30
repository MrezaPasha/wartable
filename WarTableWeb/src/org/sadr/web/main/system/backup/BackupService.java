package org.sadr.web.main.system.backup;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.system._type.TtBackupType;

/**
 * @author masoud
 */
public interface BackupService extends GenericService<Backup> {

    public Backup backup(TtBackupType backupType, boolean isLog, boolean isEncrypted, boolean isSigned);

    public String restore(Backup backup, boolean isEncrypted, boolean isSigned);
}
