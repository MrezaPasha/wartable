package org.sadr.share.main.material.area;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class MaterialAreaController extends GenericControllerImpl<MaterialArea, MaterialAreaService> {

    public MaterialAreaController() {
    }
}
