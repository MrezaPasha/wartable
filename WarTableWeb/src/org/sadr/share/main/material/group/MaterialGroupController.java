package org.sadr.share.main.material.group;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class MaterialGroupController extends GenericControllerImpl<MaterialGroup, MaterialGroupService> {

    public MaterialGroupController() {
    }
}
