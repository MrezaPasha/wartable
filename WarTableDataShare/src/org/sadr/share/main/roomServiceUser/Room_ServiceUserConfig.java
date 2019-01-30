package org.sadr.share.main.roomServiceUser;

import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.springframework.context.annotation.Bean;
import org.tuckey.web.filters.urlrewrite.Conf;

public class Room_ServiceUserConfig extends Conf {

    @Bean
    public Room_ServiceUserDaoImp room_serviceUserDaoImp()
    {
        Room_ServiceUserDaoImp udi = new Room_ServiceUserDaoImp();
        return udi;
    }

    @Bean
    public Room_ServiceUserServiceImp room_serviceUserServiceImp()
    {
        Room_ServiceUserServiceImp usi = new Room_ServiceUserServiceImp();
        usi.setDao(room_serviceUserDaoImp());
        usi.setRoomServiceImp(new RoomServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        /*usi.setRoomMemberServiceImp(new Room_MemberServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());*/
        return usi;
    }

}
