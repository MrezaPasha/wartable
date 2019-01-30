package org.sadr.share.main.item.entity;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingService;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemService;
import org.sadr.share.main.meetingItem._types.TtItemType;
import org.sadr.share.main.meetingItem._types.TtMeetingItemDeleted;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserService;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EntityServiceImp extends GenericServiceImpl<Entity, EntityDao> implements EntityService {
    private SessionsService sessionsService;
    private MeetingService meetingService;
    private MeetingItemService meetingItemService;
    private Room_ServiceUserService roomServiceUserService;


    @Autowired
    public void setSessionsService(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Autowired
    public void setMeetingService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Autowired
    public void setMeetingItemService(MeetingItemService meetingItemService) {
        this.meetingItemService = meetingItemService;
    }

    @Autowired
    public void setRoomServiceUserService(Room_ServiceUserService roomServiceUserService) {
        this.roomServiceUserService = roomServiceUserService;
    }

    public RpcResponse generateAddMapEntity(MeetingItem meetingItem, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.AddMapEntity.ordinal());
        java.util.Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        if (error == TtErrors.NoError) {
            result.put("EntityInstanceId", meetingItem.getId());
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        } else {
            rpcResponse.setResult(result);
            rpcResponse.setError(errorResponse);
        }
        return rpcResponse;
    }

    public RpcResponse generatelistMapEntitiesResponse(List<MeetingItem> meetingItems, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListMapEntities.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        List<Long> entityInstanceIds = new ArrayList<>();
        List<Integer> layerIds = new ArrayList<>();
        List<Integer> entityTypeIds = new ArrayList<>();
        List<String> jsonString = new ArrayList<>();
        List<Integer> deleted = new ArrayList<>();


        try {
            if (error == TtErrors.NoError) {
                if (meetingItems == null) {
                    rpcResponse.setResult(result);
                    return rpcResponse;
                }
                for (MeetingItem meetingItem : meetingItems) {
                    entityInstanceIds.add(meetingItem.getId());
                    layerIds.add(meetingItem.getOrder());
                    entityTypeIds.add(meetingItem.getEntity().getType());
                    jsonString.add(meetingItem.getEntity().getJsonString());
                    deleted.add(meetingItem.getDeleted().ordinal());
                }
                result.put("EntityInstanceIds", entityInstanceIds);
                result.put("LayerIds", layerIds);
                result.put("EntityTypeIds", entityTypeIds);
                result.put("JsonString", jsonString);
                result.put("Deleted", deleted);
                rpcResponse.setResult(result);

            } else {
                rpcResponse.setResult(result);
            }

        } catch (Exception e) {
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);
            rpcResponse.setResult(null);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
        }
        return rpcResponse;
    }

    public RpcResponse addMapEntity(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        String DEFAULT_COMMENT = "default comment";
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsService.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
                Meeting meeting = meetingService.findBy(Restrictions.and(Restrictions.eq(Meeting.ID, meetingId), Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted)));
                if (meeting != null) {
                    Integer layerId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("LayerId").toString())).intValue();
                    Integer entityTypeId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("EntityTypeId").toString())).intValue();
                    String jsonString = (String) rpcRequest.getParams().get("JsonString");

                    // start insert entity
                    Entity entity = new Entity();
                    entity.setType(entityTypeId);
                    entity.setJsonString(jsonString);
                    entity.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                    save(entity);
                    // end insert entity
                    MeetingItem meetingItem = new MeetingItem();
                    meetingItem.setDeleted(TtMeetingItemDeleted.Undeleted);
                    meetingItem.setComment(DEFAULT_COMMENT);
                    meetingItem.setItemType(TtItemType.Entity);
                    meetingItem.setInserterUser(sessions.getServiceUser());
                    meetingItem.setMeeting(meeting);
                    meetingItem.setOrder(layerId);
                    meetingItem.setEntity(entity);
                    meetingItem.setCreateDateTime(ParsCalendar.getInstance().getShortDateTime());
                    meetingItemService.save(meetingItem);
                    rpcResponse = generateAddMapEntity(meetingItem, TtErrors.NoError);
                    // broadcast message
                    List<MeetingItem> meetingItems = meetingItemService.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Entity)), MeetingItem._ENTITY);
                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAP_ENTITY);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generatelistMapEntitiesResponse(meetingItems, TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());

                } else if (meeting == null) {
                    rpcResponse = generateAddMapEntity(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateAddMapEntity(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateAddMapEntity(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse generateDeleteEntitiesResponse(TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.DeleteMapEntities.ordinal());
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

    public RpcResponse generateModifyEntitiesResponse(TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ModifyMapEntity.ordinal());
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

    public RpcResponse listMapEntities(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsService.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            if (sessions != null) {
                Meeting meeting = meetingService.findBy(Restrictions.and(Restrictions.eq(Meeting.ID, meetingId), Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (roomServiceUserService.isUserHaveAdminPrivilages(roomServiceUserService.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser()))))) {
                    List<MeetingItem> meetingItems =
                            meetingItemService.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Entity)));
                    if (!meetingItems.isEmpty()) {
                        rpcResponse = generatelistMapEntitiesResponse(meetingItems, TtErrors.NoError);
                    } else {
                        rpcResponse = generatelistMapEntitiesResponse(null, TtErrors.NoError);
                    }
                } else {
                    rpcResponse = generatelistMapEntitiesResponse(null, TtErrors.PrivilageClassNotFound);
                }
            } else {
                rpcResponse = generatelistMapEntitiesResponse(null, TtErrors.SessionIsNull);
            }

        } catch (Exception e) {
            rpcResponse = generatelistMapEntitiesResponse(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse modifyEntities(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsService
                    .findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            if (sessions != null) {
                Meeting meeting = meetingService.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (roomServiceUserService.isUserHaveAdminPrivilages(roomServiceUserService.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser()))))) {
                    List<Double> entityInstanceIds = (List<Double>) rpcRequest.getParams().get("EntityInstanceIds");
                    List<String> jsonStrings = (List<String>) rpcRequest.getParams().get("JsonStrings");
                    List<Entity> entities = new ArrayList<>();
                    for (int INDEX = 0; INDEX < entityInstanceIds.size(); INDEX++) {
                        MeetingItem meetingItem = meetingItemService.findBy(Restrictions.eq(MeetingItem.ID, entityInstanceIds.get(INDEX).intValue()), MeetingItem._ENTITY);
                        if (meetingItem != null) {
                            entities.add(meetingItem.getEntity());
                        } else {
                            rpcResponse = generateModifyEntitiesResponse(TtErrors.NoEntityAssignForThisMeeting);
                            return rpcResponse;

                        }

                    }
                    /*for (Entity entity: entities)
                    {
                        update(entity);
                    }*/
                    for (int INDEX = 0; INDEX < entities.size(); INDEX++) {
                        Entity entity = entities.get(INDEX);
                        entity.setJsonString(jsonStrings.get(INDEX));
                        update(entity);
                    }
                    rpcResponse = generateModifyEntitiesResponse(TtErrors.NoError);
                    List<MeetingItem> meetingItems = meetingItemService.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Entity)), MeetingItem._ENTITY);
                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAP_ENTITY);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generatelistMapEntitiesResponse(meetingItems, TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());
                } else {
                    rpcResponse = generateModifyEntitiesResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            } else {
                rpcResponse = generateModifyEntitiesResponse(TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateModifyEntitiesResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }

    public RpcResponse deleteEntities(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsService.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Integer meetingId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            if (sessions != null) {
                Meeting meeting = meetingService.findBy(Restrictions.and(Restrictions.eq(Meeting.DELETED, TtMeetingDeleted.Undeleted), Restrictions.eq(Meeting.ID, meetingId)), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (roomServiceUserService.isUserHaveAdminPrivilages(roomServiceUserService.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser()))))) {
                    List<Double> entityInstanceIds = (List<Double>) rpcRequest.getParams().get("EntityInstanceIds");
                    List<MeetingItem> meetingItems = new ArrayList<>();
                    for (Double entityInstanceId : entityInstanceIds) {
                        MeetingItem meetingItem = meetingItemService.findBy(Restrictions.and(Restrictions.eq(MeetingItem.ID, entityInstanceId.intValue()), Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted)), MeetingItem._ENTITY);
                        if (meetingItem != null) {
                            meetingItems.add(meetingItem);
                        } else {
                            rpcResponse = generateDeleteEntitiesResponse(TtErrors.MeetingItemIdNotExist);
                            return rpcResponse;
                        }

                    }
                    for (MeetingItem meetingItem : meetingItems) {
                        meetingItem.setDeleted(TtMeetingItemDeleted.Deleted);
                        meetingItemService.update(meetingItem);
                    }
                    rpcResponse = generateDeleteEntitiesResponse(TtErrors.NoError);
                    List<MeetingItem> changedMeetingItems = meetingItemService.findAllBy(Restrictions.and(Restrictions.eq(MeetingItem._MEETING, meeting), Restrictions.eq(MeetingItem.DELETED, TtMeetingItemDeleted.Undeleted), Restrictions.eq(MeetingItem.ITEM_TYPE, TtItemType.Entity)), MeetingItem._ENTITY);
                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_MAP_ENTITY);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generatelistMapEntitiesResponse(changedMeetingItems, TtErrors.NoError));
                    BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());

                } else {
                    rpcResponse = generateDeleteEntitiesResponse(TtErrors.UserDoesntHaveEnoughPermission);
                }
            } else {
                rpcResponse = generateDeleteEntitiesResponse(TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateDeleteEntitiesResponse(TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }
}
