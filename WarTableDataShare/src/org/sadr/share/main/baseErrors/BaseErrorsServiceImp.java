package org.sadr.share.main.baseErrors;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BaseErrorsServiceImp extends GenericServiceImpl<BaseErrors,BaseErrorsDao> implements BaseErrorsService {
}
