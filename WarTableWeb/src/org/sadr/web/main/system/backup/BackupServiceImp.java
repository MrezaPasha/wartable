package org.sadr.web.main.system.backup;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.ShellCommander;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.archive.file.file.FileService;
import org.sadr.web.main.system._type.TtBackupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class BackupServiceImp extends GenericServiceImpl<Backup, BackupDao> implements BackupService {

    private DirectoryService directoryService;
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setDirectoryService(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @Override
    public Backup backup(TtBackupType backupType, boolean isLog) {
        String shortDateTime = ParsCalendar.getInstance().getShortDateTime();

        String[] dp = WebConfigHandler.getDatabaseParamRest();

        String backupFileName = shortDateTime
                .replace(" ", "")
                .replace("/", "")
                .replace(":", "")
                + (isLog ? "_" : "_" + dp[3]) + ".dbk";
        Directory dir = directoryService.getDirectory(TtRepoDirectory.Db_Backup);

        String filePath = ShellCommander.postgres(dir.getAbsolutePath(), (isLog ? "" : dp[3]), dp[2], backupFileName, "backup");
        if (filePath != null) {
            java.io.File f = new java.io.File(filePath);
            File file = new File();
            file.setDirectory(dir);
            file.setIsTemporary(false);
            file.setOrginalName(f.getName());
            file.setIsContainOrginal(true);
            file.setSize(f.length());
            file.setAccessLevel(TtTaskAccessLevel.Grant);
            file.setTitle(f.getName());

            fileService.save(file);

            Backup backup = new Backup();
            backup.setFile(file);
            backup.setBackupDateTime(shortDateTime);
            backup.setBackupType(backupType);
            this.save(backup);
            return backup;
        } else {
            return null;
        }
    }

    @Override
    public String restore(Backup backup) {

        Directory dir = directoryService.getDirectory(TtRepoDirectory.Db_Backup);
        String[] dp = WebConfigHandler.getDatabaseParamRest();

        ShellCommander.postgres(null, dp[3], dp[2], null, "drop");

        ShellCommander.postgres(null, dp[3], dp[2], null, "create");

        return ShellCommander.postgres(dir.getAbsolutePath(), dp[3], dp[2], backup.getFile().getOrginalName(), "restore");

    }
}
