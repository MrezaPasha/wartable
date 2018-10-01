package org.sadr.web.main.system.log.signin;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author masoud
 */
@Repository
@Component
public class SigninLogDaoImp extends GenericDaoImpl<SigninLog> implements SigninLogDao {
}
