package org.sadr.web.main.system.task;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class TaskConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TaskDaoImp taskDaoImp() {
        TaskDaoImp udi = new TaskDaoImp();
        return udi;
    }

    @Bean
    public TaskServiceImp taskServiceImp() {
        TaskServiceImp usi = new TaskServiceImp();
        usi.setDao(taskDaoImp());
        return usi;
    }

    @Bean
    public TaskController taskController() {
        TaskController uc = new TaskController();
        uc.setService(taskServiceImp());
        return uc;
    }
}
