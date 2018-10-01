package org.sadr.share.main.material.material;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MaterialConfig extends Config {

    @Bean
    public MaterialDaoImp materialDaoImp() {
        MaterialDaoImp udi = new MaterialDaoImp();
        return udi;
    }

    @Bean
    public MaterialServiceImp materialServiceImp() {
        MaterialServiceImp usi = new MaterialServiceImp();
        usi.setDao(materialDaoImp());
        return usi;
    }

    @Bean
    public MaterialController materialController() {
        MaterialController uc = new MaterialController();
        uc.setService(materialServiceImp());
        return uc;
    }
}
