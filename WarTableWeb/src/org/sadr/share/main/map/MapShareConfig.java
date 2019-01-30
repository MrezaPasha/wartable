package org.sadr.share.main.map;

import org.sadr.share.main.Room_Map.Room_MapShareConfig;
import org.sadr.share.main.room.RoomShareConfig;
import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class MapShareConfig extends WebConfig {

    @Bean
    public MapDaoImp mapDaoImp() {
        MapDaoImp udi = new MapDaoImp();
        return udi;
    }

    @Bean
    public MapShareServiceImp mapShareServiceImp() {
        MapShareServiceImp usi = new MapShareServiceImp();
        usi.setDao(mapDaoImp());
        return usi;
    }

    @Bean
    public MapShareController mapShareController() {
        MapShareController uc = new MapShareController();
        uc.setService(mapShareServiceImp());
        uc.setRoom_mapShareService(new Room_MapShareConfig().room_MapShareServiceImp());
        uc.setRoomShareService(new RoomShareConfig().roomShareServiceImp());
        return uc;
    }
}
