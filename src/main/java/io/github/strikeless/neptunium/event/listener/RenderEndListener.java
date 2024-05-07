package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.RenderEndEvent;

public interface RenderEndListener {

    void onRenderEnd(final RenderEndEvent event);
}
