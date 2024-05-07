package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.ClientShutdownListener;

public class ClientShutdownEvent extends CancellableEvent<ClientShutdownListener> {

    @Override
    public void consume(final ClientShutdownListener listener) {
        listener.onClientShutdown(this);
    }
}
