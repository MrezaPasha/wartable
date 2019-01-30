package org.sadr.share.main.meetingLog;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class MeetingLogDaoImp extends GenericDaoImpl<MeetingLog> implements MeetingLogDao {
}
