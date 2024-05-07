package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.RenderEndListener;

public class RenderEndEvent extends Event<RenderEndListener> {

    @Override
    public void consume(final RenderEndListener listener) {
        listener.onRenderEnd(this);
    }
}
