package org.sadr.web.main.system.log.signin;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtSigninLogStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * @author masoud
 */
public interface SigninLogService extends GenericService<SigninLog> {

    public void persistSigninLog(HttpServletRequest request, String uuid, User user, TtSigninLogStatus status) throws UnknownHostException;
}
