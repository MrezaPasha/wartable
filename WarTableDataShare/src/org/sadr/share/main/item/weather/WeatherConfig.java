package org.sadr.share.main.item.weather;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class WeatherConfig extends ShareConfig {


    @Bean
    public WeatherDaoImp weatherDaoImp()
    {
        WeatherDaoImp udi = new WeatherDaoImp();
        return udi;
    }

    @Bean
    public WeatherServiceImp weatherServiceImp()
    {
        WeatherServiceImp usi = new WeatherServiceImp();
        usi.setDao(weatherDaoImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setMeetingItemServiceImp(new MeetingItemServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        return usi;
    }


}
