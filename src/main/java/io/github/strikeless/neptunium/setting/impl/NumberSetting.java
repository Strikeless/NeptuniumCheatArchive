package io.github.strikeless.neptunium.setting.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.strikeless.neptunium.setting.Setting;
import io.github.strikeless.neptunium.setting.holder.SettingHolder;
import io.github.strikeless.neptunium.util.MathUtil;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@Getter
public class NumberSetting extends Setting<Number> {

    private final Number min, max, step;

    public NumberSetting(final SettingHolder parent, final String name, final Number value, final Number min, final Number max) {
        this(
                parent, name, value, min, max,
                (value instanceof Float || value instanceof Double) ? 0.01D : 1.0
        );
    }

    public NumberSetting(final SettingHolder parent, final String name, final Number value, final Number min, final Number max, final Number step) {
        super(parent, name, value);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public void setValue(Number value) {
        // During construction the super constructor calls setValue, but we haven't assigned min and max yet!
        if (this.min != null && this.max != null) {
            value = MathUtil.clamp((double) value, (double) min, (double) max);
        }

        this.setValueUnchecked(value);
    }

    public void setValueUnchecked(final Number value) {
        super.setValue(value);
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(this.getValue());
    }

    @Override
    public void deserialize(final JsonElement serialized) {
        this.setValue(serialized.getAsNumber());
    }
}
