package io.github.strikeless.neptunium.setting.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.strikeless.neptunium.setting.Setting;
import io.github.strikeless.neptunium.setting.holder.SettingHolder;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
@Getter
public class ArraySetting<T> extends Setting<T> {

    private final T[] values;

    private int index;

    @SafeVarargs
    public ArraySetting(final SettingHolder parent, final String name, final T... values) {
        super(parent, name, values[0]);
        this.values = values;
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(this.getIndex());
    }

    @Override
    public void deserialize(final JsonElement serialized) {
        this.setValueByIndex(serialized.getAsInt());
    }

    public T getValueByIndex(final int index) {
        // Validate the index and then return the value in that index
        this.validateIndex(index);
        return this.values[index];
    }

    public int getIndexByValue(final T value) {
        // Loop through every value in the setting...
        for (int i = 0; i < this.values.length; ++i) {
            // ... and then return the index of the matching value...
            if (this.values[i] == value) return i;
        }

        // ... or default to 0 if no matching value is found
        return 0;
    }

    public void setValueByIndex(final int index) {
        this.validateIndex(index);

        super.setValue(this.getValueByIndex(index)); // intentional super instead of this
        this.index = index;
    }

    public void setValue(final T value) {
        super.setValue(value);

        // When calling the super constructor, values hasn't been assigned yet.
        // This however, is not a problem, as we don't allow specified defaults (due to java limitations with varargs),
        // thus we know for a fact that the index is 0.
        if (this.values == null) return;

        this.index = this.getIndexByValue(value);
    }

    public void next() {
        // Set the value by the next index and roll the next index to 0 if it's out of bounds
        this.setValueByIndex((this.index + 1) % this.values.length);
    }

    public void previous() {
        int previousIndex = this.index - 1;

        // Roll the previous index to the index of the last value if it's out of bounds
        if (previousIndex < 0) previousIndex = this.values.length - 1;

        this.setValueByIndex(previousIndex);
    }

    private void validateIndex(final int index) {
        if (index < 0 || index >= this.values.length) throw new IndexOutOfBoundsException(
                "Index \"" + index + "\" is out of bounds for array setting \"" + this.getName() + "\""
        );
    }
}
