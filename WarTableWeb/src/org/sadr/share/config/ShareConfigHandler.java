package org.sadr.share.config;

import org.sadr._core.utils.OutLog;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.Room_Map.Room_MapShareConfig;
import org.sadr.share.main.SC.SCLogConfig.SCLogConfig;
import org.sadr.share.main.SC.SCServerConfig.SCServerConfig;
import org.sadr.share.main.accessCategoury.AccessCategory;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigShareConfig;
import org.sadr.share.main.baseErrors.BaseErrors;
import org.sadr.share.main.baseErrors.BaseErrorsShareConfig;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogShareConfig;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeShareConfig;
import org.sadr.share.main.groupPolicy.GroupPolicy;
import org.sadr.share.main.invite.Invite;
import org.sadr.share.main.item.entity.Entity;
import org.sadr.share.main.item.media.Media;
import org.sadr.share.main.item.object.Object;
import org.sadr.share.main.item.object.ObjectShareConfig;
import org.sadr.share.main.item.position.Position;
import org.sadr.share.main.item.svg.Svg;
import org.sadr.share.main.item.vector.Vector;
import org.sadr.share.main.item.vector.VectorShareConfig;
import org.sadr.share.main.item.weather.Weather;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.layer.LayerShareConfig;
import org.sadr.share.main.log.models.importance.Importance;
import org.sadr.share.main.log.models.serviceLog.ServiceLog;
import org.sadr.share.main.log.models.serviceLog.ServiceLogShareConfig;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapShareConfig;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingShareConfig;
import org.sadr.share.main.meetingItem.MeetingItem;
import org.sadr.share.main.meetingItem.MeetingItemShareConfig;
import org.sadr.share.main.meetingLog.MeetingLog;
import org.sadr.share.main.meetingRecFile.MeetingRecFile;
import org.sadr.share.main.meetingSetting.MeetingSetting;
import org.sadr.share.main.meetingSetting.MeetingSettingConfig;
import org.sadr.share.main.meetingSetting.MeetingSettingShareConfig;
import org.sadr.share.main.meetingSnapshot.MeetingSnapshot;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.orgPosition.OrgPositionShareConfig;
import org.sadr.share.main.personModel.PersonModel;
import org.sadr.share.main.personModel.PersonModelShareConfig;
import org.sadr.share.main.pollVotes.PollVotes;
import org.sadr.share.main.privateTalk.PrivateTalk;
import org.sadr.share.main.privateTalk.PrivateTalkShareConfig;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomShareConfig;
import org.sadr.share.main.roomModel.RoomModel;
import org.sadr.share.main.roomPolls.RoomPolls;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareConfig;
import org.sadr.share.main.servicePolicy.ServicePolicy;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserShareConfig;
import org.sadr.share.main.services.Services;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsShareConfig;
import org.sadr.share.main.startupNotice.item.StartupNoticeItem;
import org.sadr.share.main.startupNotice.item.StartupNoticeItemShareConfig;
import org.sadr.share.main.startupNotice.startupNotice.StartupNotice;
import org.sadr.share.main.startupNotice.startupNotice.StartupNoticeShareConfig;
import org.sadr.share.main.textChat.TextChat;
import org.sadr.share.main.textChat.TextChatShareConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MSD
 */
public class ShareConfigHandler {

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
//        //============================  ServiceUser

        addModelClass(AccessCategory.class);
        addModelClass(BaseConfig.class);
        addModelClass(BaseErrors.class);
        addModelClass(CriticalLog.class);

        addModelClass(Grade.class);
        addModelClass(GroupPolicy.class);
        addModelClass(Invite.class);

        addModelClass(Entity.class);
        addModelClass(Media.class);
        addModelClass(Object.class);
        addModelClass(Position.class);
        addModelClass(Weather.class);
        addModelClass(Svg.class);
        addModelClass(Vector.class);

        addModelClass(Layer.class);

        addModelClass(Importance.class);
        addModelClass(ServiceLog.class);

        addModelClass(Map.class);
        addModelClass(Meeting.class);
        addModelClass(MeetingItem.class);
        addModelClass(MeetingLog.class);
        addModelClass(MeetingRecFile.class);
        addModelClass(MeetingSetting.class);
        addModelClass(MeetingSnapshot.class);

        addModelClass(Mrml.class);
        addModelClass(OrgPosition.class);
        addModelClass(PersonModel.class);
        addModelClass(PollVotes.class);
        addModelClass(PrivateTalk.class);

        addModelClass(Room.class);
        addModelClass(Room_Map.class);
        addModelClass(RoomModel.class);
        addModelClass(RoomPolls.class);
        addModelClass(Room_ServiceUser.class);

        addModelClass(SCLogConfig.class);
        addModelClass(SCServerConfig.class);

        addModelClass(ServicePolicy.class);
        addModelClass(Services.class);
        addModelClass(ServiceUser.class);
        addModelClass(Sessions.class);
        addModelClass(TextChat.class);

        addModelClass(StartupNotice.class);
        addModelClass(StartupNoticeItem.class);


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
        //============================  ServiceUser SHARE SHARE
        addConfigClass(BaseConfigShareConfig.class);
        addConfigClass(BaseErrorsShareConfig.class);
        addConfigClass(CriticalLogShareConfig.class);
        addConfigClass(GradeShareConfig.class);
        addConfigClass(LayerShareConfig.class);
        addConfigClass(MapShareConfig.class);
        addConfigClass(MeetingShareConfig.class);
        addConfigClass(RoomShareConfig.class);
        addConfigClass(Room_ServiceUserShareConfig.class);
        addConfigClass(ObjectShareConfig.class);
        addConfigClass(OrgPositionShareConfig.class);
        addConfigClass(Room_MapShareConfig.class);
        addConfigClass(ServiceLogShareConfig.class);
        addConfigClass(ServiceUserShareConfig.class);
        addConfigClass(PersonModelShareConfig.class);
        addConfigClass(SessionsShareConfig.class);
        addConfigClass(TextChatShareConfig.class);
        addConfigClass(PrivateTalkShareConfig.class);
        addConfigClass(MeetingSettingShareConfig.class);
        addConfigClass(StartupNoticeShareConfig.class);
        addConfigClass(StartupNoticeItemShareConfig.class);
        addConfigClass(MeetingItemShareConfig.class);
        addConfigClass(VectorShareConfig.class);

    }

}
