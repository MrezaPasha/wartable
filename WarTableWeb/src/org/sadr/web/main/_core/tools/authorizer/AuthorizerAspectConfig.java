package org.sadr.web.main._core.tools.authorizer;

import org.sadr.web.main.admin.user.confirm.UserConfirmConfig;
import org.sadr.web.main.admin.user.group.UserGroupConfig;
import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.system.irror.irror.IrrorConfig;
import org.sadr.web.main.system.log.general.LogConfig;
import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dev1
 */
public class AuthorizerAspectConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AuthorizerAspect authorizerAspect() {
        AuthorizerAspect aa = new AuthorizerAspect();
        aa.setTaskService(new TaskConfig().taskServiceImp());
        aa.setUserService(new UserConfig().userServiceImp());
        aa.setIrrorService(new IrrorConfig().irrorServiceImp());
        aa.setLogService(new LogConfig().logServiceImp());
        aa.setUserGroupService(new UserGroupConfig().userGroupServiceImp());
        aa.setUserConfirmService(new UserConfirmConfig().userConfirmServiceImp());
        return aa;
    }

}
