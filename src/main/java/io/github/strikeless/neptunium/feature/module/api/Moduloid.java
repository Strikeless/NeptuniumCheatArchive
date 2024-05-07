package io.github.strikeless.neptunium.feature.module.api;

import io.github.strikeless.neptunium.setting.holder.SettingHolder;
import io.github.strikeless.neptunium.util.interfaces.Named;

/**
 * An interface providing module-like functionality.
 * Used for dealing with modes and modules.
 *
 * @author Strikeless
 * @since 13.07.2022
 */
// Yes I added this thing again, life sucks sometimes.
public interface Moduloid extends Named, SettingHolder {

    boolean isEnabled();

    void setEnabled(final boolean enabled);

    default void toggle() {
        this.setEnabled(!this.isEnabled());
    }

    default void onEnabled() {
    }

    default void onDisabled() {
    }
}
