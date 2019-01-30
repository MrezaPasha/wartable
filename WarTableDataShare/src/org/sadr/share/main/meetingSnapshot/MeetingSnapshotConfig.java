package org.sadr.share.main.meetingSnapshot;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class MeetingSnapshotConfig extends ShareConfig {

    @Bean
    public MeetingSnapshotDaoImp meetingSnapshotDaoImp()
    {
        MeetingSnapshotDaoImp udi = new MeetingSnapshotDaoImp();
        return udi;
    }

    @Bean
    public MeetingSnapshotServiceImp meetingSnapshotServiceImp()
    {
        MeetingSnapshotServiceImp usi = new MeetingSnapshotServiceImp();
        usi.setDao(meetingSnapshotDaoImp());
        return usi;
    }



}
