package org.sadr.share.main.roomModel;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class RoomModelConfig extends ShareConfig {

    @Bean
    public RoomModelDaoImp roomModelDaoImp()
    {
        RoomModelDaoImp udi = new RoomModelDaoImp();
        return udi;
    }

    @Bean
    public RoomModelServiceImp roomModelServiceImp()
    {
        RoomModelServiceImp usi = new RoomModelServiceImp();
        usi.setDao(roomModelDaoImp());
        return usi;
    }

}
