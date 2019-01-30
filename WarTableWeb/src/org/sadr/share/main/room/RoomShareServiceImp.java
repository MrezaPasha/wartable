package org.sadr.share.main.room;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class RoomShareServiceImp extends GenericServiceImpl<Room, RoomDao> implements RoomShareService {
}
