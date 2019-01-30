package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class ServiceLogShareServiceImp extends GenericServiceImpl<ServiceLog, ServiceLogDao> implements ServiceLogShareService {
}
