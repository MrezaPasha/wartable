package org.sadr.share.main.roomPolls;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class RoomPollsConfig extends ShareConfig {

    @Bean
    public RoomPollsDaoImp roomPollsDaoImp()
    {
        RoomPollsDaoImp udi = new RoomPollsDaoImp();
        return udi;
    }


    @Bean
    public RoomPollsServiceImp roomPollsServiceImp()
    {
        RoomPollsServiceImp usi = new RoomPollsServiceImp();
        usi.setDao(roomPollsDaoImp());
        return usi;
    }


}
