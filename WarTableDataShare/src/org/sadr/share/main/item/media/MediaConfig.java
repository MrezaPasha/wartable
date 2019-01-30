package org.sadr.share.main.item.media;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class MediaConfig extends ShareConfig {

    @Bean
    public MediaDaoImp mediaDaoImp()
    {
        MediaDaoImp udi = new MediaDaoImp();
        return udi;
    }

    @Bean
    public MediaServiceImp mediaServiceImp()
    {
        MediaServiceImp usi = new MediaServiceImp();
        usi.setDao(mediaDaoImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMeetingItemServiceImp(new MeetingItemServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        return usi;

    }
}
