package org.sadr.web.main._core.tools.listener;

import org.sadr._core.utils.OutLog;
import org.sadr.web.main.admin.user.user.User;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author masoud
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("sUser".equals(event.getName())) {
            SessionListener.setUser(event.getSession().getId(), ((User) event.getValue()).getId());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("sUser".equals(event.getName())) {
            SessionListener.removeUser(event.getSession().getId());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        OutLog.pl("attributeReplaced " + event.getName());
    }
}
