package org.sadr.web.main.system.backup;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.*;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core.utils.CacheStatic;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.archive.file.file.FileService;
import org.sadr.web.main.system._type.TtBackupType;
import org.sadr.web.main.system._type.TtRegisteryKey;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public Backup backup(TtBackupType backupType, boolean isLog, boolean isEncrypted, boolean isSigned) {
        String shortDateTime = ParsCalendar.getInstance().getShortDateTime();
        String[] dp = WebConfigHandler.getDatabaseParamRest();
        RegisteryService registeryService = null;
        Registery registery;
        Path fpath;
        java.io.File bkFile = null;

        String backupName = shortDateTime
                .replace(" ", "")
                .replace("/", "")
                .replace(":", "")
                + (isLog ? "_" : "_" + dp[3]);
        String backupFileName = backupName + ".db";

        Directory dir = directoryService.getDirectory(TtRepoDirectory.Db_Backup);

        String filePath;
        if (isEncrypted) {
            registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
            if (registeryService == null) {
                return null;
            }
            registery = registeryService.findByKey(TtRegisteryKey.BackupCryptKey);
            if (registery == null) {
                return null;
            }

            filePath = ShellCommander.postgres(dir.getAbsolutePath(), (isLog ? "" : dp[3]), dp[2], backupFileName, "backup");
            Cipher aes = Cryptor.getEncryptCipher(Cryptor.decrypt(registery.getValue()));
            FileOutputStream fs;
            try {
                Path path = Paths.get(filePath);
                filePath += "k";
                fs = new FileOutputStream(filePath);
                CipherOutputStream out = new CipherOutputStream(fs, aes);
                out.write(Files.readAllBytes(path));
                out.flush();
                out.close();
                Files.delete(path);
            } catch (IOException e) {
                CacheStatic.setDevelopingMode(false);
                return null;
            }
        } else {
            filePath = ShellCommander.postgres(dir.getAbsolutePath(), (isLog ? "" : dp[3]), dp[2], backupFileName, "backup");
        }

        if (isSigned) {
            if (registeryService == null) {
                registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
                if (registeryService == null) {
                    return null;
                }
            }
            registery = registeryService.findByKey(TtRegisteryKey.BackupPrivateKey);
            if (registery == null) {
                return null;
            }
            fpath = Paths.get(filePath);
            Path fpathSign = Paths.get(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + backupName + ".sign");
            try {
                byte[] fileContent = Files.readAllBytes(fpath);
                byte[] sign = Cryptor.sign(fileContent, registery.getValueByte());
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fpathSign.toString()))) {
                    stream.write(sign);
                }
            } catch (IOException e) {
                CacheStatic.setDevelopingMode(false);
                e.printStackTrace();
            }

            backupFileName = backupName + ".zip";

            bkFile = FileUtil.zipFiles(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + backupFileName, fpath.toFile(), fpathSign.toFile());

            try {
                Files.delete(fpath);
                Files.delete(fpathSign);
            } catch (IOException e) {
                CacheStatic.setDevelopingMode(false);
                e.printStackTrace();
            }

        } else {
            bkFile = new java.io.File(filePath);
        }

        if (bkFile != null) {
            File file = new File();
            file.setDirectory(dir);
            file.setIsTemporary(false);
            file.setOrginalName(bkFile.getName());
            file.setIsContainOrginal(true);
            file.setSize(bkFile.length());
            file.setAccessLevel(TtTaskAccessLevel.Grant);
            file.setTitle(bkFile.getName());

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
    public String restore(Backup backup, boolean isEncrypted, boolean isSigned) {

//        Directory dir = directoryService.getDirectory(TtRepoDirectory.Db_Backup);
        String absolutePath = backup.getFile().getDirectoryAbsolutePath();
        String[] dp = WebConfigHandler.getDatabaseParamRest();
        RegisteryService registeryService;
        Registery registery;
        String filePath;
        String backupName;
        if (isSigned) {
            java.io.File sourceFile = backup.getFile().getSourceFile();
            if (!"zip".equals(backup.getFile().getExtention())) {
                return null;
            }
            if (!FileUtil.unzip(sourceFile)) {
                return null;
            }
            registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
            if (registeryService == null) {
                return null;
            }
            registery = registeryService.findByKey(TtRegisteryKey.BackupPublicKey);
            if (registery == null) {
                return null;
            }

            Path dbPath = Paths.get(sourceFile.getParent() + Environment.FILE_SEPARATOR + backup.getFile().getName() + ".dbk");
            Path dbPathSign = Paths.get(sourceFile.getParent() + Environment.FILE_SEPARATOR + backup.getFile().getName() + ".sign");

            if (!dbPath.toFile().exists()) {
                return null;
            }

            if (!dbPathSign.toFile().exists()) {
                return null;
            }

            try {
                if (!Cryptor.verifySign(Files.readAllBytes(dbPath), Files.readAllBytes(dbPathSign), registery.getValueByte())) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            backupName = backup.getFile().getName() + ".dbk";
            filePath = absolutePath + Environment.FILE_SEPARATOR + backupName;
        } else {
            if (!"dbk".equals(backup.getFile().getExtention())) {
                return null;
            }
            filePath = absolutePath + Environment.FILE_SEPARATOR + backup.getFile().getOrginalName();
            backupName = backup.getFile().getOrginalName();
        }

        String restore;
        if (isEncrypted) {
            registeryService = WebConfigHandler.getWebApplicationContext().getBean(RegisteryService.class);
            if (registeryService == null) {
                return null;
            }
            registery = registeryService.findByKey(TtRegisteryKey.BackupCryptKey);
            if (registery == null) {
                return null;
            }

            Cipher aes = Cryptor.getDecryptCipher(Cryptor.decrypt(registery.getValue()));
            FileOutputStream fs;
            try {
                Path path = Paths.get(filePath);
                filePath += "t";
                fs = new FileOutputStream(filePath);
                CipherOutputStream out = new CipherOutputStream(fs, aes);
                out.write(Files.readAllBytes(path));
                out.flush();
                out.close();

                ShellCommander.postgres(null, dp[3], dp[2], null, "drop");
                ShellCommander.postgres(null, dp[3], dp[2], null, "create");
                restore = ShellCommander.postgres(absolutePath, dp[3], dp[2], backupName + "t", "restore");

                Files.delete(Paths.get(filePath));
            } catch (IOException e) {
                return null;
            }
        } else {
            ShellCommander.postgres(null, dp[3], dp[2], null, "drop");
            ShellCommander.postgres(null, dp[3], dp[2], null, "create");
            restore = ShellCommander.postgres(absolutePath, dp[3], dp[2], backupName, "restore");
        }

        return restore;

    }
}
