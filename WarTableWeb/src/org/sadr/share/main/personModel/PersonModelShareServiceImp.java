package org.sadr.share.main.personModel;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class PersonModelShareServiceImp extends GenericServiceImpl<PersonModel, PersonModelDao> implements PersonModelShareService {
}
