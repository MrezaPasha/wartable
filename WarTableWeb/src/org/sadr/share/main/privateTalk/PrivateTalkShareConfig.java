package org.sadr.share.main.privateTalk;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class PrivateTalkShareConfig extends ShareConfig {

    @Bean
    public PrivateTalkDaoImp privateTalkDaoImp() {
        PrivateTalkDaoImp udi = new PrivateTalkDaoImp();
        return udi;
    }

    @Bean
    public PrivateTalkShareServiceImp privateTalkShareServiceImp() {
        PrivateTalkShareServiceImp usi = new PrivateTalkShareServiceImp();
        usi.setDao(privateTalkDaoImp());
        return usi;
    }

    @Bean
    public PrivateTalkShareController privateTalkShareController() {
        PrivateTalkShareController uc = new PrivateTalkShareController();
        uc.setService(privateTalkShareServiceImp());
        return uc;
    }
}
