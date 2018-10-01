package org.sadr.share.main.meeting.meeting;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MeetingConfig extends Config {

    @Bean
    public MeetingDaoImp meetingDaoImp() {
        MeetingDaoImp udi = new MeetingDaoImp();
        return udi;
    }

    @Bean
    public MeetingServiceImp meetingServiceImp() {
        MeetingServiceImp usi = new MeetingServiceImp();
        usi.setDao(meetingDaoImp());
        return usi;
    }

    @Bean
    public MeetingController meetingController() {
        MeetingController uc = new MeetingController();
        uc.setService(meetingServiceImp());
        return uc;
    }
}
