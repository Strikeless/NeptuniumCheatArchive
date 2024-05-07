package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.RenderTextEvent;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
public interface RenderTextListener {

    void onRenderText(final RenderTextEvent event);
}
