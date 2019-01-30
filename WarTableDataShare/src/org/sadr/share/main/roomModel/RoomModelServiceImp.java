package org.sadr.share.main.roomModel;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RoomModelServiceImp extends GenericServiceImpl<RoomModel,RoomModelDao> implements RoomModelService {
}
