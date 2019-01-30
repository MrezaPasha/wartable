package org.sadr.share.main.map;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.Room_Map.Room_MapServiceImp;
import org.sadr.share.main.layer.LayerServiceImp;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.privateTalk.PrivateTalkServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class MapConfig extends ShareConfig {

    @Bean
    public MapDaoImp mapDaoImp()
    {
        MapDaoImp udi = new MapDaoImp();
        return udi;
    }

    @Bean
    public MapServiceImp mapServiceImp()
    {
        MapServiceImp usi = new MapServiceImp();
        usi.setDao(mapDaoImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setRoomMapServiceImp(new Room_MapServiceImp());
        usi.setRoomServiceImp(new RoomServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setLayerServiceImp(new LayerServiceImp());
        usi.setMrmlServiceImp(new MrmlServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setPrivateTalkServiceImp(new PrivateTalkServiceImp());

        return usi;
    }

}
