package org.sadr.share.main.item.vector;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class VectorShareController extends GenericControllerImpl<Vector, VectorShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/vector";

    ////////////////////
    public VectorShareController() {
    }

}
