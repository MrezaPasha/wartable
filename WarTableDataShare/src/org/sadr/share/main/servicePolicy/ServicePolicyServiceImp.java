package org.sadr.share.main.servicePolicy;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ServicePolicyServiceImp extends GenericServiceImpl<ServicePolicy,ServicePolicyDao> implements ServicePolicyService {
}
