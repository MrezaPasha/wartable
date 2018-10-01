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
    public boolean authorizeUser(String taskSignature) {
        return dao.authorizeUser(taskSignature);
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
