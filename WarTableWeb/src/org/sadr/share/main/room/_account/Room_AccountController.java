package org.sadr.share.main.room._account;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author masoud
 */
@RestController
@PersianName("***")
public class Room_AccountController extends GenericControllerImpl<Room_Account, Room_AccountService> {

    public Room_AccountController() {
    }
}
