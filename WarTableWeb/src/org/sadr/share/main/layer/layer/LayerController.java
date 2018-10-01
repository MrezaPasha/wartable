package org.sadr.share.main.layer.layer;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class LayerController extends GenericControllerImpl<Layer, LayerService> {

    public LayerController() {
    }
}
