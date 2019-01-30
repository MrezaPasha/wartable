package org.sadr.share.main.baseErrors;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
@Component
public class BaseErrorsShareServiceImp extends GenericServiceImpl<BaseErrors, BaseErrorsDao> implements BaseErrorsShareService {
}
