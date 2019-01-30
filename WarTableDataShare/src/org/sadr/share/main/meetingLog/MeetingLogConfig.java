package org.sadr.share.main.meetingLog;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class MeetingLogConfig extends ShareConfig {

    @Bean
    public MeetingLogDaoImp meetingLogDaoImp()
    {
        MeetingLogDaoImp udi = new MeetingLogDaoImp();
        return udi;
    }

    @Bean
    public MeetingLogServiceImp meetingLogServiceImp()
    {
        MeetingLogServiceImp usi = new MeetingLogServiceImp();
        usi.setDao(meetingLogDaoImp());
        return usi;
    }
}
