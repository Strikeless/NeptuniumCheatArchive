package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.ModuleInvalidateListener;
import io.github.strikeless.neptunium.feature.module.api.Module;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@Getter
@AllArgsConstructor
public class ModuleInvalidateEvent extends Event<ModuleInvalidateListener> {

    private final Module module;

    private final InvalidationType type;

    @Override
    public void consume(final ModuleInvalidateListener listener) {
        listener.onModuleInvalidate(this);
    }

    public enum InvalidationType {
        /**
         * A module was either added or removed
         */
        MODULES_CHANGED,

        /**
         * A module was toggled
         */
        MODULE_TOGGLED,

        /**
         * A setting inside a module was changed (this does not include sub-settings of modes in modules)
         */
        SETTING_CHANGED
    }
}
