package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.RenderGuiListener;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
public class RenderGuiEvent extends Event<RenderGuiListener> {

    @Override
    public void consume(final RenderGuiListener listener) {
        listener.onRenderGUI(this);
    }
}
