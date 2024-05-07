package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.RenderGuiEvent;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
public interface RenderGuiListener {

    void onRenderGUI(final RenderGuiEvent event);
}
