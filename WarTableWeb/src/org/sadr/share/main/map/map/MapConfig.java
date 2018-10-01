package org.sadr.share.main.map.map;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MapConfig extends Config {

    @Bean
    public MapDaoImp mapDaoImp() {
        MapDaoImp udi = new MapDaoImp();
        return udi;
    }

    @Bean
    public MapServiceImp mapServiceImp() {
        MapServiceImp usi = new MapServiceImp();
        usi.setDao(mapDaoImp());
        return usi;
    }

    @Bean
    public MapController mapController() {
        MapController uc = new MapController();
        uc.setService(mapServiceImp());
        return uc;
    }
}
