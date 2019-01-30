package org.sadr.share.main.meetingSnapshot;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MeetingSnapshotServiceImp extends GenericServiceImpl<MeetingSnapshot,MeetingSnapshotDao> implements MeetingSnapshotService {
}
