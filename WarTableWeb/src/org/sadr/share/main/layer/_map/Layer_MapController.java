package org.sadr.share.main.layer._map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Layer_MapController extends GenericControllerImpl<Layer_Map, Layer_MapService> {

    public Layer_MapController() {
    }
}
