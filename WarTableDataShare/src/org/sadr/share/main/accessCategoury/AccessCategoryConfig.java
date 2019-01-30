package org.sadr.share.main.accessCategoury;

import org.sadr.share.config.ShareConfig;
import org.springframework.context.annotation.Bean;

public class AccessCategoryConfig extends ShareConfig {

    @Bean
    public AccessCategoryDaoImp accessCategoryDaoImp()
    {
        AccessCategoryDaoImp udi = new AccessCategoryDaoImp();
        return udi;
    }

    @Bean
    public AccessCategoryServiceImp accessCategoryServiceImp()
    {
        AccessCategoryServiceImp usi = new AccessCategoryServiceImp();
        usi.setDao(accessCategoryDaoImp());
        return usi;
    }

}
