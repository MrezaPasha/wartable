package org.sadr.share.main.meetingSetting;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class MeetingSettingConfig extends ShareConfig {

    @Bean
    public MeetingSettingDaoImp meetingSettingDaoImp()
    {
        MeetingSettingDaoImp udi = new MeetingSettingDaoImp();
        return udi;
    }

    @Bean
    public MeetingSettingServiceImp meetingSettingServiceImp()
    {
        MeetingSettingServiceImp usi = new MeetingSettingServiceImp();
        usi .setDao(meetingSettingDaoImp());
        return usi;
    }
}
