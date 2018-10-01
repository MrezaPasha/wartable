package org.sadr.web.main._core.propertor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class PropertorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public PropertorController propertorController() {
        PropertorController ic = new PropertorController();
        return ic;
    }
}
