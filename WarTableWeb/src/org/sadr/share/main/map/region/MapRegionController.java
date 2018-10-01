package org.sadr.share.main.map.region;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class MapRegionController extends GenericControllerImpl<MapRegion, MapRegionService> {

    public MapRegionController() {
    }
}
