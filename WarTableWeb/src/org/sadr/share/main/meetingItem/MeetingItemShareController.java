package org.sadr.share.main.meetingItem;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("***")
public class MeetingItemShareController extends GenericControllerImpl<MeetingItem, MeetingItemShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/meetingItem";

    ////////////////////
    public MeetingItemShareController() {
    }
}
