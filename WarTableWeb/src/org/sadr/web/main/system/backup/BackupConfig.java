package org.sadr.web.main.system.backup;

import org.sadr.web.config.WebConfig;
import org.sadr.web.main.archive.directory.DirectoryConfig;
import org.sadr.web.main.archive.file.file.FileConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class BackupConfig extends WebConfig {

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
        uc.setFileService(new FileConfig().fileServiceImp());
        return uc;
    }
}
