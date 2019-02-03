package org.sadr.web.main.system.irror.irror;

import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.system.irror.notify.IrrorNotifyConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class IrrorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public IrrorDaoImp irrorDaoImp() {
        IrrorDaoImp udi = new IrrorDaoImp();
        return udi;
    }

    @Bean
    public IrrorServiceImp irrorServiceImp() {
        IrrorServiceImp usi = new IrrorServiceImp();
        usi.setDao(irrorDaoImp());
        usi.setIrrorNotifyService(new IrrorNotifyConfig().irrorNotifyServiceImp());
        usi.setUserService(new UserConfig().userServiceImp());
        return usi;
    }

    @Bean
    public IrrorController irrorController() {
        IrrorController uc = new IrrorController();
        uc.setService(irrorServiceImp());
        return uc;
    }

}
