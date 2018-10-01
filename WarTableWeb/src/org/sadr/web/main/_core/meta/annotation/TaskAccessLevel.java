package org.sadr.web.main._core.meta.annotation;


import org.sadr.web.main._core._type.TtTaskAccessLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author masoud مشخص کننده سطح دسترسی پیشفرض متدها
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TaskAccessLevel {

    TtTaskAccessLevel value() default TtTaskAccessLevel.Free4Gusts;
}
