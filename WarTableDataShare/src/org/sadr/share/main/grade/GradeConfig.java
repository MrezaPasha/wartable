package org.sadr.share.main.grade;//package org.sadr.service.main.material.area;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

;

/**
 *
 * @author masoud
 */
public class GradeConfig extends ShareConfig {

    @Bean
    public GradeDaoImp gradeDaoImp() {
        GradeDaoImp udi = new GradeDaoImp();
        return udi;
    }

    @Bean
    public GradeServiceImp gradeServiceImp() {
        GradeServiceImp usi = new GradeServiceImp();
        usi.setDao(gradeDaoImp());
        return usi;
    }


}
