package org.sadr.service.config;

import org.sadr._core.utils.Cryptor;
import org.sadr._core.utils.OutLog;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.Room_Map.Room_MapConfig;
import org.sadr.share.main.SC.SCLogConfig.SCLogConfig;
import org.sadr.share.main.SC.SCLogConfig.SCLogConfigConfig;
import org.sadr.share.main.SC.SCServerConfig.SCServerConfig;
import org.sadr.share.main.SC.SCServerConfig.SCServerConfigConfig;
import org.sadr.share.main.accessCategoury.AccessCategory;
import org.sadr.share.main.accessCategoury.AccessCategoryConfig;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigConfig;
import org.sadr.share.main.baseErrors.BaseErrors;
import org.sadr.share.main.baseErrors.BaseErrorsConfig;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogConfig;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeConfig;
import org.sadr.share.main.groupPolicy.GroupPolicy;
import org.sadr.share.main.groupPolicy.GroupPolicyConfig;
import org.sadr.share.main.invite.Invite;
import org.sadr.share.main.invite.InviteConfig;
import org.sadr.share.main.item.entity.Entity;
import org.sadr.share.main.item.entity.EntityConfig;
import org.sadr.share.main.item.media.Media;
import org.sadr.share.main.item.media.MediaConfig;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object.ObjectConfig;
import org.sadr.share.main.item.position.Position;
import org.sadr.share.main.item.position.PositionConfig;
import org.sadr.share.main.item.svg.Svg;
import org.sadr.share.main.item.svg.SvgConfig;
import org.sadr.share.main.item.vector.Vector;
import org.sadr.share.main.item.vector.VectorConfig;
import org.sadr.share.main.item.weather.Weather;
import org.sadr.share.main.item.weather.WeatherConfig;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.layer.LayerConfig;
import org.sadr.share.main.log.models.importance.Importance;
import org.sadr.share.main.log.models.importance.ImportanceConfig;
import org.sadr.share.main.log.models.serviceLog.ServiceLog;
import org.sadr.share.main.log.models.serviceLog.ServiceLogConfig;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapConfig;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingConfig;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemConfig;
import org.sadr.share.main.meetingLog.MeetingLog;
import org.sadr.share.main.meetingLog.MeetingLogConfig;
import org.sadr.share.main.meetingRecFile.MeetingRecFile;
import org.sadr.share.main.meetingRecFile.MeetingRecFileConfig;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.meetingSetting.MeetingSettingConfig;
import org.sadr.share.main.meetingSnapshot.MeetingSnapshot;
import org.sadr.share.main.meetingSnapshot.MeetingSnapshotConfig;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.mrml.MrmlConfig;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.orgPosition.OrgPositionConfig;
import org.sadr.share.main.personModel.PersonModel;
import org.sadr.share.main.personModel.PersonModelConfig;
import org.sadr.share.main.pollVotes.PollVotes;
import org.sadr.share.main.pollVotes.PollVotesConfig;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.privateTalk.PrivateTalkConfig;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomConfig;
import org.sadr.share.main.roomModel.RoomModel;
import org.sadr.share.main.roomModel.RoomModelConfig;
import org.sadr.share.main.roomPolls.RoomPolls;
import org.sadr.share.main.roomPolls.RoomPollsConfig;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserConfig;
import org.sadr.share.main.servicePolicy.ServicePolicy;
import org.sadr.share.main.servicePolicy.ServicePolicyConfig;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserConfig;
import org.sadr.share.main.services.Services;
import org.sadr.share.main.services.ServicesConfig;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsConfig;
import org.sadr.share.main.startupNotice.item.StartupNoticeItem;
import org.sadr.share.main.startupNotice.item.StartupNoticeItemConfig;
import org.sadr.share.main.startupNotice.startupNotice.StartupNotice;
import org.sadr.share.main.startupNotice.startupNotice.StartupNoticeConfig;
import org.sadr.share.main.textChat.TextChat;
import org.sadr.share.main.textChat.TextChatConfig;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MSD
 */
public class ServiceConfigHandler {

    //=========================################===========================// ModelClasses
    private static List<Class<?>> modelClasses;

    public static List<Class<?>> getModelClasses() {
        return modelClasses;
    }

    public static void addModelClass(Class c) {
        if (modelClasses == null) {
            modelClasses = new ArrayList<>();
        }
        modelClasses.add(c);
    }

    public static Class<?>[] getModelClassArrays() {
        if (modelClasses == null) {
            return null;
        }
        return modelClasses.toArray(new Class<?>[modelClasses.size()]);
    }

    ////------
    public static void loadModels() {
        loadCoreModels();

    }

    private static void loadCoreModels() {

        //============================  Account
        //addModelClass(Account.class);
        //addModelClass(AccountGroup.class);
        //addModelClass(AccountModel.class);
        //============================  Notification
        //addModelClass(Notification.class);
        //addModelClass(Notification_Account.class);
        //============================  Layer
        //addModelClass(Layer.class);
        //addModelClass(Layer_Map.class);
        //============================  Map
        //addModelClass(Map.class);
        //addModelClass(MapRegion.class);
        //addModelClass(Map_Room.class);
        //============================  Material
        //addModelClass(Material.class);
        //addModelClass(MaterialArea.class);
        //addModelClass(MaterialGroup.class);
        //addModelClass(Material_Map.class);
        //============================  Meeting
        //addModelClass(Meeting.class);
        //addModelClass(Meeting_Account.class);
        //============================  Room
        //addModelClass(Room.class);
        //addModelClass(Room_Account.class);

        //============================  Grade
        addModelClass(Grade.class);
        //============================  OrgPosition
        addModelClass(OrgPosition.class);
        //============================  PrivilageClass

        addModelClass(ServiceUser.class);
        //============================  Importance
        addModelClass(Importance.class);


        //============================  Map
        addModelClass(Map.class);
        //============================  Meeting
        addModelClass(Meeting.class);
        //============================  Room
        addModelClass(Room.class);
        //============================  RoomMap
        addModelClass(Room_Map.class);
        //============================  Sessions
        addModelClass(Sessions.class);
        //============================  CriticalLog
        addModelClass(CriticalLog.class);
        //============================  SCLogConfig
        addModelClass(SCLogConfig.class);
        //============================  SCServerConfig
        addModelClass(SCServerConfig.class);

        //============================  Room_ServiceUser
        addModelClass(Room_ServiceUser.class);


        //============================  Object
        addModelClass(Object.class);



        //============================  BaseErrors.class
        addModelClass(BaseErrors.class);
        //============================  BaseConfig.class
        addModelClass(BaseConfig.class);
        //============================  ServiceLog
        addModelClass(ServiceLog.class);

        addModelClass(StartupNotice.class);
        addModelClass(StartupNoticeItem.class);


        ////////////////////////////////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////
        //////////// new designed //////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////

        //============================  layer
        addModelClass(Layer.class);

        //============================  meeting
        addModelClass(Meeting.class);

        //============================  MeetingItem
        addModelClass(MeetingItem.class);
        //============================  Position
        addModelClass(Position.class);

        //============================  Entity
        addModelClass(Entity.class);

        //============================  Media
        addModelClass(Media.class);

        //============================  Weather
        addModelClass(Weather.class);

        //============================  Object
        addModelClass(Object.class);
        //============================  Invite
        addModelClass(Invite.class);

        //============================  MeetingSetting
        addModelClass(MeetingSetting.class);

        //============================  MeetingLog
        addModelClass(MeetingLog.class);
        //============================  RoomPolls
        addModelClass(RoomPolls.class);
        //============================  PollVotes
        addModelClass(PollVotes.class);
        //============================  GroupPolicy
        addModelClass(GroupPolicy.class);
        //============================  PersonModel
        addModelClass(PersonModel.class);
        //============================  RoomModel
        addModelClass(RoomModel.class);
        //============================  mrml
        addModelClass(Mrml.class);

        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        //============================  ServicePolicy
        addModelClass(ServicePolicy.class);

        //============================  MeetingRecFile
        addModelClass(MeetingRecFile.class);

        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        //============================  PrivateTalk
        addModelClass(PrivateTalk.class);

        //============================  Services
        addModelClass(Services.class);
        //============================  AccessCategory
        addModelClass(AccessCategory.class);
        //============================  TextChat
        addModelClass(TextChat.class);
        //============================  MeetingSnapshot
        addModelClass(MeetingSnapshot.class);

        //============================  Svg
        addModelClass(Svg.class);

        //============================  vector
        addModelClass(Vector.class);

        //============================  vector
        addModelClass(Room_ServiceUser.class);

        //============================  MeetingItemLayer
        //addModelClass(MeetingItemLayer.class);

    }

    //=========================################===========================// ConfigClasses
    private static List<Class<?>> configClasses;

    public static void addConfigClass(Class c) {
        if (configClasses == null) {
            configClasses = new ArrayList<>();
        }
        configClasses.add(c);
    }

    public static List<Class<?>> getConfigClasses() {
        return configClasses;
    }

    public static Class<?>[] getConfigClassArrays() {
        if (configClasses == null) {
            return null;
        }
        return configClasses.toArray(new Class<?>[configClasses.size()]);
    }

    ////------
    public static void loadConfigs() {
        loadCoreConfigs();
    }

    private static void loadCoreConfigs() {
        OutLog.po("");
        //============================  Account
        //============================  Account
        //addConfigClass(AccountConfig.class);
        //addConfigClass(AccountGroupConfig.class);
        //addConfigClass(AccountModelConfig.class);
        //============================  Notification
        //addConfigClass(NotificationConfig.class);
        //addConfigClass(Notification_AccountConfig.class);
        //============================  Layer
        //addConfigClass(LayerConfig.class);
        //addConfigClass(Layer_MapConfig.class);
        //============================  Map
        //addConfigClass(MapConfig.class);
        //addConfigClass(MapRegionConfig.class);
        //addConfigClass(Map_RoomConfig.class);
        //============================  Material
        //addConfigClass(MaterialConfig.class);
        //addConfigClass(MaterialAreaConfig.class);
        //addConfigClass(MaterialGroupConfig.class);
        //addConfigClass(Material_MapConfig.class);
        //============================  Meeting
        //addConfigClass(MeetingConfig.class);
       // addConfigClass(Meeting_AccountConfig.class);
        //============================  Room
        //addConfigClass(RoomConfig.class);
        //addConfigClass(Room_AccountConfig.class);
        //============================  Grade
        addConfigClass(GradeConfig.class);
        //============================  OrgPosition
        addConfigClass(OrgPositionConfig.class);



        //============================  Importance
        addConfigClass(ImportanceConfig.class);



        //============================  Map
        addConfigClass(MapConfig.class);
        //============================  Meeting
        addConfigClass(MeetingConfig.class);
        //============================  Room
        addConfigClass(RoomConfig.class);
        //============================  RoomMap
        addConfigClass(Room_MapConfig.class);
        //============================  Sessions
        addConfigClass(SessionsConfig.class);
        //============================  CriticalLog
        addConfigClass(CriticalLogConfig.class);
        //============================  SCLogConfigConfig
        addConfigClass(SCLogConfigConfig.class);
        //============================  SCServerConfigConfig
        addConfigClass(SCServerConfigConfig.class);
        //============================  Room_ServiceUserConfig
//        addConfigClass(Room_ServiceUserConfig.class);

        //============================  BaseErrors
        addConfigClass(BaseErrorsConfig.class);
        //============================  BaseConfigConfig
        addConfigClass(BaseConfigConfig.class);

        //============================  ServiceLog
        addConfigClass(ServiceLogConfig.class);
        //============================  ServiceUser
        addConfigClass(ServiceUserConfig.class);

        ////////////////////////////////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////
        //////////// new designed //////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////
        ////////////////////////////////////////////

        //============================  layer
        addConfigClass(LayerConfig.class);

        //============================  meeting
        addConfigClass(MeetingConfig.class);

        //============================  MeetingItem
        addConfigClass(MeetingItemConfig.class);
        //============================  Position
        addConfigClass(PositionConfig.class);

        //============================  Entity
        addConfigClass(EntityConfig.class);

        //============================  Media
        addConfigClass(MediaConfig.class);

        //============================  Weather
        addConfigClass(WeatherConfig.class);

        //============================  Object
        addConfigClass(ObjectConfig.class);
        //============================  Invite
        addConfigClass(InviteConfig.class);

        //============================  MeetingSetting
        addConfigClass(MeetingSettingConfig.class);

        //============================  MeetingLog
        addConfigClass(MeetingLogConfig.class);
        //============================  RoomPolls
        addConfigClass(RoomPollsConfig.class);
        //============================  PollVotes
        addConfigClass(PollVotesConfig.class);
        //============================  GroupPolicy
        addConfigClass(GroupPolicyConfig.class);
        //============================  PersonModel
        addConfigClass(PersonModelConfig.class);
        //============================  RoomModel
        addConfigClass(RoomModelConfig.class);
        //============================  mrml
        addConfigClass(MrmlConfig.class);

        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        //============================  ServicePolicy
        addConfigClass(ServicePolicyConfig.class);

        //============================  MeetingRecFile
        addConfigClass(MeetingRecFileConfig.class);

        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        //============================  PrivateTalk
        addConfigClass(PrivateTalkConfig.class);

        //============================  Services
        addConfigClass(ServicesConfig.class);
        //============================  AccessCategory
        addConfigClass(AccessCategoryConfig.class);

        //============================  TextChat
        addConfigClass(TextChatConfig.class);

        //============================  MeetingSnapshot
        addConfigClass(MeetingSnapshotConfig.class);

        //============================  Object
        addConfigClass(ObjectConfig.class);


        //============================  svg
        addConfigClass(SvgConfig.class);

        //============================  vector
        addConfigClass(VectorConfig.class);

        //============================  Room_ServiceUserConfig
        addConfigClass(Room_ServiceUserConfig.class);

        addConfigClass(StartupNoticeConfig.class);
        addConfigClass(StartupNoticeItemConfig.class);

        //============================  MeetingItemLayer
        //addModelClass(MeetingItemLayerConfig.class);
    }


    //=========================################===========================// Database
    private static String[] databaseParamRest;
    private static String[] databaseParamLog;

    public static void setDatabaseParamsRest(Environment env) {
        databaseParamRest = new String[]{
                Cryptor.decrypt(env.getProperty("db.rest.local.url")) + env.getProperty("db.rest.local.name"),
                Cryptor.decrypt(env.getProperty("db.rest.local.username")),
                Cryptor.decrypt(env.getProperty("db.rest.local.password")),
                env.getProperty("db.rest.local.name")};
    }

    public static String[] getDatabaseParamRest() {
        return databaseParamRest;
    }

    public static void setDatabaseParamsLog(Environment env) {
        databaseParamLog = new String[]{
                Cryptor.decrypt(env.getProperty("db.log.local.url")) + env.getProperty("db.log.local.name"),
                Cryptor.decrypt(env.getProperty("db.log.local.username")),
                Cryptor.decrypt(env.getProperty("db.log.local.password")),
                env.getProperty("db.log.local.name")};
    }

    public static String[] getDatabaseParamLog() {
        return databaseParamLog;
    }



}
