package org.sadr.web.main.system.task;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
//@Component
public class TaskServiceImp extends GenericServiceImpl<Task, TaskDao> implements TaskService {

    @Override
    public boolean authorizeClient(String taskSignature) {
        return dao.authorizeClient(taskSignature);
    }

    @Override
    public Task fetchClientTask(String taskSignature) {
        return dao.fetchClientTask(taskSignature);
    }

    @Override
    public Task fetchMasterTask(String taskSignature) {
        return dao.fetchMasterTask(taskSignature);
    }

    @Override
    public Task fetchTask(String taskSignature) {
        return dao.fetchTask(taskSignature);
    }

    @Override
    public boolean authorizeGuest(String taskSignature) {
        return dao.authorizeGuest(taskSignature);
    }

    @Override
    public Task fetchGuestTask(String taskSignature) {
        return dao.fetchGuestTask(taskSignature);
    }

    @Override
    public boolean isNeedToConfirm(String taskSignature) {
        return dao.isNeedToConfirm(taskSignature);
    }

}
