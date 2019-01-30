package org.sadr.share.main.item.object;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.layer.LayerServiceImp;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class ObjectConfig extends ShareConfig {

    @Bean
    public ObjectDaoImp objectDaoImp()
    {
        ObjectDaoImp udi = new ObjectDaoImp();
        return udi;
    }

    @Bean
    public ObjectServiceImp objectServiceImp()
    {
        ObjectServiceImp usi = new ObjectServiceImp();
        usi.setDao(objectDaoImp());
        usi.setLayerServiceImp(new LayerServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setMeetingItemServiceImp(new MeetingItemServiceImp());
        usi.setObjectServiceImp(new ObjectServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setObjectServiceImp(new ObjectServiceImp());
        usi.setRoomServiceImp(new RoomServiceImp());
        return usi;
    }
}
