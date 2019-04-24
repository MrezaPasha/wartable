package org.sadr.share.main.meetingItem;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MeetingItemShareConfig extends ShareConfig {

    @Bean
    public MeetingItemDaoImp meetingItemDaoImp() {
        MeetingItemDaoImp udi = new MeetingItemDaoImp();
        return udi;
    }

    @Bean
    public MeetingItemShareServiceImp meetingItemShareServiceImp() {
        MeetingItemShareServiceImp usi = new MeetingItemShareServiceImp();
        usi.setDao(meetingItemDaoImp());
        return usi;
    }

    @Bean
    public MeetingItemShareController meetingItemShareController() {
        MeetingItemShareController uc = new MeetingItemShareController();
        uc.setService(meetingItemShareServiceImp());
        return uc;
    }
}
