package org.sadr.share.main.privateTalk;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت تماس های خصوصی")
public class PrivateTalkShareController extends GenericControllerImpl<PrivateTalk, PrivateTalkShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/privateTalk";

    ////////////////////
    public PrivateTalkShareController() {
    }

}
