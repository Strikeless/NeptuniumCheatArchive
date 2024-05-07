package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.SendChatListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class SendChatEvent extends CancellableEvent<SendChatListener> {

    private String message;

    @Override
    public void consume(final SendChatListener listener) {
        listener.onSendChat(this);
    }
}
