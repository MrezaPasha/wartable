package org.sadr.web.main.system.log.validation;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class ValidationLogDaoImp extends GenericDaoImpl<ValidationLog> implements ValidationLogDao {
}
