package org.sadr.web.main.system.irror;

import org.aspectj.lang.ProceedingJoinPoint;
import org.sadr._core.meta.generic.GenericService;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;

import javax.servlet.http.HttpServletRequest;

/**
 * @author masoud
 */
public interface IrrorService extends GenericService<Irror> {

    public Irror submit(Exception e, ProceedingJoinPoint joinPoint, HttpServletRequest request, TtIrrorPlace place);

    public Irror submit(Exception e, HttpServletRequest request, TtIrrorPlace place, TtIrrorLevel level);

}
