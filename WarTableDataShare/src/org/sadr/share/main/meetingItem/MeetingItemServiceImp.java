package org.sadr.share.main.meetingItem;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MeetingItemServiceImp extends GenericServiceImpl<MeetingItem,MeetingItemDao> implements MeetingItemService {
}
