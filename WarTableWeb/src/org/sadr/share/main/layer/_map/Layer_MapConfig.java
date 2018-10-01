package org.sadr.share.main.layer._map;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class Layer_MapConfig extends Config {

    @Bean
    public Layer_MapDaoImp layer_MapDaoImp() {
        Layer_MapDaoImp udi = new Layer_MapDaoImp();
        return udi;
    }

    @Bean
    public Layer_MapServiceImp layer_MapServiceImp() {
        Layer_MapServiceImp usi = new Layer_MapServiceImp();
        usi.setDao(layer_MapDaoImp());
        return usi;
    }

    @Bean
    public Layer_MapController layer_MapController() {
        Layer_MapController uc = new Layer_MapController();
        uc.setService(layer_MapServiceImp());
        return uc;
    }
}
