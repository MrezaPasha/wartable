package org.sadr.share.main.services;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ServicesServiceImp extends GenericServiceImpl<Services,ServicesDao> implements ServicesService {
}
