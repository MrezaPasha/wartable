package org.sadr.share.main.privateTalk;

import org.apache.xpath.operations.String;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingRecFile.MeetingRecFile;
import org.sadr.share.main.meetingRecFile.MeetingRecFileServiceImp;
import org.sadr.share.main.meetingRecFile._types.TtMeetingRecFileType;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.meetingSetting.MeetingSettingServiceImp;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PrivateTalkServiceImp extends GenericServiceImpl<PrivateTalk, PrivateTalkDao> implements PrivateTalkService {


    private RoomServiceImp roomServiceImp;
    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private ServiceUserServiceImp serviceUserServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    private MeetingRecFileServiceImp meetingRecFileServiceImp;
    private MeetingSettingServiceImp meetingSettingServiceImp;
    private BaseConfigServiceImp baseConfigServiceImp;


    @Autowired
    public void setBaseConfigServiceImp(BaseConfigServiceImp baseConfigServiceImp) {
        this.baseConfigServiceImp = baseConfigServiceImp;
    }

    @Autowired
    public void setMeetingSettingServiceImp(MeetingSettingServiceImp meetingSettingServiceImp) {
        this.meetingSettingServiceImp = meetingSettingServiceImp;
    }

    @Autowired
    public void setMeetingRecFileServiceImp(MeetingRecFileServiceImp meetingRecFileServiceImp) {
        this.meetingRecFileServiceImp = meetingRecFileServiceImp;
    }

    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }

    @Autowired
    public void setServiceUserServiceImp(ServiceUserServiceImp serviceUserServiceImp) {
        this.serviceUserServiceImp = serviceUserServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;

    }


    public RpcResponse generateGetPrivateVoiceArchiveResponse(List<PrivateTalk> privateTalks, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetPrivateVoiceArchive.ordinal());
        List<Long> ids = new ArrayList<>();
        List<java.lang.String> names = new ArrayList<>();
        List<java.lang.String> descs = new ArrayList<>();
        List<java.lang.String> startDateTimes = new ArrayList<>();
        List<java.lang.String> endDateTimes = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        if (error == TtErrors.NoError) {
            for (PrivateTalk privateTalk : privateTalks) {
                ids.add(privateTalk.getId());
                names.add("privateTalk_" + privateTalk.getId().toString());
                descs.add("");
                startDateTimes.add(privateTalk.getStartDateTime());
                endDateTimes.add(privateTalk.getEndDateTime());
            }
            result.put("Ids", ids);
            result.put("Names", names);
            result.put("Descs", descs);
            result.put("StartDateTimes", startDateTimes);
            result.put("EndDateTimes", endDateTimes);
            rpcResponse.setResult(result);
        } else {
            rpcResponse.setResult(result);
        }
        return rpcResponse;
    }


    public RpcResponse generateRequesterUserPrivateTalkDetails(ServiceUser requesterUser, PrivateTalk privateTalk) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.RequesterPrivateVoiceChat.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(TtErrors.NoError.ordinal());
        errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        result.put("UserId", requesterUser.getId());
        result.put("Name", requesterUser.getName());
        result.put("Family", requesterUser.getFamily());
        result.put("Username", requesterUser.getUserName());
        result.put("PrivateTalkId", privateTalk.getId());
        rpcResponse.setResult(result);
        return rpcResponse;
    }

    public RpcResponse generateRequestForPrivateVoiceChat(Long privateConfNumber, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.RequestForPrivateVoiceChat.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        if (privateConfNumber == null) {
            result.put("RoomId", new String());
        } else {
            result.put("RoomId", privateConfNumber);
        }
        rpcResponse.setResult(result);
        return rpcResponse;

    }

    public RpcResponse generateAcceptRequesterPrivateVoiceChatRequest(Long privateConfNumber, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.AcceptRequesterPrivateVoiceChatRequest.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        if (privateConfNumber == null) {
            result.put("RoomId", new String());
        } else {
            result.put("RoomId", privateConfNumber);
        }
        rpcResponse.setResult(result);
        return rpcResponse;

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

    public RpcResponse generateGetPublicVoiceArchiveResponse(List<MeetingSetting> meetingSettings, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        List<Long> ids = new ArrayList<>();
        List<java.lang.String> names = new ArrayList<>();
        List<java.lang.String> descs = new ArrayList<>();
        List<java.lang.String> startDateTimes = new ArrayList<>();
        List<java.lang.String> endDateTimes = new ArrayList<>();


        rpcResponse.setId(TtGlobalId.GetPublicVoiceArchive.ordinal());
        Map result = new HashMap();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        if (error == TtErrors.NoError) {
            for (MeetingSetting meetingSetting : meetingSettings) {
                ids.add(meetingSetting.getId());
                names.add(meetingSetting.getName());
                descs.add(meetingSetting.getDescription());
                startDateTimes.add(meetingSetting.getStartDateTime());
                endDateTimes.add(meetingSetting.getEndDateTime());
            }

            result.put("Ids", ids);
            result.put("Names", names);
            result.put("Descs", descs);
            result.put("StartDateTimes", startDateTimes);
            result.put("EndDateTimes", endDateTimes);
            rpcResponse.setResult(result);
        } else {
            rpcResponse.setResult(result);
        }
        return rpcResponse;

    }


    public RpcResponse generateListVoiceChatUsers(List<ServiceUser> serviceUsers, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.ListVoiceChatUsers.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        List<java.lang.String> names = new ArrayList<>();
        List<java.lang.String> families = new ArrayList<>();
        List<java.lang.String> usernames = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        Map result = new HashMap();
        if (error == TtErrors.NoError) {
            for (ServiceUser serviceUser : serviceUsers) {
                userIds.add(serviceUser.getId());
                names.add(serviceUser.getName());
                families.add(serviceUser.getFamily());
                usernames.add(serviceUser.getUserName());
            }
            result.put("UserIds", userIds);
            result.put("Names", names);
            result.put("Families", families);
            result.put("Usernames", usernames);
            rpcResponse.setResult(result);

        } else {
            rpcResponse.setResult(result);
        }
        return rpcResponse;
    }


    public RpcResponse generateRequestForJoiningPublicVoiceChat(Room room, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.RequestForJoiningPublicVoiceChat.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        if (error == TtErrors.NoError) {
            result.put("RoomId", room.getId());
        } else {
            result.put("RoomId", new Room());
        }
        rpcResponse.setResult(result);
        return rpcResponse;
    }

    public RpcResponse generateGetMapSessionTimeStartDateTimeResponse(MeetingSetting meetingSetting, TtErrors error) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetMapSessionTimeStartDateTime.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.ordinal());
        errorResponse.setDescription(error.getErrorValueService());
        rpcResponse.setError(errorResponse);
        Map result = new HashMap();
        if (error == TtErrors.NoError) {
            result.put("SessionStartDateTime", meetingSetting.getStartDateTime());
        } else {
            result.put("SessionStartDateTime", new RpcResponse());
        }
        rpcResponse.setResult(result);
        return rpcResponse;
    }


    public Boolean isMeetingIsCurrentlyRunning(Meeting meeting) {
        try {
            MeetingSetting meetingSetting = meetingSettingServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingSetting._MEETING, meeting), Restrictions.isNull(MeetingSetting.END_DATE_TIME)));
            if (meetingSetting != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;

        }
    }


    public RpcResponse requestForPrivateVoiceChat(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            //List<ServiceUser> serviceUsers = new ArrayList<>();
            Set<ServiceUser> serviceUsers = new HashSet<>();
            List<Double> userIds = new ArrayList<Double>();
            userIds = (List<Double>) rpcRequest.getParams().get("UserIds");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    for (Double userId : userIds) {
                        ServiceUser serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID, userId.intValue()));
                        if (serviceUser != null) {
                            serviceUsers.add(serviceUser);
                        } else {
                            rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.UserDoesntexist);
                            return rpcResponse;
                        }
                    }
                    PrivateTalk privateTalk = new PrivateTalk();
                    privateTalk.setMeeting(meeting);
                    privateTalk.setRequestUser(sessions.getServiceUser());
                    privateTalk.setServiceUsers(serviceUsers);
                    java.lang.String creationPrivateTalkDateTime = ParsCalendar.getInstance().getShortDateTime();
                    privateTalk.setStartDateTime(creationPrivateTalkDateTime);
                    //java.lang.String privateConfNumber = Utils.generateUUID();
                    save(privateTalk);
                    PrivateTalk savedPrivateCall = findBy(Restrictions.and(Restrictions.eq(PrivateTalk.CREATE_DATE_TIME, creationPrivateTalkDateTime), Restrictions.eq(PrivateTalk._MEETING, meeting)));
                    if (!Utils.createVoipConferenceBridgeById(savedPrivateCall.getId().toString())) {
                        rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.OperationalErrorOccured);
                        delete(savedPrivateCall);
                        return rpcResponse;
                    }
                    java.lang.String savedFileName = Utils.startRecord(savedPrivateCall.getId().toString());
                    if (savedFileName.isEmpty()) {
                        rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.OperationalErrorOccured);
                        delete(savedPrivateCall);
                        return rpcResponse;
                    }
                    savedPrivateCall.setFileName(savedFileName);
                    Set<ServiceUser> joinedServiceUser = new HashSet<>();
                    joinedServiceUser.add(sessions.getServiceUser());
                    savedPrivateCall.setJoinedServiceUSer(joinedServiceUser);
                    update(savedPrivateCall);
                    /*PrivateTalk insertedPrivateTalk = findBy(Restrictions.and(Restrictions.eq(PrivateTalk._MEETING,meeting),Restrictions.eq(PrivateTalk._REQUEST_USER,sessions.getServiceUser()),Restrictions.eq(PrivateTalk.CALL_NUMBER,privateConfNumber)));*/
                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_REQUESTPRIVATE);
                    java.lang.String unicastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generateRequesterUserPrivateTalkDetails(sessions.getServiceUser(), savedPrivateCall));
                    for (ServiceUser serviceUser : serviceUsers) {
                        Sessions invokeUserSession = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER, serviceUser));
                        if (invokeUserSession != null) {
                            BrokerUtils.unicastMessage(unicastMessage, invokeUserSession.getSessionId());
                        }
                    }
                    rpcResponse = generateRequestForPrivateVoiceChat(savedPrivateCall.getId(), TtErrors.NoError);

                } else {
                    rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.SessionIsNull);
            }

        } catch (Exception e) {
            rpcResponse = generateRequestForPrivateVoiceChat(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }


    public RpcResponse notifyJoiningPrivateVoiceChat(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double meetingId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString());
            double privateTalkId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("PrivateTalkId").toString());

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    PrivateTalk privateTalk = findBy(Restrictions.and(Restrictions.eq(PrivateTalk._MEETING, meeting), Restrictions.eq(PrivateTalk.ID, privateTalkId)));
                    if (privateTalk != null) {
                        java.lang.String recordFileName = Utils.startRecord(privateTalk.getId().toString());
                        privateTalk.setFileName(recordFileName);
                        update(privateTalk);
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.NotifyJoiningPrivateVoiceChat, TtErrors.NoError);
                    } else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.NotifyJoiningPrivateVoiceChat, TtErrors.NoPrivateTalkExist);
                    }
                } else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.NotifyJoiningPrivateVoiceChat, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.NotifyJoiningPrivateVoiceChat, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.NotifyJoiningPrivateVoiceChat, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse acceptRequesterPrivateVoiceChatRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double meetingId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString());
            double privateTalkId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("PrivateTalkId").toString());

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    PrivateTalk privateTalk = findBy(Restrictions.and(Restrictions.eq(PrivateTalk._MEETING, meeting), Restrictions.eq(PrivateTalk.ID, privateTalkId)));
                    if (privateTalk != null) {
                        Set<ServiceUser> currentPrivateTalkUsers = privateTalk.getJoinedServiceUSer();
                        if (!currentPrivateTalkUsers.contains(sessions.getServiceUser())) {
                            Set<ServiceUser> joinedServiceUserList = privateTalk.getJoinedServiceUSer();
                            joinedServiceUserList.add(sessions.getServiceUser());
                            privateTalk.setJoinedServiceUSer(joinedServiceUserList);
                            update(privateTalk);
                            rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(privateTalk.getId(), TtErrors.NoError);
                            // broadcast message

                            List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_PRIVATE_VOICECHAT_LIST);

                            java.lang.String unicastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generateListVoiceChatUsers(new ArrayList<>(currentPrivateTalkUsers), TtErrors.NoError));
                            for (ServiceUser serviceUser : currentPrivateTalkUsers) {
                                Sessions unicatUserSession = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER, serviceUser));
                                BrokerUtils.unicastMessage(unicastMessage, meeting.getCurrentRoomMap().getRoom().getName());
                            }

                            // End broadcast message

                        } else {
                            rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(null, TtErrors.UserIsCurrentlyAddedToPrivateTalk);

                        }
                    } else {
                        rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(null, TtErrors.NoPrivateTalkExist);
                    }
                } else {
                    rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateAcceptRequesterPrivateVoiceChatRequest(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }


    public RpcResponse requestForJoiningPublicVoiceChat(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Integer roomId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("RoomId").toString())).intValue();
            Integer privateTalkId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("privateTalkId").toString())).intValue();
            //double roomId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("RoomId").toString());
            //double privateTalkId = Double.parseDouble((java.lang.String) rpcRequest.getParams().get("PrivateTalkId").toString());

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                PrivateTalk privateTalk = findBy(Restrictions.eq(PrivateTalk.ID, privateTalkId));
                if (privateTalk != null) {
                    Set<ServiceUser> serviceUsers = privateTalk.getJoinedServiceUSer();
                    if (serviceUsers.contains(sessions.getServiceUser())) {
                        serviceUsers.remove(sessions.getServiceUser());
                        if (serviceUsers.isEmpty()) {
                            Utils.stopRecord(privateTalkId.toString());
                        }
                        privateTalk.setJoinedServiceUSer(serviceUsers);
                        update(privateTalk);

                        // get the room
                        Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));
                        if (room != null) {
                            List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_PRIVATE_VOICECHAT_LIST);

                            java.lang.String unicastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, generateListVoiceChatUsers(new ArrayList<>(serviceUsers), TtErrors.NoError));
                            for (ServiceUser serviceUser : serviceUsers) {
                                Sessions unicatUserSession = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER, serviceUser));
                                BrokerUtils.unicastMessage(unicastMessage, unicatUserSession.getSessionId());
                            }
                            rpcResponse = generateRequestForJoiningPublicVoiceChat(room, TtErrors.NoError);
                        } else {
                            rpcResponse = generateRequestForJoiningPublicVoiceChat(null, TtErrors.RoomDoesntExist);
                        }
                    } else {
                        rpcResponse = generateRequestForJoiningPublicVoiceChat(null, TtErrors.PrivateTalkDoesntHaveSpecificUser);
                    }
                } else {
                    rpcResponse = generateRequestForJoiningPublicVoiceChat(null, TtErrors.PrivateTalkDoesntExist);
                }
            } else {
                rpcResponse = generateRequestForJoiningPublicVoiceChat(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateRequestForJoiningPublicVoiceChat(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }


    public RpcResponse startMapSessionTime(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            Integer saveSoundInteger = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("SaveSound").toString())).intValue();
            java.lang.String name = (java.lang.String) rpcRequest.getParams().get("Name");
            Boolean saveSound;
            if (saveSoundInteger == 1)
                saveSound = true;
            else
                saveSound = false;

            //Boolean saveSound = (Boolean) rpcRequest.getParams().get("SaveSound");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser())));
                    if (roomServiceUser != null && roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUser)) {
                        MeetingSetting meetingSetting = new MeetingSetting();
                        meetingSetting.setName(name);
                        meetingSetting.setStartDateTime(ParsCalendar.getInstance().getShortDateTime());
                        meetingSetting.setMeeting(meeting);
                        MeetingRecFile meetingRecFile = new MeetingRecFile();
                        Set<MeetingRecFile> meetingRecFiles = new HashSet<>();
                        if (saveSound) {
                            java.lang.String recFileName = Utils.startRecord(meeting.getCurrentRoomMap().getRoom().getId().toString());
                            if (!recFileName.isEmpty()) {
                                meetingRecFile.setFileName(recFileName);
                                meetingRecFile.setFileType(TtMeetingRecFileType.Audio);
                                meetingRecFileServiceImp.save(meetingRecFile);
                                meetingRecFiles.add(meetingRecFile);
                                meetingSetting.setRecFiles(meetingRecFiles);
                                meetingSettingServiceImp.save(meetingSetting);

                                // broadcast to all the user of the room that start session
                                List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS__UPDATE_START_MAPSESSIONTIME);
                                java.lang.String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, new RpcResponse());
                                BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());
                            } else {
                                rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.OperationalErrorOccured);
                            }

                        } else {
                            meetingSetting.setRecFiles(null);
                            meetingSettingServiceImp.save(meetingSetting);
                            // broadcast to all the user of the room that start session
                            List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS__UPDATE_START_MAPSESSIONTIME);
                            java.lang.String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, new RpcResponse());
                            BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());
                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.NoError);

                        }
                    } else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.UserDoesntHaveEnoughPermission);
                    }
                } else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.StartMapSessionTime, TtErrors.SessionIsNull);

        }

        return rpcResponse;

    }

    public RpcResponse getMapSessionTimeStartDateTime(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            //Boolean saveSound = (Boolean) rpcRequest.getParams().get("SaveSound");


            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser())));
                    if (roomServiceUser != null && roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUser)) {
                        MeetingSetting meetingSetting = meetingSettingServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingSetting._MEETING, meeting), Restrictions.isNull(MeetingSetting.END_DATE_TIME)));
                        rpcResponse = generateGetMapSessionTimeStartDateTimeResponse(meetingSetting, TtErrors.NoError);

                    } else {
                        rpcResponse = generateGetMapSessionTimeStartDateTimeResponse(null, TtErrors.UserDoesntHaveEnoughPermission);
                    }
                } else {
                    rpcResponse = generateGetMapSessionTimeStartDateTimeResponse(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGetMapSessionTimeStartDateTimeResponse(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGetMapSessionTimeStartDateTimeResponse(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }


    public RpcResponse stopMapSessionTime(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, meeting.getCurrentRoomMap().getRoom()), Restrictions.eq(Room_ServiceUser._SERVICE_USER, sessions.getServiceUser())));
                    if (roomServiceUser != null && roomServiceUserServiceImp.isUserHaveAdminPrivilages(roomServiceUser)) {

                        MeetingSetting meetingSetting = meetingSettingServiceImp.findBy(Restrictions.and(Restrictions.eq(MeetingSetting._MEETING, meeting), Restrictions.isNull(MeetingSetting.END_DATE_TIME)));
                        if (meetingSetting != null) {
                            Utils.stopRecord(meeting.getCurrentRoomMap().getRoom().getId().toString());
                            meetingSetting.setEndDateTime(ParsCalendar.getInstance().getShortDateTime());
                            meetingSettingServiceImp.update(meetingSetting);
                            // broadcst to all member of the meeting that meeting stoped
                            List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS__UPDATE_STOP_MAPSESSIONTIME);
                            java.lang.String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, new RpcResponse());
                            BrokerUtils.broadcastMessage(broadcastMessage, meeting.getCurrentRoomMap().getRoom().getName());

                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.NoError);
                        } else {
                            rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.NoMeetingExistForStoping);
                        }
                    } else {
                        rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.UserDoesntHaveEnoughPermission);
                    }
                } else {
                    rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGenericSuccessResponse(TtGlobalId.StopMapSessionTime, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }

    public RpcResponse getPublicVoiceArchive(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM, Room._ROOM_SERVICEUSER, Room_ServiceUser._SERVICE_USER));
                if (meeting != null) {
                    List<MeetingSetting> meetingSettings = meetingSettingServiceImp.findAllBy(Restrictions.and(Restrictions.eq(MeetingSetting._MEETING, meeting), Restrictions.isNotNull(MeetingSetting.END_DATE_TIME), Restrictions.isNotNull(MeetingSetting.START_DATE_TIME)), MeetingSetting._REC_FILES);
                    if (!meetingSettings.isEmpty()) {
                        rpcResponse = generateGetPublicVoiceArchiveResponse(meetingSettings, TtErrors.NoError);
                    } else {
                        rpcResponse = generateGetPublicVoiceArchiveResponse(null, TtErrors.NoRecordedArchiveForThisMeeting);
                    }
                } else {
                    rpcResponse = generateGetPublicVoiceArchiveResponse(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGetPublicVoiceArchiveResponse(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGetPublicVoiceArchiveResponse(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;
    }

    public RpcResponse getPrivateVoiceArchive(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        List<PrivateTalk> finalPrivateTalks = new ArrayList<>();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM, Room._ROOM_SERVICEUSER, Room_ServiceUser._SERVICE_USER));
                if (meeting != null) {
                    List<PrivateTalk> privateTalks = findAllBy(Restrictions.and(Restrictions.eq(PrivateTalk._MEETING, meeting), Restrictions.isNotNull(PrivateTalk.END_DATE_TIME), Restrictions.isNotNull(PrivateTalk.START_DATE_TIME)), PrivateTalk._SERVICE_USERS);
                    if (!privateTalks.isEmpty()) {
                        for (PrivateTalk privateTalk : privateTalks) {
                            Set<ServiceUser> serviceUsers = privateTalk.getServiceUsers();
                            if (serviceUsers.contains(sessions.getServiceUser())) {
                                finalPrivateTalks.add(privateTalk);
                            }
                        }
                        rpcResponse = generateGetPrivateVoiceArchiveResponse(finalPrivateTalks, TtErrors.NoError);
                    } else {
                        rpcResponse = generateGetPrivateVoiceArchiveResponse(null, TtErrors.NoRecordedArchiveForThisMeeting);
                    }
                } else {
                    rpcResponse = generateGetPrivateVoiceArchiveResponse(null, TtErrors.Meeting_MeetingIsNull);
                }
            } else {
                rpcResponse = generateGetPrivateVoiceArchiveResponse(null, TtErrors.SessionIsNull);
            }
        } catch (Exception e) {
            rpcResponse = generateGetPrivateVoiceArchiveResponse(null, TtErrors.OperationalErrorOccured);
        }
        return rpcResponse;

    }


    public RpcResponse getItemArchiveVoiceChat(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        java.lang.String finalUrl;
        List<PrivateTalk> finalPrivateTalks = new ArrayList<>();
        try {
            java.lang.String sessionId = (java.lang.String) rpcRequest.getParams().get("SessionId");
            Integer meetingId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("MapSessionId").toString())).intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            Boolean isPrivate;
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            if (sessions != null) {
                Meeting meeting = meetingServiceImp.findBy(Restrictions.eq(Meeting.ID, meetingId), RePa.p__(Meeting._CURRENT_ROOM_MAP, Room_Map._ROOM));
                if (meeting != null) {
                    Integer itemId = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("ItemId").toString())).intValue();
                    Integer isPrivateInteger = new Double(Double.parseDouble((java.lang.String) rpcRequest.getParams().get("IsPrivate").toString())).intValue();
                    if (isPrivateInteger == 1)
                        isPrivate = true;
                    else
                        isPrivate = false;
                    if (isPrivate) {
                        PrivateTalk privateTalk = findBy(Restrictions.eq(PrivateTalk.ID, itemId));
                        if (privateTalk != null) {
                            finalUrl = "http://" + baseConfigs.get(TtConfig.VoipServerIp.ordinal()) + ":" + baseConfigs.get(TtConfig.VoipServerRestPort.ordinal()) + "/download?name=" + privateTalk.getFileName();
                        } else {

                        }
                    }
                }


            }

        } catch (Exception e) {

        }
        return null;


    }
}



