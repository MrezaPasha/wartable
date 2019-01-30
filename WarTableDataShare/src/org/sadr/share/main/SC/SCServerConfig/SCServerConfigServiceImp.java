package org.sadr.share.main.SC.SCServerConfig;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SCServerConfigServiceImp extends GenericServiceImpl<SCServerConfig,SCServerConfigDao> implements SCServerConfigService {
}
