package io.github.strikeless.neptunium.feature.module.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {

    String name();

    String description();

    ModuleCategory category();
}
