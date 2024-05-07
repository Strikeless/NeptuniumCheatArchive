package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.ResizeListener;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
public class ResizeEvent extends Event<ResizeListener> {

    @Override
    public void consume(final ResizeListener listener) {
        listener.onResize(this);
    }
}
