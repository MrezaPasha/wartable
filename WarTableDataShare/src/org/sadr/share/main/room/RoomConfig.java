package org.sadr.share.main.room;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class RoomConfig extends ShareConfig {

    @Bean
    public RoomDaoImp roomDaoImp()
    {
        RoomDaoImp roomDaoImp = new RoomDaoImp();
        return roomDaoImp;
    }

    @Bean
    public RoomServiceImp roomServiceImp()
    {
        RoomServiceImp roomServiceImp = new RoomServiceImp();
        roomServiceImp.setDao(roomDaoImp());
        roomServiceImp.setSessionsServiceImp(new SessionsServiceImp());
        roomServiceImp.setRoomServiceImp(new RoomServiceImp());
        return roomServiceImp;
    }
}
