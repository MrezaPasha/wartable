package org.sadr.share.main.room._account;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Room_AccountConfig extends Config {

    @Bean
    public Room_AccountDaoImp room_AccountDaoImp() {
        Room_AccountDaoImp udi = new Room_AccountDaoImp();
        return udi;
    }

    @Bean
    public Room_AccountServiceImp room_AccountServiceImp() {
        Room_AccountServiceImp usi = new Room_AccountServiceImp();
        usi.setDao(room_AccountDaoImp());
        return usi;
    }

    @Bean
    public Room_AccountController room_AccountController() {
        Room_AccountController uc = new Room_AccountController();
        uc.setService(room_AccountServiceImp());
        return uc;
    }
}
