package org.sadr.web.main.system.task;

import org.sadr._core.meta.generic.GenericService;

/**
 * @author masoud
 */
public interface TaskService extends GenericService<Task> {

    public boolean authorizeUser(String taskSignature);

    public boolean authorizeGuest(String taskSignature);

    Task fetchGuestTask(String taskSignature);

    public boolean isNeedToConfirm(String taskSignature);

}
