package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.UpdateEvent;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
public interface UpdateListener {

    void onUpdate(final UpdateEvent event);
}
