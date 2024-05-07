package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.api.Event;
import io.github.strikeless.neptunium.event.listener.RenderTextListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 17.07.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class RenderTextEvent extends CancellableEvent<RenderTextListener> {

    private String text;

    private float x, y;

    @Override
    public void consume(final RenderTextListener listener) {
        listener.onRenderText(this);
    }
}
