package org.sadr.share.main.material.material;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class MaterialController extends GenericControllerImpl<Material, MaterialService> {

    public MaterialController() {
    }
}
