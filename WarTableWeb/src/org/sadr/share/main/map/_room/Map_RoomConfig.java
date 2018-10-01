package org.sadr.share.main.map._room;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Map_RoomConfig extends Config {

    @Bean
    public Map_RoomDaoImp map_RoomDaoImp() {
        Map_RoomDaoImp udi = new Map_RoomDaoImp();
        return udi;
    }

    @Bean
    public Map_RoomServiceImp map_RoomServiceImp() {
        Map_RoomServiceImp usi = new Map_RoomServiceImp();
        usi.setDao(map_RoomDaoImp());
        return usi;
    }

    @Bean
    public Map_RoomController map_RoomController() {
        Map_RoomController uc = new Map_RoomController();
        uc.setService(map_RoomServiceImp());
        return uc;
    }
}
