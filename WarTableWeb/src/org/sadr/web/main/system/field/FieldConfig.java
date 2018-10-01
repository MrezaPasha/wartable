package org.sadr.web.main.system.field;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class FieldConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FieldDaoImp fieldDaoImp() {
        FieldDaoImp udi = new FieldDaoImp();
        return udi;
    }

    @Bean
    public FieldServiceImp fieldServiceImp() {
        FieldServiceImp usi = new FieldServiceImp();
        usi.setDao(fieldDaoImp());
        return usi;
    }
}
