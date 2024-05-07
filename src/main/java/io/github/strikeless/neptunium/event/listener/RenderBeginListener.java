package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.RenderBeginEvent;

public interface RenderBeginListener {

    void onRenderBegin(final RenderBeginEvent event);
}
