package org.sadr.share.main.orgPosition;

import org.sadr.web.config.WebConfig;
import org.sadr.web.main.system.irror.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

/**
 * @author masoud
 */
public class OrgPositionShareConfig extends WebConfig {

    @Bean
    public OrgPositionDaoImp orgPositionDaoImp() {
        OrgPositionDaoImp udi = new OrgPositionDaoImp();
        return udi;
    }

    @Bean
    public OrgPositionShareServiceImp orgPositionShareServiceImp() {
        OrgPositionShareServiceImp usi = new OrgPositionShareServiceImp();
        usi.setDao(orgPositionDaoImp());
        return usi;
    }

    @Bean
    public OrgPositionShareController orgPositionShareController() {
        OrgPositionShareController uc = new OrgPositionShareController();
        uc.setService(orgPositionShareServiceImp());
        return uc;
    }
}
