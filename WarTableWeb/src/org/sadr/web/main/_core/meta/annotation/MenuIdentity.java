package org.sadr.web.main._core.meta.annotation;

import org.sadr.web.main._core._type.TtTile___;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MenuIdentity {
    TtTile___ value();
}
