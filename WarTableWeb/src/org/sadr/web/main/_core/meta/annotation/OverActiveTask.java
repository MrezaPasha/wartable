package org.sadr.web.main._core.meta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author masoud متدهایی که هنگام غیرفعال بودن کاربر هم قابل دسترسی هستند.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OverActiveTask {
}
