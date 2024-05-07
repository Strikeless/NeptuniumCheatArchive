package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.ResizeEvent;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
public interface ResizeListener {

    void onResize(final ResizeEvent event);
}
