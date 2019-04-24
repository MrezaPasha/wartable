package org.sadr.share.main.textChat;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class TextChatShareConfig extends ShareConfig {

    @Bean
    public TextChatDaoImp textChatDaoImp() {
        TextChatDaoImp udi = new TextChatDaoImp();
        return udi;
    }

    @Bean
    public TextChatShareServiceImp textChatShareServiceImp() {
        TextChatShareServiceImp usi = new TextChatShareServiceImp();
        usi.setDao(textChatDaoImp());
        return usi;
    }

    @Bean
    public TextChatShareController textChatShareController() {
        TextChatShareController uc = new TextChatShareController();
        uc.setService(textChatShareServiceImp());
        return uc;
    }
}
