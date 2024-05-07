package io.github.strikeless.neptunium.setting.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.strikeless.neptunium.feature.module.api.Mode;
import io.github.strikeless.neptunium.setting.holder.ModeHolder;
import io.github.strikeless.neptunium.setting.holder.SettingHolder;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public class ModeSetting extends ArraySetting<Mode> implements ModeHolder {

    public ModeSetting(final SettingHolder parent, final String name, final Mode... values) {
        super(parent, name, values);

        for (final Mode value : values) {
            value.setParentSetting(this);
        }
    }

    public Mode getValueByName(final String name) {
        // Loop through every value in the setting...
        for (final Mode value : this.getValues()) {
            // ... and then return the matching value...
            if (value.getName().equalsIgnoreCase(name)) return value;
        }

        // ... or default to null if no matching value is found
        return null;
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(this.getValue().getName());
    }

    @Override
    public void deserialize(final JsonElement serialized) {
        this.setValue(this.getValueByName(serialized.getAsString()));
    }

    @Override
    public void setValue(final Mode value) {
        // Don't call the onDisable or onEnable methods if the parent declares settings shouldn't be active,
        // otherwise we would get duplicate onDisabled invocations if a parent module was disabled and the mode
        // changed after that.
        if (!this.getParent().areSettingsActive()) {
            super.setValue(value);
            return;
        }

        // Call onDisabled for the mode we're about to switch out of
        Mode mode = this.getMode();
        if (mode != null) mode.onDisabled();

        // Actually switch the mode/value
        super.setValue(value);

        // Call onEnabled for the mode we just switched to
        mode = this.getMode();
        if (mode != null) mode.onEnabled();
    }

    @Override
    public Mode getMode() {
        return this.getValue();
    }
}
