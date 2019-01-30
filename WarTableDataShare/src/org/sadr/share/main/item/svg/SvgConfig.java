package org.sadr.share.main.item.svg;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;

public class SvgConfig extends ShareConfig {
    @Bean
    public SvgDaoImp svgDaoImp()
    {
        SvgDaoImp udi = new SvgDaoImp();
        return udi;
    }

    @Bean
    public SvgServiceImp svgServiceImp()
    {
        SvgServiceImp usi = new SvgServiceImp();
        usi.setDao(svgDaoImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMeetingItemServiceImp(new MeetingItemServiceImp());
        return usi;
    }

}
