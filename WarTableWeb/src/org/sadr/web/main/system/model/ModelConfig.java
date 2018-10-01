package org.sadr.web.main.system.model;

import org.sadr.web.main.system.field.FieldConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class ModelConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ModelDaoImp modelDaoImp() {
        ModelDaoImp udi = new ModelDaoImp();
        return udi;
    }

    @Bean
    public ModelServiceImp modelServiceImp() {
        ModelServiceImp usi = new ModelServiceImp();
        usi.setDao(modelDaoImp());
        return usi;
    }

    @Bean
    public ModelController modelController() {
        ModelController uc = new ModelController();
        uc.setService(modelServiceImp());
        uc.setFieldService(new FieldConfig().fieldServiceImp());
        return uc;
    }

}
