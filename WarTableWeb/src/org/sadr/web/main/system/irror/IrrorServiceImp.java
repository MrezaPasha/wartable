package org.sadr.web.main.system.irror;

import org.aspectj.lang.ProceedingJoinPoint;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.sadr.web.main.system._type.TtIrrorLevel;
import org.sadr.web.main.system._type.TtIrrorPlace;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author masoud
 */
@Service
//@Component
public class IrrorServiceImp extends GenericServiceImpl<Irror, IrrorDao> implements IrrorService {

    @Override
    public Irror submit(Exception e, ProceedingJoinPoint joinPoint, HttpServletRequest request, TtIrrorPlace place) {
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            String[] split = errors.toString().split("\n");
            String err = "<span class='first-line'>" + split[0] + "</span><br/>";
            for (int i = 1; i < split.length; i++) {
                if (split[i].contains("at org.sadr.")) {
                    err += "<span class='at-org-sadr'>" + split[i] + "</span><br/>";
                } else if (split[i].trim().startsWith("at ")) {
                    err += "<span class='at-others'>" + split[i] + "</span><br/>";
                } else if (split[i].contains("Caused by:")) {
                    err += "<span class='caused-by'>" + split[i] + "</span><br/>";
                } else {
                    err += split[i] + "<br/>";
                }
            }
            Irror irror = new Irror(err,
                    request.getRequestURI(),
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                    TtHttpErrorCode___.InternalServerError_500, TtIrrorLevel.Fatal, place, request
            );
            save(irror);
            return irror;

        } catch (Exception ee) {
            ee.printStackTrace();
            return null;
        }

    }

    @Override
    public Irror submit(Exception e, HttpServletRequest request, TtIrrorPlace place, TtIrrorLevel level) {
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            String[] split = errors.toString().split("\n");
            String err = "<span class='first-line'>" + split[0] + "</span><br/>";
            for (int i = 1; i < split.length; i++) {
                if (split[i].contains("at org.sadr.")) {
                    err += "<span class='at-org-sadr'>" + split[i] + "</span><br/>";
                } else if (split[i].trim().startsWith("at ")) {
                    err += "<span class='at-others'>" + split[i] + "</span><br/>";
                } else if (split[i].contains("Caused by:")) {
                    err += "<span class='caused-by'>" + split[i] + "</span><br/>";
                } else {
                    err += split[i] + "<br/>";
                }
            }

            Irror irror = new Irror(err,
                    request.getRequestURI(),
                    e.getStackTrace()[0].getClassName() + "." + e.getStackTrace()[0].getMethodName(),
                    TtHttpErrorCode___.InternalServerError_500, level, place, request
            );
            save(irror);
            return irror;

        } catch (Exception ee) {
            ee.printStackTrace();
            return null;
        }

    }
}
