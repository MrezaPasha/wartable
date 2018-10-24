package org.sadr.web.main._core.setting;

import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.archive.directory.DirectoryConfig;
import org.sadr.web.main.archive.file.file.FileConfig;
import org.sadr.web.main.system.log.daily.DailyLogConfig;
import org.sadr.web.main.system.model.ModelConfig;
import org.sadr.web.main.system.module.ModuleConfig;
import org.sadr.web.main.system.registery.RegisteryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class SettingConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SettingController settingController() {
        SettingController ic = new SettingController();
        ic.setModuleService(new ModuleConfig().moduleServiceImp());
        ic.setDirectoryService(new DirectoryConfig().directoryServiceImp());
        ic.setUserService(new UserConfig().userServiceImp());
        return ic;
    }
}
