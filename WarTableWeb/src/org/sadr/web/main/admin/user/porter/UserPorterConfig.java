package org.sadr.web.main.admin.user.porter;

import org.sadr.web.main.admin.user.user.UserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class UserPorterConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserPorterController userPorterController() {
        UserPorterController uc = new UserPorterController();
        uc.setService(userPorterServiceImp());
        return uc;
    }

    @Bean
    public UserPorterServiceImp userPorterServiceImp() {
        UserPorterServiceImp usi = new UserPorterServiceImp();
        usi.setDao(userPorterDaoImp());
        return usi;
    }

    @Bean
    public UserPorterDaoImp userPorterDaoImp() {
        UserPorterDaoImp udi = new UserPorterDaoImp();
        return udi;
    }
}
