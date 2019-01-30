package org.sadr.share.main.item.media;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.meetingItem.MeetingItemServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MediaServiceImp extends GenericServiceImpl<Media,MediaDao> implements MediaService {
    private SessionsServiceImp sessionsServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private MeetingItemServiceImp meetingItemServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;


    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setMeetingItemServiceImp(MeetingItemServiceImp meetingItemServiceImp) {
        this.meetingItemServiceImp = meetingItemServiceImp;
    }

    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

}
