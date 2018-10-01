package org.sadr.share.main.layer.layer;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class LayerConfig extends Config {

    @Bean
    public LayerDaoImp layerDaoImp() {
        LayerDaoImp udi = new LayerDaoImp();
        return udi;
    }

    @Bean
    public LayerServiceImp layerServiceImp() {
        LayerServiceImp usi = new LayerServiceImp();
        usi.setDao(layerDaoImp());
        return usi;
    }

    @Bean
    public LayerController layerController() {
        LayerController uc = new LayerController();
        uc.setService(layerServiceImp());
        return uc;
    }
}
