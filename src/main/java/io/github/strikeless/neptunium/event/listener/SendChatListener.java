package io.github.strikeless.neptunium.event.listener;

import io.github.strikeless.neptunium.event.impl.SendChatEvent;

/**
 * @author Strikeless
 * @since 06.07.2022
 */
public interface SendChatListener {

    void onSendChat(final SendChatEvent event);
}
