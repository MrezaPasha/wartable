package org.sadr._core.meta.generic;

import org.sadr._core.utils.OutLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * @param <T>
 * @param <D>
 * @author masoud
 */
public abstract class GenericControllerImpl<T extends Serializable, D extends GenericService<T>> implements GenericController {

    public GenericControllerImpl() {
        OutLog.p("GenericControllerImpl: " + this.getClass().getName());
        try {
            redirectPath = this.getClass().getAnnotation(RequestMapping.class).value()[0];
        } catch (Exception e) {
        }
    }

    @Autowired
    protected D service;

    protected String redirectPath;

    public void setService(D service) {
        this.service = service;
    }

}
