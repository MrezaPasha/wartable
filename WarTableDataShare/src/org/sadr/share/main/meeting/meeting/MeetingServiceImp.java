package org.sadr.share.main.meeting.meeting;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class MeetingServiceImp extends GenericServiceImpl<Meeting, MeetingDao> implements MeetingService {
}
