package org.sadr.share.main.grade;

import org.sadr.web.config.WebConfig;
import org.sadr.web.main.system.irror.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class GradeShareConfig extends WebConfig {

    @Bean
    public GradeDaoImp gradeDaoImp() {
        GradeDaoImp udi = new GradeDaoImp();
        return udi;
    }

    @Bean
    public GradeShareServiceImp gradeShareServiceImp() {
        GradeShareServiceImp usi = new GradeShareServiceImp();
        usi.setDao(gradeDaoImp());
        return usi;
    }

    @Bean
    public GradeShareController gradeShareController() {
        GradeShareController uc = new GradeShareController();
        uc.setService(gradeShareServiceImp());
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        return uc;
    }
}
