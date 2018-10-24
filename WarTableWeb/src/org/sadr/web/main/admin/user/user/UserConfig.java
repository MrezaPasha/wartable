package org.sadr.web.main.admin.user.user;

import org.sadr.web.main.admin.user.confirm.UserConfirmConfig;
import org.sadr.web.main.admin.user.group.UserGroupConfig;
import org.sadr.web.main.admin.user.porter.UserPorterConfig;
import org.sadr.web.main.admin.user.uuid.UserUuidConfig;
import org.sadr.web.main.system.irror.IrrorConfig;
import org.sadr.web.main.system.log.attempt.UserAttemptConfig;
import org.sadr.web.main.system.module.ModuleConfig;
import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UserConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserDaoImp userDaoImp() {
        UserDaoImp udi = new UserDaoImp();

        return udi;
    }

    @Bean
    public UserServiceImp userServiceImp() {
        UserServiceImp usi = new UserServiceImp();
        usi.setDao(userDaoImp());
        usi.setUserUuidService(new UserUuidConfig().userUuidServiceImp());
        usi.setUserGroupService(new UserGroupConfig().userGroupServiceImp());
        usi.setTaskService(new TaskConfig().taskServiceImp());
        return usi;
    }

    @Bean
    public UserController userController() {
        UserController uc = new UserController();

        uc.setService(userServiceImp());

        uc.setTaskService(new TaskConfig().taskServiceImp());
        uc.setModuleService(new ModuleConfig().moduleServiceImp());
        uc.setUserGroupService(new UserGroupConfig().userGroupServiceImp());
        uc.setUserAttemptService(new UserAttemptConfig().userAttemptServiceImp());
        uc.setUserConfirmService(new UserConfirmConfig().userConfirmServiceImp());
        uc.setIrrorService(new IrrorConfig().irrorServiceImp());
        return uc;
    }

}
