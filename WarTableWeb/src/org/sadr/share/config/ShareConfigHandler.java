package org.sadr.share.config;

import org.sadr._core.utils.OutLog;
import org.sadr.share.main.admin.account.account.Account;
import org.sadr.share.main.admin.account.account.AccountConfig;
import org.sadr.share.main.admin.account.group.AccountGroup;
import org.sadr.share.main.admin.account.group.AccountGroupConfig;
import org.sadr.share.main.admin.account.model.AccountModel;
import org.sadr.share.main.admin.account.model.AccountModelConfig;
import org.sadr.share.main.admin.notification._account.Notification_Account;
import org.sadr.share.main.admin.notification._account.Notification_AccountConfig;
import org.sadr.share.main.admin.notification.notification.Notification;
import org.sadr.share.main.admin.notification.notification.NotificationConfig;
import org.sadr.share.main.layer._map.Layer_Map;
import org.sadr.share.main.layer._map.Layer_MapConfig;
import org.sadr.share.main.layer.layer.Layer;
import org.sadr.share.main.layer.layer.LayerConfig;
import org.sadr.share.main.map._room.Map_Room;
import org.sadr.share.main.map._room.Map_RoomConfig;
import org.sadr.share.main.map.map.Map;
import org.sadr.share.main.map.map.MapConfig;
import org.sadr.share.main.map.region.MapRegion;
import org.sadr.share.main.map.region.MapRegionConfig;
import org.sadr.share.main.material._map.Material_Map;
import org.sadr.share.main.material._map.Material_MapConfig;
import org.sadr.share.main.material.area.MaterialArea;
import org.sadr.share.main.material.area.MaterialAreaConfig;
import org.sadr.share.main.material.group.MaterialGroup;
import org.sadr.share.main.material.group.MaterialGroupConfig;
import org.sadr.share.main.material.material.Material;
import org.sadr.share.main.material.material.MaterialConfig;
import org.sadr.share.main.meeting._account.Meeting_Account;
import org.sadr.share.main.meeting._account.Meeting_AccountConfig;
import org.sadr.share.main.meeting.meeting.Meeting;
import org.sadr.share.main.meeting.meeting.MeetingConfig;
import org.sadr.share.main.room._account.Room_Account;
import org.sadr.share.main.room._account.Room_AccountConfig;
import org.sadr.share.main.room.room.Room;
import org.sadr.share.main.room.room.RoomConfig;
import org.sadr.web.main.CoreConfig;

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
        //============================  Account
        addModelClass(Account.class);
        addModelClass(AccountGroup.class);
        addModelClass(AccountModel.class);
        //============================  Notification
        addModelClass(Notification.class);
        addModelClass(Notification_Account.class);
        //============================  Layer
        addModelClass(Layer.class);
        addModelClass(Layer_Map.class);
        //============================  Map
        addModelClass(Map.class);
        addModelClass(MapRegion.class);
        addModelClass(Map_Room.class);
        //============================  Material
        addModelClass(Material.class);
        addModelClass(MaterialArea.class);
        addModelClass(MaterialGroup.class);
        addModelClass(Material_Map.class);
        //============================  Meeting
        addModelClass(Meeting.class);
        addModelClass(Meeting_Account.class);
        //============================  Room
        addModelClass(Room.class);
        addModelClass(Room_Account.class);


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
        addConfigClass(CoreConfig.class);
        //============================  Account
        addConfigClass(AccountConfig.class);
        addConfigClass(AccountGroupConfig.class);
        addConfigClass(AccountModelConfig.class);
        //============================  Notification
        addConfigClass(NotificationConfig.class);
        addConfigClass(Notification_AccountConfig.class);
        //============================  Layer
        addConfigClass(LayerConfig.class);
        addConfigClass(Layer_MapConfig.class);
        //============================  Map
        addConfigClass(MapConfig.class);
        addConfigClass(MapRegionConfig.class);
        addConfigClass(Map_RoomConfig.class);
        //============================  Material
        addConfigClass(MaterialConfig.class);
        addConfigClass(MaterialAreaConfig.class);
        addConfigClass(MaterialGroupConfig.class);
        addConfigClass(Material_MapConfig.class);
        //============================  Meeting
        addConfigClass(MeetingConfig.class);
        addConfigClass(Meeting_AccountConfig.class);
        //============================  Room
        addConfigClass(RoomConfig.class);
        addConfigClass(Room_AccountConfig.class);

    }

}
