package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class StartupNoticeDaoImp extends GenericDaoImpl<StartupNotice> implements StartupNoticeDao {
}
