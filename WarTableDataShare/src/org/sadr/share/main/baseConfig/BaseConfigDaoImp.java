package org.sadr.share.main.baseConfig;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class BaseConfigDaoImp extends GenericDaoImpl<BaseConfig> implements BaseConfigDao {
}
