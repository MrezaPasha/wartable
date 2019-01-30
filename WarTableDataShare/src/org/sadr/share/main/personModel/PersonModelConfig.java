package org.sadr.share.main.personModel;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class PersonModelConfig extends ShareConfig {

    @Bean
    public PersonModelDaoImp personModelDaoImp() {
        PersonModelDaoImp udi = new PersonModelDaoImp();
        return udi;
    }

    @Bean
    public PersonModelServiceImp personModelServiceImp()
    {
        PersonModelServiceImp usi = new PersonModelServiceImp();
        usi.setDao(personModelDaoImp());
        return usi;
    }



}
