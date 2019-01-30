package org.sadr.share.main.meetingItem;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class MeetingItemDaoImp extends GenericDaoImpl<MeetingItem> implements MeetingItemDao {
}
