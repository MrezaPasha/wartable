package org.sadr.share.main.meetingItem;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class MeetingItemShareServiceImp extends GenericServiceImpl<MeetingItem, MeetingItemDao> implements MeetingItemShareService {
}
