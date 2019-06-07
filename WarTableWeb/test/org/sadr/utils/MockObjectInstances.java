package org.sadr.utils;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.utils.CodeGenerator;
import org.sadr._core.utils.Digester;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr._core.utils._type.TtUsernameType;
import org.sadr.share.main._types.*;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigShareService;
import org.sadr.share.main.baseErrors.BaseErrors;
import org.sadr.share.main.baseErrors.BaseErrorsShareService;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogShareService;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeShareService;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object.ObjectShareService;
import org.sadr.share.main.item.object._types.TtObjectArea;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.layer.LayerShareService;
import org.sadr.share.main.layer._type.TtLayerStatus;
import org.sadr.share.main.layer._type.TtLayerType;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.serviceLog.ServiceLog;
import org.sadr.share.main.log.models.serviceLog.ServiceLogShareService;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapShareService;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingShareService;
import org.sadr.share.main.meeting._types.TtMeetingDeleted;
import org.sadr.share.main.meeting._types.TtMeetingStatus;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.meetingSetting.MeetingSettingShareService;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.orgPosition.OrgPositionShareService;
import org.sadr.share.main.personModel.PersonModel;
import org.sadr.share.main.personModel.PersonModelShareService;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.privateTalk.PrivateTalkShareService;
import org.sadr.share.main.privateTalk._types.TtPrivateTalkStatus;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomShareService;
import org.sadr.share.main.room._types.TtRoomAccessSetting;
import org.sadr.share.main.room._types.TtRoomRecSetting;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareService;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserEnablePrivateCommunication;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserPresence;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserShareService;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsShareService;
import org.sadr.share.main.startupNotice.startupNotice.StartupNotice;
import org.sadr.share.main.startupNotice.startupNotice.StartupNoticeShareService;
import org.sadr.share.main.textChat.TextChat;
import org.sadr.share.main.textChat.TextChatShareService;
import org.sadr.share.main.textChat._types.TtTextChatSendStatus;
import org.sadr.share.main.textChat._types.TtTextChatType;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.uiBag.UiBag;
import org.sadr.web.main._core.uiBag.UiBagService;
import org.sadr.web.main.admin._type.TtGender;
import org.sadr.web.main.admin._type.TtUserIpRangeType;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin._type.TtUserStatus;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupService;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.note._type.TtNoteImportance;
import org.sadr.web.main.note.note.Note;
import org.sadr.web.main.note.note.NoteService;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.backup.Backup;
import org.sadr.web.main.system.backup.BackupService;
import org.sadr.web.main.system.irror.irror.Irror;
import org.sadr.web.main.system.irror.irror.IrrorService;
import org.sadr.web.main.system.log.general.Log;
import org.sadr.web.main.system.log.general.LogService;
import org.sadr.web.main.system.log.remote.RemoteLog;
import org.sadr.web.main.system.log.remote.RemoteLogService;
import org.sadr.web.main.system.log.signin.SigninLog;
import org.sadr.web.main.system.log.signin.SigninLogService;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockObjectInstances {

    public static final String _ADMIN_USER_NAME = "admin2";
    public static final String _ADMIN_PASSWORD = "admin2";
    public static final String _LOGMANAGER_USER_NAME = "security";
    public static final String _LOGMANAGER_PASSWORD = "security";
    private static MockObjectInstances instance = new MockObjectInstances();


    private MockObjectInstances() {
    }

    public static MockObjectInstances getInstance() {
        return instance;
    }


    //=================================
    public String getListAP() {
        return "{" +
                "\"c\":\"\"," +
                "\"o\":[]," +
                "\"df\":true," +
                "\"pi\":1," +
                "\"ps\":5," +
                "\"pr\":2," +
                "\"list\":[]" +
                "}";
    }

    //=================================

    public User getClientUser() {
        return getClientUser(false);
    }

    public User getClientUser(boolean isRandomUsername) {
        User user = new User();
        user.setEntityState(TtEntityState.Exist);
        user.setUsername("client" + (isRandomUsername ? "_" + CodeGenerator.username(TtUsernameType.LittleBigChar, 8) : ""));
        user.setGender(TtGender.Male);
        user.setFirstName("نام" + (isRandomUsername ? "_" + CodeGenerator.username(TtUsernameType.NumberOnly, 4) : ""));
        user.setLastName("فامیل" + (isRandomUsername ? "_" + CodeGenerator.username(TtUsernameType.NumberOnly, 4) : ""));
        user.setLevel(TtUserLevel.Client);
        user.setPassword(Digester.digestSHA1("client"));
        user.setPasswordDateTime(ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Hour, -2));
        user.setStatus(TtUserStatus.Active);
        user.setIpRangeType(TtUserIpRangeType.All);
        user.setIsNeedToChangePassword(false);
        return user;
    }
    //=================================

    //============================ BaseConfig

    public BaseConfig getBaseConfig(boolean isRandom) {
        BaseConfig obj = new BaseConfig();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setConfigName("configName_" + rand);
        obj.setConfigValue("configValue_" + rand);

        obj.setConfigId(null);
        return obj;
    }

    public BaseConfig getRealBaseConfig() {
        BaseConfigShareService service = WebConfigHandler.getWebApplicationContext().getBean(BaseConfigShareService.class);
        List<BaseConfig> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getBaseConfig(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ BaseErrors

    public BaseErrors getBaseErrors(boolean isRandom) {
        BaseErrors obj = new BaseErrors();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setErrorValue("errorValue_" + rand);
        obj.setErrorDescription("errorDescription_" + rand);

        obj.setErrorId(null);
        return obj;
    }

    public BaseErrors getRealBaseErrors() {
        BaseErrorsShareService service = WebConfigHandler.getWebApplicationContext().getBean(BaseErrorsShareService.class);
        List<BaseErrors> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getBaseErrors(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ CriticalLog

    public CriticalLog getCriticalLog(boolean isRandom) {
        CriticalLog obj = new CriticalLog();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setLogDetailPosition("logDetailPosition_" + rand);
        obj.setLogDetailMessage("logDetailMessage_" + rand);
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        return obj;
    }

    public CriticalLog getRealCriticalLog() {
        CriticalLogShareService service = WebConfigHandler.getWebApplicationContext().getBean(CriticalLogShareService.class);
        List<CriticalLog> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getCriticalLog(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Grade

    public Grade getGrade(boolean isRandom) {
        Grade obj = new Grade();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setValue("value_" + rand);
        obj.setCode(111 + rand);

        return obj;
    }

    public Grade getRealGrade() {
        GradeShareService service = WebConfigHandler.getWebApplicationContext().getBean(GradeShareService.class);
        List<Grade> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getGrade(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Object

    public Object getObject(boolean isRandom) {
        Object obj = new Object();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setSize(111 + rand);
        obj.setDescription("description_" + rand);
        obj.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setFileName("fileName_" + rand);
        obj.setCategory("category_" + rand);

        obj.setUploaderUser(null);
        obj.setArea(TtObjectArea.All);
        return obj;
    }

    public Object getRealObject() {
        ObjectShareService service = WebConfigHandler.getWebApplicationContext().getBean(ObjectShareService.class);
        List<Object> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getObject(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Layer

    public Layer getLayer(boolean isRandom) {
        Layer obj = new Layer();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setType("type_" + rand);
        obj.setDescription("description_" + rand);
        obj.setOrder(111 + rand);
        obj.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setFileName("fileName_" + rand);
        obj.setSamplesHeight(111 + rand);
        obj.setTileCountVert(111 + rand);
        obj.setMaxHeight(111 + rand);
        obj.setSamplesWidth(111 + rand);
        obj.setTileCountHorz(111 + rand);
        obj.setBounds("bounds_" + rand);
        obj.setMinHeight(111 + rand);
        obj.setSpacingHorz(111 + rand);
        obj.setSpacingVert(111 + rand);
        obj.setSpacingHeight(111 + rand);

        obj.setMap(getRealMap());
        obj.setUploaderUser(getRealServiceUser());
        obj.setMrmls(null);
        obj.setLayerStatus(TtLayerStatus.Active);
        obj.setLayerType(TtLayerType.Default);
        return obj;
    }

    public Layer getRealLayer() {
        LayerShareService service = WebConfigHandler.getWebApplicationContext().getBean(LayerShareService.class);
        List<Layer> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getLayer(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ ServiceLog

    public ServiceLog getServiceLog(boolean isRandom) {
        ServiceLog obj = new ServiceLog();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setDescription("description_" + rand);
        obj.setUsername("username_" + rand);
        obj.setServerHostname("serverHostname_" + rand);
        obj.setSubTypeDescription("subTypeDescription_" + rand);
        obj.setSoftwareVersion("softwareVersion_" + rand);
        obj.setClientHostname("clientHostname_" + rand);
        obj.setUrl("url_" + rand);
        obj.setPortNumber("portNumber_" + rand);
        obj.setClientIP("clientIP_" + rand);
        obj.setSoftwareName("softwareName_" + rand);
        obj.setSoftwareID("softwareID_" + rand);
        obj.setServerIP("serverIP_" + rand);
        obj.setPageTitle("pageTitle_" + rand);
        obj.setUserUniqueID("userUniqueID_" + rand);
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setSensitivity(TtSensitivity.Alarm);
        obj.setActionType(TtActionType.Add);
        obj.setFlag(TtFlag.Success);
        obj.setSubType(TtSubType.AddGroup);
        obj.setImportance(TtImportance.HighImportance);
        return obj;
    }

    public ServiceLog getRealServiceLog() {
        ServiceLogShareService service = WebConfigHandler.getWebApplicationContext().getBean(ServiceLogShareService.class);
        List<ServiceLog> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getServiceLog(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Map

    public Map getMap(boolean isRandom) {
        Map obj = new Map();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setFileName("fileName_" + rand);
        obj.setFileSize(111 + rand);
        obj.setDescriptions("descriptions_" + rand);
        obj.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setAssignRooms(null);
        obj.setLayers(null);
        obj.setCategory(TtMapCategory.WarMap);
        return obj;
    }

    public Map getRealMap() {
        MapShareService service = WebConfigHandler.getWebApplicationContext().getBean(MapShareService.class);
        List<Map> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getMap(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    //============================ Meeting

    public void getPrivateTalk(Meeting meeting) {
        PrivateTalkShareService service = WebConfigHandler.getWebApplicationContext().getBean(PrivateTalkShareService.class);

        PrivateTalk privateTalk = new PrivateTalk();
        Set<ServiceUser> set = new HashSet<>();
        set.add(getRealServiceUser(1));
        set.add(getRealServiceUser(2));
        set.add(getRealServiceUser(3));
        set.add(getRealServiceUser(4));
        privateTalk.setJoinedServiceUsers(set);
        privateTalk.setSize(122);
        privateTalk.setMeeting(meeting);
        privateTalk.setEndDateTime(ParsCalendar.getInstance().getShortDateTime());
        privateTalk.setStartDateTime(ParsCalendar.getInstance().getShortDateTime());
        privateTalk.setFileName("001.mp3");
        privateTalk.setRequestUser(getRealServiceUser(4));
        privateTalk.setServiceUsers(set);

        service.save(privateTalk);
    }

    public void getPublicTalk(Meeting meeting) {
        MeetingSettingShareService service = WebConfigHandler.getWebApplicationContext().getBean(MeetingSettingShareService.class);

        MeetingSetting meetingSetting = new MeetingSetting();
        Set<ServiceUser> set = new HashSet<>();
        set.add(getRealServiceUser(1));
        set.add(getRealServiceUser(2));
        set.add(getRealServiceUser(3));
        set.add(getRealServiceUser(4));
        meetingSetting.setSoundRecFileName("002.mp3");
        meetingSetting.setSoundRecFileSize(122);
        meetingSetting.setMeeting(meeting);
        meetingSetting.setEndDateTime(ParsCalendar.getInstance().getShortDateTime());
        meetingSetting.setStartDateTime(ParsCalendar.getInstance().getShortDateTime());
        meetingSetting.setDescription("توضیحات مربوطه ....");

        service.save(meetingSetting);
    }

    public Meeting getMeeting(boolean isRandom) {
        Meeting obj = new Meeting();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setResult("result_" + rand);
        obj.setDescription("description_" + rand);
        obj.setGoal("goal_" + rand);
        obj.setBoardText("boardText_" + rand);
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setStatus(TtMeetingStatus.Active);
        obj.setMrmls(null);
        obj.setMeetingItems(null);
        obj.setMeetingLogs(null);
        obj.setInvites(null);
        obj.setPrivateTalks(null);
        obj.setDeleted(TtMeetingDeleted.Undeleted);
        obj.setMeetingSettings(null);
        obj.setCurrentRoomMap(null);
        obj.setRoom(getRealRoom());
        return obj;
    }

    public Meeting getRealMeeting() {
        MeetingShareService service = WebConfigHandler.getWebApplicationContext().getBean(MeetingShareService.class);
        List<Meeting> list = service.findAll(1, Meeting._ROOM);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            Meeting meeting = getMeeting(true);
            service.save(meeting);
            getPrivateTalk(meeting);
            getPublicTalk(meeting);
            return meeting;
        }
    }
//============================ OrgPosition

    public OrgPosition getOrgPosition(boolean isRandom) {
        OrgPosition obj = new OrgPosition();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setValue("value_" + rand);
        obj.setCode(111 + rand);

        return obj;
    }

    public OrgPosition getRealOrgPosition() {
        OrgPositionShareService service = WebConfigHandler.getWebApplicationContext().getBean(OrgPositionShareService.class);
        List<OrgPosition> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getOrgPosition(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }
//============================ PersonModel

    public PersonModel getPersonModel(boolean isRandom) {
        PersonModel obj = new PersonModel();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setSize(111 + rand);
        obj.setScale(111 + rand);
        obj.setFileName("fileName_" + rand);
        obj.setFileId(rand);
        obj.setUploadDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setAssignUSers(null);
        obj.setUploaderUser(null);
        return obj;
    }

    public PersonModel getRealPersonModel() {
        PersonModelShareService service = WebConfigHandler.getWebApplicationContext().getBean(PersonModelShareService.class);
        List<PersonModel> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getPersonModel(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    //============================ Room
    private TextChat getTextChatPublic(Room room) {

        TextChatShareService textChatShareService = WebConfigHandler.getWebApplicationContext().getBean(TextChatShareService.class);
        TextChat textChat = new TextChat();
        textChat.setChatType(TtTextChatType.Public);
        textChat.setDeliverDateTime(ParsCalendar.getInstance().getShortDateTime());
        textChat.setMessageBody("متن پیش فرض برای پیام خصوصی کاربر... اطلاعات تصادفی: " + CodeGenerator.code(20));
        Set<ServiceUser> set = new HashSet<>();
        set.add(getRealServiceUser(1));
        set.add(getRealServiceUser(2));
        set.add(getRealServiceUser(3));
        set.add(getRealServiceUser(4));
        textChat.setReceivers(set);
        textChat.setRoom(room);
        textChat.setSendStatus(TtTextChatSendStatus.Delivered);
        textChat.setSender(getRealServiceUser());
        textChat.setPrivateChatReceiver(getRealServiceUser(2));
        textChatShareService.save(textChat);

        return textChat;
    }

    public Room getRoom(boolean isRandom) {
        Room obj = new Room();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setDescriptions("descriptions_" + rand);
        obj.setTableLenght(111 + rand);
        obj.setTableWidth(111 + rand);

        obj.setCurrentMeeting(null);
        obj.setRoom_serviceUsers(null);
        obj.setRoom_maps(null);
        obj.setMeetings(null);
        obj.setRoomModel(null);
        obj.setPolls(null);
        obj.setRoomType(TtRoomType.FirstModel);
        obj.setRecSetting(TtRoomRecSetting.NotRecVideoNotRecSound);
        obj.setAccessSetting(TtRoomAccessSetting.ChangeVideoChangeSound);
        return obj;
    }

    public Room getRealRoom() {
        RoomShareService service = WebConfigHandler.getWebApplicationContext().getBean(RoomShareService.class);
        List<Room> list = service.findAll(1, Room._CURRENT_MEETING, Room._TEXT_CHATS);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            Room room = getRoom(true);
            service.save(room);
            getTextChatPublic(room);
            getTextChatPublic(room);
            getTextChatPublic(room);
            getTextChatPublic(room);
            return room;
        }
    }

    //============================ ServiceUser
    private TextChat getTextChatPrivate(ServiceUser user, boolean isSender) {

        TextChatShareService textChatShareService = WebConfigHandler.getWebApplicationContext().getBean(TextChatShareService.class);
        TextChat textChat = new TextChat();
        textChat.setChatType(TtTextChatType.Private);
        textChat.setDeliverDateTime(ParsCalendar.getInstance().getShortDateTime());
        textChat.setMessageBody("متن پیش فرض برای پیام خصوصی کاربر... اطلاعات تصادفی: " + CodeGenerator.code(20));
        textChat.setPrivateChatReceiver(isSender ? getRealServiceUser() : user);
        textChat.setRoom(getRealRoom());
        textChat.setSendStatus(TtTextChatSendStatus.Delivered);
        textChat.setSender(isSender ? user : getRealServiceUser());

        textChatShareService.save(textChat);

        return textChat;
    }

    private Room_ServiceUser getOnlineRoom() {
        Room_ServiceUserShareService service = WebConfigHandler.getWebApplicationContext().getBean(Room_ServiceUserShareService.class);
        Room_ServiceUser room_serviceUser = new Room_ServiceUser();
        room_serviceUser.setTemporaryUserRoleFlag(TtUserRoleFlags.Admin);
        room_serviceUser.setPresence(TtRoomServiceUserPresence.Online);
        room_serviceUser.setPresence(TtRoomServiceUserPresence.Online);
        room_serviceUser.setUserStatus(TtMemberStatus.Active);
        room_serviceUser.setJoinDateTime(ParsCalendar.getInstance().getShortDateTime());
        room_serviceUser.setRoom(getRealRoom());
        room_serviceUser.setPermanentUserRoleFlag(TtUserRoleFlags.Admin);
        room_serviceUser.setAcceptPrivateChat(TtRoomServiceUserEnablePrivateCommunication.Enable);
        room_serviceUser.setAcceptPrivateTalk(TtRoomServiceUserEnablePrivateCommunication.Disable);
        service.save(room_serviceUser);
        return room_serviceUser;
    }

    private PrivateTalk getPrivateTalk(String fileName) {
        PrivateTalkShareService service = WebConfigHandler.getWebApplicationContext().getBean(PrivateTalkShareService.class);
        PrivateTalk privateTalk = new PrivateTalk();

        privateTalk.setFileName(fileName);
        privateTalk.setMeeting(getRealMeeting());
        privateTalk.setStartDateTime(ParsCalendar.getInstance().getShortDateTime());
        privateTalk.setSize(1212);
        privateTalk.setStatus(TtPrivateTalkStatus.Finished);
        privateTalk.setEndDateTime(ParsCalendar.getInstance().getShortDateTime());

        Set<ServiceUser> set = new HashSet<>();
        set.add(getRealServiceUser(1));
        set.add(getRealServiceUser(2));
        set.add(getRealServiceUser(3));
        set.add(getRealServiceUser(4));
        set.add(getRealServiceUser(5));

        privateTalk.setJoinedServiceUsers(set);

        service.save(privateTalk);
        return privateTalk;
    }

    public ServiceUser getServiceUser(boolean isRandom) {
        ServiceUser obj = new ServiceUser();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setName("name_" + rand);
        obj.setPassword("password_" + rand);
        obj.setFailedPasswordCount(111 + rand);
        obj.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setLoginCount(111 + rand);
        obj.setFamily("family_" + rand);
        obj.setUserName("userName_" + rand);
        obj.setLastPassword("lastPassword_" + rand);
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setRoom_serviceUsers(null);
        obj.setPrivateTalks(null);
        obj.setDeleted(TtServiceUserState.UnDeletedUser);
        obj.setGroupPolicy(null);
        obj.setBanned(TtServiceUserState.BannedUser);
        obj.setLastRoom(getRealRoom());
        obj.setUserModel(getRealPersonModel());
        obj.setTextChats(null);
        obj.setOrgPosition(getRealOrgPosition());
        obj.setGrade(getRealGrade());

        return obj;
    }

    public ServiceUser getRealServiceUser() {
        return getRealServiceUser(0);
    }

    public ServiceUser getRealServiceUser(int i) {
        ServiceUserShareService service = WebConfigHandler.getWebApplicationContext().getBean(ServiceUserShareService.class);
        List<ServiceUser> list = service.findAll(i + 1, ServiceUser._GRADE, ServiceUser._ORG_POSITION, ServiceUser._USER_MODEL);
        if (list != null && list.size() > i) {
            ServiceUser obj = list.get(i);
            boolean isO = false;
            if (obj.getGrade() == null) {
                obj.setGrade(getRealGrade());
                isO = true;
            }
            if (obj.getOrgPosition() == null) {
                obj.setOrgPosition(getRealOrgPosition());
                isO = true;
            }
            if (isO) {
                service.update(obj);
            }
            return obj;
        } else {
            service.save(getServiceUser(true));
            list = service.findAll(i + 1);
            if (list != null && list.size() > i) {
                return list.get(i);
            }
        }
        return null;
    }


    public ServiceUser getRealServiceUserWithDependency() {
        ServiceUserShareService service = WebConfigHandler.getWebApplicationContext().getBean(ServiceUserShareService.class);
        List<ServiceUser> list = service.findAll(1, ServiceUser._GRADE, ServiceUser._ORG_POSITION, ServiceUser._USER_MODEL, ServiceUser._TEXT_CHATS);
        if (list != null && !list.isEmpty() && list.get(0).getTextChats() != null && !list.get(0).getTextChats().isEmpty()) {
            ServiceUser obj = list.get(0);
            boolean isO = false;
            if (obj.getGrade() == null) {
                obj.setGrade(getRealGrade());
                isO = true;
            }
            if (obj.getOrgPosition() == null) {
                obj.setOrgPosition(getRealOrgPosition());
                isO = true;
            }
            if (isO) {
                service.update(obj);
            }
            return obj;
        } else {
            ServiceUser obj = getServiceUser(true);

            ///
            //
            obj.setGrade(getRealGrade());
            //
            obj.setOrgPosition(getRealOrgPosition());
            //
            obj.setOnlineRoom(getOnlineRoom());
            /////
            Set<PrivateTalk> ptSet = new HashSet<>();
            ptSet.add(getPrivateTalk("001.mp3"));
            ptSet.add(getPrivateTalk("002.mp3"));
            ptSet.add(getPrivateTalk("003.mp3"));
            obj.setPrivateTalks(ptSet);

            service.save(obj);

            getTextChatPrivate(obj, false);
            getTextChatPrivate(obj, true);
            getTextChatPrivate(obj, false);
            getTextChatPrivate(obj, true);
            getTextChatPrivate(obj, true);
            getTextChatPrivate(obj, true);
            getTextChatPrivate(obj, false);

            return obj;
        }
    }

//============================ Sessions

    public Sessions getSessions(boolean isRandom) {
        Sessions obj = new Sessions();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setSessionId("sessionId_" + rand);
        obj.setPrivilageFlag("privilageFlag_" + rand);
        obj.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setStatus(null);
        obj.setServiceUser(null);
        return obj;
    }

    public Sessions getRealSessions() {
        SessionsShareService service = WebConfigHandler.getWebApplicationContext().getBean(SessionsShareService.class);
        List<Sessions> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getSessions(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ UserGroup

    public UserGroup getUserGroup(boolean isRandom) {
        UserGroup obj = new UserGroup();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setTitle("title_" + rand);

        obj.setParent(null);
        obj.setTasks(null);
        obj.setUsers(null);
        obj.setChilds(null);
        return obj;
    }

    public UserGroup getRealUserGroup() {
        UserGroupService service = WebConfigHandler.getWebApplicationContext().getBean(UserGroupService.class);
        List<UserGroup> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getUserGroup(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ User

    public User getUser(boolean isRandom) {
        User obj = new User();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setComment("comment_" + rand);
        obj.setUsername("username_" + rand);
        obj.setPassword("password_" + rand);
        obj.setPasswordHistory("passwordHistory_" + rand);
        obj.setLastName("lastName_" + rand);
        obj.setFirstName("firstName_" + rand);
        obj.setIpAddress("10.10.10.10");
        obj.setUserCode("userCode_" + rand);
        obj.setIpAddressEnd("10.10.10.10");
        obj.setPorterUuid("");
        obj.setAccessLimitDailyStart(0);
        obj.setIpAddressStart("10.10.10.10");
        obj.setAccessLimitTimelyEnd("");
        obj.setAccessLimitYearlyEnd("");
        obj.setAccessLimitYearlyStart("");
        obj.setAccessLimitTimelyStart("");
        obj.setAccessLimitDailyEnd(0);
        obj.setAccessLimitMonthlyStart(0);
        obj.setAccessLimitMonthlyEnd(0);
        obj.setPasswordDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setLastSigninDateTime("");
        obj.setIpAddressFirstSignin("10.10.10.10");

        obj.setLevel(TtUserLevel.Client);
        obj.setStatus(TtUserStatus.Active);
        obj.setTasks(null);
        obj.setIsLogManager(false);
        obj.setIsSuperAdmin(false);
        obj.setUserGroups(null);
        obj.setGender(TtGender.Male);
        obj.setIpRangeType(TtUserIpRangeType.All);
        obj.setIsBlocked(false);
        obj.setLogo(null);
        obj.setIsNeedToChangePassword(false);
        return obj;
    }

    public User getRealUser() {
        UserService service = WebConfigHandler.getWebApplicationContext().getBean(UserService.class);
        List<User> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getUser(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    public User getRealUserAdmin() {
        UserService service = WebConfigHandler.getWebApplicationContext().getBean(UserService.class);
        User user = service.findBy(
                Restrictions.eq(User.USERNAME, _ADMIN_USER_NAME)
        );
        if (user == null) {
            user = MockObjectInstances.getInstance().getUser(false);
            user.setUsername(MockObjectInstances._ADMIN_USER_NAME);
            user.setPassword(Digester.digestSHA1(MockObjectInstances._ADMIN_PASSWORD));
            user.setLevel(TtUserLevel.Administrator);
            user.setFirstName("کاربر");
            user.setLastName("مدیر1");
            service.save(user);
            user = service.findBy(
                    Restrictions.eq(User.USERNAME, _ADMIN_USER_NAME)
            );
        }

        return user;
    }

//============================ Note

    public Note getNote(boolean isRandom) {
        Note obj = new Note();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setMessage("message_" + rand);
        obj.setTitle("title_" + rand);
        obj.setDateTimeG(new Date().getTime());
        obj.setDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setIsVisited(false);
        obj.setUser(getRealUser());
        obj.setImportance(TtNoteImportance.High);
        obj.setIsNotified(false);
        return obj;
    }

    public Note getRealNote() {
        NoteService service = WebConfigHandler.getWebApplicationContext().getBean(NoteService.class);
        List<Note> list = service.findAllBy(
                Restrictions.eq(Note._USER, getRealUserAdmin()), 1
        );
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getNote(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Backup

    public Backup getBackup(boolean isRandom) {
        Backup obj = new Backup();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setBackupDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setBackupType(null);
        obj.setFile(null);
        return obj;
    }

    public Backup getRealBackup() {
        BackupService service = WebConfigHandler.getWebApplicationContext().getBean(BackupService.class);
        List<Backup> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getBackup(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Irror

    public Irror getIrror(boolean isRandom) {
        Irror obj = new Irror();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setCause("cause_" + rand);
        obj.setMessage("message_" + rand);
        obj.setSessionId("sessionId_" + rand);
        obj.setVisitCount(111 + rand);
        obj.setTaskName("taskName_" + rand);
        obj.setPorterUuid("porterUuid_" + rand);
        obj.setComputerSignature("computerSignature_" + rand);
        obj.setAgentSignature("agentSignature_" + rand);

        obj.setLevel(null);
        obj.setStatus(null);
        obj.setIsVisited(null);
        obj.setUser(null);
        obj.setPlace(null);
        obj.setHttpErrorCode(null);
        return obj;
    }

    public Irror getRealIrror() {
        IrrorService service = WebConfigHandler.getWebApplicationContext().getBean(IrrorService.class);
        List<Irror> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getIrror(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Log

    public Log getLog(boolean isRandom) {
        Log obj = new Log();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setRequestMethod("requestMethod_" + rand);
        obj.setMessage("message_" + rand);
        obj.setUrl("url_" + rand);
        obj.setSessionId("sessionId_" + rand);
        obj.setTaskName("taskName_" + rand);
        obj.setPorterUuid("porterUuid_" + rand);
        obj.setDateTimeG(new Date().getTime());
        obj.setHostPortNumber(111 + rand);
        obj.setHostIpAddress("127.0.0.1");
        obj.setClientPortNumber(111 + rand);
        obj.setClientIpAddress("127.0.0.1");
        obj.setClientName("clientName_" + rand);
        obj.setHttpCode("httpCode_" + rand);
        obj.setUserGroupId(111l + rand);
        obj.setUserId(111l + rand);
        obj.setUsername("userName_" + rand);
        obj.setUserCode("userCode_" + rand);
        obj.setSendDateTimeG(new Date().getTime());
        obj.setTaskTitle("taskTitle_" + rand);
        obj.setServerId("serverId_" + rand);
        obj.setComputerSignature("computerSignature_" + rand);
        obj.setAgentSignature("agentSignature_" + rand);

        obj.setIsTaskTwoLevelConfirm(false);
        obj.setSendStatus(TtLogOnlineSendStatus.Sent);
        obj.setSensitivity(TtTaskSensitivity.Debug);
        obj.setActionType(TtTaskActionType.Add_Edit);
        obj.setUserLevel(TtUserLevel.Master);
        obj.setActionSubType(TtTaskActionSubType.Add_Token);
        obj.setActionStatus(TtTaskActionStatus.Failure);
        obj.setImportanceLevel(TtTaskImportanceLevel.Important);
        obj.setOnlineLoggingStrategy(TtTaskOnlineLoggingStrategy.Scheduling);
        return obj;
    }

    public Log getRealLog() {
        LogService service = WebConfigHandler.getWebApplicationContext().getBean(LogService.class);
        List<Log> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getLog(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ RemoteLog

    public RemoteLog getRemoteLog(boolean isRandom) {
        RemoteLog obj = new RemoteLog();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setRequestMethod("requestMethod_" + rand);
        obj.setMessage("message_" + rand);
        obj.setUrl("url_" + rand);
        obj.setSessionId("sessionId_" + rand);
        obj.setTaskName("taskName_" + rand);
        obj.setPorterUuid("porterUuid_" + rand);
        obj.setDateTimeG(new Date().getTime());
        obj.setHostPortNumber(111 + rand);
        obj.setHostIpAddress("127.0.0.1");
        obj.setClientPortNumber(111 + rand);
        obj.setClientIpAddress("127.0.0.1");
        obj.setClientName("clientName_" + rand);
        obj.setHttpCode("httpCode_" + rand);
        obj.setUserGroupId(111l + rand);
        obj.setUserId(111l + rand);
        obj.setUsername("userName_" + rand);
        obj.setUserCode("userCode_" + rand);
        obj.setSendDateTimeG(new Date().getTime());
        obj.setTaskTitle("taskTitle_" + rand);
        obj.setServerId("serverId_" + rand);
        obj.setComputerSignature("computerSignature_" + rand);
        obj.setAgentSignature("agentSignature_" + rand);

        obj.setIsTaskTwoLevelConfirm(false);
        obj.setSendStatus(TtLogOnlineSendStatus.Sent);
        obj.setSensitivity(TtTaskSensitivity.Debug);
        obj.setActionType(TtTaskActionType.Add_Edit);
        obj.setUserLevel(TtUserLevel.Master);
        obj.setActionSubType(TtTaskActionSubType.Add_Token);
        obj.setActionStatus(TtTaskActionStatus.Failure);
        obj.setImportanceLevel(TtTaskImportanceLevel.Important);
        obj.setOnlineLoggingStrategy(TtTaskOnlineLoggingStrategy.Scheduling);
        return obj;
    }

    public RemoteLog getRealRemoteLog() {
        RemoteLogService service = WebConfigHandler.getWebApplicationContext().getBean(RemoteLogService.class);
        List<RemoteLog> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getRemoteLog(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ SigninLog

    public SigninLog getSigninLog(boolean isRandom) {
        SigninLog obj = new SigninLog();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setIpAddress("ipAddress_" + rand);
        obj.setUuid("uuid_" + rand);
        obj.setLastDateTimeG(new Date().getTime());
        obj.setDomainName("domainName_" + rand);
        obj.setLastDateTime(ParsCalendar.getInstance().getShortDateTime());
        obj.setComputerSignature("computerSignature_" + rand);
        obj.setAgentSignature("agentSignature_" + rand);

        obj.setStatus(null);
        obj.setUser(null);
        return obj;
    }

    public SigninLog getRealSigninLog() {
        SigninLogService service = WebConfigHandler.getWebApplicationContext().getBean(SigninLogService.class);
        List<SigninLog> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getSigninLog(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ Module

    public Module getModule(boolean isRandom) {
        Module obj = new Module();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setClassName("className_" + rand);
        obj.setPackageName("packageName_" + rand);
        obj.setMessageCode("messageCode_" + rand);
        obj.setUrl("url_" + rand);
        obj.setMenuMessageCode("menuMessageCode_" + rand);

        obj.setTasks(null);
        obj.setIsRefreshed(null);
        obj.setIsProtected(null);
        return obj;
    }

    public Module getRealModule() {
        ModuleService service = WebConfigHandler.getWebApplicationContext().getBean(ModuleService.class);
        List<Module> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getModule(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

//============================ UiBag

    public UiBag getUiBag(boolean isRandom) {
        UiBag obj = new UiBag();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }


        obj.setUser(null);
        obj.setFont(null);
        obj.setStyle(null);
        return obj;
    }

    public UiBag getRealUiBag() {
        UiBagService service = WebConfigHandler.getWebApplicationContext().getBean(UiBagService.class);
        List<UiBag> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getUiBag(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }
//============================ StartupNotice

    public StartupNotice getStartupNotice(boolean isRandom) {
        StartupNotice obj = new StartupNotice();

        int rand = 0;
        if (isRandom) {
            rand = CodeGenerator.code(5);
        }

        obj.setTitle("title_" + rand);
        obj.setMessageBody("messageBody_" + rand);
        obj.setSendDateTime(ParsCalendar.getInstance().getShortDateTime());

        obj.setReceiverTemps(null);
        obj.setReceivers(null);
        return obj;
    }

    public StartupNotice getRealStartupNotice() {
        StartupNoticeShareService service = WebConfigHandler.getWebApplicationContext().getBean(StartupNoticeShareService.class);
        List<StartupNotice> list = service.findAll(1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            service.save(getStartupNotice(true));
            list = service.findAll(1);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }


}
