package org.sadr.share.main.meeting;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class MeetingShareServiceImp extends GenericServiceImpl<Meeting, MeetingDao> implements MeetingShareService {
}
