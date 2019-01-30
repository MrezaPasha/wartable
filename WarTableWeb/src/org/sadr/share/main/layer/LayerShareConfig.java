package org.sadr.share.main.layer;

import org.sadr.web.config.WebConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class LayerShareConfig extends WebConfig {

    @Bean
    public LayerDaoImp layerDaoImp() {
        LayerDaoImp udi = new LayerDaoImp();
        return udi;
    }

    @Bean
    public LayerShareServiceImp layerShareServiceImp() {
        LayerShareServiceImp usi = new LayerShareServiceImp();
        usi.setDao(layerDaoImp());
        return usi;
    }

    @Bean
    public LayerShareController layerShareController() {
        LayerShareController uc = new LayerShareController();
        uc.setService(layerShareServiceImp());
        return uc;
    }
}
