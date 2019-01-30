package org.sadr.share.main.Room_Map;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;



public class Room_MapConfig extends ShareConfig {

    @Bean
    public Room_MapDaoImp room_mapDaoImp()
    {
        Room_MapDaoImp udi = new Room_MapDaoImp();
        return udi;
    }

    @Bean
    public  Room_MapServiceImp room_mapServiceImp()
    {
        Room_MapServiceImp usi = new Room_MapServiceImp();
        usi.setDao(room_mapDaoImp());
        return usi;
    }


}
