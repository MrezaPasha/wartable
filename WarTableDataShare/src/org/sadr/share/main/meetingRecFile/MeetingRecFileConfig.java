package org.sadr.share.main.meetingRecFile;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class MeetingRecFileConfig extends ShareConfig {

    @Bean
    public MeetingRecFileDaoImp meetingRecFileDaoImp()
    {
        MeetingRecFileDaoImp udi = new MeetingRecFileDaoImp();
        return udi;
    }

    @Bean
    public  MeetingRecFileServiceImp meetingRecFileServiceImp()
    {
        MeetingRecFileServiceImp usi = new MeetingRecFileServiceImp();
        usi.setDao(meetingRecFileDaoImp());
        return usi;
    }
}
