package org.sadr.web.main._core.develop;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class DevelopConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DevelopController developController() {
        DevelopController ic = new DevelopController();
        return ic;
    }
}
