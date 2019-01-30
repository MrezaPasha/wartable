package org.sadr.web.main.system.log.remote;

import org.sadr.web.config.WebConfig;
import org.sadr.web.main.system.module.ModuleConfig;
import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class RemoteLogConfig extends WebConfig {

    @Bean
    public RemoteLogDaoImp remoteLogDaoImp() {
        RemoteLogDaoImp udi = new RemoteLogDaoImp();
        return udi;
    }

    @Bean
    public RemoteLogServiceImp remoteLogServiceImp() {
        RemoteLogServiceImp usi = new RemoteLogServiceImp();
        usi.setDao(remoteLogDaoImp());
        return usi;
    }

    @Bean
    public RemoteLogController remoteLogController() {
        RemoteLogController uc = new RemoteLogController();
        uc.setService(remoteLogServiceImp());
        uc.setModuleService(new ModuleConfig().moduleServiceImp());
        uc.setTaskService(new TaskConfig().taskServiceImp());
        return uc;
    }
}
