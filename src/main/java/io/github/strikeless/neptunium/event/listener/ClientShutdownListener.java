package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.ClientShutdownEvent;

public interface ClientShutdownListener {

    void onClientShutdown(final ClientShutdownEvent event);
}
