package org.sadr.share.main.startupNotice.item;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class StartupNoticeItemServiceImp extends GenericServiceImpl<StartupNoticeItem, StartupNoticeItemDao> implements StartupNoticeItemService {
}
