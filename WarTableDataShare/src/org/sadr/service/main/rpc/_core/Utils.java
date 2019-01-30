package org.sadr.service.main.rpc._core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc._types.TtRpcResponseResult;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogServiceImp;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.item.object._types.TtObjectPrivacy;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.log.models.serviceLog.ServiceLogServiceImp;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.*;

public class Utils {
    private ServiceLogServiceImp serviceLogServiceImp = (ServiceLogServiceImp)IOCContainer.GetBeans(ServiceLogServiceImp.class);
    private static String UPLOAD_FILE_DIR = "I:\\LastwarTableServer\\Java FrameWork\\ftp\\upload\\";
    private static String OBJ_FILE_PATH = "I:\\LastwarTableServer\\Java FrameWork\\ftp\\objects\\";

    public Utils() {
    }

    public static RpcResponse generateSuccessLoginResponse(RpcRequest rpcRequest, ServiceUser user, Map config, String queueName) {
        try {
            RpcResponse rpcResponse = new RpcResponse();
            ErrorResponse errorResponse = new ErrorResponse();
            rpcResponse.setId(rpcRequest.getId());
            Map result = new HashMap();
            result.put(TtRpcResponseResult.SessionId.getName(), queueName);
            result.put(TtRpcResponseResult.LoginCounter.getName(), user.getLoginCount());
            result.put(TtRpcResponseResult.CurrentDateTime.getName(), BrokerUtils.nowTime().toString());
            result.put(TtRpcResponseResult.LastVersion.getName(), config.get(TtConfig.LastSoftwareVersion).toString());
            result.put(TtRpcResponseResult.LastVersionSetupSize.getName(), config.get(TtConfig.LastSoftwareSize).toString());
            if (user.getLastRoom() == null) {
                result.put(TtRpcResponseResult.LastRoomID.getName(), -1);
            } else if (user.getLastRoom() != null) {
                result.put(TtRpcResponseResult.LastRoomID.getName(), user.getLastRoom().getId());
            }

            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.NoError.ordinal());
            errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } catch (Exception var7) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var7.getMessage());
            return null;
        }
    }

    public static RpcResponse generateLockedUserLoginResponse(RpcRequest rpcRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setCode(TtErrors.UserIsLocked.ordinal());
        errorResponse.setDescription(TtErrors.UserIsLocked.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateDeletedUserdLoinResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setCode(TtErrors.UserIsDeleted.ordinal());
        errorResponse.setDescription(TtErrors.UserIsDeleted.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateTresholdRetryLoginResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setCode(TtErrors.UserPasswordWrong.ordinal());
        errorResponse.setDescription(TtErrors.UserPasswordWrong.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateLockUserLoginResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setCode(TtErrors.UserIsLocked.ordinal());
        errorResponse.setDescription(TtErrors.UserIsLocked.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateUnvalidUsernameLoginRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setDescription(TtErrors.UserDoesntexist.getErrorValueService());
        errorResponse.setCode(TtErrors.UserDoesntexist.ordinal());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateUnvalidUsernamePasswordLoginRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult((Map)null);
        errorResponse.setDescription(TtErrors.UserPasswordWrong.getErrorValueService());
        errorResponse.setCode(TtErrors.UserPasswordWrong.ordinal());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcRequest JsonToRpcProtocolObj(String inputJson) {
        try {
            Gson gson = new Gson();
            RpcRequest protocol = (RpcRequest)gson.fromJson(inputJson, RpcRequest.class);
            return protocol;
        } catch (Exception var3) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var3.getMessage());
            return null;
        }
    }

    public static RpcResponse failedPasswordChangeDueSessionError(RpcRequest rpcRequest) {
        try {
            RpcResponse rpcResponse = new RpcResponse();
            ErrorResponse errorResponse = new ErrorResponse();
            rpcResponse.setId(rpcRequest.getId());
            Map result = new HashMap();
            result.put(TtRpcResponseResult.Success.getName(), 0);
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.ChangePasswordFailedDueSessionErroe.ordinal());
            errorResponse.setDescription(TtErrors.ChangePasswordFailedDueSessionErroe.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } catch (Exception var4) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var4.getMessage());
            return null;
        }
    }

    public static RpcResponse failedPasswordChangeDueUsernameError(RpcRequest rpcRequest) {
        try {
            RpcResponse rpcResponse = new RpcResponse();
            ErrorResponse errorResponse = new ErrorResponse();
            rpcResponse.setId(rpcRequest.getId());
            Map result = new HashMap();
            result.put(TtRpcResponseResult.Success.getName(), 0);
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.ChangePasswordFailedDueUsernameError.ordinal());
            errorResponse.setDescription(TtErrors.ChangePasswordFailedDueUsernameError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } catch (Exception var4) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var4.getMessage());
            return null;
        }
    }

    public static RpcResponse successPasswordChange(RpcRequest rpcRequest) {
        try {
            RpcResponse rpcResponse = new RpcResponse();
            ErrorResponse errorResponse = new ErrorResponse();
            rpcResponse.setId(rpcRequest.getId());
            Map result = new HashMap();
            result.put(TtRpcResponseResult.Success.name(), 1);
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.NoError.ordinal());
            errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } catch (Exception var4) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var4.getMessage());
            return null;
        }
    }

    public static String invalidJsonRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();

        try {
            rpcResponse.setId(rpcRequest.getId());
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(TtErrors.InvalidJsonResquest.ordinal());
            errorResponse.setDescription(TtErrors.InvalidJsonResquest.getErrorValueService());
            rpcResponse.setResult((Map)null);
            rpcResponse.setError(errorResponse);
            return RpcResponseObjToJson(rpcResponse);
        } catch (Exception var3) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var3.getMessage());
            return null;
        }
    }

    public static String RpcResponseObjToJson(RpcResponse rpcResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRespnse = objectMapper.writeValueAsString(rpcResponse);
            return jsonRespnse;
        } catch (Exception var3) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var3.getMessage());
            return null;
        }
    }

    public static String ObjToJson(java.lang.Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(object);
            return jsonRequest;
        } catch (Exception var3) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var3.getMessage());
            return null;
        }
    }

    public static boolean isInTreshold(String lastFailedTime, int duration, String username, String password) {
        try {
            ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp)IOCContainer.GetBeans(ServiceUserServiceImp.class);
            String nowTime = ParsCalendar.getInstance().getShortDateTime();
            String nowNegConfig = ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Second, -duration, nowTime);
            ServiceUser serviceUser = (ServiceUser)serviceUserServiceImp.findBy(Restrictions.and(new Criterion[]{Restrictions.gt("failedDateTime", nowNegConfig), Restrictions.eq("userName", username), Restrictions.eq("password", password)}));
            return serviceUser != null;
        } catch (Exception var8) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var8.getMessage());
            return false;
        }
    }

    public static void insertCriticalLog(String dateTime, String position, String errorMessage) {
        try {
            CriticalLogServiceImp criticalLogServiceImp = (CriticalLogServiceImp)IOCContainer.GetBeans(CriticalLogServiceImp.class);
            CriticalLog criticalLog = new CriticalLog();
            criticalLog.setCreationDateTime(dateTime);
            criticalLog.setLogDetailPosition(position);
            criticalLog.setLogDetailMessage(errorMessage);
            criticalLogServiceImp.save(criticalLog);
        } catch (Exception var5) {
            insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var5.getMessage());
        }

    }

    public static RpcResponse failedPrivilageClassChange(RpcRequest rpcRequest, TtErrors ttErrors, int resultValue) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        Map result = new HashMap();
        result.put(TtRpcResponseResult.Success.getName(), Integer.toString(resultValue));
        rpcResponse.setResult(result);
        errorResponse.setCode(ttErrors.ordinal());
        errorResponse.setDescription(ttErrors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateDoBanndingUserResponse(RpcRequest rpcRequest, TtErrors ttErrors, int resultValue) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        Map result = new HashMap();
        result.put(TtRpcResponseResult.Success.getName(), Integer.toString(resultValue));
        rpcResponse.setResult(result);
        errorResponse.setCode(ttErrors.ordinal());
        errorResponse.setDescription(ttErrors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateListAllUserResponse(RpcRequest rpcRequest, List<ServiceUser> serviceUsers, List<ServiceUser> sessionUsers, TtErrors ttErrors) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        Map result = new HashMap();
        if (serviceUsers.isEmpty() && sessionUsers.isEmpty()) {
            rpcResponse.setResult(result);
            errorResponse.setCode(ttErrors.ordinal());
            errorResponse.setDescription(ttErrors.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } else {
            List<String> users = new ArrayList();
            List<String> privilageClasses = new ArrayList();
            List<String> banned = new ArrayList();
            List<String> lastUserId = new ArrayList();
            List<String> names = new ArrayList();
            List<String> families = new ArrayList();
            List<String> grades = new ArrayList();
            List<String> orgPositions = new ArrayList();
            List<String> onlines = new ArrayList();
            Iterator var17 = serviceUsers.iterator();

            while(var17.hasNext()) {
                ServiceUser serviceUser = (ServiceUser)var17.next();
                users.add(serviceUser.getUserName());
                banned.add(serviceUser.getBanned().getName());
                lastUserId.add((String) null);
                names.add(serviceUser.getName());
                families.add(serviceUser.getFamily());
                grades.add(serviceUser.getGrade().getValue());
                orgPositions.add(serviceUser.getOrgPosition().getValue());
                if (sessionUsers.contains(serviceUser)) {
                    onlines.add("1");
                } else {
                    onlines.add("0");
                }
            }

            result.put("Users", users);
            result.put("PrivilegeClasses", privilageClasses);
            result.put("Banned", banned);
            result.put("Online", onlines);
            result.put("LastUserId", lastUserId);
            result.put("Names", names);
            result.put("Families", families);
            result.put("Grades", grades);
            result.put("OrgPositions", orgPositions);
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.NoError.ordinal());
            errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        }
    }

    public static Map getJsonElement(String jsonFileDirectory, String element) {
        try {
            JsonParser parser = new JsonParser();
            java.lang.Object object = parser.parse(new FileReader(jsonFileDirectory));
            JsonObject jsonObject = (JsonObject)object;
            Map result = new HashMap();
            result.put(element, jsonObject.get(element));
            return result;
        } catch (Exception var6) {
            return null;
        }
    }

    public static void deleteFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
        } catch (Exception var2) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var2.getMessage());
        }

    }

    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception var2) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var2.getMessage());
        }

    }

    public static void copyFile(String from, String to) {
        try {
            File source = new File(from);
            File dest = new File(to);
            Files.copy(source.toPath(), dest.toPath());
        } catch (Exception var4) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var4.getMessage());
        }

    }

    public static double getFileSize(String filePath) {
        try {
            File file = new File(filePath);
            double kByte = (double)file.length();
            return kByte;
        } catch (Exception var4) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), var4.getMessage());
            return 0.0D;
        }
    }

    public boolean unZipIt(String zipFile, String outputFolder) {
        try {
            ZipFile zFile = new ZipFile(zipFile);
            zFile.extractAll(outputFolder);
            return true;
        } catch (Exception var4) {
            return false;
        }
    }

    public static String getOrigFileName(String mapName) {
        int pos = mapName.lastIndexOf("\\");
        int end = mapName.lastIndexOf("l");
        String origMapName = null;
        if (pos > 0) {
            origMapName = mapName.substring(pos + 1, end + 1);
        }

        return origMapName;
    }

    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.unZipIt("I:\\LastwarTableServer\\Java FrameWork\\ftp\\upload\\7414f8ccaf16379cca328d581558de82.zip", "I:\\LastwarTableServer\\Java FrameWork\\ftp\\upload\\7414f8ccaf16379cca328d581558de82");
    }

    public static boolean uploadObject(String objFilePath, ServiceUser serviceUser) {
        try {
            String origObjNameWithExtension = getOrigFileName(objFilePath);
            copyFile(objFilePath, OBJ_FILE_PATH + origObjNameWithExtension);
            double fileSize = getFileSize(objFilePath);
            String origObjNameWithoutExtension = origObjNameWithExtension.substring(0, origObjNameWithExtension.lastIndexOf("."));
            String tempFolder = UPLOAD_FILE_DIR + origObjNameWithoutExtension;
            createFolder(tempFolder);
            String mustRenameFile = origObjNameWithoutExtension + ".zip";
            (new Utils()).unZipIt(mustRenameFile, tempFolder);
            String jsonHeaderFilePath = tempFolder + "/header.json";
            new FileReader(jsonHeaderFilePath);
            String name = (String)getJsonElement(jsonHeaderFilePath, "Name").get("Name");
            String description = (String)getJsonElement(jsonHeaderFilePath, "Description").get("Description");
            String category = (String)getJsonElement(jsonHeaderFilePath, "Category").get("Category");
            Integer roomId = (Integer)getJsonElement(jsonHeaderFilePath, "RoomId").get("RoomId");
            Integer areaId = (Integer)getJsonElement(jsonHeaderFilePath, "Area").get("Area");
            RoomServiceImp roomServiceImp = (RoomServiceImp)IOCContainer.GetBeans(RoomServiceImp.class);
            ObjectServiceImp objectServiceImp = (ObjectServiceImp)IOCContainer.GetBeans(ObjectServiceImp.class);
            Room room = (Room)roomServiceImp.findBy(Restrictions.eq("id", roomId));
            TtObjectArea objectArea = TtObjectArea.getByOrdinal(areaId);
            copyFile(objFilePath, OBJ_FILE_PATH);
            Object object = new Object();
            object.setName(name);
            object.setSize(fileSize);
            object.setFileName(origObjNameWithExtension);
            object.setUploaderUser(serviceUser);
            object.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
            object.setCategory(category);
            object.setArea(objectArea);
            object.setDescription(description);
            if (roomId == 0) {
                object.setPrivacy(TtObjectPrivacy.PUBLIC);
            } else {
                object.setPrivacy(TtObjectPrivacy.PRIVATE);
                Set<Room> rooms = new HashSet();
                rooms.add(room);
                object.setRooms(rooms);
            }

            objectServiceImp.save(object);
            return true;
        } catch (Exception var21) {
            return false;
        }
    }

    public void writeToFile(InputStream uploadedInputStream , String uploadedFileLocation)
    {
        try
        {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1)
            {
                out.write(bytes,0,read);
            }
            out.flush();
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    public String generateMd5Name(String origFileName) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(origFileName.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            return hashText;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static boolean renameFile(String oldDirectoryPath, String newName) {
        try {
            String newdirectoryPath = oldDirectoryPath.substring(0, oldDirectoryPath.lastIndexOf("/")) + newName;
            File oldFile = new File(oldDirectoryPath);
            File newFile = new File(newdirectoryPath);
            return newFile.exists() ? false : oldFile.renameTo(newFile);
        } catch (Exception var5) {
            return false;
        }
    }

    public static boolean createFolder(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdir();
            }

            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean insertVoipUser(ServiceUser serviceUser) {
        try {
            new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
            String serviceName = "";
            String voipServerAddress = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerIp))).getConfigValue();
            String voipServerPort = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerRestPort))).getConfigValue();
            URL url = new URL("http://" + voipServerAddress + ":" + voipServerPort + "/add_user?user_num=" + serviceUser.getUserName() + "&user_pass=" + serviceUser.getPassword());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        } catch (Exception var9) {
            return false;
        }
    }

    public static boolean deleteVoipUser(ServiceUser serviceUser) {
        try {
            new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
            String serviceName = "";
            String voipServerAddress = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerIp))).getConfigValue();
            String voipServerPort = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerRestPort))).getConfigValue();
            URL url = new URL("http://" + voipServerAddress + ":" + voipServerPort + "/delete_user?user_num=" + serviceUser.getUserName());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        } catch (Exception var9) {
            return false;
        }
    }

    public static boolean createVoipConferenceBridgeByroom(Room room) {
        try {
            new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
            String serviceName = "";
            String voipServerAddress = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerIp))).getConfigValue();
            String voipServerPort = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerRestPort))).getConfigValue();
            URL url = new URL("http://" + voipServerAddress + ":" + voipServerPort + "/add_confroom?room_num=" + room.getId() + "&admin_conf_num=" + room.getId());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        } catch (Exception var9) {
            return false;
        }
    }

    public static boolean deleteVoipConferenceBridge(Room room) {
        try {
            new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
            String serviceName = "";
            String voipServerAddress = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerIp))).getConfigValue();
            String voipServerPort = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerRestPort))).getConfigValue();
            URL url = new URL("http://" + voipServerAddress + ":" + voipServerPort + "/delete_confroom?room_num=" + room.getId());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        } catch (Exception var9) {
            return false;
        }
    }

    public static final String generateUUID() {
        String secondstSyllabus = UUID.randomUUID().toString();
        return secondstSyllabus;
    }

    public static boolean createVoipConferenceBridgeById(String conferenceNumber) {
        try {
            new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
            String serviceName = "";
            String voipServerAddress = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerIp))).getConfigValue();
            String voipServerPort = ((BaseConfig)baseConfigServiceImp.findBy(Restrictions.eq("configId", TtConfig.VoipServerRestPort))).getConfigValue();
            String finalRequest = "http://" + voipServerAddress + ":" + voipServerPort + "/add_confroom?room_num=" + conferenceNumber + "&admin_conf_num=" + conferenceNumber;
            URL url = new URL(finalRequest);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        } catch (Exception var10) {
            return false;
        }
    }

    public static String startRecord(String conferenceId) {
        try {
            URL url = new URL("http://192.168.88.238:5000/start_record?room_num=1");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output = br.readLine();
            return output;
        } catch (Exception var5) {
            return null;
        }
    }

    public static boolean stopRecord(String conferenceId) {
        try {
            URL url = new URL("http://192.168.88.238:5000/stop_record?room_num=1");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output = br.readLine();
            return true;
        } catch (Exception var5) {
            return false;
        }
    }
}
