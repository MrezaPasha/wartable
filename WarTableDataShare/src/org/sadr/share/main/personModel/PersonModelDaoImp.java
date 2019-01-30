package org.sadr.share.main.personModel;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class PersonModelDaoImp extends GenericDaoImpl<PersonModel> implements PersonModelDao {
}
