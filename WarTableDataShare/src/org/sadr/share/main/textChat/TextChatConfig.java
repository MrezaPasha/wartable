package org.sadr.share.main.textChat;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

;

public class TextChatConfig extends ShareConfig {


    @Bean
    public TextChatDaoImp textChatDaoImp()
    {
        TextChatDaoImp textChatDaoImp = new TextChatDaoImp();
        return textChatDaoImp;
    }


    @Bean
    public TextChatServiceImp textChatServiceImp()
    {
        TextChatServiceImp usi = new TextChatServiceImp();
        usi.setDao(textChatDaoImp());
        return usi;
    }

}
