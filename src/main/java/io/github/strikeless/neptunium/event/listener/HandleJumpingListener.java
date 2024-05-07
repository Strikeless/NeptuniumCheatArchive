package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.HandleJumpingEvent;

/**
 * @author Strikeless
 * @since 15.07.2022
 */
public interface HandleJumpingListener {

    void onHandleJumping(final HandleJumpingEvent event);
}
