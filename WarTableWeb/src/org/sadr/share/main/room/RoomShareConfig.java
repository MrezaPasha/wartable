package org.sadr.share.main.room;

import org.sadr.share.main.Room_Map.Room_MapShareConfig;
import org.sadr.share.main.map.MapShareConfig;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareConfig;
import org.sadr.share.main.serviceUser.ServiceUserShareConfig;
import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class RoomShareConfig extends WebConfig {

    @Bean
    public RoomDaoImp roomDaoImp() {
        RoomDaoImp udi = new RoomDaoImp();
        return udi;
    }

    @Bean
    public RoomShareServiceImp roomShareServiceImp() {
        RoomShareServiceImp usi = new RoomShareServiceImp();
        usi.setDao(roomDaoImp());
        return usi;
    }

    @Bean
    public RoomShareController roomShareController() {
        RoomShareController uc = new RoomShareController();
        uc.setService(roomShareServiceImp());
        uc.setRoom_serviceUserShareService(new Room_ServiceUserShareConfig().room_ServiceUserShareServiceImp());
        uc.setRoom_mapShareService(new Room_MapShareConfig().room_MapShareServiceImp());
        uc.setMapShareService(new MapShareConfig().mapShareServiceImp());
        uc.setServiceUserShareService(new ServiceUserShareConfig().serviceUserShareServiceImp());
        return uc;
    }
}
