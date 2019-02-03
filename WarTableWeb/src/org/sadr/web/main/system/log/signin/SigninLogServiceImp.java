package org.sadr.web.main.system.log.signin;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.system._type.TtSigninLogStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author masoud
 */
@Service
@Component
public class SigninLogServiceImp extends GenericServiceImpl<SigninLog, SigninLogDao> implements SigninLogService {
    @Override
    public void persistSigninLog(HttpServletRequest request, String uuid, User user, TtSigninLogStatus status) throws UnknownHostException {

        SigninLog signinLog = new SigninLog();

        signinLog.setUser(user);
        signinLog.setAgentSignature(request.getHeader("User-Agent"));
        signinLog.setComputerSignature(request.getRemoteAddr());
        signinLog.setIpAddress(request.getRemoteAddr());
        signinLog.setDomainName(request.getServerName());
        signinLog.setLastDateTime(ParsCalendar.getInstance().getShortDateTime());
        signinLog.setLastDateTimeG(new Date().getTime());
        signinLog.setStatus(status);
        signinLog.setUuid(uuid);
        this.save(signinLog);
    }
}
