package org.sadr.share.main.textChat;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class TextChatShareServiceImp extends GenericServiceImpl<TextChat, TextChatDao> implements TextChatShareService {
}
