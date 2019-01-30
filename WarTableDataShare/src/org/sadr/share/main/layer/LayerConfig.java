package org.sadr.share.main.layer;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.item.object.ObjectServiceImp;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;

;

public class LayerConfig extends ShareConfig {

    @Bean
    public LayerDaoImp layerDaoImp()
    {
        LayerDaoImp udi = new LayerDaoImp();
        return udi;
    }


    @Bean
    public LayerServiceImp layerServiceImp()
    {
        LayerServiceImp usi = new LayerServiceImp();
        usi.setDao(layerDaoImp());
        usi.setMapServiceImp(new MapServiceImp());
        usi.setObjectServiceImp(new ObjectServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setMrmlServiceImp(new MrmlServiceImp());
        usi.setRoomServiceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setMeetingServiceImp(new MeetingServiceImp());
        return usi;
    }
}
