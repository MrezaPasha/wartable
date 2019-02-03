package org.sadr.web.main.system.irror.notify;

import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.system.irror.irror.Irror;

/**
 *
 * @author masoud
 */
public interface IrrorNotifyService extends GenericService<IrrorNotify> {

    void createNotify(Irror irror);
}
