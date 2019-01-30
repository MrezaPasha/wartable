package org.sadr.share.main.servicePolicy;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ServicePolicyDaoImp extends GenericDaoImpl<ServicePolicy> implements ServicePolicyDao {
}
