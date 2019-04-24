package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.serviceUser.ServiceUserShareConfig;
import org.sadr.share.main.startupNotice.item.StartupNoticeItemShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class StartupNoticeShareConfig extends ShareConfig {

    @Bean
    public StartupNoticeDaoImp startupNoticeDaoImp() {
        StartupNoticeDaoImp udi = new StartupNoticeDaoImp();
        return udi;
    }

    @Bean
    public StartupNoticeShareServiceImp startupNoticeShareServiceImp() {
        StartupNoticeShareServiceImp usi = new StartupNoticeShareServiceImp();
        usi.setDao(startupNoticeDaoImp());
        return usi;
    }

    @Bean
    public StartupNoticeShareController startupNoticeShareController() {
        StartupNoticeShareController uc = new StartupNoticeShareController();
        uc.setService(startupNoticeShareServiceImp());
        uc.setStartupNoticeItemShareService(new StartupNoticeItemShareConfig().startupNoticeItemShareServiceImp());
        uc.setServiceUserShareService(new ServiceUserShareConfig().serviceUserShareServiceImp());
        return uc;
    }
}
