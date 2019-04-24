package org.sadr.share.main.startupNotice.item;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

;

public class StartupNoticeItemConfig extends ShareConfig {


    @Bean
    public StartupNoticeItemDaoImp startupNoticeItemDaoImp()
    {
        StartupNoticeItemDaoImp StartupNoticeItemDaoImp = new StartupNoticeItemDaoImp();
        return StartupNoticeItemDaoImp;
    }


    @Bean
    public StartupNoticeItemServiceImp startupNoticeItemServiceImp()
    {
        StartupNoticeItemServiceImp usi = new StartupNoticeItemServiceImp();
        usi.setDao(startupNoticeItemDaoImp());
        return usi;
    }

}
