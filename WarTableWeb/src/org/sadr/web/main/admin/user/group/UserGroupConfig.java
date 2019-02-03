/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.admin.user.group;

import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.system.irror.irror.IrrorConfig;
import org.sadr.web.main.system.module.ModuleConfig;
import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UserGroupConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserGroupDaoImp userGroupDaoImp() {
        UserGroupDaoImp udi = new UserGroupDaoImp();
        return udi;
    }

    @Bean
    public UserGroupServiceImp userGroupServiceImp() {
        UserGroupServiceImp usi = new UserGroupServiceImp();
        usi.setDao(userGroupDaoImp());
        return usi;
    }

    @Bean
    public UserGroupController userGroupController() {
        UserGroupController uc = new UserGroupController();
        uc.setService(userGroupServiceImp());
        uc.setUserService(new UserConfig().userServiceImp());
        uc.setModuleService(new ModuleConfig().moduleServiceImp());
        uc.setTaskService(new TaskConfig().taskServiceImp());
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        return uc;
    }
}
