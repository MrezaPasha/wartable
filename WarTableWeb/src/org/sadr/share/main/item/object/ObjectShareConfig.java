package org.sadr.share.main.item.object;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class ObjectShareConfig extends WebConfig {

    @Bean
    public ObjectDaoImp objectDaoImp() {
        ObjectDaoImp udi = new ObjectDaoImp();
        return udi;
    }

    @Bean
    public ObjectShareServiceImp objectShareServiceImp() {
        ObjectShareServiceImp usi = new ObjectShareServiceImp();
        usi.setDao(objectDaoImp());
        return usi;
    }

    @Bean
    public ObjectShareController objectShareController() {
        ObjectShareController uc = new ObjectShareController();
        uc.setService(objectShareServiceImp());
        return uc;
    }
}
