package org.sadr.share.main.sessions;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت نشست ها")
public class SessionsShareController extends GenericControllerImpl<Sessions, SessionsShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/sessions";

    ////////////////////
    public SessionsShareController() {
    }

}
