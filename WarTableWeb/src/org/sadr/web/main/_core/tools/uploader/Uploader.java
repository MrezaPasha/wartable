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

    public File uploadFromUrl(String uploadLink, TtRepoDirectory ttRepoDirectory) {
        //  OutLog.pl("ii");
        if (uploadLink == null || uploadLink.isEmpty()) {
            //  OutLog.pl("EE");
            return null;
        }
        File file = new File();
        file.setUploadLink(uploadLink);
        file.setDirectory(directoryService.getDirectory(ttRepoDirectory));
        file.setUploadStatus(TtFileUploadStatus.ReadyToUpload);

        String acceptedFileName;
        String fileNamee = FilenameUtils.getBaseName(file.getUploadLink());
        String fe = FilenameUtils.getExtension(file.getUploadLink());
        String fileExt = fe.isEmpty() ? "" : "." + fe;
        //  OutLog.pl(fileNamee + "    " + fileExt);

        try {
//            byte[] bytes = sourceFile.getBytes();
            java.io.File dir = new java.io.File(file.getDirectory().getAbsolutePath());

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (fileNamee.length() > 36) {
                fileNamee = fileNamee.substring(0, 36);
            }
            acceptedFileName = fileNamee + fileExt;
            //  OutLog.pl(acceptedFileName);
            // Create the file on server
            java.io.File serverFile = new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + acceptedFileName);

            int i = 1;
            while (serverFile.exists()) {

                acceptedFileName = fileNamee
                    + "_" + i
                    + fileExt;
                serverFile = new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + acceptedFileName);
                i++;
                if (i > 5000) {
                    return null;
                }
            }

            //  OutLog.pl(acceptedFileName);
            FileUtils.copyURLToFile(new URL(file.getUploadLink()), serverFile);

            file.setOrginalName(acceptedFileName);
            file.setSize(serverFile.length());
            file.setContentType(Files.probeContentType(serverFile.toPath()));
            this.fileService.save(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File uploadFromUrl(String uploadLink, TtRepoDirectory repoDirectory, TtThumbnailsOf thumbnailsOf) {
        return uploadFromUrl(uploadLink, repoDirectory, thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    public File uploadFromUrl(String uploadLink, TtRepoDirectory repoDirectory, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        File file = uploadFromUrl(uploadLink, repoDirectory);
        if (file == null) {
            return null;
        }
        if (sizes != null && thumbnail(file, method, mode, cropMode, op, sizes) && tuof == TtUploadOrginalFile.DeleteOrginalFile) {
            OutLog.pl("Deleting orginal file...");
            new java.io.File(file.getAbsolutePathName()).delete();
        }
        //  outlog.pl("");
        file.setIsContainOrginal(tuof == TtUploadOrginalFile.KeepOrginalFile);
        fileService.update(file);
        return file;
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
            if (sizes != null && thumbnail(file, method, mode, cropMode, op, sizes) && tuof == TtUploadOrginalFile.DeleteOrginalFile) {
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

    public File upload(MultipartFile sourceFile, TtRepoDirectory repoDirectory, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(repoDirectory), ifExist, tuof, method, mode, cropMode, op, sizes);
    }

    public File upload(MultipartFile sourceFile, TtRepoDirectory repoDirectory, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(repoDirectory), thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    public File upload(MultipartFile sourceFile, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(thumbnailsOf.getRepoDirectory()), thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    public File upload(MultipartFile sourceFile, String dirPath, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(dirPath), ifExist, tuof, method, mode, cropMode, op, sizes);
    }

    public File upload(MultipartFile sourceFile, String dirPath, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(dirPath),
            thumbnailsOf.getUploadIfExist(),
            thumbnailsOf.getUploadOrginalFile(),
            thumbnailsOf.getMethod(),
            thumbnailsOf.getMode(),
            thumbnailsOf.getCropMode(),
            thumbnailsOf.getImageOp(),
            thumbnailsOf.getThumbs());
    }

    public File upload(MultipartFile sourceFile, String dirPath, TtDirectoryOrigin directoryOrigin, TtUploadIfExist ifExist) {
        return uploadAndReturnFile(sourceFile, directoryService.getDirectory(dirPath, directoryOrigin),
            ifExist,
            TtUploadOrginalFile.KeepOrginalFile,
            null,
            null,
            null,
            null,
            null);
    }

    //------------  edit previouse
    private File uploadAndReturnFile(File oldFile, MultipartFile sourceFile, Directory dir, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        //  OutLog.pl("");
        if (sourceFile != null) {
            if (sourceFile.isEmpty()) {
                //  OutLog.pl("");
                return oldFile;
            }
            //  OutLog.pl("");
            File file = Uploader.getInstance().uploadAndReturnFile(sourceFile, dir, ifExist, tuof, method, mode, cropMode, op, sizes);
            //  OutLog.pl("");
            if (file != null) {
                //  OutLog.pl("");
                if (oldFile != null) {
                    //  OutLog.pl("");
                    Uploader.getInstance().trash(oldFile);
                    oldFile.setEntityState(TtEntityState.Trash);
                    this.fileService.update(oldFile);
                }
                return file;
            }
            //  OutLog.pl("");
            return oldFile;
        }
        //  OutLog.pl("");
        if (oldFile != null) {
            //  OutLog.pl("");
            Uploader.getInstance().trash(oldFile);
            oldFile.setEntityState(TtEntityState.Trash);
            this.fileService.update(oldFile);
        }
        return null;
    }

    public File upload(File oldFile, MultipartFile sourceFile, String dirPath, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        return uploadAndReturnFile(oldFile, sourceFile, directoryService.getDirectory(dirPath), ifExist, tuof, method, mode, cropMode, op, sizes);
    }

    public File upload(File oldFile, MultipartFile sourceFile, String dirPath, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(oldFile, sourceFile, directoryService.getDirectory(dirPath), thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    public File upload(File oldFile, MultipartFile sourceFile, TtRepoDirectory repoDirectory, TtUploadIfExist ifExist, TtUploadOrginalFile tuof, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        return uploadAndReturnFile(oldFile, sourceFile, directoryService.getDirectory(repoDirectory), ifExist, tuof, method, mode, cropMode, op, sizes);
    }

    public File upload(File oldFile, MultipartFile sourceFile, TtRepoDirectory repoDirectory, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(oldFile, sourceFile, directoryService.getDirectory(repoDirectory), thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    public File upload(File oldFile, MultipartFile sourceFile, TtThumbnailsOf thumbnailsOf) {
        return uploadAndReturnFile(oldFile, sourceFile, directoryService.getDirectory(thumbnailsOf.getRepoDirectory()), thumbnailsOf.getUploadIfExist(), thumbnailsOf.getUploadOrginalFile(), thumbnailsOf.getMethod(), thumbnailsOf.getMode(), thumbnailsOf.getCropMode(), thumbnailsOf.getImageOp(), thumbnailsOf.getThumbs());
    }

    //------------  dropzone

    //==//////////////////////////////////////////////////
    public boolean thumbnail(File file, Scalr.Method method, Scalr.Mode mode, TtThumnailCropMode cropMode, BufferedImageOp op, TtThumbnailSize... sizes) {
        if (file == null) {
            return false;
        }
        OutLog.pl(Arrays.toString(sizes));
        for (TtThumbnailSize sz : sizes) {
            OutLog.pl(sz.toString());
            try {
                BufferedImage image = null;

//                if (Validator.image(file)) {   // Image files
////                    OutLog.pl("image " + file.getAbsolutePathName());
//                    image = ImageIO.read(new java.io.File(file.getAbsolutePathName()));
//                } else if (Validator.pdf(file)) {    // PDF files
//                    PDDocument pdfDoc = PDDocument.load(new java.io.File(file.getAbsolutePathName()));
//                    try {
//                        image = new PDFRenderer(pdfDoc).renderImageWithDPI(0, 300, ImageType.RGB);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        pdfDoc.close();
//                    }
//                } else {  // Other files
//                    //  outlog.pl();
//                    return false;
//                }

                java.io.File dir = new java.io.File(file.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + sz.getDirectoryName());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                if (cropMode == TtThumnailCropMode.Crop && mode == Scalr.Mode.FIT_EXACT) {
                    if (image == null) {
                        OutLog.pl();
                        return false;
                    }
                    BufferedImage thumbnail;
                    if (sz.getRatio_HrW() < (float) image.getHeight() / image.getWidth()) {
                        thumbnail = Scalr.resize(
                            image,
                            method,
                            Scalr.Mode.FIT_TO_WIDTH,
                            sz.getWidth(),
                            sz.getHeight(),
                            op
                        );
                        thumbnail = Scalr.crop(thumbnail,
                            0,
                            (thumbnail.getHeight() - sz.getHeight()) / 2,
                            sz.getWidth(),
                            sz.getHeight());

                    } else if (sz.getRatio_HrW() > (image.getHeight() / image.getWidth())) {
                        thumbnail = Scalr.resize(
                            image,
                            method,
                            Scalr.Mode.FIT_TO_HEIGHT,
                            sz.getWidth(),
                            sz.getHeight(),
                            op
                        );
                        thumbnail = Scalr.crop(thumbnail,
                            (thumbnail.getWidth() - sz.getWidth()) / 2,
                            0,
                            sz.getWidth(),
                            sz.getHeight());
                    } else {
                        thumbnail = Scalr.resize(
                            image,
                            method,
                            mode,
                            sz.getWidth(),
                            sz.getHeight(),
                            op
                        );
                    }

                    ImageIO.write(thumbnail,
                        "jpg",
                        new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + file.getThumbnailName())
                    );

                } else {
                    ImageIO.write(
                        Scalr.resize(
                            image,
                            method,
                            mode,
                            sz.getWidth(),
                            sz.getHeight(),
                            op
                        ),
                        "jpg",
                        new java.io.File(dir.getAbsolutePath() + Environment.FILE_SEPARATOR + file.getThumbnailName())
                    );
                }
                file.addThumbnails(sz.getDirectoryName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean thumbnail(File f, TtThumbnailsOf thumbnailsOf) {
        return thumbnail(f,
            thumbnailsOf.getMethod(),
            thumbnailsOf.getMode(),
            thumbnailsOf.getCropMode(),
            thumbnailsOf.getImageOp(),
            thumbnailsOf.getThumbs());
    }

    public boolean thumbnail(File file, TtThumbnailSize... sizes) {
        return thumbnail(file, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, TtThumnailCropMode.DontCrop, Scalr.OP_ANTIALIAS, sizes);
    }

    public boolean thumbnail(File file, Scalr.Mode mode, TtThumnailCropMode cropMode, TtThumbnailSize... sizes) {
        return thumbnail(file, Scalr.Method.AUTOMATIC, mode, cropMode, Scalr.OP_ANTIALIAS, sizes);
    }

    public boolean trash(File file) {
        try {
            java.io.File serverFile = new java.io.File(file.getAbsolutePathName());
            if (!serverFile.exists()) {
                return false;
            }
            for (String taDir : file.getThumbnailsArray()) {
                new java.io.File(file.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + file.getThumbnailName()).delete();
            }
            file.emtyThumbnails();
            file.setOrginalName("t_" + (new Date().getTime() % 1000000000) + "_" + file.getOrginalName());
            serverFile.renameTo(new java.io.File(file.getAbsolutePathName()));

            thumbnail(file, TtThumbnailSize._50X50);
            file.addThumbnails(TtThumbnailSize._50X50.getDirectoryName());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(File file) {
        try {
            java.io.File serverFile = new java.io.File(file.getAbsolutePathName());
            if (!serverFile.exists()) {
                return false;
            }
            for (String taDir : file.getThumbnailsArray()) {
                new java.io.File(file.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + file.getThumbnailName()).delete();
            }
            serverFile.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllThumbnailDirectory(TtRepoDirectory repoDirectory) {
        Directory dir = directoryService.getDirectory(repoDirectory);
        if (dir != null) {
            OutLog.pl(dir.getAbsolutePath());
            java.io.File fl = new java.io.File(dir.getAbsolutePath());
            deleteThumbnailOfThisDir(fl);
        }
        return true;
    }

    private boolean deleteThumbnailOfThisDir(java.io.File fl) {
        if (fl.exists() && fl.isDirectory()) {
            OutLog.pl(fl.getName());
            java.io.File[] lfs = fl.listFiles();
            if (lfs != null) {
                for (java.io.File f : lfs) {
                    OutLog.p(f.getAbsolutePath() + "   " + f.getName());
                    if (f.isDirectory()) {
                        if (f.getName().startsWith(".thum")) {
                            OutLog.p(f.exists(), f.canExecute(), f.canRead(), f.canWrite(), f.isAbsolute());
//                            OutLog.p(f.exists() + " " + " deleted  " + f.delete());
                            try {
                                FileUtils.deleteDirectory(f);
                                OutLog.p(" deleted  ");
                            } catch (IOException ex) {
                                Logger.getLogger(Uploader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            OutLog.p("");
                            deleteThumbnailOfThisDir(f);
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean deleteThumbnailOfThisFile(File f) {
        if (f != null) {

        }
        return true;
    }

    public boolean moveFile(File tergetFile, Directory destDirectory) {
        try {
            java.io.File serverFile = new java.io.File(tergetFile.getAbsolutePathName());
            if (!serverFile.exists()) {
                return false;
            }
            // //  OutLog.pl("");

            for (String taDir : tergetFile.getThumbnailsArray()) {

                java.io.File dir1 = new java.io.File(destDirectory.getAbsolutePath() + Environment.FILE_SEPARATOR + taDir);
                if (!dir1.exists()) {
                    dir1.mkdirs();
                }

                // //  OutLog.pl(tergetFile.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + tergetFile.getThumbnailName());
                // //  OutLog.p(destDirectory.getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + tergetFile.getOrginalName());
                new java.io.File(tergetFile.getDirectory().getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + tergetFile.getThumbnailName())
                    .renameTo(new java.io.File(destDirectory.getAbsolutePath() + Environment.FILE_SEPARATOR + taDir + Environment.FILE_SEPARATOR + tergetFile.getThumbnailName()));
            }
            serverFile.renameTo(new java.io.File(destDirectory.getAbsolutePath() + Environment.FILE_SEPARATOR + tergetFile.getOrginalName()));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cleanTrash() {
        return true;
    }
}
