package org.sadr.share.main.meeting;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.room.RoomShareConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class MeetingShareConfig extends ShareConfig {

    @Bean
    public MeetingDaoImp meetingDaoImp() {
        MeetingDaoImp udi = new MeetingDaoImp();
        return udi;
    }

    @Bean
    public MeetingShareServiceImp meetingShareServiceImp() {
        MeetingShareServiceImp usi = new MeetingShareServiceImp();
        usi.setDao(meetingDaoImp());
        return usi;
    }

    @Bean
    public MeetingShareController meetingShareController() {
        MeetingShareController uc = new MeetingShareController();
        uc.setService(meetingShareServiceImp());
        uc.setRoomShareService(new RoomShareConfig().roomShareServiceImp());
        return uc;
    }
}
