package io.github.strikeless.neptunium.setting.holder;

import io.github.strikeless.neptunium.feature.module.api.Mode;

/**
 * An interface for settings that may contain a mode.
 * This can technically be used in other objects than just settings, but it'll be ineffective as
 * onParentEnabled and onParentDisabled won't be called, which are responsible for calling the mode's
 * onEnabled and onDisabled methods respectively. (which in turn hook the mode to the event bus).
 *
 * @author Strikeless
 * @since 15.07.2022
 */
public interface ModeHolder {

    Mode getMode();

    default void onParentEnabled() {
        final Mode mode = this.getMode();
        if (mode == null) return;

        mode.onEnabled();
    }

    default void onParentDisabled() {
        final Mode mode = this.getMode();
        if (mode == null) return;

        mode.onDisabled();
    }
}
