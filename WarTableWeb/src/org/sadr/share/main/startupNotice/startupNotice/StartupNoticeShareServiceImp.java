package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class StartupNoticeShareServiceImp extends GenericServiceImpl<StartupNotice, StartupNoticeDao> implements StartupNoticeShareService {
}
