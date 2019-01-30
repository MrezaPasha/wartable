package org.sadr.share.main.serviceUser;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ServiceUserDaoImp extends GenericDaoImpl<ServiceUser> implements ServiceUserDao {
}
