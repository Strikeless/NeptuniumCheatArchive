package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.UpdateListener;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
public class UpdateEvent extends CancellableEvent<UpdateListener> {

    @Override
    public void consume(final UpdateListener listener) {
        listener.onUpdate(this);
    }
}
