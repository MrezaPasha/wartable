package org.sadr.web.main._core.tools.uploader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.criterion.Restrictions;
import org.imgscalr.Scalr;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.tools._type.*;
import org.sadr.web.main.archive._type.TtDirectoryOrigin;
import org.sadr.web.main.archive._type.TtFileUploadStatus;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.archive.file.file.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author masoud
 * @version 1.95.07.07
 */
public class Uploader {

    private final static Uploader INSTANCE = new Uploader();

    //    private final String fs = Environment.FILE_SEPARATOR;
    public static Uploader getInstance() {
        return INSTANCE;
    }

    private Uploader() {
        directoryService = WebConfigHandler.getWebApplicationContext().getBean(DirectoryService.class);
        fileService = WebConfigHandler.getWebApplicationContext().getBean(FileService.class);
    }

    DirectoryService directoryService;
    FileService fileService;

    public boolean upload(MultipartFile sourceFile, String destinationPath) {
        if (sourceFile.isEmpty()) {
            return false;
        }
        String fileNamee = sourceFile.getOriginalFilename();
        try {
            byte[] bytes = sourceFile.getBytes();
            java.io.File dir = new java.io.File(destinationPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the file on server
            java.io.File serverFile = new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + fileNamee);
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean upload(MultipartFile sourceFile, File file) {
        //  OutLog.pl("ii");
        if (sourceFile.isEmpty()) {
            //  OutLog.pl("EE");
            return false;
        }

        String acceptedFileName;
        String fileNamee = file.getName().replace("#", "_");
        String fe = file.getExtention();
        String fileExt = fe.isEmpty() ? "" : "." + fe.toLowerCase();
        try {
            byte[] bytes = sourceFile.getBytes();
            java.io.File dir = new java.io.File(file.getDirectory().getAbsolutePath());

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (fileNamee.length() > 36) {
                fileNamee = fileNamee.substring(0, 36);
            }
            acceptedFileName = fileNamee + fileExt;
            // Create the file on server
            java.io.File serverFile = new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + acceptedFileName);

            if (serverFile.exists()) {
                for (String taDir : file.getThumbnailsArray()) {
                    new java.io.File(file.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + file.getThumbnailName()).delete();
                }
                serverFile.delete();
                serverFile = new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + acceptedFileName);

            }
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
            }

            file.setOrginalName(acceptedFileName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ///==/////////////////////////////////////////////////// Version2
    //------------  create new
    private File uploadAndReturnFile(MultipartFile sourceFile, Directory dir, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        //  outlog.pl("");
        if (sourceFile == null || sourceFile.isEmpty()) {
            //  outlog.pl();
            return null;
        }
        String originalFileName, fe, fn, acceptedFileName;
        //  outlog.pl("");
        originalFileName = sourceFile.getOriginalFilename().replace("#", "_").replace(" ", "_").replace("(", "_").replace(")", "_");
        fe = (originalFileName == null || originalFileName.lastIndexOf(".") == -1)
            ? ""
            : originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        fe = fe.isEmpty() ? "" : "." + fe.toLowerCase();
        fn = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //  outlog.pl(orginalFileName + "  " + fn + "   " + fe + "  " + fileExt + "   " + sourceFile.getName());
        acceptedFileName = originalFileName;

        boolean isFound = false;
        //  outlog.pl();
        if (fileService.isExist(Restrictions.and(
            Restrictions.eq(File.ORGINAL_NAME, originalFileName),
            Restrictions.eq(File._DIRECTORY, dir)))) {
            //  outlog.pl("");
            switch (ifExist) {
                case KeepFileNameByFoldering:
                    //  outlog.pl("");
                    dir = this.directoryService.getDirectoryAndSubs(dir.getRepoDirectory());
                    for (Directory d : dir.getDirectorys()) {
                        //  outlog.pl("");
                        if (!fileService.isExist(Restrictions.and(
                            Restrictions.eq(File.ORGINAL_NAME, originalFileName),
                            Restrictions.eq(File._DIRECTORY, d)))) {
                            dir = d;
                            isFound = true;
                            //  outlog.p("");
                            break;
                        }
                    }
                    //  outlog.pl("");
                    if (!isFound) {
                        //  outlog.pl("");
                        dir = directoryService.getAutoSubDirectory(dir);
                    }
                    //  outlog.pl("");
                    break;
                case RenameNewFile:
                default:
                    //  outlog.pl("");
                    int i = 1;
                    while (fileService.isExist(Restrictions.and(
                        Restrictions.eq(File.ORGINAL_NAME, acceptedFileName),
                        Restrictions.eq(File._DIRECTORY, dir)))) {
                        acceptedFileName
                            = fn + "_" + i
                            + fe;
                        i++;
                    }
                    break;
                case ReplaceOldFile:

                    break;
            }
        }
        //  outlog.pl(acceptedFileName);
        File file = new File(
            acceptedFileName,
            sourceFile.getContentType(),
            sourceFile.getSize(),
            dir,
            true);
        //  outlog.pl("");
        if (upload(sourceFile, file)) {
            if (sizes != null  && tuof == TtUploadOrginalFile.DeleteOrginalFile) {
                //  outlog.pl("Deleting orginal file...");
                new java.io.File(file.getAbsolutePathName()).delete();
            }
            //  outlog.pl("");
            file.setIsContainOrginal(tuof == TtUploadOrginalFile.KeepOrginalFile);
            fileService.save(file);
            return file;
        }
        //  outlog.pl("");
        return null;
    }

    public File upload(MultipartFile sourceFile, TtRepoDirectory repoDirectory, TtUploadIfExist ifExist) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(repoDirectory), ifExist, TtUploadOrginalFile.KeepOrginalFile, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC, TtThumnailCropMode.Crop, null, null);
    }

}
