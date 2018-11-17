package org.sadr.web.main.system.log.validation;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class ValidationLogServiceImp extends GenericServiceImpl<ValidationLog, ValidationLogDao> implements ValidationLogService {
}
