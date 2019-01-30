package org.sadr.share.main.item.entity;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.meeting.MeetingConfig;
import org.sadr.share.main.meetingItem.MeetingItemConfig;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserConfig;
import org.sadr.share.main.sessions.SessionsConfig;
import org.springframework.context.annotation.Bean;



public class EntityConfig extends ShareConfig {

    @Bean
    public EntityDaoImp entityDaoImp() {
        EntityDaoImp udi = new EntityDaoImp();
        return udi;
    }

    @Bean
    public EntityServiceImp entityServiceImp() {
        EntityServiceImp usi = new EntityServiceImp();
        usi.setDao(entityDaoImp());
        usi.setMeetingItemService(new MeetingItemConfig().meetingItemServiceImp());
        usi.setMeetingService(new MeetingConfig().meetingServiceImp());
        usi.setSessionsService(new SessionsConfig().sessionsServiceImp());
        usi.setRoomServiceUserService(new Room_ServiceUserConfig().room_serviceUserServiceImp());
        return usi;
    }


}
