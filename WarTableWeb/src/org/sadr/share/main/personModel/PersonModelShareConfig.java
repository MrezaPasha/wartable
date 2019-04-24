package org.sadr.share.main.personModel;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class PersonModelShareConfig extends ShareConfig {

    @Bean
    public PersonModelDaoImp personModelDaoImp() {
        PersonModelDaoImp udi = new PersonModelDaoImp();
        return udi;
    }

    @Bean
    public PersonModelShareServiceImp personModelShareServiceImp() {
        PersonModelShareServiceImp usi = new PersonModelShareServiceImp();
        usi.setDao(personModelDaoImp());
        return usi;
    }

    @Bean
    public PersonModelShareController personModelShareController() {
        PersonModelShareController uc = new PersonModelShareController();
        uc.setService(personModelShareServiceImp());
        return uc;
    }
}
