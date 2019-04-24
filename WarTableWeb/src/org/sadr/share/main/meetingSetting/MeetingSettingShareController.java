package org.sadr.share.main.meetingSetting;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت مکالمات جلسات")
public class MeetingSettingShareController extends GenericControllerImpl<MeetingSetting, MeetingSettingShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/meetingSetting";

    ////////////////////
    public MeetingSettingShareController() {
    }
}
