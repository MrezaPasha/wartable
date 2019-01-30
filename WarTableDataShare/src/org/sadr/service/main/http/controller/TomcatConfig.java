package org.sadr.service.main.http.controller;

import org.sadr.share.main.grade.GradeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dev5
 */
public class TomcatConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TomcatController tomcatController() {
        TomcatController ac = new TomcatController();
        ac.setGradeService(new GradeConfig().gradeServiceImp());
        return ac;
    }

}
