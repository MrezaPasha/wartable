package org.sadr._core.meta.annotation;

import org.sadr._core._type.TtIxporterModeSection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author masoud
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExporterMode {

    TtIxporterModeSection value();
}
