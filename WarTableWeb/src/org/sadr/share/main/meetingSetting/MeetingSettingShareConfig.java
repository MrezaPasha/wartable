package org.sadr.share.main.meetingSetting;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MeetingSettingShareConfig extends ShareConfig {

    @Bean
    public MeetingSettingDaoImp meetingSettingDaoImp() {
        MeetingSettingDaoImp udi = new MeetingSettingDaoImp();
        return udi;
    }

    @Bean
    public MeetingSettingShareServiceImp meetingSettingShareServiceImp() {
        MeetingSettingShareServiceImp usi = new MeetingSettingShareServiceImp();
        usi.setDao(meetingSettingDaoImp());
        return usi;
    }

    @Bean
    public MeetingSettingShareController meetingSettingShareController() {
        MeetingSettingShareController uc = new MeetingSettingShareController();
        uc.setService(meetingSettingShareServiceImp());
        return uc;
    }
}
