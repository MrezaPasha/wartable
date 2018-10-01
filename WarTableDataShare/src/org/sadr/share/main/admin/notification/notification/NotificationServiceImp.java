package org.sadr.share.main.admin.notification.notification;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author masoud
 */
@Service
@Component
public class NotificationServiceImp extends GenericServiceImpl<Notification, NotificationDao> implements NotificationService {
}
