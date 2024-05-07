package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.StrafeEvent;

/**
 * @author Strikeless
 * @since 07.07.2022
 */
public interface StrafeListener {

    void onStrafe(final StrafeEvent event);
}
