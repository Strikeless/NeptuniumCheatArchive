package io.github.strikeless.neptunium.event.impl;

import io.github.strikeless.neptunium.event.api.CancellableEvent;
import io.github.strikeless.neptunium.event.listener.DisplayGuiScreenListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.GuiScreen;

@Getter
@AllArgsConstructor
public class DisplayGuiScreenEvent extends CancellableEvent<DisplayGuiScreenListener> {

    private final GuiScreen oldGuiScreen;

    private final GuiScreen newGuiScreen;

    @Override
    public void consume(final DisplayGuiScreenListener listener) {
        listener.onDisplayGuiScreen(this);
    }
}
