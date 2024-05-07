package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.KeyInputEvent;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
public interface KeyInputListener {

    void onKeyInput(final KeyInputEvent event);
}
