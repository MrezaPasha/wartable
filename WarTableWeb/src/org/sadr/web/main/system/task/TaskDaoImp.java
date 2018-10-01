package org.sadr.web.main.system.task;

import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.generic.GenericDaoImpl;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author masoud
 */
@Repository
@Component
public class TaskDaoImp extends GenericDaoImpl<Task> implements TaskDao {

    @Override
    public boolean authorizeGuest(String taskSignature) {
        return (long) getCurrentSession().createQuery("SELECT COUNT(id) FROM " + Task.class.getName()
                + " WHERE entityState = " + TtEntityState.Exist.ordinal()
                + " AND signature = '" + taskSignature
                + "' AND accessLevel = " + TtTaskAccessLevel.Free4Gusts.ordinal()).uniqueResult() > 0;
    }

    @Override
    public Task fetchGuestTask(String taskSignature) {
        List list = getCurrentSession().createQuery("FROM " + Task.class.getName()
                + " AS x WHERE x.entityState = " + TtEntityState.Exist.ordinal()
                + " AND x.signature = '" + taskSignature
                + "' AND x.accessLevel = " + TtTaskAccessLevel.Free4Gusts.ordinal()).list();
        return list.isEmpty() ? null : (Task) list.get(0);
    }

    @Override
    public boolean isNeedToConfirm(String taskSignature) {
        return (long) getCurrentSession().createQuery("SELECT COUNT(id) FROM " + Task.class.getName()
                + " WHERE entityState = " + TtEntityState.Exist.ordinal()
                + " AND signature = '" + taskSignature
                + "' AND isTwoLevelConfirm = " + true).uniqueResult() > 0;
    }

    @Override
    public boolean authorizeUser(String taskSignature) {
        return (long) getCurrentSession().createQuery("SELECT COUNT(id) FROM " + Task.class.getName()
                + " WHERE entityState = " + TtEntityState.Exist.ordinal()
                + " AND signature = '" + taskSignature
                + "' AND accessLevel = " + TtTaskAccessLevel.Free4Users.ordinal()).uniqueResult() > 0;
    }

}
