package org.sadr.share.main.meeting;


import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.Room_Map.Room_MapServiceImp;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.meetingSetting.MeetingSettingServiceImp;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class MeetingConfig extends ShareConfig {

    @Bean
    public MeetingDaoImp meetingDaoImp()
    {
        MeetingDaoImp udi = new MeetingDaoImp();
        return udi;
    }

    @Bean
    public MeetingServiceImp meetingServiceImp()
    {
        MeetingServiceImp usi = new MeetingServiceImp();
        usi.setDao(meetingDaoImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMapServiceImp(new MapServiceImp());
        usi.setRoomMapServiceImp(new Room_MapServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setMrmlServiceImp(new MrmlServiceImp());
        usi.setMeetingSettingServiceImp(new MeetingSettingServiceImp());
        return usi;
    }

}
