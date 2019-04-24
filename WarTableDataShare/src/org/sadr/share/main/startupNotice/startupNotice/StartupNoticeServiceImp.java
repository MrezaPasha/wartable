package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class StartupNoticeServiceImp extends GenericServiceImpl<StartupNotice, StartupNoticeDao> implements StartupNoticeService {
}
