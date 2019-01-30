package org.sadr.share.main.services;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ServicesDaoImp extends GenericDaoImpl<Services> implements ServicesDao {
}
