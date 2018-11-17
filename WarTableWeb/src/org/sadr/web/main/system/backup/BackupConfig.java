package org.sadr.web.main.system.backup;

import org.sadr.web.config.Config;
import org.sadr.web.main.archive.directory.DirectoryConfig;
import org.sadr.web.main.archive.file.file.FileConfig;
import org.sadr.web.main.system.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class BackupConfig extends Config {

    @Bean
    public BackupDaoImp backupDaoImp() {
        BackupDaoImp udi = new BackupDaoImp();
        return udi;
    }

    @Bean
    public BackupServiceImp backupServiceImp() {
        BackupServiceImp usi = new BackupServiceImp();
        usi.setDao(backupDaoImp());
        usi.setDirectoryService(new DirectoryConfig().directoryServiceImp());
        usi.setFileService(new FileConfig().fileServiceImp());
        return usi;
    }

    @Bean
    public BackupController backupController() {
        BackupController uc = new BackupController();
        uc.setService(backupServiceImp());
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        uc.setFileService(new FileConfig().fileServiceImp());
        return uc;
    }
}
