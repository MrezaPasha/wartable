package org.sadr.share.main.baseConfig;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BaseConfigServiceImp extends GenericServiceImpl<BaseConfig,BaseConfigDao> implements BaseConfigService {
}
