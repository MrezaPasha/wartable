package org.sadr.share.main.map.region;

import org.sadr.web.config.Config;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class MapRegionConfig extends Config {

    @Bean
    public MapRegionDaoImp mapRegionDaoImp() {
        MapRegionDaoImp udi = new MapRegionDaoImp();
        return udi;
    }

    @Bean
    public MapRegionServiceImp mapRegionServiceImp() {
        MapRegionServiceImp usi = new MapRegionServiceImp();
        usi.setDao(mapRegionDaoImp());
        return usi;
    }

    @Bean
    public MapRegionController mapRegionController() {
        MapRegionController uc = new MapRegionController();
        uc.setService(mapRegionServiceImp());
        return uc;
    }
}
