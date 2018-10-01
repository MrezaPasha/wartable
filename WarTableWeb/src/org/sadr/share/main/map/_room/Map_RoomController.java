package org.sadr.share.main.map._room;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Map_RoomController extends GenericControllerImpl<Map_Room, Map_RoomService> {

    public Map_RoomController() {
    }
}
