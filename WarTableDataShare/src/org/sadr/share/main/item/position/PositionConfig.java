package org.sadr.share.main.item.position;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class PositionConfig extends ShareConfig {

    @Bean
    public PositionDaoImp positionDaoImp()
    {
        PositionDaoImp udi = new PositionDaoImp();
        return udi;
    }

    @Bean
    public PositionServiceImp positionServiceImp()
    {
        PositionServiceImp usi = new PositionServiceImp();
        usi.setDao(positionDaoImp());
        usi.setMeetingItemServiceImp(new MeetingItemServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        return usi;
    }


}
