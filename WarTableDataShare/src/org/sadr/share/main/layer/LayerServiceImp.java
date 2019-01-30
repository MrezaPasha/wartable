package org.sadr.share.main.layer;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.item.object.model.ModelJsonObject;
import org.sadr.share.main.layer.model.Info;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@Component
public class LayerServiceImp extends GenericServiceImpl<Layer, LayerDao> implements LayerService {
    private static final String INPUT_ZIP_FOLDER = "G:/WatTable/FINAL SERVER/NewServer/Java FrameWork/ftp/upload/";
    private static final String OUTPUT_ZIP_FOLDER = "G:/WatTable/FINAL SERVER/NewServer/Java FrameWork/ftp/map/";
    private static final String OUTPUT_OBJECT_FOLDER = "G:/WatTable/FINAL SERVER/NewServer/Java FrameWork/ftp/objects/";
    private static final String JSON_FILE = "header.json";
    private MapServiceImp mapServiceImp;
    private ObjectServiceImp objectServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private MrmlServiceImp mrmlServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    private MeetingServiceImp meetingServiceImp;


    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }


    @Autowired
    public void setMrmlServiceImp(MrmlServiceImp mrmlServiceImp) {
        this.mrmlServiceImp = mrmlServiceImp;
    }


    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    @Autowired
    public void setObjectServiceImp(ObjectServiceImp objectServiceImp) {
        this.objectServiceImp = objectServiceImp;
    }

    @Autowired
    public void setMapServiceImp(MapServiceImp mapServiceImp) {
        this.mapServiceImp = mapServiceImp;
    }

    public RpcResponse generateGenericSuccessResponse(TtGlobalId globalId, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(globalId.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        java.util.Map result = new HashMap();
        if (error == TtErrors.NoError) {
            result.put("Success", 1);
        } else {
            result.put("Success", 0);
        }
        rpcResponse.setError(errorResponse);
        rpcResponse.setResult(result);
        return rpcResponse;
    }

    public Object convertModelJsonObjectToObject(ModelJsonObject jsonObject, TtObjectArea objectArea, String fileName, double fileSize, ServiceUser uploader) {
        try {
            Object object = new Object();
            object.setArea(objectArea);
            object.setName(object.getFileName());
            object.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
            object.setCategory(jsonObject.getCategory());
            object.setDescription(jsonObject.getDescription());
            object.setFileName(fileName);
            object.setSize(fileSize);
            object.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
            object.setUploaderUser(uploader);
            return object;

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;
        }
    }


    public void uploadObject(String objName, TtObjectArea objectArea, ServiceUser uploader) {
        try {
            String origObjName = prepareArgument(objName);
            String inputDirectory = INPUT_ZIP_FOLDER + objName;
            String outputDirectory = OUTPUT_OBJECT_FOLDER + origObjName;
            String bufferReaderFilePath = OUTPUT_ZIP_FOLDER + objName;
            double fileSize = getFileSize(inputDirectory);
            String jsonHeaderFilePath = outputDirectory + "/" + JSON_FILE;
            unZipIt(inputDirectory, outputDirectory);
            FileReader fileReader = new FileReader(jsonHeaderFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ModelJsonObject modelJsonObject = new Gson().fromJson(bufferedReader, ModelJsonObject.class);
            //Object object = new Gson().fromJson(bufferedReader, Object.class);
            Object object = convertModelJsonObjectToObject(modelJsonObject, objectArea, origObjName, fileSize, uploader);
            fileReader.close();
            deleteFolder(outputDirectory);
            copyFile(inputDirectory, OUTPUT_OBJECT_FOLDER + objName);
            deleteFile(inputDirectory);
            objectServiceImp.save(object);

            //mapServiceImp.save(map);

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
    }

    public void deleteFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
    }

    public void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
    }

    public void copyFile(String from, String to) {
        try {
            File source = new File(from);
            File dest = new File(to);
            Files.copy(source.toPath(), dest.toPath());

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
    }

    public static void main(String[] args) {
        LayerServiceImp layerServiceImp = new LayerServiceImp();
        layerServiceImp.uploadObject("sarbaz_khody.model", TtObjectArea.forest, null);
    }

    public void uploadMap(String mapName) {
        try {
            String origMapName = prepareArgument(mapName);
            String inputDirectory = INPUT_ZIP_FOLDER + mapName;
            String outputDirectory = OUTPUT_ZIP_FOLDER + origMapName;
            String bufferReaderFilePath = OUTPUT_ZIP_FOLDER + mapName;
            double fileSize = getFileSize(inputDirectory);
            String jsonHeaderFilePath = outputDirectory + "/" + JSON_FILE;
            unZipIt(inputDirectory, outputDirectory);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonHeaderFilePath));
            Info sample = new Gson().fromJson(bufferedReader, Info.class);
            Map map = convertInfoToMap(sample, origMapName, fileSize);
            //mapServiceImp.save(map);


        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
    }

    public String prepareArgument(String mapName) {
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

    public void unZipIt(String zipFile, String outputFolder) {

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

    public double getFileSize(String filePath) {
        try {
            File file = new File(filePath);
            double kByte = file.length();
            return kByte;
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return 0;
        }
    }

    public Map convertInfoToMap(Info info, String mapName, double fileSize) {
        try {
            Map map = new Map();
            //info.get
            map.setName(info.getName());
            map.setDescriptions(info.getDescription());
            map.setFileName(mapName);
            map.setFileSize(fileSize);
            map.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            mapServiceImp.save(map);
            List<org.sadr.share.main.layer.model.Layer> layers = info.getLayers();
            List<Layer> origLayers = new ArrayList<>();
            for (org.sadr.share.main.layer.model.Layer layer : layers) {
                Layer origLayer = new Layer();
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
                origLayer.setMap(map);
                save(origLayer);
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

    public RpcResponse modifylayerDetails(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
                List<Integer> layerIds = (List<Integer>) rpcRequest.getParams().get("LayerIds");
                List<Integer> layerorders = (List<Integer>) rpcRequest.getParams().get("LayerOrders");
                List<Double> layerOpacities = (List<Double>) rpcRequest.getParams().get("LayerOpacities");
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId));
                List<Mrml> mrmls = new ArrayList<>();
                for (int INDEX = 0; INDEX < layerIds.size(); INDEX++) {
                    Layer layer = findBy(Restrictions.eq(Layer.ID, layerIds.get(INDEX)));
                    if (layer != null) {
                        Mrml mrml = mrmlServiceImp.findBy(Restrictions.and(Restrictions.eq(Mrml._MEETING, meeting), Restrictions.eq(Mrml._LAYER, layer)));
                        if (mrml != null) {
                            mrmls.add(mrml);
                        } else {
                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.ModifyLayerDetails, TtErrors.MrmlIsNotExist);
                            return rpcResponse;
                        }
                    } else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.ModifyLayerDetails, TtErrors.LayerIdNotExist);
                        return rpcResponse;
                    }

                }
                for (int INDEX = 0; INDEX < mrmls.size(); INDEX++) {
                    Mrml updateMrml = mrmls.get(INDEX);
                    updateMrml.setOrder(layerorders.get(INDEX));
                    updateMrml.setOpacity(layerOpacities.get(INDEX));
                    mrmlServiceImp.update(updateMrml);
                }
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.ModifyOpacity, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }

    public RpcResponse generateGetLayerDetailsResponse(List<Long> layerIds, List<Long> layerOrders, List<Double> layerOpacities, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetLayersDetails.ordinal());
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError) {
            result.put("LayerIds", layerIds);
            result.put("LayerOrders", layerOrders);
            result.put("LayerOpacities", layerOpacities);
            rpcResponse.setResult(result);
        } else {
            rpcResponse.setResult(result);
        }
        return rpcResponse;
    }

    public RpcResponse getLayerDetails(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetLayersDetails.ordinal());
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            List<Long> layerIds = new ArrayList<>();
            List<Double> layerOpacities = new ArrayList<>();
            List<Long> layerOrders = new ArrayList<>();
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._MAP, Map._LAYERS));
                if (meeting != null) {
                    for (Layer layer : meeting.getCurrentRoomMap().getMap().getLayers()) {
                        Mrml mrml = mrmlServiceImp.findBy(Restrictions.and(Restrictions.eq(Mrml._LAYER, layer), Restrictions.eq(Mrml._MEETING, meeting)));
                        layerIds.add(layer.getId());
                        layerOpacities.add(mrml.getOpacity());
                        layerOrders.add(mrml.getOrder());
                        rpcResponse = generateGetLayerDetailsResponse(layerIds, layerOrders, layerOpacities, TtErrors.NoError);
                        return rpcResponse;
                    }
                } else {
                    rpcResponse = generateGetLayerDetailsResponse(null, null, null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGetLayerDetailsResponse(null, null, null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGetLayerDetailsResponse(null, null, null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
}
