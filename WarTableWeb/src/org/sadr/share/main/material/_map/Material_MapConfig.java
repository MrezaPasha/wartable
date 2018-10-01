package org.sadr.share.main.material._map;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Material_MapConfig extends Config {

    @Bean
    public Material_MapDaoImp material_MapDaoImp() {
        Material_MapDaoImp udi = new Material_MapDaoImp();
        return udi;
    }

    @Bean
    public Material_MapServiceImp material_MapServiceImp() {
        Material_MapServiceImp usi = new Material_MapServiceImp();
        usi.setDao(material_MapDaoImp());
        return usi;
    }

    @Bean
    public Material_MapController material_MapController() {
        Material_MapController uc = new Material_MapController();
        uc.setService(material_MapServiceImp());
        return uc;
    }
}
