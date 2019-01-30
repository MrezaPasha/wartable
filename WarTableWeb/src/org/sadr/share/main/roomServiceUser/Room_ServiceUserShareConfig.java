package org.sadr.share.main.roomServiceUser;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.room.RoomShareConfig;
import org.sadr.share.main.serviceUser.ServiceUserShareConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class Room_ServiceUserShareConfig extends ShareConfig {

    @Bean
    public Room_ServiceUserDaoImp room_ServiceUserDaoImp() {
        Room_ServiceUserDaoImp udi = new Room_ServiceUserDaoImp();
        return udi;
    }

    @Bean
    public Room_ServiceUserShareServiceImp room_ServiceUserShareServiceImp() {
        Room_ServiceUserShareServiceImp usi = new Room_ServiceUserShareServiceImp();
        usi.setDao(room_ServiceUserDaoImp());
        return usi;
    }

    @Bean
    public Room_ServiceUserShareController room_ServiceUserShareController() {
        Room_ServiceUserShareController uc = new Room_ServiceUserShareController();
        uc.setService(room_ServiceUserShareServiceImp());
        uc.setRoomShareService(new RoomShareConfig().roomShareServiceImp());
        uc.setServiceUserShareService(new ServiceUserShareConfig().serviceUserShareServiceImp());
        return uc;
    }
}
