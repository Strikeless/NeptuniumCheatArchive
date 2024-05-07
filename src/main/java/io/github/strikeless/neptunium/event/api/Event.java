package io.github.strikeless.neptunium.event.api;

import com.google.common.reflect.TypeToken;
import io.github.strikeless.neptunium.Neptunium;
import lombok.Getter;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@Getter
@SuppressWarnings({"unchecked", "UnstableApiUsage"}) // just let me make my mistakes
public abstract class Event<T> {

    private final TypeToken<T> listenerTypeToken = new TypeToken<T>(this.getClass()) {
    };

    public abstract void consume(final T listener);

    public void consumeObject(final Object object) {
        this.consume((T) object);
    }

    public void dispatch() {
        Neptunium.INSTANCE.getEventBus().dispatch(this);
    }
}
