package io.github.strikeless.neptunium.event.api;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@Getter
@Setter
public abstract class CancellableEvent<T> extends Event<T> {

    private boolean cancelled;
}
