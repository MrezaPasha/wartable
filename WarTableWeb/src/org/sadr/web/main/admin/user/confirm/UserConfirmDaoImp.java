package org.sadr.web.main.admin.user.confirm;

import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.sadr.web.main.admin.user.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author masoud
 */
@Repository
@Component
public class UserConfirmDaoImp extends GenericDaoImpl<UserConfirm> implements UserConfirmDao {
    @Override
    public boolean isConfirmed(User u, String taskSignature) {
        UserConfirm uc = (UserConfirm) getCurrentSession().createQuery("FROM " + UserConfirm.class.getName()
                + " WHERE entityState = " + TtEntityState.Exist.ordinal()
                + " AND user_id = " + u.getIdi()
                + " AND taskSignature = '" + taskSignature
                + "' AND callCount < " + 6
                + " AND confirmDateTimeG > " + (new Date().getTime() - 120000)).uniqueResult();

        if (uc == null) {
            return false;
        }
        uc.addCallCount();
        update(uc);
        return true;
    }
}
