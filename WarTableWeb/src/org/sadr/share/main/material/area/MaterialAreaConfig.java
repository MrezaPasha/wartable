package org.sadr.share.main.material.area;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MaterialAreaConfig extends Config {

    @Bean
    public MaterialAreaDaoImp materialAreaDaoImp() {
        MaterialAreaDaoImp udi = new MaterialAreaDaoImp();
        return udi;
    }

    @Bean
    public MaterialAreaServiceImp materialAreaServiceImp() {
        MaterialAreaServiceImp usi = new MaterialAreaServiceImp();
        usi.setDao(materialAreaDaoImp());
        return usi;
    }

    @Bean
    public MaterialAreaController materialAreaController() {
        MaterialAreaController uc = new MaterialAreaController();
        uc.setService(materialAreaServiceImp());
        return uc;
    }
}
