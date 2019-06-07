package org.sadr.share.main.item.object;

import com.google.gson.internal.LinkedTreeMap;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.item.object._types.TtObjectPrivacy;
import org.sadr.share.main.layer.LayerServiceImp;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ObjectServiceImp extends GenericServiceImpl<Object, ObjectDao> implements ObjectService {

    private final String BASE_OBJECT_DIR = "I:\\LastwarTableServer\\Java FrameWork\\ftp\\objects\\";

    private LayerServiceImp layerServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;
    private ObjectServiceImp objectServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    private RoomServiceImp roomServiceImp;


    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
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
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setLayerServiceImp(LayerServiceImp layerServiceImp) {
        this.layerServiceImp = layerServiceImp;
    }

    public RpcResponse generateListObjcetsResponse(List<Object> objects, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListObjects.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        java.util.Map result = new HashMap();
        List<Long> objectIds = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        List<Integer> areas = new ArrayList<>();
        try {
            if (error == TtErrors.NoError) {
                for (Object object : objects) {
                    objectIds.add(object.getId());
                    names.add(object.getName());
                    descriptions.add(object.getDescription());
                    categories.add(object.getCategory());
                    areas.add(object.getArea().ordinal());
                }
                result.put("ObjectIds", objectIds);
                result.put("Names", names);
                result.put("Descriptions", descriptions);
                result.put("Categories", categories);
                result.put("Areas", areas);
                rpcResponse.setResult(result);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);
            } else {
                rpcResponse.setResult(null);
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);
                return rpcResponse;

            }


        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
        return rpcResponse;
    }

    public RpcResponse generateListMapObjectChangesResponse(List<MeetingItem> meetingItems, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListMapObjectChanges.ordinal());
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError) {
            List<Long> objectInstanceIds = new ArrayList<>();
            List<Integer> layerIds = new ArrayList<>();
            List<Long> objectIds = new ArrayList<>();
            List<String> usernames = new ArrayList<>();
            List<Double> posx = new ArrayList<>();
            List<Double> posy = new ArrayList<>();
            List<Double> posz = new ArrayList<>();
            List<Double> yaw = new ArrayList<>();
            List<Double> pitch = new ArrayList<>();
            List<Double> roll = new ArrayList<>();
            List<Double> scale = new ArrayList<>();
            List<Double> angle = new ArrayList<>();
            List<Integer> deleted = new ArrayList<>();
            try {
                for (MeetingItem meetingItem : meetingItems) {
                    objectInstanceIds.add(meetingItem.getId());
                    /*layerIds.add(meetingItem.getMrml().getLayer().getId());*/
                    layerIds.add(meetingItem.getOrder());
                    objectIds.add(meetingItem.getObject().getId());
                    usernames.add(meetingItem.getInserterUser().getUserName());
                    posx.add(meetingItem.getPosX());
                    posy.add(meetingItem.getPosY());
                    posz.add(meetingItem.getPosZ());
                    yaw.add(meetingItem.getYaw());
                    pitch.add(meetingItem.getPitch());
                    roll.add(meetingItem.getRoll());
                    scale.add(meetingItem.getScale());
                    angle.add(meetingItem.getAngle());
                    deleted.add(meetingItem.getDeleted().ordinal());
                }
                result.put("ObjectInstanceIds", objectInstanceIds);
                result.put("LayerIds", layerIds);
                result.put("ObjectIds", objectIds);
                result.put("Usernames", usernames);
                result.put("PosX", posx);
                result.put("PosY", posy);
                result.put("PosZ", posz);
                result.put("Yaw", yaw);
                result.put("Pitch", pitch);
                result.put("Roll", roll);
                result.put("Scale", scale);
                result.put("Angle", angle);
                result.put("Deleted", deleted);
                rpcResponse.setResult(result);
                rpcResponse.setError(errorResponse);


            } catch (Exception e) {
                LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

            }
        } else {
            rpcResponse.setResult(null);
            rpcResponse.setError(errorResponse);
        }
        return rpcResponse;

    }

    public RpcResponse generateModifyMapObjectsResponse(TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ModifyMapObjects.ordinal());
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

    public RpcResponse generateDeleteMapObjectsResponse(TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.DeleteMapObjects.ordinal());
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

    public File getObject(RpcRequest rpcRequest) {
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleObjectId = Double.parseDouble((String) rpcRequest.getParams().get("ObjectId").toString());
            Double a = new Double(doubleObjectId);
            Integer objectId = a.intValue();
            Sessions session = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId));
            if (session != null) {
                Object object = findBy(Restrictions.eq(Object.ID, objectId));
                if (object != null) {
                    String finalObjPath = BASE_OBJECT_DIR + object.getFileName();
                    File file = new File(finalObjPath);
                    return file;


                } else if (object == null) {
                    return null;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
        return null;
    }

    public RpcResponse ListObjects(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                List<Object> objects = findAll();
                if (!objects.isEmpty()) {
                    rpcResponse = generateListObjcetsResponse(objects, TtErrors.NoError);
                } else {
                    rpcResponse = generateListObjcetsResponse(null, TtErrors.NoObjectFound);
                }
            } else {
                rpcResponse = generateListObjcetsResponse(null, TtErrors.SessionIsNull);

            }

        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
        return rpcResponse;
    }

    public RpcResponse listMapObjectChanges(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
            Double a = new Double(doubleMeetingId);
            Integer meetingId = a.intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId));
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), Meeting._MEETING_ITEMS);
                if (meeting != null) {
                    List<MeetingItem> objects = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Object)), MeetingItem._OBJECT, MeetingItem._INSERTER_USER);
                    rpcResponse = generateListMapObjectChangesResponse(objects, TtErrors.NoError);


                } else if (meeting == null) {
                    rpcResponse = generateListMapObjectChangesResponse(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateListMapObjectChangesResponse(null, TtErrors.SessionIsNull);

            }

        } catch (Exception e) {
            rpcResponse = generateListMapObjectChangesResponse(null, TtErrors.OperationalErrorOccured);

        }
        return rpcResponse;
    }

    public RpcResponse generateAddMapObjectResponse(MeetingItem meetingItem, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.AddMapObject.ordinal());
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError) {
            result.put("ObjectInstanceId", meetingItem.getId());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        } else {
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        }
        return rpcResponse;

    }


    public RpcResponse addMapObject(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        String DEFAULT_COMMENT = "no comment";

        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                double doubleMeetingId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
                Double a = new Double(doubleMeetingId);
                Integer meetingId = a.intValue();
                //
                Meeting meeting = meetingServiceImp.findBy(Restrictions
                    .and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted),
                        Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    double doubleLayerId = Double.parseDouble((String) rpcRequest.getParams().get("LayerId").toString());
                    Double b = new Double(doubleLayerId);
                    Integer layerId = b.intValue();

                    double doubleObjectId = Double.parseDouble((String) rpcRequest.getParams().get("ObjectId").toString());
                    Double c = new Double(doubleObjectId);
                    Integer objectId = c.intValue();

                    Object object = objectServiceImp.findBy(Restrictions.eq(Object.ID, objectId));
                    if (object != null) {
                        double posx = Double.parseDouble((String) rpcRequest.getParams().get("PosX").toString());
                        double posy = Double.parseDouble((String) rpcRequest.getParams().get("PosY").toString());
                        double posz = Double.parseDouble((String) rpcRequest.getParams().get("PosZ").toString());
                        double yaw = Double.parseDouble((String) rpcRequest.getParams().get("Yaw").toString());
                        double pitch = Double.parseDouble((String) rpcRequest.getParams().get("Pitch").toString());
                        double roll = Double.parseDouble((String) rpcRequest.getParams().get("Roll").toString());
                        double scale = Double.parseDouble((String) rpcRequest.getParams().get("Scale").toString());

                        MeetingItem meetingItem = new MeetingItem();
                        meetingItem.setPosX(posx);
                        meetingItem.setPosY(posy);
                        meetingItem.setPosZ(posz);
                        meetingItem.setYaw(yaw);
                        meetingItem.setPitch(pitch);
                        meetingItem.setRoll(roll);
                        meetingItem.setScale(scale);
                        meetingItem.setComment(DEFAULT_COMMENT);
                        meetingItem.setInserterUser(sessions.getServiceUser());
                        meetingItem.setMeeting(meeting);
                        meetingItem.setItemType(TtItemType.Object);
                        meetingItem.setObject(object);
                        meetingItem.setDeleted(TtMeetingItemDeleted.Undeleted);
                        meetingItem.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());

                        meetingItemServiceImp.save(meetingItem);
                        //rpcResponse.setId(TtGlobalId.Update.ordinal());

                        // here we insert
                        List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Object)), MeetingItem._OBJECT, MeetingItem._INSERTER_USER);
                        List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAPOBJECTS);
                        String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generateListMapObjectChangesResponse(changeMeetingItems, TtErrors.NoError));
                        BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());

                        rpcResponse = generateAddMapObjectResponse(meetingItem, TtErrors.NoError);
                    } else if (object == null) {
                        rpcResponse = generateAddMapObjectResponse(null, TtErrors.NoObjectFound);
                    }
                } else if (meeting == null) {
                    rpcResponse = generateAddMapObjectResponse(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateAddMapObjectResponse(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateAddMapObjectResponse(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse deleteMapObjects(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
/*
            String test = ((String) rpcRequest.getParams().get("ObjectInstanceIds").toString());
*/
            List<Double> objectInstanceIds = new ArrayList<Double>();
            objectInstanceIds = (List<Double>) rpcRequest.getParams().get("ObjectInstanceIds");
            /*Double[] doubleObjectInstanceId = Double[].parseDouble((String) rpcRequest.getParams().get("ObjectInstanceIds").toString());
            Double[] objectInstanceId = doubleObjectInstanceId;*/
            /*Double[] a = new Double[](doubleObjectInstanceId);
            Integer objectInstanceId = a.intValue();*/


            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId));
            if (sessions != null) {
                Meeting meeting = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted), Restrictions.eq(MeetingItem.ID, objectInstanceIds.get(0).intValue())), RePa.p__(MeetingItem._MEETING, Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM)).getMeeting();
                List<MeetingItem> meetingItems = new ArrayList<>();

                for (Double objectInstanceId : objectInstanceIds) {
                    MeetingItem meetingItem = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted), Restrictions.eq(MeetingItem.ID, objectInstanceId.intValue())), RePa.p__(MeetingItem._MEETING, Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));

                    if (meetingItem != null) {
                        meetingItems.add(meetingItem);

                    } else if (meetingItem == null) {
                        rpcResponse = generateDeleteMapObjectsResponse(TtErrors.Meeting_MeetingIsNull);

                    }

                }
                for (MeetingItem meetingItem : meetingItems) {
                    meetingItem.setDeleted(TtMeetingItemDeleted.Deleted);
                    meetingItemServiceImp.update(meetingItem);
                }
                rpcResponse.setId(TtGlobalId.Update.ordinal());
                List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAPOBJECTS);
                List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Object)), MeetingItem._OBJECT, MeetingItem._INSERTER_USER);
                String broadcastMessage;
                if (!changeMeetingItems.isEmpty()) {
                    broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generateListMapObjectChangesResponse(changeMeetingItems, TtErrors.NoError));
                } else {
                    rpcResponse.setId(TtGlobalId.Update.ordinal());
                    broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, rpcResponse);

                }
                BrokerUtils.broadcastMessage(broadcastMessage, meetingItems.get(0).getMeeting().getCurrentRoomMap().getRoom().getName());
                rpcResponse = generateDeleteMapObjectsResponse(TtErrors.NoError);

            } else {
                rpcResponse = generateDeleteMapObjectsResponse(TtErrors.SessionIsNull);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rpcResponse = generateDeleteMapObjectsResponse(TtErrors.OperationalErrorOccured);

        }
        return rpcResponse;
    }

    public RpcResponse modifyMapObjects(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            List<MeetingItem> meetingItems = new ArrayList<>();
            List<Double> objectInstanceIds = (List<Double>) rpcRequest.getParams().get("ObjectInstanceIds");
            List<Double> posxs = (List<Double>) rpcRequest.getParams().get("PosXs");
            List<Double> posys = (List<Double>) rpcRequest.getParams().get("PosYs");
            List<Double> poszs = (List<Double>) rpcRequest.getParams().get("PosZs");
            List<Double> yaws = (List<Double>) rpcRequest.getParams().get("Yaws");
            List<Double> pitchs = (List<Double>) rpcRequest.getParams().get("Pitchs");
            List<Double> rolls = (List<Double>) rpcRequest.getParams().get("Rolls");
            List<Double> scales = (List<Double>) rpcRequest.getParams().get("Scales");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            MeetingItem meetingItem = meetingItemServiceImp.findBy(Restrictions.eq(MeetingItem.ID, objectInstanceIds.get(0).intValue()),
                    RePa.p__(MeetingItem._MEETING, Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
            if (sessions != null && meetingItem != null) {
                if (true) {
                    for (int INDEX = 0; INDEX < objectInstanceIds.size(); INDEX++) {
                        MeetingItem changeItem = meetingItemServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingItem.ID, objectInstanceIds.get(INDEX).intValue()),
                                Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Object)));
                        if (changeItem != null) {
                            changeItem.setPosX(posxs.get(INDEX));
                            changeItem.setPosY(posys.get(INDEX));
                            changeItem.setPosZ(poszs.get(INDEX));
                            changeItem.setYaw(yaws.get(INDEX));
                            changeItem.setPitch(pitchs.get(INDEX));
                            changeItem.setRoll(rolls.get(INDEX));
                            changeItem.setScale(scales.get(INDEX));
                            meetingItemServiceImp.update(changeItem);
                        }

                    }
                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAPOBJECTS);
                    List<MeetingItem> changeMeetingItems = meetingItemServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meetingItem.getMeeting()),
                            Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Object)), MeetingItem._OBJECT, MeetingItem._INSERTER_USER);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags,
                            generateListMapObjectChangesResponse(changeMeetingItems, TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage, meetingItem.getMeeting().getCurrentRoomMap().getRoom().getName());
                    rpcResponse = generateModifyMapObjectsResponse(TtErrors.NoError);
                } else {
                    rpcResponse = generateModifyMapObjectsResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            } else if (sessions == null || meetingItem == null) {
                rpcResponse = generateModifyMapObjectsResponse(TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rpcResponse = generateModifyMapObjectsResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }

    public boolean addObject(RpcRequest rpcRequest, String objFilePath) {
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), RePa.p__(Sessions._SERVICE_USER, ServiceUser._LAST_ROOM));
            if (sessions != null) {
                Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, sessions.getServiceUser().getLastRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser())));
                if (roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUser)) {
                    String origObjNameWithExtension = new Utils().getOrigFileName(objFilePath);
                    double fileSize = new Utils().getFileSize(objFilePath);
                    //String name = rpcRequest.getParams().get();
                    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) rpcRequest.getParams().get("jsonheader");

                    //JsonObject jsonHeader =(JsonObject) rpcRequest.getParams().get("jsonheader");
                    //JsonObject jsonObject = new JsonParser().parse("kudjdwe").getAsJsonObject();
                    //////////////////
                    String name = (String) linkedTreeMap.get("Name");
                    String description = (String) linkedTreeMap.get("Description");
                    String category = (String) linkedTreeMap.get("Category");

                    Double doubleRoomId = (Double) linkedTreeMap.get("RoomId");
                    Double doubleAreaId = (Double) linkedTreeMap.get("Area");
                    Integer roomId = doubleRoomId.intValue();
                    Integer areaId = doubleAreaId.intValue();

                    TtObjectArea objectArea = TtObjectArea.getByOrdinal(areaId);
                    /*String name = jsonObject.get("Name").getAsString();
                    String description = jsonObject.get("Description").getAsString();
                    String category = jsonObject.get("Category").getAsString();
                    Integer roomId = jsonObject.get("RoomId").getAsInt();
                    Integer areaId = jsonObject.get("Area").getAsInt();

                    TtObjectArea objectArea = TtObjectArea.getByOrdinal(areaId);*/

                    ///
                    Object object = new Object();
                    object.setName(name);
                    object.setSize(fileSize);
                    object.setFileName(origObjNameWithExtension);
                    object.setUploaderUser(sessions.getServiceUser());
                    object.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
                    object.setCategory(category);
                    object.setArea(objectArea);
                    object.setDescription(description);
                    Set<Room> rooms = new HashSet<>();
                    if (roomId != 0) {
                        Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));
                        if (room != null) {
                            rooms.add(room);
                            object.setRooms(rooms);
                            object.setPrivacy(TtObjectPrivacy.PRIVATE);
                        } else {
                            return false;
                        }
                    } else {
                        object.setPrivacy(TtObjectPrivacy.PUBLIC);
                    }
                    save(object);
                    return true;
                }
            }
            return false;


        } catch (Exception e) {
            return false;
        }
    }


}
