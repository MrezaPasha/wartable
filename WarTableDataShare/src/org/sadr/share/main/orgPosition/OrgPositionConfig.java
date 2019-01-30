package org.sadr.share.main.orgPosition;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;



public class OrgPositionConfig extends ShareConfig {

    @Bean
    public OrgPositionDaoImp orgPositionDaoImp()
    {
        OrgPositionDaoImp udi = new OrgPositionDaoImp();
        return udi;
    }
    @Bean
    public OrgPositionServiceImp orgPositionServiceImp()
    {
        OrgPositionServiceImp usi = new OrgPositionServiceImp();
        usi.setDao(orgPositionDaoImp());
        return usi;
    }

}
