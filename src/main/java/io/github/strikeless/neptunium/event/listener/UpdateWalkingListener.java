package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.UpdateWalkingEvent;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
public interface UpdateWalkingListener {

    void onUpdateWalking(final UpdateWalkingEvent event);
}
