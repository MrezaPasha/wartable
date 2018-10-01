package org.sadr.share.main.material._map;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Material_MapController extends GenericControllerImpl<Material_Map, Material_MapService> {

    public Material_MapController() {
    }
}
