package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.KeyInputListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@Getter
@AllArgsConstructor
public class KeyInputEvent extends Event<KeyInputListener> {

    private final int keyCode;

    private final boolean state;

    @Override
    public void consume(final KeyInputListener listener) {
        listener.onKeyInput(this);
    }
}
