package org.sadr.web.main;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dev5
 */
public class CoreConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CoreController coreController() {
        CoreController ac = new CoreController();
        return ac;
    }

}
