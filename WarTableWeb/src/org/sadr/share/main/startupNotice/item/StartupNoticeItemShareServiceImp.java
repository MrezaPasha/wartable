package org.sadr.share.main.startupNotice.item;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class StartupNoticeItemShareServiceImp extends GenericServiceImpl<StartupNoticeItem, StartupNoticeItemDao> implements StartupNoticeItemShareService {
}
