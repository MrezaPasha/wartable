package org.sadr.share.main.startupNotice.startupNotice;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

;

public class StartupNoticeConfig extends ShareConfig {


    @Bean
    public StartupNoticeDaoImp startupNoticeDaoImp()
    {
        StartupNoticeDaoImp StartupNoticeDaoImp = new StartupNoticeDaoImp();
        return StartupNoticeDaoImp;
    }


    @Bean
    public StartupNoticeServiceImp startupNoticeServiceImp()
    {
        StartupNoticeServiceImp usi = new StartupNoticeServiceImp();
        usi.setDao(startupNoticeDaoImp());
        return usi;
    }

}
