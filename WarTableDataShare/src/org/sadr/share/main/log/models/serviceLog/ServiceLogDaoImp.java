package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ServiceLogDaoImp extends GenericDaoImpl<ServiceLog> implements ServiceLogDao {
}
