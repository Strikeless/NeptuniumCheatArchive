package io.github.strikeless.neptunium.feature.module.api;

import io.github.strikeless.neptunium.event.impl.ModuleInvalidateEvent;
import io.github.strikeless.neptunium.feature.api.Feature;
import io.github.strikeless.neptunium.feature.module.api.bind.ModuleBind;
import io.github.strikeless.neptunium.setting.Setting;
import io.github.strikeless.neptunium.setting.holder.ModeHolder;
import io.github.strikeless.neptunium.util.interfaces.Described;
import lombok.Getter;
import lombok.Setter;
import org.atteo.classindex.IndexSubclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Strikeless
 * @since 05.07.2022
 */
@Getter
@Setter
@IndexSubclasses
public abstract class Module implements Feature, Moduloid, Described {

    // getSettings for Moduloid/SettingHolder generated automatically by lombok
    private final List<Setting<?>> settings = new ArrayList<>();

    private final String name;

    private final String description;

    private final ModuleCategory category;

    // Getter for the Toggleable interface is automatically created by lombok here, setter defined down below
    private boolean enabled;

    private ModuleBind bind;

    public Module() {
        final ModuleInfo moduleInfo = this.getClass().getDeclaredAnnotation(ModuleInfo.class);
        if (moduleInfo == null) {
            throw new IllegalStateException("Module " + this.getClass().getSimpleName() + " is not annotated!");
        }

        this.name = moduleInfo.name();
        this.description = moduleInfo.description();
        this.category = moduleInfo.category();
    }

    public Module(final String name, final String description, final ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public void onEnabled() {
        Moduloid.super.onEnabled();

        // Dispatch a new ModuleInvalidateEvent for other event consumers to handle
        final ModuleInvalidateEvent event = new ModuleInvalidateEvent(this, ModuleInvalidateEvent.InvalidationType.MODULE_TOGGLED);
        event.dispatch();

        // Register this module with the event bus in order to consume events
        neptunium.getEventBus().register(this);

        // Notify all the mode holders about this parent module enabling in order to get the modes enabled
        this.settings.forEach(setting -> {
            if (setting instanceof ModeHolder) {
                ((ModeHolder) setting).onParentEnabled();
            }
        });
    }

    @Override
    public void onDisabled() {
        Moduloid.super.onDisabled();

        // Notify all the mode holders about this parent module enabling in order to get the modes disabled
        this.settings.forEach(setting -> {
            if (setting instanceof ModeHolder) {
                ((ModeHolder) setting).onParentDisabled();
            }
        });

        // Dispatch a new ModuleInvalidateEvent for other event consumers to handle
        final ModuleInvalidateEvent event = new ModuleInvalidateEvent(this, ModuleInvalidateEvent.InvalidationType.MODULE_TOGGLED);
        event.dispatch();

        // Unregister this module from the event bus in order to stop consuming events
        neptunium.getEventBus().unregister(this);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;

        if (enabled) this.onEnabled();
        else this.onDisabled();
    }

    @Override
    public boolean areSettingsActive() {
        return this.isEnabled();
    }

    public boolean isBound() {
        return this.bind != null;
    }
}
