package io.github.strikeless.neptunium.event.impl;


import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.RenderBeginListener;

public class RenderBeginEvent extends Event<RenderBeginListener> {



    @Override
    public void consume(final RenderBeginListener listener) {
        listener.onRenderBegin(this);
    }
}
