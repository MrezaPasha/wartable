package org.sadr.share.main.meetingItem;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class MeetingItemConfig extends ShareConfig {

    @Bean
    public MeetingItemDaoImp meetingItemDaoImp()
    {
        MeetingItemDaoImp udi = new MeetingItemDaoImp();
        return udi;

    }
    @Bean
    public MeetingItemServiceImp meetingItemServiceImp()
    {
        MeetingItemServiceImp usi = new MeetingItemServiceImp();
        usi.setDao(meetingItemDaoImp());
        return usi;
    }
}
