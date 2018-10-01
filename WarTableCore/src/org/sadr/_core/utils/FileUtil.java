/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author dev1
 */
public class FileUtil {

    public static void fillFileList(String directoryName, List<File> fileList) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList == null) {
            return;
        }
        for (File file : fList) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                fillFileList(file.getAbsolutePath(), fileList);
            }
        }
    }

    public static boolean unzip(File zipFile) {
        byte[] buffer = new byte[1024];
        try {

            //create output directory is not exists
            File folder = new File(zipFile.getParentFile().getAbsolutePath());
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                new ZipInputStream(new FileInputStream(zipFile.getAbsolutePath()));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(zipFile.getParentFile().getAbsolutePath() + File.separator + fileName);

                OutLog.p("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                if (!ze.isDirectory()) {
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /////////////////////////// ZIP

    private static void _getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    _getAllFiles(file, fileList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void _addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
        IOException {

        FileInputStream fis = new FileInputStream(file);

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
            file.getCanonicalPath().length());
        OutLog.p("Writing '" + zipFilePath + "' to zip file");
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
