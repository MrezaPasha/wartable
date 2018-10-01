package org.sadr.web.main.system.module;

import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class ModuleConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ModuleDaoImp moduleDaoImp() {
        ModuleDaoImp udi = new ModuleDaoImp();
        return udi;
    }

    @Bean
    public ModuleServiceImp moduleServiceImp() {
        ModuleServiceImp usi = new ModuleServiceImp();
        usi.setDao(moduleDaoImp());
        usi.setTaskDao(new TaskConfig().taskDaoImp());
        return usi;
    }

    @Bean
    public ModuleController moduleController() {
        ModuleController uc = new ModuleController();
        uc.setService(moduleServiceImp());
        return uc;
    }

}
