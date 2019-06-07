package org.sadr.share.main._utils;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.item.object.model.ModelJsonObject;
import org.sadr.share.main.item.vector.Vector;
import org.sadr.share.main.item.vector.model.VectorJ;
import org.sadr.share.main.layer._type.TtLayerStatus;
import org.sadr.share.main.layer._type.TtLayerType;
import org.sadr.share.main.layer.model.Info;
import org.sadr.share.main.layer.model.Layer;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.serviceUser.ServiceUser;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ShareUtils {
    private static final String INPUT_ZIP_FOLDER = "D:/_proji_sadr/ftp/upload/";
    private static final String OUTPUT_ZIP_FOLDER = "D:/_proji_sadr/ftp/map/";
    private static final String OUTPUT_OBJECT_FOLDER = "D:/_proji_sadr/ftp/objects/";
    private static final String JSON_FILE = "header.json";

    public static String prepareArgument(String mapName) {

        int pos = mapName.lastIndexOf(".");
        String origMapName = null;
        if (pos > 0) {
            origMapName = mapName.substring(0, pos);
        }
        //String inputZipFile = INPUT_ZIP_FOLDER + mapName;
        //String lastOutZipFile = OUTPUT_ZIP_FOLDER + origMapName;
        //unZipIt(inputZipFile,lastOutZipFile);
        return origMapName;
    }


    public static double getFileSize(String filePath) {
        try {
            File file = new File(filePath);
            double kByte = file.length();
            return kByte;
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return 0;
        }
    }

    public static void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];
        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                //System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Map convertInfoToMap(Info info, String mapName, double fileSize) {
        try {
            Map map = new Map();
            //info.get
            map.setName(info.getName());
            map.setDescriptions(info.getDescription());
            map.setFileName(mapName);
            map.setFileSize(fileSize);
            map.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
//            mapServiceImp.save(map);
            List<Layer> layers = info.getLayers();
            map.setLayers(new HashSet<>());
            for (org.sadr.share.main.layer.model.Layer layer : layers) {
                org.sadr.share.main.layer.Layer origLayer = new org.sadr.share.main.layer.Layer();
                origLayer.setOrder(layer.getId());
                origLayer.setName(layer.getName());
                origLayer.setType(layer.getType());
                origLayer.setSamplesWidth(layer.getSamplesWidth());
                origLayer.setSamplesHeight(layer.getSamplesHeight());
                origLayer.setTileCountHorz(layer.getTileCountHorz());
                origLayer.setTileCountVert(layer.getTileCountVert());
                origLayer.setBounds(layer.getBounds().toString());
                origLayer.setMinHeight(layer.getMinHeight());
                origLayer.setMaxHeight(layer.getMaxHeight());
                origLayer.setSpacingHorz(layer.getSpacingHorz());
                origLayer.setSpacingVert(layer.getSpacingVert());
                origLayer.setSpacingHeight(layer.getSpacingHeight());
                origLayer.setLayerStatus(TtLayerStatus.Active);
                origLayer.setLayerType(TtLayerType.Default);
                origLayer.setMap(null);

                map.getLayers().add(origLayer);
//                save(origLayer);
                //save(origLayer);
                //origLayer = null;
                //origLayers.add(origLayer);
            }
            //Set<Layer> layerSet = new HashSet<Layer>();
            //for (Layer layer:origLayers)
            //{
            //layerSet.add(layer);
            //}
            // map.setLayers(layerSet);
            //save();
            return map;

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;

        }
    }


    public static Map uploadMap(String mapName) {
        try {
            String origMapName = prepareArgument(mapName);
            String inputDirectory = INPUT_ZIP_FOLDER + mapName;
            String outputDirectory = OUTPUT_ZIP_FOLDER + origMapName;
            String bufferReaderFilePath = OUTPUT_ZIP_FOLDER + mapName;
            double fileSize = getFileSize(inputDirectory);
            String jsonHeaderFilePath = outputDirectory + "/" + JSON_FILE;
//            unZipIt(inputDirectory, outputDirectory);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonHeaderFilePath));
            Info sample = new Gson().fromJson(bufferedReader, Info.class);
            return convertInfoToMap(sample, origMapName, fileSize);


        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;
        }
    }


    private static Object convertModelJsonObjectToObject(ModelJsonObject jsonObject, TtObjectArea objectArea, String fileName, double fileSize, ServiceUser uploader, String outDirectoryPath) {
        try {
            Object object = new Object();
            object.setArea(objectArea);
            object.setName(jsonObject.getName());
            object.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
            object.setCategory(jsonObject.getCategory());
            object.setDescription(jsonObject.getDescription());
            object.setFileName(fileName);
            object.setSize(fileSize);
            object.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
            object.setUploaderUser(uploader);
            object.setModelPath(outDirectoryPath + "/" + jsonObject.getMeshFilename());
            return object;

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;
        }
    }


    private static void deleteFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
    }

    private static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
    }

    private static void copyFile(String from, String to) {
        try {
            File source = new File(from);
            File dest = new File(to);
            if (!dest.exists()) {
                Files.copy(source.toPath(), dest.toPath());
            }

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
    }

    public static Object uploadObject(String objName, TtObjectArea objectArea, ServiceUser uploader) {
        try {
            String origObjName = ShareUtils.prepareArgument(objName);
            String inputDirectory = INPUT_ZIP_FOLDER + objName;
            String outputDirectory = OUTPUT_OBJECT_FOLDER + origObjName;
            //String bufferReaderFilePath = OUTPUT_ZIP_FOLDER + objName;
            double fileSize = ShareUtils.getFileSize(inputDirectory);
            String jsonHeaderFilePath = outputDirectory + "/" + JSON_FILE;
            ShareUtils.unZipIt(inputDirectory, outputDirectory);
//            FileReader fileReader = new FileReader(jsonHeaderFilePath);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonHeaderFilePath), "UTF-8"));
            ModelJsonObject modelJsonObject = new Gson().fromJson(bufferedReader, ModelJsonObject.class);
            //Object object = new Gson().fromJson(bufferedReader, Object.class);
            Object object = convertModelJsonObjectToObject(modelJsonObject, objectArea, origObjName, fileSize, uploader, outputDirectory);
//            fileReader.close();
            //deleteFolder(outputDirectory);
            copyFile(inputDirectory, OUTPUT_OBJECT_FOLDER + objName);
            deleteFile(inputDirectory);


            return object;

            //mapServiceImp.save(map);

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;
        }
    }


    public static String getTalkFileAddress(String fileName) {
        return "D:/_proji_sadr/ftp/voice/" + fileName;
    }

    public static MeetingItem UploadVector(File file, String dest) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        File newFile = new File(dest + File.separator + uuid + (file.getName().lastIndexOf(".") != -1 ? file.getName().substring(file.getName().lastIndexOf(".")) : ""));

        file.renameTo(newFile);

        dest = dest + File.separator + uuid;
        unZipIt(newFile.getPath(), dest);

        MeetingItem mi = null;
        try {
            Vector v;
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dest + File.separator + JSON_FILE), "UTF-8"));
            VectorJ vectorJ = new Gson().fromJson(bufferedReader, VectorJ.class);
            v = new Vector();

            v.setName(vectorJ.getName());
            v.setFileName(newFile.getName());
            v.setBounds(vectorJ.getBounds());
            v.setDescription(vectorJ.getDescription());
            v.setSize(getFileSize(newFile.getPath()));
            v.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
            v.setVectorType(vectorJ.getVectorType());

            mi = new MeetingItem();

            mi.setVector(v);
            mi.setItemType(TtItemType.VECTOR);
            mi.setDeleted(TtMeetingItemDeleted.Undeleted);
            mi.setOrder(0);


        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }


        return mi;
    }

}
