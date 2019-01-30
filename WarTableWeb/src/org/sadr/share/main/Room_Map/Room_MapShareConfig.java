package org.sadr.share.main.Room_Map;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class Room_MapShareConfig extends ShareConfig {

    @Bean
    public Room_MapDaoImp room_MapDaoImp() {
        Room_MapDaoImp udi = new Room_MapDaoImp();
        return udi;
    }

    @Bean
    public Room_MapShareServiceImp room_MapShareServiceImp() {
        Room_MapShareServiceImp usi = new Room_MapShareServiceImp();
        usi.setDao(room_MapDaoImp());
        return usi;
    }

    @Bean
    public Room_MapShareController room_MapShareController() {
        Room_MapShareController uc = new Room_MapShareController();
        uc.setService(room_MapShareServiceImp());
        return uc;
    }
}
