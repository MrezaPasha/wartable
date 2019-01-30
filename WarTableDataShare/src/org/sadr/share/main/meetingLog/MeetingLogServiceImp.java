package org.sadr.share.main.meetingLog;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.share.main._types.TtRpcFunction;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MeetingLogServiceImp extends GenericServiceImpl<MeetingLog,MeetingLogDao> implements MeetingLogService {

    public void insertMeetingLog(String request , Meeting meeting , ServiceUser user , String description , String response , TtRpcFunction function , String creationDateTime)
    {
        try
        {
            MeetingLog meetingLog = new MeetingLog();
            meetingLog.setUser(user);
            meetingLog.setDescription(description);
            meetingLog.setCreationDateTime(creationDateTime);
            meetingLog.setMethod(function);
            meetingLog.setMeeting(meeting);
            save(meetingLog);


        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
    }
}
