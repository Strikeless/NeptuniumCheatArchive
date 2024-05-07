package io.github.strikeless.neptunium.feature.module.api;

import io.github.strikeless.neptunium.setting.Setting;
import io.github.strikeless.neptunium.util.interfaces.InstanceAccessor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
// The whole mode thing is kind of duct taped together, but it works
@Data
public abstract class Mode implements InstanceAccessor, Moduloid {

    // getSettings for Moduloid/SettingHolder generated automatically by lombok
    private final List<Setting<?>> settings = new ArrayList<>();

    private final String name;

    private Setting<?> parentSetting;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        throw new UnsupportedOperationException("Cannot enable/disable a mode directly, use a mode setting");
    }

    @Override
    public void onEnabled() {
        Moduloid.super.onEnabled();
        neptunium.getEventBus().register(this);
    }

    @Override
    public void onDisabled() {
        Moduloid.super.onDisabled();
        neptunium.getEventBus().unregister(this);
    }

    @Override
    public boolean areSettingsActive() {
        return (this.parentSetting == null || this.parentSetting.getParent().areSettingsActive())
                && this.isEnabled();
    }
}
