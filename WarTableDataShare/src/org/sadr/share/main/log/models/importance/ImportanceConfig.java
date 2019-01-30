package org.sadr.share.main.log.models.importance;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



public class ImportanceConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ImportanceDaoImp importanceDaoImp()
    {
        ImportanceDaoImp importanceDaoImp = new ImportanceDaoImp();
        return importanceDaoImp;
    }

    @Bean
    public ImportanceServiceImp importanceServiceImp()
    {
        ImportanceServiceImp importanceServiceImp = new ImportanceServiceImp();
        importanceServiceImp.setDao(importanceDaoImp());
        return importanceServiceImp;
    }
}
