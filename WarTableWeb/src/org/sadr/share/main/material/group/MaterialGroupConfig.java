package org.sadr.share.main.material.group;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MaterialGroupConfig extends Config {

    @Bean
    public MaterialGroupDaoImp materialGroupDaoImp() {
        MaterialGroupDaoImp udi = new MaterialGroupDaoImp();
        return udi;
    }

    @Bean
    public MaterialGroupServiceImp materialGroupServiceImp() {
        MaterialGroupServiceImp usi = new MaterialGroupServiceImp();
        usi.setDao(materialGroupDaoImp());
        return usi;
    }

    @Bean
    public MaterialGroupController materialGroupController() {
        MaterialGroupController uc = new MaterialGroupController();
        uc.setService(materialGroupServiceImp());
        return uc;
    }
}
