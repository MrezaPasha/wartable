package org.sadr.share.main.item.vector;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class VectorShareConfig extends ShareConfig {

    @Bean
    public VectorDaoImp vectorDaoImp() {
        VectorDaoImp udi = new VectorDaoImp();
        return udi;
    }

    @Bean
    public VectorShareServiceImp vectorShareServiceImp() {
        VectorShareServiceImp usi = new VectorShareServiceImp();
        usi.setDao(vectorDaoImp());
        return usi;
    }

    @Bean
    public VectorShareController vectorShareController() {
        VectorShareController uc = new VectorShareController();
        uc.setService(vectorShareServiceImp());
        return uc;
    }
}
