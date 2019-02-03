package org.sadr.web.main.system.irror.irror;

import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main._core.tools.driver.email.EmailDriver;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.sadr.web.main.system.irror.notify.IrrorNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author masoud
 */
@Service
//@Component
public class IrrorServiceImp extends GenericServiceImpl<Irror, IrrorDao> implements IrrorService {

    private IrrorNotifyService irrorNotifyService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setIrrorNotifyService(IrrorNotifyService irrorNotifyService) {
        this.irrorNotifyService = irrorNotifyService;
    }

    @Override
    public Irror submit(Exception e, ProceedingJoinPoint joinPoint, HttpServletRequest request, TtIrrorPlace place) {
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            String[] split = errors.toString().split("\n");
            String err = "<span class='first-line'>" + split[0] + "</span><br/>";
            for (int i = 1; i < split.length; i++) {
                if (split[i].contains("at org.sadr.")) {
                    err += "<span class='at-org-sadr'>" + split[i] + "</span><br/>";
                } else if (split[i].trim().startsWith("at ")) {
                    err += "<span class='at-others'>" + split[i] + "</span><br/>";
                } else if (split[i].contains("Caused by:")) {
                    err += "<span class='caused-by'>" + split[i] + "</span><br/>";
                } else {
                    err += split[i] + "<br/>";
                }
            }
            Irror irror = new Irror(err,
                    request.getRequestURI(),
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                    TtHttpErrorCode___.InternalServerError_500, TtIrrorLevel.Fatal, place, request
            );
            save(irror);

            sendIrrorAlert(irror);

            return irror;

        } catch (Exception ee) {
            ee.printStackTrace();
            return null;
        }

    }

    @Override
    public Irror submit(Exception e, HttpServletRequest request, TtIrrorPlace place, TtIrrorLevel level) {
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            String[] split = errors.toString().split("\n");
            String err = "<span class='first-line'>" + split[0] + "</span><br/>";
            for (int i = 1; i < split.length; i++) {
                if (split[i].contains("at org.sadr.")) {
                    err += "<span class='at-org-sadr'>" + split[i] + "</span><br/>";
                } else if (split[i].trim().startsWith("at ")) {
                    err += "<span class='at-others'>" + split[i] + "</span><br/>";
                } else if (split[i].contains("Caused by:")) {
                    err += "<span class='caused-by'>" + split[i] + "</span><br/>";
                } else {
                    err += split[i] + "<br/>";
                }
            }

            Irror irror = new Irror(err,
                    request.getRequestURI(),
                    e.getStackTrace()[0].getClassName() + "." + e.getStackTrace()[0].getMethodName(),
                    TtHttpErrorCode___.InternalServerError_500, level, place, request
            );
            save(irror);

            sendIrrorAlert(irror);

            return irror;

        } catch (Exception ee) {
            ee.printStackTrace();
            return null;
        }

    }


    private void sendIrrorAlert(Irror irror) {
        if (PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertPublicOn)) {
            if (PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertEmailOn)) {
                List<User> users = userService.findAllBy(
                        Restrictions.and(
                                Restrictions.neOrIsNotNull(User.EMAIL, ""),
                                Restrictions.eq(User.LEVEL, TtUserLevel.Administrator)
                        )
                );
                if (!users.isEmpty()) {
                    String emails = "";
                    for (User u : users) {
                        emails += u.getEmail() != null && !u.getEmail().isEmpty() ? ("," + u.getEmail()) : "";
                    }
                    if (!emails.isEmpty()) {
                        EmailDriver.getInstance().send(irror, emails.substring(1));
                    }
                }
            }
            if (PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyOn)) {
                irrorNotifyService.createNotify(irror);
            }
        }
    }
}
