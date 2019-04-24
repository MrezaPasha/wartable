package org.sadr.share.main.textChat;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت چت ها")
public class TextChatShareController extends GenericControllerImpl<TextChat, TextChatShareService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/service/textChat";

    ////////////////////
    public TextChatShareController() {
    }

}
