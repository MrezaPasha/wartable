package org.sadr.web.main._core.ui;

import org.sadr.web.main.system.registery.RegisteryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UiConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UiController uiController() {
        UiController ic = new UiController();
        ic.setRegisteryService(new RegisteryConfig().registeryServiceImp());
        return ic;
    }
}
