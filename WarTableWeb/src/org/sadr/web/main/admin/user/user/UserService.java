package org.sadr.web.main.admin.user.user;

import org.sadr._core.meta.generic.GenericService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author masoud
 */
public interface UserService extends GenericService<User> {

    public User updateUserSession(HttpSession session, User user);

    public boolean authenticateE(String username);

    public User authenticateE(String username, String password);

    public User authenticateEAndLogin(String username, String password, HttpSession session);

    public User findByUsername(String username, String... relatedClass);

    public User autoLogin(HttpServletRequest request);

    public User autoLogin(HttpServletRequest request, User user);

    public User autoLogin(HttpServletRequest request, int userId);

    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    public void logoutAll(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    public void logoutAll(User user);

    public ModelAndView goToSignin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes);

    public boolean isAccessLimitPassed(User user);
}
