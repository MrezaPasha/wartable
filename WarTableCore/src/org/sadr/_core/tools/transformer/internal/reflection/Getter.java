package org.sadr._core.tools.transformer.internal.reflection;

import org.sadr._core.meta.annotation.PersianName;

import java.lang.reflect.Method;

/**
 * @author DoubleF1re
 */
@PersianName("گیرنده")
public final class Getter {

    @PersianName("متد")
    private final Method method;

    public Getter(Method method) {
        this.method = method;
    }

    public Class<?> getReturnType() {
        return method.getReturnType();
    }

}
