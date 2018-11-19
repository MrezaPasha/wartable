package org.sadr.web.main.system.log.attempt;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.admin._type.TtUserAttemptResult;
import org.sadr.web.main.admin._type.TtUserAttemptType;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;

/**
 * @author masoud
 */
public interface UserAttemptService extends GenericService<UserAttempt> {

    public void deleteUUID(User u);

    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType) throws UnknownHostException;

    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, boolean mergeNotice) throws UnknownHostException;

    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, UserAttempt uatt, String uuid) throws UnknownHostException;

    public TtUserAttemptResult attemptStatus(HttpServletRequest request, HttpServletResponse response, Model model, User user, TtUserAttemptType attemptType, UserAttempt uatt, String uuid, boolean mergeNotice) throws UnknownHostException;

    public void rebuildUerAttempt(HttpServletRequest request, HttpServletResponse response, TtUserAttemptType attemptType, String uuid, User user) throws UnknownHostException;

}
