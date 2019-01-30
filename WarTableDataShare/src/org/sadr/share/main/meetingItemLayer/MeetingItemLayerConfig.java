package org.sadr.share.main.meetingItemLayer;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

public class MeetingItemLayerConfig extends ShareConfig {

    @Bean
    public MeetingItemLayerDaoImp meetingItemLayerDaoImp()
    {
        MeetingItemLayerDaoImp udi = new MeetingItemLayerDaoImp();
        return udi;
    }

    @Bean
    public MeetingItemLayerServiceImp meetingItemLayerServiceImp()
    {
        MeetingItemLayerServiceImp usi = new MeetingItemLayerServiceImp();
        usi.setDao(new MeetingItemLayerDaoImp());
        return usi;
    }

}
