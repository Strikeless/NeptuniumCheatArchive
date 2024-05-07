package io.github.strikeless.neptunium.setting.holder;

import io.github.strikeless.neptunium.setting.Setting;

import java.util.List;

/**
 * An interface providing abstraction for objects that can contain settings.
 *
 * @author Strikeless
 * @since 15.07.2022
 */
public interface SettingHolder {

    List<Setting<?>> getSettings();

    /**
     * Whether the child settings should be considered active.
     * An active setting will enable and disable its mode(s) if such exists
     * based on the value upon change.
     *
     * Currently, this is only used in ModeHolders. This shall not affect visibility of settings.
     *
     * @return whether the child settings should be considered active
     */
    boolean areSettingsActive();
}
