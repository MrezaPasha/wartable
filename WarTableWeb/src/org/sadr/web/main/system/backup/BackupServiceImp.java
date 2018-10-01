package org.sadr.web.main.system.backup;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class BackupServiceImp extends GenericServiceImpl<Backup, BackupDao> implements BackupService {
}
