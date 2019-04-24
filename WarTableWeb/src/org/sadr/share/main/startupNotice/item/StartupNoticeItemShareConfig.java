package org.sadr.share.main.startupNotice.item;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class StartupNoticeItemShareConfig extends ShareConfig {

    @Bean
    public StartupNoticeItemDaoImp startupNoticeItemDaoImp() {
        StartupNoticeItemDaoImp udi = new StartupNoticeItemDaoImp();
        return udi;
    }

    @Bean
    public StartupNoticeItemShareServiceImp startupNoticeItemShareServiceImp() {
        StartupNoticeItemShareServiceImp usi = new StartupNoticeItemShareServiceImp();
        usi.setDao(startupNoticeItemDaoImp());
        return usi;
    }

    @Bean
    public StartupNoticeItemShareController startupNoticeItemShareController() {
        StartupNoticeItemShareController uc = new StartupNoticeItemShareController();
        uc.setService(startupNoticeItemShareServiceImp());
        return uc;
    }
}
