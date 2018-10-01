package org.sadr.share.main.room.room;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class RoomConfig extends Config {

    @Bean
    public RoomDaoImp roomDaoImp() {
        RoomDaoImp udi = new RoomDaoImp();
        return udi;
    }

    @Bean
    public RoomServiceImp roomServiceImp() {
        RoomServiceImp usi = new RoomServiceImp();
        usi.setDao(roomDaoImp());
        return usi;
    }

    @Bean
    public RoomController roomController() {
        RoomController uc = new RoomController();
        uc.setService(roomServiceImp());
        return uc;
    }
}
