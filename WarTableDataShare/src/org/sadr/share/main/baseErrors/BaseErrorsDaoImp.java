package org.sadr.share.main.baseErrors;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class BaseErrorsDaoImp extends GenericDaoImpl<BaseErrors> implements BaseErrorsDao {
}
