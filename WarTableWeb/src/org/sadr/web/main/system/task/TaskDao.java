package org.sadr.web.main.system.task;

import org.sadr._core.meta.generic.GenericDao;

/**
 * @author masoud
 */
public interface TaskDao extends GenericDao<Task> {

    boolean authorizeClient(String taskSignature);

    Task fetchClientTask(String taskSignature);

    Task fetchMasterTask(String taskSignature);

    Task fetchTask(String taskSignature);

    boolean authorizeGuest(String taskSignature);

    Task fetchGuestTask(String taskSignature);

    public boolean isNeedToConfirm(String taskSignature);

}
