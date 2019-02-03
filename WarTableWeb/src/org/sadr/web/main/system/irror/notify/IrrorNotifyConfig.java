package org.sadr.web.main.system.irror.notify;

import org.sadr.share.config.ShareConfig;
import org.sadr.web.main.admin.user.user.UserConfig;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author masoud
 */
public class IrrorNotifyConfig extends ShareConfig {

    @Bean
    public IrrorNotifyDaoImp irrorNotifyDaoImp() {
        IrrorNotifyDaoImp udi = new IrrorNotifyDaoImp();
        return udi;
    }

    @Bean
    public IrrorNotifyServiceImp irrorNotifyServiceImp() {
        IrrorNotifyServiceImp usi = new IrrorNotifyServiceImp();
        usi.setDao(irrorNotifyDaoImp());
        usi.setUserService(new UserConfig().userServiceImp());
        return usi;
    }
}
