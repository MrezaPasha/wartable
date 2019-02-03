package org.sadr.web.main.system.irror.notify;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr.web.config.WebConfigHandler;
import org.sadr.web.main._core.propertor.PropertorInWeb;
import org.sadr.web.main._core.propertor._type.TtPropertorInWebList;
import org.sadr.web.main.admin._type.TtUserLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserService;
import org.sadr.web.main.system._type.TtIrrorNotifyStatus;
import org.sadr.web.main.system.irror.irror.Irror;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author masoud
 */
@Service
@Component
public class IrrorNotifyServiceImp extends GenericServiceImpl<IrrorNotify, IrrorNotifyDao> implements IrrorNotifyService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createNotify(Irror irror) {

        switch (irror.getLevel()) {
            case Warn:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyWarn))
                    return;
                break;
            case Fatal:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyFatal))
                    return;
                break;
            case Debug:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyDebug))
                    return;
                break;
            case Error:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyError))
                    return;
                break;
            case Info:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyInfo))
                    return;
                break;
            case Trace:
                if (!PropertorInWeb.getInstance().isOnProperty(TtPropertorInWebList.IrrorAlertNotifyTrace))
                    return;
                break;
            default:
                return;

        }

        List<User> users = userService.findAllBy(
                Restrictions.eq(User.LEVEL, TtUserLevel.Administrator)
        );

        if (users != null) {
            IrrorNotify notify;
            for (User u : users) {
                notify = new IrrorNotify();
                notify.setUser(u);
                notify.setIrror(irror);
                notify.setStatus(TtIrrorNotifyStatus.New);
                this.dao.save(notify);
            }
        }


    }

}
