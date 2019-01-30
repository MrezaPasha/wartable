package org.sadr.share.main.personModel;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PersonModelServiceImp extends GenericServiceImpl<PersonModel,PersonModelDao> implements PersonModelService {
}
