package org.sadr.share.main.baseConfig;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class BaseConfigShareServiceImp extends GenericServiceImpl<BaseConfig, BaseConfigDao> implements BaseConfigShareService {
}
