package org.sadr.share.main.meeting;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.item.vector.VectorShareConfig;
import org.sadr.share.main.meetingItem.MeetingItemShareConfig;
import org.sadr.share.main.meetingSetting.MeetingSettingShareConfig;
import org.sadr.share.main.privateTalk.PrivateTalkConfig;
import org.sadr.share.main.privateTalk.PrivateTalkShareConfig;
import org.sadr.share.main.room.RoomShareConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class MeetingShareConfig extends ShareConfig {

    @Bean
    public MeetingDaoImp meetingDaoImp() {
        MeetingDaoImp udi = new MeetingDaoImp();
        return udi;
    }

    @Bean
    public MeetingShareServiceImp meetingShareServiceImp() {
        MeetingShareServiceImp usi = new MeetingShareServiceImp();
        usi.setDao(meetingDaoImp());
        return usi;
    }

    @Bean
    public MeetingShareController meetingShareController() {
        MeetingShareController uc = new MeetingShareController();
        uc.setService(meetingShareServiceImp());
        uc.setRoomShareService(new RoomShareConfig().roomShareServiceImp());
        uc.setPrivateTalkShareService(new PrivateTalkShareConfig().privateTalkShareServiceImp());
        uc.setMeetingSettingShareService(new MeetingSettingShareConfig().meetingSettingShareServiceImp());
        uc.setMeetingItemShareService(new MeetingItemShareConfig().meetingItemShareServiceImp());
        uc.setVectorShareService(new VectorShareConfig().vectorShareServiceImp());
        return uc;
    }
}
