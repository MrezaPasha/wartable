package org.sadr.share.main.meeting.meeting;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class MeetingController extends GenericControllerImpl<Meeting, MeetingService> {

    public MeetingController() {
    }
}
