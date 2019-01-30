package org.sadr.share.main.privateTalk;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingRecFile.MeetingRecFileServiceImp;
import org.sadr.share.main.meetingSetting.MeetingSettingServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class PrivateTalkConfig extends ShareConfig {

    @Bean
    public PrivateTalkDaoImp privateTalkDaoImp()
    {
        PrivateTalkDaoImp udi = new PrivateTalkDaoImp();
        return udi ;
    }

    @Bean
    public PrivateTalkServiceImp privateTalkServiceImp()
    {
        PrivateTalkServiceImp usi = new PrivateTalkServiceImp();
        usi.setDao(privateTalkDaoImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setServiceUserServiceImp(new ServiceUserServiceImp());
        usi.setRoomServiceImp(new RoomServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setMeetingSettingServiceImp(new MeetingSettingServiceImp());
        usi.setMeetingRecFileServiceImp(new MeetingRecFileServiceImp());
        usi.setBaseConfigServiceImp(new BaseConfigServiceImp());
        return usi;
    }
}
