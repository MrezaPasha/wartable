package org.sadr.share.main.room.room;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class RoomController extends GenericControllerImpl<Room, RoomService> {

    public RoomController() {
    }
}
